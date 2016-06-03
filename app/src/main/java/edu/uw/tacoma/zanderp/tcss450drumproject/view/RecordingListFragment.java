package edu.uw.tacoma.zanderp.tcss450drumproject.view;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
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

import org.json.JSONException;
import org.json.JSONObject;

import edu.uw.tacoma.zanderp.tcss450drumproject.R;
import edu.uw.tacoma.zanderp.tcss450drumproject.data.RecordingDB;
import edu.uw.tacoma.zanderp.tcss450drumproject.Drums.Recording;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
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
    private static final String ALL_RECORDINGS_URL = "http://cssgate.insttech.washington.edu/~zanderp/browseRecordings.php";
    private static final String MY_RECORDINGS_URL = "http://cssgate.insttech.washington.edu/~zanderp/viewMyRecordings.php";
    private String mURL;
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

        //Check if displaying all shared recordings or just my recordings.
        mURL = MY_RECORDINGS_URL;
        if (getActivity().getIntent().hasExtra("displayAll") && getActivity().getIntent().getExtras().getBoolean("displayAll")) {
             mURL = ALL_RECORDINGS_URL;
        }

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
            DownloadRecordingsTask task = new DownloadRecordingsTask();
            task.execute(mURL + "?username=" + getActivity().getSharedPreferences(getString(R.string.LOGIN_PREFS),
                    Context.MODE_PRIVATE).getString(getString(R.string.USERNAME), "0"));
        }
        else {
            Toast.makeText(view.getContext(),
                    "No network connection available. Cannot display recordings",
                    Toast.LENGTH_SHORT) .show();
        }
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

    private class DownloadRecordingsTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            Log.d(TAG, "doInBackground: " + urls[0]);
            String response = "";
            HttpURLConnection urlConnection = null;
            for (String url : urls) {
                try {
                    URL urlObject = new URL(url);
                    urlConnection = (HttpURLConnection) urlObject.openConnection();
                    InputStream content = urlConnection.getInputStream();
                    BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
                    String s;
                    while ((s = buffer.readLine()) != null) {
                        response += s;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    response = "Unable to add recording, Reason: "
                            + e.getMessage();
                } finally {
                    if (urlConnection != null)
                        urlConnection.disconnect();
                }
            }
            return response;
        }

        @Override
        protected void onPostExecute(String result) {

            mRecordingList = new ArrayList<>();

            try {
                JSONObject jsonObject = new JSONObject(result);
                String status = (String) jsonObject.get("result");
                if (status.equals("success")) {
                    Log.d(TAG, "onPostExecute: the array of data " + jsonObject.getString("data"));

                    Recording.parseCourseJSON(jsonObject.getString("data"), mRecordingList);

                    //Everything good, show list of recordings.
                    if (!mRecordingList.isEmpty()) {
                        mRecyclerView.setAdapter(new MyRecordingRecyclerViewAdapter(mRecordingList, mListener));
                    }

                    if (mRecordingDB == null) {
                        mRecordingDB = new RecordingDB(getActivity());
                    }

                    mRecordingDB.closeDB();


                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Failed to retrieve recordings: " + jsonObject.get("message"),
                            Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                Toast.makeText(getActivity().getApplicationContext(), "Error: Failed because " + e.getMessage(),
                        Toast.LENGTH_LONG).show();
            }


        }
    }
}
