package edu.uw.tacoma.zanderp.tcss450drumproject.drums;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import edu.uw.tacoma.zanderp.tcss450drumproject.R;

public class DrumSet1 extends AppCompatActivity {
    private MediaRecorder record;
    private MediaPlayer playRecording;
    private Button btnRecord;
    private Button btnStopRecord;
    private Button btnPlay;
    private Button btnPause;
    private String FILE;
    private ArrayList<Integer> music;
    private ArrayList<Long> time;
    private Long pause;
    private Long startTime;
    private Long endTime;
    private Boolean mRecord;


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
    }


    public void startRecording(View view) throws IOException {
        if (music != null){
            music.clear();
        }
        if (time != null){
            time.clear();
        }
        mRecord = true;
        music = new ArrayList<>();
        time = new ArrayList<>();
        startTime = SystemClock.elapsedRealtime();
        btnRecord.setVisibility(TextView.INVISIBLE);
        btnStopRecord.setVisibility(TextView.VISIBLE);

    }

    public void stopRecording(View view){
        mRecord = false;
        endTime = SystemClock.elapsedRealtime();
        pause = startTime - endTime;
        time.add(pause);
        btnRecord.setVisibility(TextView.VISIBLE);
        btnStopRecord.setVisibility(TextView.INVISIBLE);
        btnPlay.setVisibility(TextView.VISIBLE);
    }

    public void playRecording(View view) throws IOException, InterruptedException {
        if(playRecording != null){
            playRecording.stop();
            playRecording.release();
        }
        btnPlay.setVisibility(TextView.INVISIBLE);
        btnPause.setVisibility(TextView.VISIBLE);
        for (int i = 0;i<time.size()-1;i++){
            Thread.sleep(time.get(i));
            switch (music.get(i)){
                case 1:
                    final MediaPlayer crash = MediaPlayer.create(this,R.raw.crash);
                    crash.start();
                    crash.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            crash.release();
                        }
                    });
                    break;
                case 2:
                    final MediaPlayer tom1 = MediaPlayer.create(this, R.raw.tom2);
                    tom1.start();
                    tom1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            tom1.release();
                        }
                    });
                    break;
                case 3:
                    final MediaPlayer snare = MediaPlayer.create(this, R.raw.snare);
                    snare.start();
                    snare.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            snare.release();
                        }
                    });
                    break;
                case 4:
                    final MediaPlayer tom2 = MediaPlayer.create(this, R.raw.tomshort);
                    tom2.start();
                    tom2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            tom2.release();
                        }
                    });
                    break;
                case 5:
                    final MediaPlayer tomFloor = MediaPlayer.create(this, R.raw.tom1);
                    tomFloor.start();
                    tomFloor.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            tomFloor.release();
                        }
                    });
                    break;
                case 6:
                    final MediaPlayer ride = MediaPlayer.create(this, R.raw.ride);
                    ride.start();
                    ride.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            ride.release();
                        }
                    });
                    break;
                case 7:
                    final MediaPlayer hihat = MediaPlayer.create(this, R.raw.hihat);
                    hihat.start();
                    hihat.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            hihat.release();
                        }
                    });
                    break;
                case 8:
                    final MediaPlayer kick = MediaPlayer.create(this, R.raw.kick1);
                    kick.start();
                    kick.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            kick.release();
                        }
                    });
                    break;
            }
        }

    }

    public void stopPlaying(View view){

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
            endTime = SystemClock.elapsedRealtime();
            pause = endTime - startTime;
            music.add(1);
            time.add(pause);
            startTime = SystemClock.elapsedRealtime();
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
            endTime = SystemClock.elapsedRealtime();
            pause = endTime - startTime;
            music.add(2);
            time.add(pause);
            startTime = SystemClock.elapsedRealtime();
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
            endTime = SystemClock.elapsedRealtime();
            pause = endTime - startTime;
            music.add(3);
            time.add(pause);
            startTime = SystemClock.elapsedRealtime();
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
            endTime = SystemClock.elapsedRealtime();
            pause = endTime - startTime;
            music.add(4);
            time.add(pause);
            startTime = SystemClock.elapsedRealtime();
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
            endTime = SystemClock.elapsedRealtime();
            pause = endTime - startTime;
            music.add(5);
            time.add(pause);
            startTime = SystemClock.elapsedRealtime();
        }
    }
    public void playRide(View view){
        final MediaPlayer ride = MediaPlayer.create(this, R.raw.ride);
        ride.start();
        ride.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                ride.release();
            }
        });
        if (mRecord){
            endTime = SystemClock.elapsedRealtime();
            pause = endTime - startTime;
            music.add(6);
            time.add(pause);
            startTime = SystemClock.elapsedRealtime();
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
            endTime = SystemClock.elapsedRealtime();
            pause = endTime - startTime;
            music.add(7);
            time.add(pause);
            startTime = SystemClock.elapsedRealtime();
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
            endTime = SystemClock.elapsedRealtime();
            pause = endTime - startTime;
            music.add(8);
            time.add(pause);
            startTime = SystemClock.elapsedRealtime();
        }
    }
}