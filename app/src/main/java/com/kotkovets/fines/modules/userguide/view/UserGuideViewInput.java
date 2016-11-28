package com.kotkovets.fines.modules.userguide.view;

import com.kotkovets.fines.modules.userguide.entity.UserGuideSlide;

import java.util.List;

/**
 * Created by igork on 9/25/16.
 */
public interface UserGuideViewInput {
    void displayGuideScreens(List<UserGuideSlide> slides);
}
