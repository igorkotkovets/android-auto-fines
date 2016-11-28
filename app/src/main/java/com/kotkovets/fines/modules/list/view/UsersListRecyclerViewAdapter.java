package com.kotkovets.fines.modules.list.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kotkovets.fines.R;
import com.kotkovets.fines.common.display.DisplayDriverInfo;

import java.util.List;


public class UsersListRecyclerViewAdapter extends RecyclerView.Adapter<UserRecycleViewHolder> {
    private final List<DisplayDriverInfo> mValues;
    private final OnUsersListFragmentInteractionListener mListener;

    public UsersListRecyclerViewAdapter(List<DisplayDriverInfo> items, OnUsersListFragmentInteractionListener listener) {
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
        DisplayDriverInfo item = mValues.get(position);
        holder.userUniqueId = item.getUserId();
        holder.set(item.getName(), item.getSurname(), item.getPatronymic());
        holder.set(item.getSeries(), item.getNumber());
        holder.setLoadingAnimationEnabled(item.getProgressBarVisible());
        holder.setFineResult(item.getFineResult());

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != mListener) {
                    mListener.onListFragmentItemDelete(holder.userUniqueId);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }
}
