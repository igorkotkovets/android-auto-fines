package com.kotkovets.fines.modules.userguide.assembly;

import com.kotkovets.fines.modules.userguide.view.UserGuideFragment;

import dagger.Component;

/**
 * Created by igork on 9/25/16.
 */

@Component(modules = {UserGuideModule.class})
public interface UserGuideComponent {
    void inject(UserGuideFragment fragment);
}
