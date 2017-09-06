package com.example.ganesh.beatbox;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

/** Created by Ganesh on 29-01-2016 at 12:17 AM for BeatBox  */
public class BeatBoxFragment extends Fragment {
    private BeatBox mBeatBox;
    private Sound mSound;
    public static BeatBoxFragment newInstance() {
        return new BeatBoxFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beat_box, viewGroup, false);

        RecyclerView recyclerView= (RecyclerView) view.findViewById(R.id.fragment_beat_box_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerView.setAdapter(new SoundAdapter(mBeatBox.getSounds()));

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mBeatBox = new BeatBox(getActivity());
    }


        private class SoundHolder extends RecyclerView.ViewHolder {
        private Button mButton;

        SoundHolder(LayoutInflater inflater, ViewGroup viewGroup) {
            super(inflater.inflate(R.layout.list_item_sound, viewGroup, false));

            mButton= (Button) itemView.findViewById(R.id.list_item_sound_button);
            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mBeatBox.play(mSound);
                }
            });
        }
            public void bindSound(Sound sound) {
                mSound = sound;
                mButton.setText(mSound.getName());
            }
    }

    private class SoundAdapter extends RecyclerView.Adapter<SoundHolder>{

        private List<Sound> mSounds;

        public SoundAdapter(List<Sound> sound){
            mSounds = sound;
        }
        @Override
        public SoundHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new SoundHolder(inflater,parent);
        }

        @Override
        public void onBindViewHolder(SoundHolder holder, int position) {
            Sound sound = mSounds.get(position);
            holder.bindSound(sound);
        }

        @Override
        public int getItemCount() {
            return mSounds.size();
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mBeatBox.release();
    }
}