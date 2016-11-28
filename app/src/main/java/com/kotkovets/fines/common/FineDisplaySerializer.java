package com.kotkovets.fines.common;

import android.content.Context;

import com.kotkovets.fines.R;
import com.kotkovets.fines.common.display.FineDisplayItem;
import com.kotkovets.fines.common.model.Fine;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by igork on 4/23/16.
 */
public class FineDisplaySerializer implements DisplayObjectsSerializer<Fine, FineDisplayItem> {

    @Override
    public FineDisplayItem create(Context context, Fine object) {
        String stringDate = "--:--";
        String message = context.getString(R.string.response_no_any_error_result);
        String error = context.getString(R.string.response_no_any_message_result);

        if (object != null) {
            Date date = object.getDate();
            if (date != null) {
                SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm");
                stringDate = formatter.format(date);
            }

            String messageString = object.getMessage();
            if (messageString != null) {
                message = messageString;
            }
        }

        FineDisplayItem responseData = new FineDisplayItem(message, error, stringDate);
        return responseData;
    }
}
