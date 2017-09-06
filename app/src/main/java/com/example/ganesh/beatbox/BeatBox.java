package com.example.ganesh.beatbox;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ganesh on 29-01-2016.
 */
public class BeatBox {
    private static final String TAG = "BeatBox";
    private static final String SOUNDS_FOLDER = "sample_sound";
    private static final int MAX_SOUNDS = 5;

    private AssetManager mAsset;
    private SoundPool mSoundPool;
    private List<Sound> mSounds = new ArrayList<>();

    public BeatBox(Context context){
        mAsset = context.getAssets();
        // deprecated but need for compatibility
        mSoundPool = new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC, 0);

        loadSounds();
    }

    private void loadSounds() {
        String[] soundNames;

        try {
            soundNames = mAsset.list(SOUNDS_FOLDER);
            Log.i(TAG, "Found " + soundNames.length + " Sounds");
        } catch (IOException ioe) {
            Log.e(TAG,"COuld not list assets", ioe );
            return;
        }

        for (String filename : soundNames) {
            try {
                String assetPath = SOUNDS_FOLDER + "/" + filename;
                Sound sound = new Sound(assetPath);
                load(sound);
                mSounds.add(sound);
            } catch (IOException e) {
                Log.e(TAG, "Failed to load " + filename, e);
            }
        }

    }

    public List<Sound> getSounds() {
        return mSounds;
    }

    private void load(Sound sound) throws IOException {
        AssetFileDescriptor afd= mAsset.openFd(sound.getAssetPath());
        int soundId= mSoundPool.load(afd, 1);
        sound.setSoundId(soundId);
    }

    public void play(Sound sound){
        Integer soundId =  sound.getSoundId();
        if (soundId == null) return;

        mSoundPool.play(soundId,1.0f,1.0f,1,0,1.0f);
    }

    public void release(){
        mSoundPool.release();
    }
}
