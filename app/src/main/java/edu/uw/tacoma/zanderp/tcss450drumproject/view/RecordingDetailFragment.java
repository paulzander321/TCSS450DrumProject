package edu.uw.tacoma.zanderp.tcss450drumproject.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import edu.uw.tacoma.zanderp.tcss450drumproject.R;
import edu.uw.tacoma.zanderp.tcss450drumproject.Drums.Recording;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RecordingDetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RecordingDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecordingDetailFragment extends Fragment {

    public static String RECORDING_SELECTED = "recording_selected";

    private TextView mRecordingNameTextView;
    private TextView mRecordingCreatorTextView;
    private TextView mRecordingTotalLengthTextView;
    private TextView mSharedStatusTextView;
    private TextView mTimeCreatedTextView;
    private Button mPlayButton;

    private Recording mRecording;

    private OnFragmentInteractionListener mListener;

    public RecordingDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment RecordingDetailFragment.
     */
    public static RecordingDetailFragment newInstance() {
        return new RecordingDetailFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recording_detail, container, false);
        mRecordingNameTextView = (TextView) view.findViewById(R.id.recording_detail_name);
        mRecordingCreatorTextView = (TextView) view.findViewById(R.id.recording_detail_creator);
        mRecordingTotalLengthTextView = (TextView) view.findViewById(R.id.recording_detail_total_time);
        mSharedStatusTextView = (TextView) view.findViewById(R.id.recording_detail_shared);
        mTimeCreatedTextView = (TextView) view.findViewById(R.id.recording_detail_time_created);
        mPlayButton = (Button) view.findViewById(R.id.play_recording);
        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentInteraction(mRecording);
        }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    /**
     * Updates the view with the current recording's data.
     * @param recording the current recording
     */
    public void updateView(Recording recording) {
        mRecordingNameTextView.setText(recording.getmName());
        mRecordingCreatorTextView.setText(recording.getmCreator());
        mRecordingTotalLengthTextView.setText(recording.getTotalTime().toString());
        mSharedStatusTextView.setText(recording.ismIsShared() ? "Sharing ON" : "Sharing OFF");
        mTimeCreatedTextView.setText(recording.getmCreationTime().toString());
    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle args = getArguments();
        if (args != null) {
            mRecording = (Recording) args.getSerializable(RECORDING_SELECTED);
            updateView(mRecording);
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
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Recording recording);
    }
}
