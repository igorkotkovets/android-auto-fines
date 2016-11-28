package com.kotkovets.fines.modules.widget.config.applicationlogic.router;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Intent;

/**
 * Created by igork on 4/24/16.
 */
public class WidgetConfigRouter implements WidgetConfigRouterInterface {
    Activity activity;
    int widgetId;

    public WidgetConfigRouter(Activity activity, int widgetId) {
        this.activity = activity;
        this.widgetId = widgetId;
    }

    /**
     * WidgetConfigRouterInterface
     */

    @Override
    public void finish() {
        Intent resultValue = new Intent();
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
        activity.setResult(activity.RESULT_OK, resultValue);

        activity.finish();
    }
}
