package com.kotkovets.fines.modules.widget.common;

/**
 * Created by igork on 4/25/16.
 */
public interface WidgetConfigStoreInterface {
    void saveWidgetUserId(int widgetId, int userId);
    int loadWidgetUserId(int widgetId);
    void resetWidgetUserId(int widgetId);
}
