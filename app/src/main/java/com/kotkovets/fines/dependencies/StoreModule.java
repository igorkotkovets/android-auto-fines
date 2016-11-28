package com.kotkovets.fines.dependencies;

import android.app.Application;

import com.kotkovets.fines.services.store.DatabaseServiceInterface;
import com.kotkovets.fines.services.store.realm.RealmDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by igork on 4/3/16.
 */
@Module
public class StoreModule {
    String _storeFileName;

    public StoreModule(String fileName) {
        _storeFileName = fileName;
    }

    @Provides
    @Singleton
    DatabaseServiceInterface providesDatabaseStore(Application application) {
        return new RealmDatabase();
    }

}
