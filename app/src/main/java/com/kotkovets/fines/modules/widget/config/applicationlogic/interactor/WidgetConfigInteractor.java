package com.kotkovets.fines.modules.widget.config.applicationlogic.interactor;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.util.Log;

import com.kotkovets.fines.common.model.Driver;
import com.kotkovets.fines.modules.widget.common.WidgetConfigStore;
import com.kotkovets.fines.modules.widget.common.WidgetConfigStoreInterface;
import com.kotkovets.fines.modules.widget.main.applicationlogic.service.WidgetBroadcastReceiver;
import com.kotkovets.fines.services.store.DatabaseServiceInterface;
import com.kotkovets.fines.services.store.realm.RealmDatabase;

import java.util.List;

/**
 * Created by igork on 4/24/16.
 */
public class WidgetConfigInteractor implements WidgetConfigInteractorInput {
    WidgetConfigInteractorOutput output;
    DatabaseServiceInterface database;
    WidgetConfigStoreInterface widgetConfigStore;
    Activity context;
    private static final String TAG = "WidgetConfigInteractor";


    public WidgetConfigInteractor(Activity context, WidgetConfigInteractorOutput output) {
        this.context = context;
        this.output = output;
        this.database = new RealmDatabase();
        this.widgetConfigStore = new WidgetConfigStore(context);
    }

    /**
     * WidgetConfigInteractorInput
     */

    @Override
    public void loadUsers() {
        List<Driver> drivers = database.loadAllDrivers();
        if (output != null) {
            output.onLoadUsers(drivers);
        }
    }

    @Override
    public void updateWidgetWithUserId(int userId, int widgetId) {
        widgetConfigStore.saveWidgetUserId(widgetId, userId);

        Intent intent = new Intent(context, WidgetBroadcastReceiver.class);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        // Use an array and EXTRA_APPWIDGET_IDS instead of AppWidgetManager.EXTRA_APPWIDGET_ID,
        // since it seems the onUpdate() is only fired on that:
//        AppWidgetManager widgetManager = AppWidgetManager.getInstance(context);

//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
//            widgetManager.notifyAppWidgetViewDataChanged(ids, android.R.id.list);

        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
        Log.i(TAG, "updateWidgetWithUserId with widgetId "+widgetId+" userId "+userId);
        context.sendBroadcast(intent);
    }
}
