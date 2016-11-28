package com.kotkovets.fines.modules.list.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.kotkovets.fines.R;

/**
 * Created by igork on 4/24/16.
 */
public class UserRecycleViewHolder extends RecyclerView.ViewHolder implements ListViewCellInterface {
    public final View mView;
    public final TextView nameTextView;
    public final TextView seriesTextView;
    public int userUniqueId;
    public final TextView fineResultTextView;
    public final RelativeLayout progressBarContainer;
    public final Button deleteButton;



    public UserRecycleViewHolder(View view) {
        super(view);
        mView = view;
        nameTextView = (TextView) view.findViewById(R.id.nameTextView);
        seriesTextView = (TextView) view.findViewById(R.id.seriesTextView);
        fineResultTextView = (TextView) view.findViewById(R.id.fineTextView);
        progressBarContainer = (RelativeLayout) view.findViewById(R.id.progressBarContainer);
        SwipeLayout swipeLayout =  (SwipeLayout)view.findViewById(R.id.swipe_layout);
        deleteButton = (Button) view.findViewById(R.id.bt_delete);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //set show mode.
        swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);

        swipeLayout.addSwipeListener(new SwipeLayout.SwipeListener() {
            @Override
            public void onClose(SwipeLayout layout) {
                //when the SurfaceView totally cover the BottomView.
            }

            @Override
            public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {
                //you are swiping.
            }

            @Override
            public void onStartOpen(SwipeLayout layout) {

            }

            @Override
            public void onOpen(SwipeLayout layout) {
                //when the BottomView totally show.
            }

            @Override
            public void onStartClose(SwipeLayout layout) {

            }

            @Override
            public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {
                //when user's hand released.
            }
        });
    }

    @Override
    public String toString() {
        return super.toString() + " '" + nameTextView.getText() + "'";
    }



    /**
     * * ListViewCellInterface
     */

    @Override
    public void set(String name, String surname, String patronymic) {
        String fullName = "";
        if (name != null) {
            fullName = fullName + name + " ";
        }
        if (surname != null) {
            fullName = fullName + surname + " ";
        }
        if (patronymic != null) {
            fullName = fullName + patronymic;
        }

        nameTextView.setText(fullName);
    }

    @Override
    public void set(String series, String number) {
        String fullData = "";
        if (series != null) {
            fullData = fullData + series + " ";
        }
        if (number != null) {
            fullData = fullData + number;
        }

        seriesTextView.setText(fullData);
    }

    @Override
    public void setLoadingAnimationEnabled(boolean animating) {
        if (animating) {
            fineResultTextView.setVisibility(View.GONE);
            progressBarContainer.setVisibility(View.VISIBLE);
        }
        else {
            fineResultTextView.setVisibility(View.VISIBLE);
            progressBarContainer.setVisibility(View.GONE);
        }
    }

    @Override
    public void setFineResult(String text) {
        fineResultTextView.setText(text);
    }

    @Override
    public void setUpdateDate(String text) {

    }
}
