package edu.uw.tacoma.zanderp.tcss450drumproject.drums;

import android.support.v7.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;

public class Recording implements Serializable {

    private ArrayList<Note> mNotes;

    public Recording() {
        mNotes = new ArrayList<>();
    }

    public void addNote(Note addMe) {
        mNotes.add(addMe);
    }

    public void playRecording(final AppCompatActivity ctx) {
        for (int i = 0; i < mNotes.size(); i++) {
            mNotes.get(i).playNote(ctx);
        }
    }



}
