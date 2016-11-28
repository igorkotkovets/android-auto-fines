package com.kotkovets.fines.common.model;

import java.util.Date;

/**
 * Created by igork on 4/5/16.
 */
public class Fine {
    private String message;
    private int uniqueKey;
    private Date date;

    public Fine(String message, Date date, int uniqueKey) {
        this.message = message;
        this.date = date;
        this.uniqueKey = uniqueKey;
    }

    public Fine(String message, Date date) {
        this.message = message;
        this.date = date;
        this.uniqueKey = hashCode(message, date);
    }

    public String getMessage() {
        return message;
    }

    public int getUniqueKey() {
        return uniqueKey;
    }

    public Date getDate() {
        return date;
    }

    private int hashCode(String message, Date date) {
        int messageHash = (message != null)?message.hashCode():0;
        int dateHash = (date != null)?date.hashCode():0;

        return messageHash+dateHash;
    }
}
