package edu.uw.tacoma.zanderp.tcss450drumproject.DrumSet;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.TextView;

import edu.uw.tacoma.zanderp.tcss450drumproject.Drums.Note;
import edu.uw.tacoma.zanderp.tcss450drumproject.Drums.Recording;
import edu.uw.tacoma.zanderp.tcss450drumproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DrumSet extends Fragment {


    public DrumSet() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drum_set, container, false);
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


}
