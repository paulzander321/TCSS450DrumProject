package edu.uw.tacoma.zanderp.tcss450drumproject.drums;

import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import java.io.Serializable;

public class Note implements Serializable {

    private Long mTimeFromStart;
    private int mResourceID;

    public Note(Long offsetTime, int resourceID) {
        mTimeFromStart = offsetTime;
        mResourceID = resourceID;
    }

    public void playNote(final AppCompatActivity ctx) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                final MediaPlayer note = MediaPlayer.create(ctx, mResourceID);
                note.start();
                note.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        note.release();
                    }
                });
            }
        }, mTimeFromStart);
    }

}
