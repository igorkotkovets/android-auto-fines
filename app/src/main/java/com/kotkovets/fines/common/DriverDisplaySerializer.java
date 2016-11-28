package com.kotkovets.fines.common;

import android.content.Context;

import com.kotkovets.fines.common.display.DriverDisplayItem;
import com.kotkovets.fines.common.model.Driver;

/**
 * Created by igork on 4/23/16.
 */
public class DriverDisplaySerializer implements DisplayObjectsSerializer<Driver, DriverDisplayItem> {
    @Override
    public DriverDisplayItem create(Context context, Driver object) {
        if (object == null) {
            return new DriverDisplayItem("", "", "", "", "", Driver.USER_ID_INVALID);
        }

        return new DriverDisplayItem(object.getName(), object.getSurname(), object.getPatronymic(),
                object.getSeries(), object.getNumber(), object.getUniqueKey());
    }
}
