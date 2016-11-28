package com.kotkovets.fines.modules.app;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.kotkovets.fines.common.model.Driver;
import com.kotkovets.fines.services.store.realm.RealmDatabase;

import io.fabric.sdk.android.Fabric;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by igork on 4/3/16.
 */
public class CurrentApplication extends Application {
    private static CurrentApplication applicationInstance;

    CurrentApplication getInstance() {
        return applicationInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());

        final RealmConfiguration config = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(config);

//        setupDatabaseByDebugRecords();
    }

    void setupDatabaseByDebugRecords() {
        Driver driver0 = new Driver("Иванов", "Иван", "Иванович", "ААА", "123456");
        Driver driver1 = new Driver("Иванова", "Мария", "Ивановна", "AAB", "234567");

        RealmDatabase database = new RealmDatabase();
        database.saveUser(driver0);
        database.saveUser(driver1);
    }


}
