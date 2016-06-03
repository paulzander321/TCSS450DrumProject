package edu.uw.tacoma.zanderp.tcss450drumproject.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.uw.tacoma.zanderp.tcss450drumproject.R;
import edu.uw.tacoma.zanderp.tcss450drumproject.Drums.Note;
import edu.uw.tacoma.zanderp.tcss450drumproject.Drums.Recording;

/**
 * A class used to save recording from the database as well as to retrieve them. Uses
 * SQLiteOpenHelper for opening and initializing the database.
 */
public class RecordingDB {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "MyRecordings.db";
    private static final String TAG = "RECORDING_DB";

    /**
     * The helper for the database.
     */
    private RecordingDBHelper mRecordingDBHelper;

    /**
     * The Database that is used to save recordings.
     */
    private SQLiteDatabase mSQLiteDatabase;

    /**
     * Creates new RecordingDB.
     */
    public RecordingDB(Context context) {
        mRecordingDBHelper = new RecordingDBHelper(
                context, DB_NAME, null, DB_VERSION);
        mSQLiteDatabase = mRecordingDBHelper.getWritableDatabase();
    }

    /**
     * Closes the database.
     */
    public void closeDB() {
        mSQLiteDatabase.close();
    }

    /**
     * Saves a new recording to the database.
     * @param recording the recording itself
     * @return true if save was successful, false if failed
     */
    public boolean insertRecording(Recording recording) {
        DateFormat s = SimpleDateFormat.getDateTimeInstance();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", recording.getmName());
        contentValues.put("creator", recording.getmCreator());
        contentValues.put("shared", recording.ismIsShared());
        contentValues.put("total_time", recording.getTotalTime());
        contentValues.put("time_created", s.format(recording.getmCreationTime()));
        long recordingID = mSQLiteDatabase.insert("Recording", null, contentValues);
        if (recordingID != -1) {
            ArrayList<Note> notes = recording.getmNotes();
            for (int i = 0; i < notes.size(); i++) {
                contentValues.clear();
                contentValues.put("instrument_id", notes.get(i).getmResourceID());
                contentValues.put("delay_time", notes.get(i).getmTimeFromStart());
                long noteID = mSQLiteDatabase.insert("Notes", null, contentValues);
                contentValues.clear();
                contentValues.put("recording_id", recordingID);
                contentValues.put("note_id", noteID);
                mSQLiteDatabase.insert("RecordingNotes", null, contentValues);
            }
        }
        return recordingID != -1;
    }

    /**
     * Retrieves all the recordings of the current user.
     * @param recordingCreator user's username
     * @return list of recordingCreator's saved recordings.
     */
    public List<Recording> getMyRecordings(String recordingCreator) {
        List<Recording> toReturn = new ArrayList<>();
        String selectAll = "SELECT * FROM Recording WHERE creator = '" + recordingCreator + "'";
        Cursor c = mSQLiteDatabase.rawQuery(selectAll, null);
        if (c != null) {
            Log.d(TAG, "getMyRecordings: first cursor isn't null");
            while (c.moveToNext()) {
                Log.d(TAG, "getMyRecordings: Looking at a row");
                long recordingID = c.getInt(c.getColumnIndex("id"));
                String currentName = c.getString(c.getColumnIndex("name"));
                String currentCreator = c.getString(c.getColumnIndex("creator"));
                int currentIsShared = c.getInt(c.getColumnIndex("shared"));
                String currentTimeCreated = c.getString(c.getColumnIndex("time_created"));
                DateFormat format = SimpleDateFormat.getDateTimeInstance();
                Date date = new Date();
                try {
                    date = format.parse(currentTimeCreated);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Recording current = new Recording(currentName, currentCreator, date, currentIsShared != 0);
                String selectNotes = "SELECT * FROM Notes JOIN RecordingNotes ON Notes.id = RecordingNotes.note_id WHERE recording_id = " + recordingID;
                Cursor c2 = mSQLiteDatabase.rawQuery(selectNotes, null);
                if (c2 != null) {
                    while (c2.moveToNext()) {
                        Note n = new Note(Long.valueOf(c2.getInt(c2.getColumnIndex("delay_time"))), c2.getInt(c2.getColumnIndex("instrument_id")));
                        current.addNote(n);
                    }
                    c2.close();
                }
                toReturn.add(current);
            }
            c.close();
        }
        return toReturn;
    }

    class RecordingDBHelper extends SQLiteOpenHelper {

        private final String CREATE_RECORDING_SQL;
        private final String DROP_RECORDING_SQL;
        private final String CREATE_NOTES_SQL;
        private final String DROP_NOTES_SQL;
        private final String CREATE_RECORDING_NOTES_SQL;
        private final String DROP_RECORDING_NOTES_SQL;

        public RecordingDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
            CREATE_RECORDING_SQL = context.getString(R.string.CREATE_RECORDING_SQL);
            DROP_RECORDING_SQL = context.getString(R.string.DROP_RECORDING_SQL);
            CREATE_NOTES_SQL = context.getString(R.string.CREATE_NOTES_SQL);
            DROP_NOTES_SQL = context.getString(R.string.DROP_NOTES_SQL);
            CREATE_RECORDING_NOTES_SQL = context.getString(R.string.CREATE_RECORDING_NOTES_SQL);
            DROP_RECORDING_NOTES_SQL = context.getString(R.string.DROP_RECORDING_NOTES_SQL);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_RECORDING_SQL);
            db.execSQL(CREATE_NOTES_SQL);
            db.execSQL(CREATE_RECORDING_NOTES_SQL);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DROP_RECORDING_SQL);
            db.execSQL(DROP_NOTES_SQL);
            db.execSQL(DROP_RECORDING_NOTES_SQL);
            onCreate(db);
        }
    }

}
