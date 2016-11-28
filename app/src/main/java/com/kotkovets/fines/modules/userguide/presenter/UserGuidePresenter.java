package com.kotkovets.fines.modules.userguide.presenter;

import com.kotkovets.fines.modules.userguide.entity.UserGuideSlide;
import com.kotkovets.fines.modules.userguide.view.UserGuideViewInput;
import com.kotkovets.fines.modules.userguide.view.UserGuideViewOutput;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by igork on 9/25/16.
 */
public class UserGuidePresenter implements UserGuideViewOutput {

    public UserGuideViewInput view;

    @Override
    public void didTriggerOnCreateView() {
        view.displayGuideScreens(getSlides());
    }

    private List<UserGuideSlide> getSlides() {
        ArrayList<UserGuideSlide> slides = new ArrayList();
        slides.add(new UserGuideSlide("1", "file:///android_asset/about-app.html"));
        slides.add(new UserGuideSlide("2", "file:///android_asset/add-driver.html"));
        slides.add(new UserGuideSlide("3", "file:///android_asset/get-fines.html"));
        slides.add(new UserGuideSlide("4", "file:///android_asset/to-do-features.html"));
        return slides;
    }
}
