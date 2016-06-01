package edu.uw.tacoma.zanderp.tcss450drumproject.Drums;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import edu.uw.tacoma.zanderp.tcss450drumproject.MainActivity;
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
    private Button btnAdd;
    private Button btnDelete;
    private Button btnSave;
    private Boolean mRecord;
    private Recording mRecording;
    private Long mStart;
    private Boolean custom;
    private Boolean isEnableSnare;
    private Boolean isEnableTom1;
    private Boolean isEnableTom2;
    private Boolean isEnableFloorTom;
    private Boolean isEnableBass;
    private Boolean isEnableCrash;
    private Boolean isEnableRide;
    private Boolean isEnableHihat;
    private Boolean isEnablePedal;
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
    private int buttonSelected;
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
        btnAdd = (Button)findViewById(R.id.add_drum);
        btnDelete = (Button) findViewById(R.id.delete_drum);
        mRecording = new Recording();
        mStart = SystemClock.elapsedRealtime();
        selected = (TextView)findViewById(R.id.select);
        snare = (Button)findViewById(R.id.snare);
        tom1 = (Button)findViewById(R.id.Tom1);
        tom2 = (Button)findViewById(R.id.Tom2);
        floortom = (Button)findViewById(R.id.floor_tom);
        btnSave = (Button) findViewById(R.id.savebutton);
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
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDrum(buttonSelected);
            }
        });

    }

    private void openDialog(){
        LayoutInflater inflater = LayoutInflater.from(Drums.this);
        View subView = inflater.inflate(R.layout.add_button_layout, null);

        final ImageView imageSnare = (ImageView) subView.findViewById(R.id.image1);
        Drawable drawableSnare = getResources().getDrawable(R.drawable.snare);
        imageSnare.setImageDrawable(drawableSnare);

        final ImageView imageTom1 = (ImageView) subView.findViewById(R.id.image2);
        Drawable drawableTom1 = getResources().getDrawable(R.drawable.hightom);
        imageTom1.setImageDrawable(drawableTom1);

        final ImageView imageTom2 = (ImageView) subView.findViewById(R.id.image3);
        Drawable drawableTom2 = getResources().getDrawable(R.drawable.midtom);
        imageTom2.setImageDrawable(drawableTom2);
        imageTom2.setVisibility(View.INVISIBLE);

        final ImageView imageFloorTom = (ImageView) subView.findViewById(R.id.image4);
        Drawable drawableFloorTom = getResources().getDrawable(R.drawable.lowtom);
        imageFloorTom.setImageDrawable(drawableFloorTom);

        final ImageView imageBass = (ImageView) subView.findViewById(R.id.image5);
        Drawable drawableBass = getResources().getDrawable(R.drawable.bass);
        imageBass.setImageDrawable(drawableBass);

        final ImageView imageCrash = (ImageView) subView.findViewById(R.id.image6);
        Drawable drawableCrash = getResources().getDrawable(R.drawable.crash);
        imageCrash.setImageDrawable(drawableCrash);

        final ImageView imageHiHat = (ImageView) subView.findViewById(R.id.image7);
        Drawable drawableHiHat = getResources().getDrawable(R.drawable.highhat);
        imageHiHat.setImageDrawable(drawableHiHat);

        final ImageView imageRide = (ImageView) subView.findViewById(R.id.image8);
        Drawable drawableRide = getResources().getDrawable(R.drawable.ride);
        imageRide.setImageDrawable(drawableRide);

        final ImageView imagePedal = (ImageView) subView.findViewById(R.id.image9);
        Drawable drawablePedal = getResources().getDrawable(R.drawable.pedal);
        imagePedal.setImageDrawable(drawablePedal);
        if(!snare.isEnabled()) {
            imageSnare.setVisibility(View.VISIBLE);
        }
        if(!tom1.isEnabled()){
            imageTom1.setVisibility(View.VISIBLE);
        }
        if(!tom2.isEnabled()){
            imageTom2.setVisibility(View.VISIBLE);
        }
        if(!floortom.isEnabled()){
            imageFloorTom.setVisibility(View.VISIBLE);
        }
        if(!bass.isEnabled()){
            imageBass.setVisibility(View.VISIBLE);
        }
        if(!crash.isEnabled()){
            imageCrash.setVisibility(View.VISIBLE);
        }
        if(!hihat.isEnabled()){
            imageHiHat.setVisibility(View.VISIBLE);
        }
        if(!ride.isEnabled()){
            imageRide.setVisibility(View.VISIBLE);
        }
        if(!pedal.isEnabled()){
            imagePedal.setVisibility(View.VISIBLE);
        }

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Button");
        builder.setMessage("There are no drums to add");
        builder.setView(subView);

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(Drums.this, "Cancel", Toast.LENGTH_LONG).show();
            }
        });
        final AlertDialog alertDialog = builder.create();

        imageSnare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDrum(snare.getId());
                alertDialog.dismiss();
            }
        });

        imageTom1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDrum(tom1.getId());
                alertDialog.dismiss();
            }
        });

        imageTom2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDrum(tom2.getId());
                alertDialog.dismiss();
            }
        });

        imageFloorTom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDrum(floortom.getId());
                alertDialog.dismiss();
            }
        });

        imageBass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDrum(bass.getId());
                alertDialog.dismiss();
            }
        });

        imageCrash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDrum(crash.getId());
                alertDialog.dismiss();
            }
        });

        imageHiHat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDrum(hihat.getId());
                alertDialog.dismiss();
            }
        });

        imageRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDrum(ride.getId());
                alertDialog.dismiss();
            }
        });

        imagePedal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDrum(pedal.getId());
                alertDialog.dismiss();
            }
        });





        alertDialog.show();
    }


    public void addDrum(int buttonID){
        Button drumDeleted = (Button) findViewById(buttonID);
        drumDeleted.setEnabled(true);
        drumDeleted.setVisibility(View.VISIBLE);

    }

    public void deleteDrum(int buttonID){
        Button drumDeleted = (Button) findViewById(buttonID);
        drumDeleted.setEnabled(false);
        drumDeleted.setVisibility(View.INVISIBLE);
        selected.setVisibility(View.INVISIBLE);
        btnDelete.setVisibility(View.INVISIBLE);
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
        isEnableSnare = sharedPreferences.getBoolean("SNARE_ENABLE",true);
        isEnableTom1 = sharedPreferences.getBoolean("TOM1_ENABLE",true);
        isEnableTom2 = sharedPreferences.getBoolean("TOM2_ENABLE",true);
        isEnableFloorTom = sharedPreferences.getBoolean("FLOORTOM_ENABLE",true);
        isEnableBass = sharedPreferences.getBoolean("BASS_ENABLE",true);
        isEnableCrash = sharedPreferences.getBoolean("CRASH_ENABLE",true);
        isEnableHihat = sharedPreferences.getBoolean("HIHAT_ENABLE",true);
        isEnablePedal = sharedPreferences.getBoolean("PEDAL_ENABLE",true);
        isEnableRide = sharedPreferences.getBoolean("RIDE_ENABLE",true);
        if(!isEnableSnare){
            deleteDrum(snare.getId());
        }
        if(!isEnableTom1){
            deleteDrum(tom1.getId());
        }
        if(!isEnableTom2){
            deleteDrum(tom2.getId());
        }
        if(!isEnableFloorTom){
            deleteDrum(floortom.getId());
        }
        if(!isEnableBass){
            deleteDrum(bass.getId());
        }
        if(!isEnableCrash){
            deleteDrum(crash.getId());
        }
        if(!isEnableHihat){
            deleteDrum(hihat.getId());
        }
        if(!isEnablePedal){
            deleteDrum(pedal.getId());
        }
        if(!isEnableRide){
            deleteDrum(ride.getId());
        }
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
        editor.putBoolean("SNARE_ENABLE", isEnableSnare);
        editor.putBoolean("TOM1_ENABLE", isEnableTom1);
        editor.putBoolean("TOM2_ENABLE", isEnableTom2);
        editor.putBoolean("FLOORTOM_ENABLE", isEnableFloorTom);
        editor.putBoolean("BASS_ENABLE", isEnableBass);
        editor.putBoolean("CRASH_ENABLE", isEnableCrash);
        editor.putBoolean("HIHAT_ENABLE", isEnableHihat);
        editor.putBoolean("PEDAL_ENABLE", isEnablePedal);
        editor.putBoolean("RIDE_ENABLE", isEnableRide);
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
        editor.commit();
    }

    public void isSelected(View view, Button button){
        buttonSelected = button.getId();
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
        btnDelete.setVisibility(View.VISIBLE);
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
            btnAdd.setVisibility(View.VISIBLE);
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
            btnAdd.setVisibility(View.INVISIBLE);
            btnDelete.setVisibility(View.INVISIBLE);
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
            bass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
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
        if (!mRecording.getmNotes().isEmpty()) btnSave.setVisibility(TextView.VISIBLE);
        btnPlay.setVisibility(TextView.VISIBLE);
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
    }

    /**
     * Stops the currently playing recording.
     */
    public void stopPlaying(View view){
        mRecording.stopPlayback();
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
            mRecording.addNote(new edu.uw.tacoma.zanderp.tcss450drumproject.Drums.Note(SystemClock.elapsedRealtime() - mStart, R.raw.tom2));
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
            mRecording.addNote(new edu.uw.tacoma.zanderp.tcss450drumproject.Drums.Note(SystemClock.elapsedRealtime() - mStart, R.raw.tomshort));
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
            mRecording.addNote(new edu.uw.tacoma.zanderp.tcss450drumproject.Drums.Note(SystemClock.elapsedRealtime() - mStart, R.raw.tom1));
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
            mRecording.addNote(new edu.uw.tacoma.zanderp.tcss450drumproject.Drums.Note(SystemClock.elapsedRealtime() - mStart, R.raw.ride));
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

    @Override
    public void onDialogPositiveClick(DialogFragment dialog, boolean sharing, String recordingName) {
        if (recordingName.isEmpty()) {
            Toast.makeText(this, "Recording not saved! Please give your recording a name", Toast.LENGTH_LONG).show();
        } else if (mRecording.getmNotes().isEmpty()) {
            Toast.makeText(this, "Recording not saved! The recording is empty!", Toast.LENGTH_LONG).show();
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

    private class SaveRecordingExternalTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            //TODO
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            //TODO
        }

    }
}