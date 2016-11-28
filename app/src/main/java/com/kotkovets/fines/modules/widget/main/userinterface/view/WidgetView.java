package com.kotkovets.fines.modules.widget.main.userinterface.view;

import android.widget.RemoteViews;

import com.kotkovets.fines.R;
import com.kotkovets.fines.common.display.FineDisplayItem;
import com.kotkovets.fines.common.display.DriverDisplayItem;

/**
 * Created by igork on 4/23/16.
 */
public class WidgetView implements WidgetViewInterface {
    RemoteViews remoteViews;

    public WidgetView(RemoteViews remoteViews) {
        this.remoteViews = remoteViews;
    }

    public RemoteViews getRemoteViews() {
        return remoteViews;
    }

    /*
    ** WidgetViewInterface
    */

    @Override
    public void displayUser(DriverDisplayItem driverDisplayItem) {
        if (driverDisplayItem != null) {
            remoteViews.setTextViewText(R.id.patronymicTextView, driverDisplayItem.getPatronymic());
            remoteViews.setTextViewText(R.id.surnameTextView, driverDisplayItem.getSurname());
            remoteViews.setTextViewText(R.id.nameTextView, driverDisplayItem.getName());
            remoteViews.setTextViewText(R.id.seriesTextView, driverDisplayItem.getSeries());
            remoteViews.setTextViewText(R.id.numberTextView, driverDisplayItem.getNumber());
        }
    }

    @Override
    public void displayResponse(FineDisplayItem responseData) {
        if (responseData != null) {
            String resultString;
            if (responseData.getMessage().length() != 0) {
                resultString = responseData.getMessage();
            }
            else {
                resultString = responseData.getError();
            }

            remoteViews.setTextViewText(R.id.resultTextView, resultString);
            remoteViews.setTextViewText(R.id.syncDateTextView, responseData.getSyncDate());
        }
    }
}
