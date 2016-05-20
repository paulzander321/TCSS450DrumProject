package edu.uw.tacoma.zanderp.tcss450drumproject.DrumSet;

import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.RelativeLayout;
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
    private final String X_POSITION = "X POSITION";
    private final String Y_POSITION = "Y POSITION";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drum_costimization);
        Button rec = (Button) findViewById(R.id.record);
        rec.setVisibility(View.INVISIBLE);
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
                drag(event,snare);
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
                drag(event,tom1);
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
                drag(event,tom2);
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
                drag(event,floortom);
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
                drag(event,bass);
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
                drag(event,crash);
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
                drag(event,ride);
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
                drag(event,hihat);
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
                drag(event,pedal);
                return false;
            }
        });

    }

    public void isSelected(View view, Button button){
        int myNewX = button.getBottom();
        int myNewY = button.getTop();
        Log.d(X_POSITION, ""+myNewX);
        Log.d(Y_POSITION,""+myNewY);
        AbsoluteLayout.LayoutParams absParams =
                (AbsoluteLayout.LayoutParams)button.getLayoutParams();
//        absParams.x= myNewX;
//        absParams.y = myNewY;
        selected.setLayoutParams(absParams);
        selected.setVisibility(View.VISIBLE);
    }

    public void drag(MotionEvent event, Button b) {

//        RelativeLayout.LayoutParams params = (android.widget.RelativeLayout.LayoutParams) b.getLayoutParams();
//        b.setSelected(true);
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_MOVE: {
//                params.topMargin = (int) event.getRawY() - (b.getHeight());
//                params.leftMargin = (int) event.getRawX() - (b.getWidth() / 2);
//                b.setLayoutParams(params);
//                break;
//            }
//            case MotionEvent.ACTION_UP: {
//                params.topMargin = (int) event.getRawY() - (b.getHeight());
//                params.leftMargin = (int) event.getRawX() - (b.getWidth() / 2);
//                b.setLayoutParams(params);
//                break;
//            }
//            case MotionEvent.ACTION_DOWN: {
//                b.setLayoutParams(params);
//                break;
//            }
//        }
    }
}
