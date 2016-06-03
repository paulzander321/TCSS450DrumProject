package edu.uw.tacoma.zanderp.tcss450drumproject.Drums;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ToggleButton;

import edu.uw.tacoma.zanderp.tcss450drumproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SaveRecordingDialogFragment extends DialogFragment {

    public SaveRecordingDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Save Recording");

        // Get the layout inflater
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.fragment_save_recording_dialog, null);

        ToggleButton toggle = (ToggleButton) dialogView.findViewById(R.id.share_checkbox);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mSharing = isChecked;
            }
        });

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(dialogView)
                // Add action buttons
                .setPositiveButton(R.string.save_confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        EditText recordingNameEditText = (EditText) dialogView.findViewById(R.id.recording_name);
                        String recordingName = recordingNameEditText.getText().toString();
                        mListener.onDialogPositiveClick(SaveRecordingDialogFragment.this, mSharing, recordingName);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onDialogNegativeClick(SaveRecordingDialogFragment.this);
                    }
                });
        return builder.create();
    }

    //The listener which will handle dialog interactions.
    SaveRecordingDialogListener mListener;

    //Sends this based on the user's input whether recording will be shared or not.
    boolean mSharing;

    /* The activity that uses the fragment must implement this interface in order to receive
     * event callbacks. Each method passes the DialogFragment in case the host needs to query it. */
    public interface SaveRecordingDialogListener {
        void onDialogPositiveClick(DialogFragment dialog, boolean sharing, String recordingName);
        void onDialogNegativeClick(DialogFragment dialog);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (SaveRecordingDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement SaveRecordingDialogListener");
        }
    }

}
