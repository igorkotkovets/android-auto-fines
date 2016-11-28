package com.kotkovets.fines.modules.launcher.assembly;

import android.content.Context;

import com.kotkovets.fines.modules.launcher.presenter.LauncherModuleInput;
import com.kotkovets.fines.modules.launcher.presenter.LauncherPresenter;
import com.kotkovets.fines.modules.launcher.router.LauncherRouter;
import com.kotkovets.fines.modules.launcher.view.LauncherViewInput;

import dagger.Module;
import dagger.Provides;

/**
 * Created by igork on 7/22/16.
 */

@Module
public class LaunchModule {
    LauncherViewInput mView;

    public LaunchModule(LauncherViewInput view) {
        mView = view;
    }

    @Provides
    LauncherModuleInput providesLauncherPresenter() {

        LauncherRouter router =  new LauncherRouter();
        router.setContext((Context) mView);
        LauncherPresenter presenter = new LauncherPresenter();
        presenter.setView(mView);
        presenter.setRouter(router);
        return presenter;
    }

}
