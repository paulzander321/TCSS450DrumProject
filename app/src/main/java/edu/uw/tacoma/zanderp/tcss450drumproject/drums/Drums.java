package edu.uw.tacoma.zanderp.tcss450drumproject.drums;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import edu.uw.tacoma.zanderp.tcss450drumproject.Authenticate.SignInActivity;
import edu.uw.tacoma.zanderp.tcss450drumproject.R;
import edu.uw.tacoma.zanderp.tcss450drumproject.data.RecordingDB;

/**
 * The Drums displays the drum play screen to the user and allows them to play different
 * drums as well as record and listen to their recording.
 */
public class Drums extends AppCompatActivity implements SaveRecordingDialogFragment.SaveRecordingDialogListener {
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
    private Button btnSave;
    private Boolean mRecord;
    private Recording mRecording;
    private Long mStart;
    private Boolean custom;
    private Button btnCustom;
    private AbsoluteLayout.LayoutParams paramsSnare;
    private AbsoluteLayout.LayoutParams paramsTom1;
    private AbsoluteLayout.LayoutParams paramsTom2;
    private AbsoluteLayout.LayoutParams paramsFloortom;
    private AbsoluteLayout.LayoutParams paramsBass;
    private AbsoluteLayout.LayoutParams paramsCrash;
    private AbsoluteLayout.LayoutParams paramsRide;
    private AbsoluteLayout.LayoutParams paramsHihat;
    private AbsoluteLayout.LayoutParams paramsPedal;
    private final String CUSTOM = "CUSTOM";
    private final String X_POSITION = "X_POSITION";
    private final String Y_POSITION = "Y_POSITION";


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
        btnSave = (Button) findViewById(R.id.savebutton);
        mRecording = new edu.uw.tacoma.zanderp.tcss450drumproject.drums.Recording();
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
        paramsSnare = (AbsoluteLayout.LayoutParams) snare.getLayoutParams();
        paramsTom1 = (AbsoluteLayout.LayoutParams) tom1.getLayoutParams();
        paramsTom2 = (AbsoluteLayout.LayoutParams) tom2.getLayoutParams();
        paramsFloortom = (AbsoluteLayout.LayoutParams) floortom.getLayoutParams();
        paramsBass = (AbsoluteLayout.LayoutParams) bass.getLayoutParams();
        paramsCrash = (AbsoluteLayout.LayoutParams) crash.getLayoutParams();
        paramsHihat = (AbsoluteLayout.LayoutParams) hihat.getLayoutParams();
        paramsPedal = (AbsoluteLayout.LayoutParams) pedal.getLayoutParams();
        paramsRide = (AbsoluteLayout.LayoutParams) ride.getLayoutParams();
        setButtons();
        LoadButtonLocation();
    }

    @Override
    public void onBackPressed() {
        SaveButtonLocation();
        super.onBackPressed();
    }

    private void LoadButtonLocation() {
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        paramsSnare.x = sharedPreferences.getInt("SNARE_X",231);
        paramsSnare.y = sharedPreferences.getInt("SNARE_Y",828);
//        Log.d(X_POSITION, ""+ (sharedPreferences.getInt("SNARE_X",231)));
//        Log.d(Y_POSITION,""+(sharedPreferences.getInt("SNARE_Y",231)));
        paramsTom1.x = sharedPreferences.getInt("TOM1_X",264);
        paramsTom1.y = sharedPreferences.getInt("TOM1_Y",459);
        paramsTom2.x = sharedPreferences.getInt("TOM2_X",552);
        paramsTom2.y = sharedPreferences.getInt("TOM2_Y",402);
        paramsFloortom.x = sharedPreferences.getInt("FLOORTOM_X",732);
        paramsFloortom.y = sharedPreferences.getInt("FLOORTOM_Y",693);
        paramsBass.x = sharedPreferences.getInt("BASS_X",261);
        paramsBass.y = sharedPreferences.getInt("BASS_Y",285);
        paramsCrash.x = sharedPreferences.getInt("CRASH_X",35);
        paramsCrash.y = sharedPreferences.getInt("CRASH_Y",110);
        paramsHihat.x = sharedPreferences.getInt("HIHAT_X",12);
        paramsHihat.y = sharedPreferences.getInt("HIHAT_Y",588);
        paramsPedal.x = sharedPreferences.getInt("PEDAL_X",495);
        paramsPedal.y = sharedPreferences.getInt("PEDAL_Y",777);
        paramsRide.x = sharedPreferences.getInt("RIDE_X",720);
        paramsRide.y = sharedPreferences.getInt("RIDE_Y",288);
        snare.setLayoutParams(paramsSnare);
        tom1.setLayoutParams(paramsTom1);
        tom2.setLayoutParams(paramsTom2);
        floortom.setLayoutParams(paramsFloortom);
        bass.setLayoutParams(paramsBass);
        crash.setLayoutParams(paramsCrash);
        hihat.setLayoutParams(paramsHihat);
        pedal.setLayoutParams(paramsPedal);
        ride.setLayoutParams(paramsRide);
    }

    private void SaveButtonLocation() {
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("SNARE_X", paramsSnare.x);
        editor.putInt("SNARE_Y", paramsSnare.y);
        editor.putInt("TOM1_X", paramsTom1.x);
        editor.putInt("TOM1_Y", paramsTom1.y);
        editor.putInt("TOM2_X", paramsTom2.x);
        editor.putInt("TOM2_Y", paramsTom2.y);
        editor.putInt("FLOORTOM_X", paramsFloortom.x);
        editor.putInt("FLOORTOM_Y", paramsFloortom.y);
        editor.putInt("BASS_X", paramsBass.x);
        editor.putInt("BASS_Y", paramsBass.y);
        editor.putInt("CRASH_X", paramsCrash.x);
        editor.putInt("CRASH_Y", paramsCrash.y);
        editor.putInt("HIHAT_X", paramsHihat.x);
        editor.putInt("HIHAT_Y", paramsHihat.y);
        editor.putInt("PEDAL_X", paramsPedal.x);
        editor.putInt("PEDAL_Y", paramsPedal.y);
        editor.putInt("RIDE_X", paramsRide.x);
        editor.putInt("RIDE_Y", paramsRide.y);
//        Log.d(X_POSITION, ""+ (paramsSnare.x));
//        Log.d(Y_POSITION,""+(paramsSnare.y));
        editor.apply();
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
            btnCustom.setText(getText(R.string.play_drums));
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
            btnCustom.setText(getText(R.string.cusomize_drums));
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
     * a recording, will destroy it and start with blank recording.
     */
    public void startRecording(View view) {
        mRecord = true;
        mRecording = new Recording();
        btnPause.setVisibility(TextView.INVISIBLE);
        btnPlay.setVisibility(TextView.INVISIBLE);
        btnRecord.setVisibility(TextView.INVISIBLE);
        btnStopRecord.setVisibility(TextView.VISIBLE);
        btnSave.setVisibility(TextView.INVISIBLE);
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
        btnSave.setVisibility(TextView.VISIBLE);
    }

    /**
     * Opens up dialog to save current recording. Gives option to share with
     * app community.
     */
    public void saveRecording(View view) {
        DialogFragment f = new SaveRecordingDialogFragment();
        f.show(getSupportFragmentManager(), "saveRecording");
    }

    /**
     * Plays the currently saved recording.
     */
    public void playRecording(View view) {
        btnPlay.setVisibility(TextView.INVISIBLE);
        btnPause.setVisibility(TextView.VISIBLE);
        mRecording.playRecording(this);
        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                btnPause.setVisibility(TextView.INVISIBLE);
                btnPlay.setVisibility(TextView.VISIBLE);
            }
        }, mRecording.getTotalTime());
    }

    /**
     * Stops the currently playing recording.
     */
    public void stopPlaying(View view){
        mRecording.stopRecording();
        btnPause.setVisibility(TextView.INVISIBLE);
        btnPlay.setVisibility(TextView.VISIBLE);
        //Add save dialogue for recording.

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
            mRecording.addNote(new edu.uw.tacoma.zanderp.tcss450drumproject.drums.Note(SystemClock.elapsedRealtime() - mStart, R.raw.tom2));
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
            edu.uw.tacoma.zanderp.tcss450drumproject.drums.Note snareNote = new edu.uw.tacoma.zanderp.tcss450drumproject.drums.Note(SystemClock.elapsedRealtime() - mStart, R.raw.snare);
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
            mRecording.addNote(new edu.uw.tacoma.zanderp.tcss450drumproject.drums.Note(SystemClock.elapsedRealtime() - mStart, R.raw.tomshort));
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
            mRecording.addNote(new edu.uw.tacoma.zanderp.tcss450drumproject.drums.Note(SystemClock.elapsedRealtime() - mStart, R.raw.tom1));
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
            mRecording.addNote(new edu.uw.tacoma.zanderp.tcss450drumproject.drums.Note(SystemClock.elapsedRealtime() - mStart, R.raw.ride));
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
            mRecording.addNote(new edu.uw.tacoma.zanderp.tcss450drumproject.drums.Note(SystemClock.elapsedRealtime() - mStart, R.raw.hihat));
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
            mRecording.addNote(new edu.uw.tacoma.zanderp.tcss450drumproject.drums.Note(SystemClock.elapsedRealtime() - mStart, R.raw.kick1));
        }
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog, boolean sharing, String recordingName) {
        if (recordingName.isEmpty()) {
            Toast.makeText(this, "Recording not saved! Please give your recording a name", Toast.LENGTH_LONG).show();
        } else if (mRecording.getmNotes().isEmpty()) {
            Toast.makeText(this, "Recording not saved! Recording must have at least one note", Toast.LENGTH_LONG).show();
        } else {
            RecordingDB db = new RecordingDB(getApplicationContext());
            SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.LOGIN_PREFS), Context.MODE_PRIVATE);
            db.insertRecording(recordingName, sharedPreferences.getString(getString(R.string.USERNAME), "0"), sharing, mRecording);
            db.closeDB();
            if (sharing) {
                //TODO Save to database on server.
            }
            Toast.makeText(this, recordingName + " was successfully saved!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        dialog.dismiss();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.logout) {
            SharedPreferences sharedPreferences =
                    getSharedPreferences(getString(R.string.LOGIN_PREFS), Context.MODE_PRIVATE);
            sharedPreferences.edit().putBoolean(getString(R.string.LOGGEDIN), false)
                    .apply();
            Intent i = new Intent(this, SignInActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
            Toast.makeText(this,"Logout Successful!", Toast.LENGTH_LONG)
                    .show();
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
