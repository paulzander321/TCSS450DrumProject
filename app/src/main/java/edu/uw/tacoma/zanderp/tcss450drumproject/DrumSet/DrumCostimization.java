package edu.uw.tacoma.zanderp.tcss450drumproject.DrumSet;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.TextView;

import edu.uw.tacoma.zanderp.tcss450drumproject.R;

public class DrumCostimization extends AppCompatActivity {
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
    private Fragment drums;
    private final String X_POSITION = "X POSITION";
    private final String Y_POSITION = "Y POSITION";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drum_costimization);
        Button rec = (Button) findViewById(R.id.record);
        rec.setVisibility(View.INVISIBLE);
        drums = getFragmentManager().findFragmentById(R.id.fragment);
        selected = (TextView)findViewById(R.id.select);
        snare = (Button)findViewById(R.id.snare);
        snare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSelected(v,snare);
            }
        });
        snare.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                drag(v,event,snare);
                return false;
            }
        });
        tom1 = (Button)findViewById(R.id.Tom1);
        tom1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSelected(v,tom1);
            }
        });
        tom1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                drag(v,event,tom1);
                return false;
            }
        });
        tom2 = (Button)findViewById(R.id.Tom2);
        tom2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSelected(v,tom2);
            }
        });
        tom2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                drag(v,event,tom2);
                return false;
            }
        });
        floortom = (Button)findViewById(R.id.floor_tom);
        floortom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSelected(v,floortom);
            }
        });
        floortom.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                drag(v,event,floortom);
                return false;
            }
        });
        bass = (Button)findViewById(R.id.bass);
        bass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSelected(v,bass);
            }
        });
        bass.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                drag(v,event,bass);
                return false;
            }
        });
        crash = (Button)findViewById(R.id.crash);
        crash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSelected(v,crash);
            }
        });
        crash.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                drag(v,event,crash);
                return false;
            }
        });
        ride = (Button)findViewById(R.id.ride);
        ride.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSelected(v,ride);
            }
        });
        ride.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                drag(v,event,ride);
                return false;
            }
        });
        hihat = (Button)findViewById(R.id.high_hat);
        hihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSelected(v,hihat);
            }
        });
        hihat.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                drag(v,event,hihat);
                return false;
            }
        });
        pedal = (Button)findViewById(R.id.pedal);
        pedal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSelected(v,pedal);
            }
        });
        pedal.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                drag(v,event,pedal);
                return false;
            }
        });
    }

    public void isSelected(View view, Button button){
//        int myNewX = button.getBottom();
//        int myNewY = button.getTop();
        AbsoluteLayout.LayoutParams absParams =
                (AbsoluteLayout.LayoutParams)button.getLayoutParams();
//        absParams.x= myNewX;
//        absParams.y = myNewY;
        Log.d(X_POSITION, ""+ (absParams.x));
        Log.d(Y_POSITION,""+(absParams.y));
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

}
