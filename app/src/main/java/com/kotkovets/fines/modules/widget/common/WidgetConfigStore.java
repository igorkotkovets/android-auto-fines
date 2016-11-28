package com.kotkovets.fines.modules.widget.common;

import android.content.Context;
import android.content.SharedPreferences;

import com.kotkovets.fines.common.model.Driver;

/**
 * Created by igork on 4/25/16.
 */
public class WidgetConfigStore implements WidgetConfigStoreInterface {
    Context context;
    SharedPreferences preferences;

    public WidgetConfigStore(Context context) {
        preferences = context.getSharedPreferences(WidgetConfigContract.PREFERENCES_FILE, Context.MODE_PRIVATE);

        this.context = context;

    }

    /**
     * WidgetConfigStoreInterface
     */

    public void saveWidgetUserId(int widgetId, int userId) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(Integer.toString(widgetId), userId);
        editor.commit();
    }

    public int loadWidgetUserId(int widgetId) {
        return preferences.getInt(Integer.toString(widgetId), Driver.USER_ID_INVALID);
    }

    @Override
    public void resetWidgetUserId(int widgetId) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(Integer.toString(widgetId));
        editor.commit();
    }
}
