package edu.uw.tacoma.zanderp.tcss450drumproject.Drums;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import edu.uw.tacoma.zanderp.tcss450drumproject.R;

public class DrumSet1 extends AppCompatActivity {
    final MediaPlayer crash = MediaPlayer.create(this, R.raw.crash);
//    final MediaPlayer hihat = MediaPlayer.create(this, R.raw.hihat);
//    final MediaPlayer kick = MediaPlayer.create(this, R.raw.kick1);
//    final MediaPlayer ride = MediaPlayer.create(this, R.raw.ride);
//    final MediaPlayer snare = MediaPlayer.create(this, R.raw.snare);
//    final MediaPlayer tomFloor = MediaPlayer.create(this, R.raw.tom1);
//    final MediaPlayer tom1 = MediaPlayer.create(this, R.raw.tom2);
//    final MediaPlayer tom2 = MediaPlayer.create(this, R.raw.tomshort);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drum_set1);

    }

    public void playCrash(View view){
        TextView playCrash = (TextView) this.findViewById(R.id.crash);
        playCrash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crash.start();
            }
        });
    }


}
