package com.kotkovets.fines.modules.launcher.presenter;

import com.kotkovets.fines.modules.launcher.router.LauncherRouterInput;
import com.kotkovets.fines.modules.launcher.view.LauncherViewInput;

/**
 * Created by igork on 6/19/16.
 */
public class LauncherPresenter implements LauncherModuleInput {
    LauncherRouterInput router;
    LauncherViewInput view;

    public LauncherRouterInput getRouter() {
        return router;
    }

    public void setRouter(LauncherRouterInput router) {
        this.router = router;
    }

    public LauncherViewInput getView() {
        return view;
    }

    public void setView(LauncherViewInput view) {
        this.view = view;
    }

    @Override
    public void onViewDidAppear() {
        router.presentList();
    }
}
