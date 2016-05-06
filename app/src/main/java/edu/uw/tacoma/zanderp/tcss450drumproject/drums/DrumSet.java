package edu.uw.tacoma.zanderp.tcss450drumproject.drums;

import android.media.MediaPlayer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import edu.uw.tacoma.zanderp.tcss450drumproject.R;

/**
 * The DrumSet displays the drum play screen to the user and allows them to play different
 * drums as well as record and listen to their recording.
 */
public class DrumSet extends AppCompatActivity {
    private Button btnRecord;
    private Button btnStopRecord;
    private Button btnPlay;
    private Button btnPause;
    private Boolean mRecord;
    private Recording mRecording;
    private Long mStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drum_set);
        mRecord = false;
        btnRecord = (Button)findViewById(R.id.record);
        btnStopRecord = (Button)findViewById(R.id.stopbutton);
        btnPlay = (Button)findViewById(R.id.play);
        btnPause = (Button)findViewById(R.id.pausebutton);
        mRecording = new Recording();
        mStart = SystemClock.elapsedRealtime();
    }

    /**
     * Starts the recording of notes being played. If there is currently
     * a recording, will destroy it and start with black recording.
     */
    public void startRecording(View view) {
        mRecording = new Recording();
        mRecord = true;
        btnPause.setVisibility(TextView.INVISIBLE);
        btnPlay.setVisibility(TextView.INVISIBLE);
        btnRecord.setVisibility(TextView.INVISIBLE);
        btnStopRecord.setVisibility(TextView.VISIBLE);
        mStart = SystemClock.elapsedRealtime();
    }

    /**
     * Stops recording of notes being played.
     */
    public void stopRecording(View view){
        mRecord = false;
        btnRecord.setVisibility(TextView.VISIBLE);
        btnStopRecord.setVisibility(TextView.INVISIBLE);
        btnPlay.setVisibility(TextView.VISIBLE);
    }

    /**
     * Plays the currently saved recording.
     */
    public void playRecording(View view) {
        btnPlay.setVisibility(TextView.INVISIBLE);
        btnPause.setVisibility(TextView.VISIBLE);
        mRecording.playRecording(this);
    }

    /**
     * Stops the currently playing recording.
     */
    public void stopPlaying(View view){
        mRecording.stopRecording();
        btnPause.setVisibility(TextView.INVISIBLE);
        btnPlay.setVisibility(TextView.VISIBLE);
    }

    /**
     * Plays the crash instrument and records if recording.
     */
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

    /**
     * Playst the tom1 and records if recording.
     */
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

    /**
     * Plays snare and records if recording.
     */
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

    /**
     * Plays the tom2 and records if currently recording.
     */
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

    /**
     * Plays the floor tom and records if currently recording.
     */
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

    /**
     * Plays the ride and records if recording.
     */
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

    /**
     * Plays hihat, will add note to recording if currently recording.
     */
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

    /**
     * Plays the kick instrument, if currently recording then add note to recording.
     */
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