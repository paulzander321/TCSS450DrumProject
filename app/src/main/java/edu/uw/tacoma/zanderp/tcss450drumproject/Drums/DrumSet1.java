package edu.uw.tacoma.zanderp.tcss450drumproject.drums;

import android.media.MediaPlayer;
import android.os.Environment;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import edu.uw.tacoma.zanderp.tcss450drumproject.R;

public class DrumSet1 extends AppCompatActivity {
    private Button btnRecord;
    private Button btnStopRecord;
    private Button btnPlay;
    private Button btnPause;
    private String FILE;
    private Boolean mRecord;
    private Recording mRecording;
    private Long mStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drum_set1);
        FILE = Environment.getExternalStorageState() + "tempRecording.3gp";
        mRecord = false;
        btnRecord = (Button)findViewById(R.id.record);
        btnStopRecord = (Button)findViewById(R.id.stopbutton);
        btnPlay = (Button)findViewById(R.id.play);
        btnPause = (Button)findViewById(R.id.pausebutton);
        mRecording = new Recording();
        mStart = SystemClock.elapsedRealtime();
    }

    public void startRecording(View view) throws IOException {
        mRecording = new Recording();
        mRecord = true;
        btnRecord.setVisibility(TextView.INVISIBLE);
        btnStopRecord.setVisibility(TextView.VISIBLE);
        mStart = SystemClock.elapsedRealtime();
    }

    public void stopRecording(View view){
        mRecord = false;
        btnRecord.setVisibility(TextView.VISIBLE);
        btnStopRecord.setVisibility(TextView.INVISIBLE);
        btnPlay.setVisibility(TextView.VISIBLE);
    }

    public void playRecording(View view) throws IOException, InterruptedException {
        btnPlay.setVisibility(TextView.INVISIBLE);
        btnPause.setVisibility(TextView.VISIBLE);
        mRecording.playRecording(this);
    }

    public void stopPlaying(View view){
        mRecording.stopRecording();
        btnPause.setVisibility(TextView.INVISIBLE);
        btnPlay.setVisibility(TextView.VISIBLE);
    }

    public void playCrash(View view){
        final MediaPlayer crash = MediaPlayer.create(this,R.raw.crash);
        crash.start();
        crash.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                crash.release();
            }
        });
        if (mRecord){
            mRecording.addNote(new Note(SystemClock.elapsedRealtime() - mStart, R.raw.crash));
        }
    }

    public void playTom1(View view){
        final MediaPlayer tom1 = MediaPlayer.create(this, R.raw.tom2);
        tom1.start();
        tom1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                tom1.release();
            }
        });
        if (mRecord){
            mRecording.addNote(new Note(SystemClock.elapsedRealtime() - mStart, R.raw.tom2));
        }
    }

    public void playSnare(View view){
        final MediaPlayer snare = MediaPlayer.create(this, R.raw.snare);
        snare.start();
        snare.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                snare.release();
            }
        });
        if (mRecord){
            Note snareNote = new Note(SystemClock.elapsedRealtime() - mStart, R.raw.snare);
            mRecording.addNote(snareNote);
        }
    }

    public void playTom2(View view){
        final MediaPlayer tom2 = MediaPlayer.create(this, R.raw.tomshort);
        tom2.start();
        tom2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                tom2.release();
            }
        });
        if (mRecord){
            mRecording.addNote(new Note(SystemClock.elapsedRealtime() - mStart, R.raw.tomshort));
        }
    }

    public void playFloorTom(View view){
        final MediaPlayer tomFloor = MediaPlayer.create(this, R.raw.tom1);
        tomFloor.start();
        tomFloor.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                tomFloor.release();
            }
        });
        if (mRecord){
            mRecording.addNote(new Note(SystemClock.elapsedRealtime() - mStart, R.raw.tom1));
        }
    }

    public void playRide(View view) {
        final MediaPlayer ride = MediaPlayer.create(this, R.raw.ride);
        ride.start();
        ride.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                ride.release();
            }
        });
        if (mRecord) {
            mRecording.addNote(new Note(SystemClock.elapsedRealtime() - mStart, R.raw.ride));
        }
    }

    public void playHiHat(View view){
        final MediaPlayer hihat = MediaPlayer.create(this, R.raw.hihat);
        hihat.start();
        hihat.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                hihat.release();
            }
        });
        if (mRecord){
            mRecording.addNote(new Note(SystemClock.elapsedRealtime() - mStart, R.raw.hihat));
        }
    }

    public void playKick(View view){
        final MediaPlayer kick = MediaPlayer.create(this, R.raw.kick1);
        kick.start();
        kick.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                kick.release();
            }
        });
        if (mRecord){
            mRecording.addNote(new Note(SystemClock.elapsedRealtime() - mStart, R.raw.kick1));
        }
    }
}