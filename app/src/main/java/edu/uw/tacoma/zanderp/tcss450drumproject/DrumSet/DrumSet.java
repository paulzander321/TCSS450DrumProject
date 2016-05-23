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
    private Button snare;
    private Button tom1;
    private Button tom2;
    private Button floortom;
    private Button bass;
    private Button crash;
    private Button ride;
    private Button hihat;
    private Button pedal;
    protected int SNARE_X;
    protected int SNARE_Y;
    protected int TOM1_X;
    protected int TOM1_Y;


    public DrumSet() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drum_set, container, false);
        snare = (Button)view.findViewById(R.id.snare);
        tom1 = (Button)view.findViewById(R.id.Tom1);
        tom2 = (Button)view.findViewById(R.id.Tom2);
        floortom = (Button)view.findViewById(R.id.floor_tom);
        bass = (Button)view.findViewById(R.id.bass);
        crash = (Button)view.findViewById(R.id.crash);
        ride = (Button)view.findViewById(R.id.ride);
        hihat = (Button)view.findViewById(R.id.high_hat);
        pedal = (Button)view.findViewById(R.id.pedal);
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
