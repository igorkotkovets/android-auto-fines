package com.kotkovets.fines.modules.userguide.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kotkovets.fines.R;
import com.kotkovets.fines.modules.userguide.assembly.DaggerUserGuideComponent;
import com.kotkovets.fines.modules.userguide.assembly.UserGuideModule;
import com.kotkovets.fines.modules.userguide.entity.UserGuideSlide;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by igork on 8/28/16.
 */
public class UserGuideFragment extends DialogFragment implements UserGuideViewInput {

    @Inject
    public UserGuideViewOutput output;

    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerUserGuideComponent.builder().userGuideModule(new UserGuideModule(this)).build().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_guide, container);
        mViewPager = (ViewPager) view.findViewById(R.id.pager);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        output.didTriggerOnCreateView();
    }

    /*
    UserGuideViewInput interface
     */

    @Override
    public void displayGuideScreens(List<UserGuideSlide> slides) {
        mPagerAdapter = new UserGuidePagerAdapter(getChildFragmentManager(), slides);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setPageTransformer(true, new DepthPageTransformer());
    }
}
