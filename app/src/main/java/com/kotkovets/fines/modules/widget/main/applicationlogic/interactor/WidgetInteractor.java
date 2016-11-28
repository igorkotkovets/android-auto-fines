package com.kotkovets.fines.modules.widget.main.applicationlogic.interactor;

import android.content.Context;
import android.util.Log;

import com.kotkovets.fines.common.model.Fine;
import com.kotkovets.fines.common.model.Driver;
import com.kotkovets.fines.modules.widget.common.WidgetConfigStore;
import com.kotkovets.fines.modules.widget.common.WidgetConfigStoreInterface;
import com.kotkovets.fines.services.network.NetworkService;
import com.kotkovets.fines.services.network.NetworkServiceInputInterface;
import com.kotkovets.fines.services.network.NetworkServiceOutputInterface;
import com.kotkovets.fines.services.store.DatabaseServiceInterface;
import com.kotkovets.fines.services.store.realm.RealmDatabase;

import java.util.Date;

/**
 * Created by igork on 4/23/16.
 */
public class WidgetInteractor implements WidgetInteractorInputInterface, NetworkServiceOutputInterface {
    NetworkServiceInputInterface network;
    DatabaseServiceInterface database;
    WidgetInteractorOutputInterface output;
    WidgetConfigStoreInterface widgetConfigStore;
    int widgetId;
    private static final String TAG = "WidgetInteractor";

    public WidgetInteractor(Context context, int widgetId, WidgetInteractorOutputInterface output) {
        this.database = new RealmDatabase();
        this.network = new NetworkService();
        this.widgetConfigStore = new WidgetConfigStore(context);
        this.output = output;
        this.widgetId = widgetId;
    }

    /**
    ** WidgetInteractorInputInterface
    */

    @Override
    public void loadUser() {
        Driver driver = getUser();

        if (output != null) {
            output.onLoadUser(driver);
        }
    }

    @Override
    public void loadResponse() {
        Driver driver = getUser();
        Fine fine = null;

        if (driver != null) {
            fine = database.loadFine(driver.getUniqueKey());
        }

        if (output != null) {
            output.onLoadResponse(fine);
        }
    }

    @Override
    public void checkOnRemote() {
        Driver driver = getUser();
        if (driver != null) {
            network.performCheck(driver.getName(), driver.getSurname(), driver.getPatronymic(), driver.getSeries(), driver.getNumber(), this);
        }
    }

    /**
    * NetworkServiceOutputInterface
     */

    @Override
    public void onError(int statusCode, String error, Date date) {
        loadUser();
    }

    @Override
    public void onResult(int statusCode, String message, Date date) {
        Driver driver = getUser();
        Fine fine = new Fine(message, date);
        database.saveFine(fine, driver.getUniqueKey());
        loadResponse();
    }

    /**
     * Private
     */

    private Driver getUser() {
        int userId = widgetConfigStore.loadWidgetUserId(widgetId);
        Log.i(TAG, "getUser with Id " + userId + " for widget with Id " + widgetId);
        Driver driver = database.loadDriver(userId);
        return driver;
    }
}
