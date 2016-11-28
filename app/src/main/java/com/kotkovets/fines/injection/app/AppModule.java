package com.kotkovets.fines.injection.app;

import android.app.Application;

import com.kotkovets.fines.modules.app.CurrentApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by igork on 4/3/16.
 */
@Module
public class AppModule {
    final CurrentApplication _application;

    public AppModule(CurrentApplication application) {
        _application = application;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return _application;
    }
}
