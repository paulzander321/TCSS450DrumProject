package edu.uw.tacoma.zanderp.tcss450drumproject.Drums;

import android.support.v7.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

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

    /** Name of this recording. */
    private String mName;

    /** Username of this recording's creator. */
    private String mCreator;

    /** Date this recording was saved. */
    private Date mCreationTime;

    /** True if this recording is shared with community. */
    private boolean mIsShared;

    private int mLocalID;

    /**
     * Creates new recording with no values for name, creatorName, or creationTime.
     */
    public Recording() {
        this(null, null, null, false);
    }

    /**
     * Creates new Recording with given values.
     * @param name name of recording.
     * @param creatorName username of recording's creator.
     * @param creationTime time this recording was created.
     */
    public Recording(String name, String creatorName, Date creationTime, boolean isShared) {
        mName = name;
        mCreator = creatorName;
        mCreationTime = creationTime;
        mIsShared = isShared;
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
    public void stopPlayback() {
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

    /**
     * Getter for mNotes.
     */
    public ArrayList<Note> getmNotes() {
        return mNotes;
    }

    /** Gets name. */
    public String getmName() {
        return mName;
    }

    /** Gets creator. */
    public String getmCreator() {
        return mCreator;
    }

    /** Gets creation time. */
    public Date getmCreationTime() {
        return mCreationTime;
    }

    /** Gets whether this recording is shared. */
    public boolean ismIsShared() {
        return mIsShared;
    }

    public void setmLocalID(int id) { mLocalID = id; }

}
