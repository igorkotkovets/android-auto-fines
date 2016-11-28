package com.kotkovets.fines.common;

import android.content.Context;

import com.kotkovets.fines.common.display.DisplayDriverInfo;
import com.kotkovets.fines.common.model.Driver;
import com.kotkovets.fines.common.model.DriverInfo;
import com.kotkovets.fines.common.model.Fine;

/**
 * Created by igork on 5/27/16.
 */
public class DriverInfoDisplaySerializer implements DisplayObjectsSerializer<DriverInfo, DisplayDriverInfo> {

    /**
     * DisplayObjectsSerializer
     */

    @Override
    public DisplayDriverInfo create(Context context, DriverInfo object) {
        String name = "";
        String surname = "";
        String patronymic = "";
        String seriers = "";
        String number = "";
        int userId = Driver.USER_ID_INVALID;
        Driver driver = null;
        Fine fine = null;
        String fineResult = "";

        if (object != null) {
            driver = object.getDriver();
            fine = object.getFine();
        }

        if (driver != null) {
            name = driver.getName();
            surname = driver.getSurname();
            patronymic = driver.getPatronymic();
            seriers = driver.getSeries();
            number = driver.getNumber();
            userId = driver.getUniqueKey();
        }

        if (fine != null) {
            fineResult = fine.getMessage();
        }

        DisplayDriverInfo displayObject = new DisplayDriverInfo();
        displayObject.setUserId(userId);
        displayObject.setName(name);
        displayObject.setSurname(surname);
        displayObject.setPatronymic(patronymic);
        displayObject.setSeries(seriers);
        displayObject.setNumber(number);
        displayObject.setFineResult(fineResult);

        return displayObject;
    }
}
