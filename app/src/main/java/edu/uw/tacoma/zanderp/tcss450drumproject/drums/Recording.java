package edu.uw.tacoma.zanderp.tcss450drumproject.drums;

import android.support.v7.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;

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
     * Length of recording, time until done.
     */
    private Long mTotalDelay;

    /**
     * Creates new recording.
     */
    public Recording() {
        mNotes = new ArrayList<>();
        mTotalDelay = Long.valueOf(0);
    }

    /**
     * Adds a note to this recording.
     * @param addMe Note to add
     */
    public void addNote(Note addMe) {
        mNotes.add(addMe);
        if (addMe.getmTimeFromStart() > mTotalDelay) mTotalDelay = addMe.getmTimeFromStart();
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
     * Stops current playback of recording
     */
    public void stopRecording() {
        for (Note note: mNotes
             ) {
            note.stopPlayback();
        }
    }

    /**
     * Returns the total delay for the recording, ie time until done.
     * @return Time until done.
     */
    public Long getTotalTime() {
        return mTotalDelay;
    }

}
