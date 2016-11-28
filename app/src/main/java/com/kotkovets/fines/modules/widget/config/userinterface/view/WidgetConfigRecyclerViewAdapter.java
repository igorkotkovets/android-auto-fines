package com.kotkovets.fines.modules.widget.config.userinterface.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kotkovets.fines.R;
import com.kotkovets.fines.common.display.DriverDisplayItem;
import com.kotkovets.fines.modules.list.view.OnUsersListFragmentInteractionListener;
import com.kotkovets.fines.modules.list.view.UserRecycleViewHolder;

import java.util.List;

/**
 * Created by igork on 4/24/16.
 */
public class WidgetConfigRecyclerViewAdapter extends RecyclerView.Adapter<UserRecycleViewHolder> {
    private final List<DriverDisplayItem> mValues;
    private final OnUsersListFragmentInteractionListener mListener;

    public WidgetConfigRecyclerViewAdapter(List<DriverDisplayItem> items, OnUsersListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public UserRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_useritem, parent, false);
        return new UserRecycleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final UserRecycleViewHolder holder, int position) {
        holder.userUniqueId = mValues.get(position).getUserId();
        holder.set(mValues.get(position).getName(), mValues.get(position).getSurname(), mValues.get(position).getPatronymic());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentItemTap(holder.userUniqueId);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }
}
