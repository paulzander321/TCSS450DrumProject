package edu.uw.tacoma.zanderp.tcss450drumproject.Drums;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import edu.uw.tacoma.zanderp.tcss450drumproject.R;

public class DrumSet1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drum_set1);

    }

    public void playCrash(View view){
        final MediaPlayer crash = MediaPlayer.create(this,R.raw.crash);
        crash.start();
    }

    public void playHiHat(View view){
        final MediaPlayer hihat = MediaPlayer.create(this, R.raw.hihat);
        hihat.start();
    }

    public void playKick(View view){
        final MediaPlayer kick = MediaPlayer.create(this, R.raw.kick1);
        kick.start();
    }

    public void playRide(View view){
        final MediaPlayer ride = MediaPlayer.create(this, R.raw.ride);
        ride.start();
    }

    public void playSnare(View view){
        final MediaPlayer snare = MediaPlayer.create(this, R.raw.snare);
        snare.start();
    }

    public void playFloorTom(View view){
        final MediaPlayer tomFloor = MediaPlayer.create(this, R.raw.tom1);
        tomFloor.start();
    }

    public void playTom1(View view){
        final MediaPlayer tom1 = MediaPlayer.create(this, R.raw.tom2);
        tom1.start();
    }

    public void playTom2(View view){
        final MediaPlayer tom2 = MediaPlayer.create(this, R.raw.tomshort);
        tom2.start();
    }
}
