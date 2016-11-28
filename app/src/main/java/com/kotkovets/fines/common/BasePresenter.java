package com.kotkovets.fines.common;

/**
 * Created by igork on 2/14/16.
 */
public abstract class BasePresenter<View> {
    private View view;

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }
}
