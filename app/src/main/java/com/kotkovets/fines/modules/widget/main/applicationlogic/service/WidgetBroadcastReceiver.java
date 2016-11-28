package com.kotkovets.fines.modules.widget.main.applicationlogic.service;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.kotkovets.fines.R;
import com.kotkovets.fines.modules.list.view.DriversListActivity;
import com.kotkovets.fines.modules.widget.main.moduleinterface.WidgetModuleInterface;
import com.kotkovets.fines.modules.widget.main.userinterface.presentor.WidgetPresentor;
import com.kotkovets.fines.modules.widget.main.userinterface.view.WidgetView;

import java.util.Arrays;

/**
 * Created by igork on 2/16/16.
 */
public class WidgetBroadcastReceiver extends AppWidgetProvider implements WidgetPresentorListener {
    private static final String TAG = "WidgetBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        Log.i(TAG, "onReceive");

        int widgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, -1);
        Log.i(TAG, "onReceive widgetId " + widgetId);

        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.app_widget);
        rv.setOnClickPendingIntent(R.id.application_name, WidgetIntentBuilder.buildLaunchApplicationPendingIntent(context, widgetId));
        rv.setOnClickPendingIntent(R.id.ll_widget_container, WidgetIntentBuilder.buildRefreshWidgetPendingIntent(context, widgetId));
        WidgetView view = new WidgetView(rv);

        WidgetModuleInterface widgetDataPresentor = new WidgetPresentor(context, view, widgetId, this);

        if (intent.getAction().equals(WidgetServiceContract.ACTION_REFRESH_WITH_REMOTE)) {
            Log.i(TAG, "onReceive event to check with remote ");
            widgetDataPresentor.onActionCheck();
        }
        else if (intent.getAction().equals(WidgetServiceContract.ACTION_LOAD_DATA)) {
            Log.i(TAG, "onReceive event to load data");
            widgetDataPresentor.onActionLoadData();
        }
        else if (intent.getAction().equals(AppWidgetManager.ACTION_APPWIDGET_UPDATE)) {
            Log.i(TAG, "onReceive event to update widget");
            widgetDataPresentor.onActionLoadData();
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        Log.i(TAG, "onDeleted widget with ids:" + Arrays.toString(appWidgetIds));

        super.onDeleted(context, appWidgetIds);
    }


    @Override
    public void onFinishUpdate(Context context, RemoteViews remoteViews) {
        Log.i(TAG, "onFinishUpdate");
        WidgetBroadcastReceiver.pushWidgetUpdate(context, remoteViews);
    }

    public static void pushWidgetUpdate(Context context, RemoteViews remoteViews) {
        Log.i(TAG, "pushWidgetUpdate");
        ComponentName myWidget = new ComponentName(context, WidgetBroadcastReceiver.class);
        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        manager.updateAppWidget(myWidget, remoteViews);
    }

    /**
     * Private
     */


    private static class WidgetIntentBuilder {
        public static PendingIntent buildRefreshWidgetPendingIntent(Context context, int appWidgetId) {
            // initiate widget update request
            Intent intent = new Intent(context, WidgetBroadcastReceiver.class);
            intent.setAction(WidgetServiceContract.ACTION_REFRESH_WITH_REMOTE);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            return PendingIntent.getBroadcast(context, 0, intent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
        }

        public static PendingIntent buildLaunchApplicationPendingIntent(Context context, int appWidgetId) {
            PendingIntent pendingIntent = null;
            try {
                Intent intent = new Intent("android.intent.action.MAIN");
                intent.addCategory("android.intent.category.LAUNCHER");

                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
                intent.setComponent(new ComponentName(context.getPackageName(),
                        DriversListActivity.class.getName()));
                pendingIntent = PendingIntent.getActivity(
                        context, 0, intent, 0);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(context.getApplicationContext(),
                        "There was a problem loading the application: ",
                        Toast.LENGTH_SHORT).show();
            }

            return pendingIntent;
        }
    }
}

