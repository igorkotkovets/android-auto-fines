package com.kotkovets.fines.common.display;

/**
 * Created by igork on 3/5/16.
 */
public class FineDisplayItem {
    private String message;
    private String error;
    private String syncDate;

    public FineDisplayItem(String message, String error, String syncDate) {
        this.message = message;
        this.error = error;
        this.syncDate = syncDate;
    }

    public String getMessage() {
        return message;
    }

    public String getError() {
        return error;
    }

    public String getSyncDate() {
        return syncDate;
    }
}
