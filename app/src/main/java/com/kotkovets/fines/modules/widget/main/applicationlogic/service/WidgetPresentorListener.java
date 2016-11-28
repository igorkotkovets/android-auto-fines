package com.kotkovets.fines.modules.widget.main.applicationlogic.service;

import android.content.Context;
import android.widget.RemoteViews;

/**
 * Created by igork on 3/4/16.
 */
public interface WidgetPresentorListener {
    void onFinishUpdate(Context context, RemoteViews remoteViews);
}
