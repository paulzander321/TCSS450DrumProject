package edu.uw.tacoma.zanderp.tcss450drumproject.Drums;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The Recording class contains a series of notes that are able
 * to be played.
 */
public class Recording implements Serializable {

    private static final String TAG = "Recording";

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

    public void setmName(String name) { mName = name; }

    public void setmCreator(String creator) { mCreator = creator; }

    public void setmCreationTime(Date creationTime) { mCreationTime = creationTime; }

    public void setmIsShared(boolean isShared) { mIsShared = isShared; }

    /** Creates and returns a JSON Object that represents this recording, including data for its notes. **/
    public JSONObject getRecordingJSON() {
        JSONObject jsonObject = new JSONObject();
        try {
            DateFormat s = SimpleDateFormat.getDateTimeInstance();
            jsonObject.put("name", mName);
            jsonObject.put("creator", mCreator);
            jsonObject.put("creation_time", s.format(mCreationTime));
            jsonObject.put("shared", mIsShared);
            JSONArray notes = new JSONArray();
            for (int i = 0; i < mNotes.size(); i++) {
                notes.put(mNotes.get(i).getNoteJSON());
            }
            jsonObject.put("notes", notes);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * Parses the json string, returns an error message if unsuccessful.
     * Returns recording list if success.
     * @param recordingJSON the array of objects representing notes for recordings.
     * @return reason or null if successful.
     */
    public static String parseCourseJSON(String recordingJSON, List<Recording> recordingList) {
        String reason = null;
        Log.d(TAG, "parseCourseJSON: " + recordingJSON);
        if (recordingJSON != null) {
            try {
                JSONArray arr = new JSONArray(recordingJSON);
                int i = 0;
                Log.d(TAG, "parseCourseJSON: json array size is " + arr.length());
                while(i < arr.length() - 1) {
                    Log.d(TAG, "parseCourseJSON: First while loop: i = " + i);
                    JSONObject obj = arr.getJSONObject(i);
                    int recordingID = obj.getInt("recording_id");
                    String name = obj.getString("name");
                    String creator = obj.getString("creator");
                    DateFormat format = SimpleDateFormat.getDateTimeInstance();
                    Date date = new Date();
                    try {
                        date = format.parse(obj.getString("time_created"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    boolean shared = obj.getInt("shared") != 0;
                    Recording current = new Recording(name, creator, date, shared);
                    while (i < arr.length() && obj.getInt("recording_id") == recordingID) {
                        int instrumentID = obj.getInt("instrument_id");
                        Long delayTime = Long.parseLong(obj.getString("delay_time"));
                        Note newNote = new Note(delayTime, instrumentID);
                        current.addNote(newNote);
                        Log.d(TAG, "parseCourseJSON: Second while loop: i = " + i);
                        i++;
                        if (i < arr.length()) obj = arr.getJSONObject(i);
                    }
                    recordingList.add(current);
                }
            } catch (JSONException e) {
                reason =  "Unable to parse data, Reason: " + e.getMessage();
                Log.d(TAG, "parseCourseJSON: Failure parsing JSON array, Reason: " + reason);
            }

        }
        return reason;
    }

}
