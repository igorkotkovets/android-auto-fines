package com.kotkovets.fines.modules.launcher.assembly;

import com.kotkovets.fines.modules.launcher.view.LauncherActivity;

import dagger.Component;

/**
 * Created by igork on 7/22/16.
 */
@Component(modules = {LaunchModule.class})
public interface LaunchComponent {
    void inject(LauncherActivity activity);
}
