package com.kotkovets.fines.modules.userguide.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.kotkovets.fines.modules.userguide.entity.UserGuideSlide;

import java.util.List;

/**
 * Created by igork on 9/25/16.
 */
public class UserGuidePagerAdapter extends FragmentStatePagerAdapter {
    private List<UserGuideSlide> mSlides;

    public UserGuidePagerAdapter(FragmentManager fm, List<UserGuideSlide> slides) {
        super(fm);
        mSlides = slides;
    }

    @Override
    public Fragment getItem(int position) {
        UserGuideSlide slide = mSlides.get(position);
        return GuideItemFragment.newInstance(slide.getTitle(), slide.getSourceURL());
    }

    @Override
    public int getCount() {
        return mSlides.size();
    }
}
