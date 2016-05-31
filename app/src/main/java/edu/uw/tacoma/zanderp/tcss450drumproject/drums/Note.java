package edu.uw.tacoma.zanderp.tcss450drumproject.Drums;

import android.media.MediaPlayer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

/**
 * The Note class contains information regarding which instrument the note
 * should play as well as the time it was played relative to the beginning of
 * recording.
 */
public class Note implements Comparable<Note> {

    /**
     * The time from the start of recording.
     */
    private Long mTimeFromStart;

    /**
     * This references the instrument for this note.
     */
    private int mResourceID;

    /**
     * Handler responsible for the timing of this note during playback.
     */
    private Handler mHandler;

    /**
     * Creates a new Note.
     * @param offsetTime time from beginning of recording
     * @param resourceID instrument resource id
     */
    public Note(Long offsetTime, int resourceID) {
        mTimeFromStart = offsetTime;
        mResourceID = resourceID;
    }

    /**
     * Plays this note through the specified context.
     * @param ctx the context
     */
    public void playNote(final AppCompatActivity ctx) {
        mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
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

    @Override
    public int compareTo(@NonNull Note other) {
        return this.mTimeFromStart.intValue() - other.mTimeFromStart.intValue();
    }

    /**
     * @return this note's delay time
     */
    public Long getmTimeFromStart() {
        return mTimeFromStart;
    }

    /**
     * Stops the playback of this note if currently playing or scheduled to play.
     */
    public void stopPlayback() {
        mHandler.removeCallbacksAndMessages(null);
    }

    /** Gets the resource id representing the instrument for this note. */
    public int getmResourceID() {
        return mResourceID;
    }
}
