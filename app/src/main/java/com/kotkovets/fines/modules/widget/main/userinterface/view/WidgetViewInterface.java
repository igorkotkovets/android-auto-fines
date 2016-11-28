package com.kotkovets.fines.modules.widget.main.userinterface.view;

import android.widget.RemoteViews;

import com.kotkovets.fines.common.display.FineDisplayItem;
import com.kotkovets.fines.common.display.DriverDisplayItem;

/**
 * Created by igork on 4/23/16.
 */
public interface WidgetViewInterface {
    RemoteViews getRemoteViews();
    void displayUser(DriverDisplayItem driverDisplayItem);
    void displayResponse(FineDisplayItem responseData);
}
