package com.example.ganesh.beatbox;

import android.support.v4.app.Fragment;

/**
 * Created by Ganesh on 29-01-2016.
 */
public class BeatBoxActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return BeatBoxFragment.newInstance();
    }
}
