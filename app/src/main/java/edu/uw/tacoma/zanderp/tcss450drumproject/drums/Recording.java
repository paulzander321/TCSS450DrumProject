package edu.uw.tacoma.zanderp.tcss450drumproject.Drums;

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

    /**
     * True if this recording is being played, false if not.
     */
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
        for (int i = 0; i < mNotes.size(); i++) {
            mNotes.get(i).playNote(ctx);
        }
    }

    /**
     * Stops current playthrough of recording (Note: currently playing notes will finish playing)
     */
    public void stopRecording() {
        mIsPlaying = false;
        for (Note note: mNotes
             ) {
            note.stopPlayback();
        }
    }

}
