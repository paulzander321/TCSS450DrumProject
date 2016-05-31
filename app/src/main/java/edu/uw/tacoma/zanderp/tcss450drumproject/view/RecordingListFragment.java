package edu.uw.tacoma.zanderp.tcss450drumproject.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import edu.uw.tacoma.zanderp.tcss450drumproject.R;
import edu.uw.tacoma.zanderp.tcss450drumproject.data.RecordingDB;
import edu.uw.tacoma.zanderp.tcss450drumproject.Drums.Recording;

import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class RecordingListFragment extends Fragment {

    private int mColumnCount = 1;
    private RecyclerView mRecyclerView;
    private List<Recording> mRecordingList;
    private RecordingDB mRecordingDB;
    private static final String RECORDINGS_URL = "http://cssgate.insttech.washington.edu/~zanderp/blah.php";
    private static final String TAG = "RECORDING_LIST_FRAGMENT";

    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RecordingListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recording_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            mRecyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                mRecyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
        }

        ConnectivityManager connMgr = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            //TODO Get the recordings from the database which are either mine or are set to be shared.
//            DownloadCoursesTask task = new DownloadCoursesTask();
//            task.execute(new String[]{RECORDINGS_URL});
        }
        else {
            Toast.makeText(view.getContext(),
                    "No network connection available. Cannot display courses",
                    Toast.LENGTH_SHORT) .show();
        }

        if (mRecordingDB == null) {
            Log.d(TAG, "onCreateView: recording database initialized");
            mRecordingDB = new RecordingDB(getActivity());
        }
        if (mRecordingList == null) {
            Log.d(TAG, "onCreateView: recording list initialized");
            SharedPreferences sharedPreferences = getActivity()
                    .getSharedPreferences(getString(R.string.LOGIN_PREFS), Context.MODE_PRIVATE);
            mRecordingList = mRecordingDB.getMyRecordings(sharedPreferences.getString(getString(R.string.USERNAME), ""));
        }
        if (mRecordingList.isEmpty()) {
            Toast.makeText(getActivity(), "You currently have no recordings!", Toast.LENGTH_LONG).show();
        }
        Log.d(TAG, "onCreateView: " + mRecordingList.size());
        mRecyclerView.setAdapter(new MyRecordingRecyclerViewAdapter(mRecordingList, mListener));
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(Recording item);
    }
}
