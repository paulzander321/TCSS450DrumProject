package edu.uw.tacoma.zanderp.tcss450drumproject.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import edu.uw.tacoma.zanderp.tcss450drumproject.Authenticate.SignInActivity;
import edu.uw.tacoma.zanderp.tcss450drumproject.R;
import edu.uw.tacoma.zanderp.tcss450drumproject.data.RecordingDB;
import edu.uw.tacoma.zanderp.tcss450drumproject.drums.Recording;

public class ViewRecordingsActivity extends AppCompatActivity implements RecordingListFragment.OnListFragmentInteractionListener,
                                    RecordingDetailFragment.OnFragmentInteractionListener {

    private boolean mIsPlaying;
    private Recording mCurrentRecording;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recordings);
        mIsPlaying = false;
        if (savedInstanceState == null || getSupportFragmentManager().findFragmentById(R.id.list) == null) {
            RecordingListFragment recordingListFragment = new RecordingListFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, recordingListFragment)
                    .commit();
        }
    }

    @Override
    public void onListFragmentInteraction(Recording item) {
        RecordingDetailFragment recordingDetailFragment = new RecordingDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(RecordingDetailFragment.RECORDING_SELECTED, item);
        recordingDetailFragment.setArguments(args);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, recordingDetailFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.logout) {
            SharedPreferences sharedPreferences =
                    getSharedPreferences(getString(R.string.LOGIN_PREFS), Context.MODE_PRIVATE);
            sharedPreferences.edit().putBoolean(getString(R.string.LOGGEDIN), false)
                    .apply();
            Intent i = new Intent(this, SignInActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
            Toast.makeText(this,"Logout Successful!", Toast.LENGTH_LONG)
                    .show();
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Recording recording) {
        if (!mIsPlaying) {
            mIsPlaying = true;
            mCurrentRecording = recording;
            mCurrentRecording.playRecording(this);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mIsPlaying = false;
                }
            }, recording.getTotalTime());
        }
    }
}
