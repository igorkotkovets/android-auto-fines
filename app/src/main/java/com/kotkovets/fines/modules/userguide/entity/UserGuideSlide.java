package com.kotkovets.fines.modules.userguide.entity;

/**
 * Created by igork on 9/25/16.
 */
public class UserGuideSlide {
    private String mTitle;
    private String mSourceURL;

    public UserGuideSlide(String mTitle, String mSourceURL) {
        this.mTitle = mTitle;
        this.mSourceURL = mSourceURL;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getSourceURL() {
        return mSourceURL;
    }
}
