package edu.uw.tacoma.zanderp.tcss450drumproject.Drums;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import edu.uw.tacoma.zanderp.tcss450drumproject.R;

public class DrumSet1 extends AppCompatActivity implements View.OnClickListener {
    private MediaRecorder record;
    private MediaPlayer playRecording;
    private final MediaPlayer crash = MediaPlayer.create(this,R.raw.crash);
    private final MediaPlayer hihat = MediaPlayer.create(this, R.raw.hihat);
    private final MediaPlayer kick = MediaPlayer.create(this, R.raw.kick1);
    private final MediaPlayer ride = MediaPlayer.create(this, R.raw.ride);
    private final MediaPlayer snare = MediaPlayer.create(this, R.raw.snare);
    private final MediaPlayer tomFloor = MediaPlayer.create(this, R.raw.tom1);
    private final MediaPlayer tom1 = MediaPlayer.create(this, R.raw.tom2);
    private final MediaPlayer tom2 = MediaPlayer.create(this, R.raw.tomshort);
    private TextView tvCrash, tvHiHat, tvHiHat2, tvKick, tvRide, tvSnare, tvFloorTom, tvTom1, tvTom2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drum_set1);

        TextView tvCrash = (TextView) findViewById(R.id.crash);
        TextView tvHiHat = (TextView) findViewById(R.id.high_hat);
        TextView tvHiHat2 = (TextView) findViewById(R.id.high_hat2);
        TextView tvKick = (TextView) findViewById(R.id.kick);
        TextView tvRide = (TextView) findViewById(R.id.ride);
        TextView tvSnare = (TextView) findViewById(R.id.snare);
        TextView tvFloorTom = (TextView) findViewById(R.id.floor_tom);
        TextView tvTom1 = (TextView) findViewById(R.id.Tom1);
        TextView tvTom2 = (TextView) findViewById(R.id.Tom2);
        tvCrash.setOnClickListener(this);
        tvFloorTom.setOnClickListener(this);
        tvHiHat.setOnClickListener(this);
        tvHiHat2.setOnClickListener(this);
        tvKick.setOnClickListener(this);
        tvRide.setOnClickListener(this);
        tvSnare.setOnClickListener(this);
        tvTom1.setOnClickListener(this);
        tvTom2.setOnClickListener(this);

        Button record = (Button)findViewById(R.id.record);
        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });

        Button play = (Button)findViewById(R.id.play);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.crash:
                playSound(crash);
                break;

            case R.id.floor_tom:
                playSound(tomFloor);
                break;

            case R.id.high_hat:
                playSound(hihat);
                break;

            case R.id.high_hat2:
                playSound(hihat);
                break;

            case R.id.kick:
                playSound(kick);
                break;

            case R.id.ride:
                playSound(ride);
                break;

            case R.id.snare:
                playSound(snare);
                break;

            case R.id.Tom1:
                playSound(tom1);
                break;

            case R.id.Tom2:
                playSound(tom2);
                break;
        }
    }

    public void startRecording(){

    }

    public void stopRecording(){

    }

    public void playRecording(){

    }

    public void stopPlaying(){

    }

    public void playSound(MediaPlayer mp){
        mp.start();
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }
        });
    }
}
