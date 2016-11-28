package com.kotkovets.fines.modules.list.view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by igork on 6/6/16.
 */
public class RecyclerViewItemVerticalSpaceDecorator extends RecyclerView.ItemDecoration {
    private final int mVerticalSpaceHeight;

    public RecyclerViewItemVerticalSpaceDecorator(int mVerticalSpaceHeight) {
        this.mVerticalSpaceHeight = mVerticalSpaceHeight;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        outRect.bottom = mVerticalSpaceHeight;
    }
}
