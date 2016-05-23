package edu.uw.tacoma.zanderp.tcss450drumproject.Drums;

import android.media.MediaPlayer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import edu.uw.tacoma.zanderp.tcss450drumproject.R;

/**
 * The Drums displays the drum play screen to the user and allows them to play different
 * drums as well as record and listen to their recording.
 */
public class Drums extends AppCompatActivity {
    private TextView selected;
    private Button snare;
    private Button tom1;
    private Button tom2;
    private Button floortom;
    private Button bass;
    private Button crash;
    private Button ride;
    private Button hihat;
    private Button pedal;
    private Button btnRecord;
    private Button btnStopRecord;
    private Button btnPlay;
    private Button btnPause;
    private Boolean mRecord;
    private Recording mRecording;
    private Long mStart;
    private Boolean custom;
    private Button btnCustom;
    private final String CUSTOM = "CUSTOM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drum_set);
        custom = getIntent().getExtras().getBoolean("custom");
        Log.d(CUSTOM, ""+custom);
        mRecord = false;
        btnRecord = (Button)findViewById(R.id.record);
        btnStopRecord = (Button)findViewById(R.id.stopbutton);
        btnPlay = (Button)findViewById(R.id.play);
        btnPause = (Button)findViewById(R.id.pausebutton);
        mRecording = new Recording();
        mStart = SystemClock.elapsedRealtime();
        selected = (TextView)findViewById(R.id.select);
        snare = (Button)findViewById(R.id.snare);
        tom1 = (Button)findViewById(R.id.Tom1);
        tom2 = (Button)findViewById(R.id.Tom2);
        floortom = (Button)findViewById(R.id.floor_tom);
        bass = (Button)findViewById(R.id.bass);
        crash = (Button)findViewById(R.id.crash);
        ride = (Button)findViewById(R.id.ride);
        hihat = (Button)findViewById(R.id.high_hat);
        pedal = (Button)findViewById(R.id.pedal);
        btnCustom = (Button)findViewById(R.id.customize);
        setButtons();

    }

    public void isSelected(View view, Button button){
//        int myNewX = button.getBottom();
//        int myNewY = button.getTop();
        AbsoluteLayout.LayoutParams absParams =
                (AbsoluteLayout.LayoutParams)button.getLayoutParams();
//        absParams.x= myNewX;
//        absParams.y = myNewY;
//        Log.d(X_POSITION, ""+ (absParams.x));
//        Log.d(Y_POSITION,""+(absParams.y));
        selected.setLayoutParams(absParams);
        selected.setVisibility(View.VISIBLE);
    }

    public void drag(View view, MotionEvent event, Button b) {

        AbsoluteLayout.LayoutParams params = (AbsoluteLayout.LayoutParams) b.getLayoutParams();
        b.setSelected(true);
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE: {
                params.y = (int) event.getRawY() - 452;
                params.x = (int) event.getRawX() - 147;
                b.setLayoutParams(params);
                break;
            }
            case MotionEvent.ACTION_UP: {
                params.y = (int) event.getRawY() - 452;
                params.x = (int) event.getRawX() - 147;
                b.setLayoutParams(params);
                break;
            }
            case MotionEvent.ACTION_DOWN: {
                b.setLayoutParams(params);
                break;
            }
        }
    }

    public void setButtons(){
        if(custom) {
            btnCustom.setText("Play Drums");
            btnRecord.setEnabled(false);
            btnRecord.setVisibility(View.INVISIBLE);
            snare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isSelected(v, snare);
                }
            });
            snare.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    drag(v, event, snare);
                    return false;
                }
            });
            tom1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isSelected(v, tom1);
                }
            });
            tom1.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    drag(v, event, tom1);
                    return false;
                }
            });
            tom2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isSelected(v, tom2);
                }
            });
            tom2.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    drag(v, event, tom2);
                    return false;
                }
            });
            floortom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isSelected(v, floortom);
                }
            });
            floortom.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    drag(v, event, floortom);
                    return false;
                }
            });
            bass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isSelected(v, bass);
                }
            });
            bass.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    drag(v, event, bass);
                    return false;
                }
            });
            crash.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isSelected(v, crash);
                }
            });
            crash.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    drag(v, event, crash);
                    return false;
                }
            });
            ride.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isSelected(v, ride);
                }
            });
            ride.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    drag(v, event, ride);
                    return false;
                }
            });
            hihat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isSelected(v, hihat);
                }
            });
            hihat.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    drag(v, event, hihat);
                    return false;
                }
            });
            pedal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isSelected(v, pedal);
                }
            });
            pedal.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    drag(v, event, pedal);
                    return false;
                }
            });
        }else if(!custom){
            btnCustom.setText("Customize");
            selected.setVisibility(View.INVISIBLE);
            btnRecord.setEnabled(true);
            btnRecord.setVisibility(View.VISIBLE);
            snare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playSnare(v);
                }
            });
            snare.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return false;
                }
            });
            tom1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playTom1(v);
                }
            });
            tom1.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return false;
                }
            });
            tom2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playTom2(v);
                }
            });
            tom2.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return false;
                }
            });
            floortom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playFloorTom(v);
                }
            });
            floortom.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return false;
                }
            });
            crash.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playCrash(v);
                }
            });
            crash.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return false;
                }
            });
            ride.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playRide(v);
                }
            });
            ride.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return false;
                }
            });
            hihat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playHiHat(v);
                }
            });
            hihat.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return false;
                }
            });
            pedal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playKick(v);
                }
            });
            pedal.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return false;
                }
            });
            bass.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return false;
                }
            });
        }
    }

    public void setBtnCustom(View view){
        custom = !custom;
        setButtons();
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