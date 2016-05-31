package edu.uw.tacoma.zanderp.tcss450drumproject.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.uw.tacoma.zanderp.tcss450drumproject.R;
import edu.uw.tacoma.zanderp.tcss450drumproject.Drums.Recording;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Recording} and makes a call to the
 * specified {@link RecordingListFragment.OnListFragmentInteractionListener}.
 */
public class MyRecordingRecyclerViewAdapter extends RecyclerView.Adapter<MyRecordingRecyclerViewAdapter.ViewHolder> {

    private final List<Recording> mValues;
    private final RecordingListFragment.OnListFragmentInteractionListener mListener;

    public MyRecordingRecyclerViewAdapter(List<Recording> items, RecordingListFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_recording, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getmName());
        holder.mContentView.setText(mValues.get(position).getmCreationTime().toString());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public Recording mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
