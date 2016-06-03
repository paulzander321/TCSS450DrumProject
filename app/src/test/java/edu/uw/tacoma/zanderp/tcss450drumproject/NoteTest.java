package edu.uw.tacoma.zanderp.tcss450drumproject;

import junit.framework.TestCase;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import edu.uw.tacoma.zanderp.tcss450drumproject.Drums.Note;

/**
 * Tests the Note class and its constructor, compareTo, and getJSON.
 */
public class NoteTest extends TestCase {

    //Note to be used for test cases.
    private Note mNote;

    @Before
    public void setUp() {
        mNote = new Note(new Long(1000), R.raw.snare);
    }

    @Test
    public void testConstructor() {
        Note note = new Note(new Long(1000), R.raw.snare);
        assertNotNull(note);
    }

    @Test
    public void testCompareTo() {
        Note earlyNote = new Note(new Long(500), R.raw.snare);
        Note lateNote = new Note(new Long(1000), R.raw.snare);
        assertTrue("The early note is less than the later note", earlyNote.compareTo(lateNote) < 0);
    }

    @Test
    public void testGetNoteJSON() {
        JSONObject expected;
        try {
            expected = new JSONObject();
            expected.put("instrument_id", mNote.getmResourceID());
            expected.put("delay_time", mNote.getmTimeFromStart());
        } catch (JSONException e) {
            expected = new JSONObject();
            e.printStackTrace();
        }
        assertEquals(expected.toString(), mNote.getNoteJSON().toString());
    }
}
