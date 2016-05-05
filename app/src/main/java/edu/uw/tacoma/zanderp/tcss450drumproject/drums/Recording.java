package edu.uw.tacoma.zanderp.tcss450drumproject.drums;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * The Recording class contains a series of notes that are able
 * to be played.
 */
public class Recording implements Serializable {

    /**
     * The series of notes for this recording.
     */
    private ArrayList<Note> mNotes;

    private boolean mIsPlaying;

    /**
     * Creates new recording.
     */
    public Recording() {
        mNotes = new ArrayList<>();
        mIsPlaying = false;
    }

    /**
     * Adds a note to this recording.
     * @param addMe Note to add
     */
    public void addNote(Note addMe) {
        mNotes.add(addMe);
    }

    /**
     * Plays the recording until end or paused.
     * @param ctx the context for recording to be played
     */
    public void playRecording(final AppCompatActivity ctx) {
        if (mNotes.size() > 0) {
            mIsPlaying = true;
            Collections.sort(mNotes);
            playRecordingHelper(ctx, mNotes.get(0).getmTimeFromStart(), 0);
        }
    }

    /**
     * Helper method for playing recording.
     * @param ctx the context for play
     * @param delay delay until next note
     * @param noteIndex current note's index in recording
     */
    private void playRecordingHelper(final AppCompatActivity ctx, final Long delay, final int noteIndex) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mNotes.get(noteIndex).playNote(ctx);
                if (noteIndex + 1 < mNotes.size() && mIsPlaying) {
                    playRecordingHelper(ctx, mNotes.get(noteIndex + 1).getmTimeFromStart()
                            - mNotes.get(noteIndex).getmTimeFromStart(), noteIndex + 1);
                }
            }
        }, delay);
    }

    /**
     * Stops current playthrough of recording (Note: currently playing notes will finish playing)
     */
    public void stopRecording() {
        mIsPlaying = false;
    }

}
