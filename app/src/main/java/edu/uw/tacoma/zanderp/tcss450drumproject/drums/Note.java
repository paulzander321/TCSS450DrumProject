package edu.uw.tacoma.zanderp.tcss450drumproject.drums;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;

import java.io.Serializable;

/**
 * The Note class contains information regarding which instrument the note
 * should play as well as the time it was played relative to the beginning of
 * recording.
 */
public class Note implements Serializable, Comparable<Note> {

    /**
     * The time from the start of recording.
     */
    private Long mTimeFromStart;

    /**
     * This references the instrument for this note.
     */
    private int mResourceID;

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
     * Plays this note
     * @param ctx
     */
    public void playNote(final AppCompatActivity ctx) {
        final MediaPlayer note = MediaPlayer.create(ctx, mResourceID);
        note.start();
        note.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                note.release();
            }
        });
    }

    @Override
    public int compareTo(Note other) {
        return this.mTimeFromStart.intValue() - other.mTimeFromStart.intValue();
    }

    /**
     * @return this note's delay time
     */
    public Long getmTimeFromStart() {
        return mTimeFromStart;
    }
}
