package com.kotkovets.fines.modules.userguide.assembly;

import com.kotkovets.fines.modules.userguide.presenter.UserGuidePresenter;
import com.kotkovets.fines.modules.userguide.view.UserGuideFragment;
import com.kotkovets.fines.modules.userguide.view.UserGuideViewOutput;

import dagger.Module;
import dagger.Provides;

/**
 * Created by igork on 9/25/16.
 */
@Module
public class UserGuideModule {
    UserGuideFragment mView;

    public UserGuideModule(UserGuideFragment mViewInput) {
        this.mView = mViewInput;
    }

    @Provides
    UserGuideViewOutput providesPresenter() {
        UserGuidePresenter presenter = new UserGuidePresenter();
        presenter.view = mView;
        mView.output = presenter;
        return presenter;
    }
}
