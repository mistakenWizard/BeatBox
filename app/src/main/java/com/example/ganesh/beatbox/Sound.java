package com.example.ganesh.beatbox;

/**
 * Created by Ganesh on 29-01-2016.
 */
public class Sound {
    private String mAssetPath;
    private String mName;

    public Integer getSoundId() {
        return mSoundId;
    }

    public void setSoundId(Integer soundId) {
        mSoundId = soundId;
    }

    private Integer mSoundId;

    public String getName() {
        return mName;
    }

    public String getAssetPath() {
        return mAssetPath;
    }



    public Sound(String AssetPath) {
        mAssetPath= AssetPath;
        String[] components = AssetPath.split("/");
        String filename= components[components.length - 1];
        mName=  filename.replace(".wav", "");
    }
}
