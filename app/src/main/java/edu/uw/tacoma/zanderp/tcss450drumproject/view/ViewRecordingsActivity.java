package edu.uw.tacoma.zanderp.tcss450drumproject.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import edu.uw.tacoma.zanderp.tcss450drumproject.R;
import edu.uw.tacoma.zanderp.tcss450drumproject.data.RecordingDB;
import edu.uw.tacoma.zanderp.tcss450drumproject.drums.Recording;

public class ViewRecordingsActivity extends AppCompatActivity implements RecordingListFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recordings);

        if (savedInstanceState == null || getSupportFragmentManager().findFragmentById(R.id.list) == null) {
            RecordingListFragment recordingListFragment = new RecordingListFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, recordingListFragment)
                    .commit();
        }

        RecordingDB db = new RecordingDB(this);
        String name = db.getMyRecordings(this);
        Toast.makeText(this, name, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onListFragmentInteraction(Recording item) {
        //Create new RecordingDetailFragment and start it. Also add stuff so it knows which recording
        //to display. TODO!!!

    }


}
