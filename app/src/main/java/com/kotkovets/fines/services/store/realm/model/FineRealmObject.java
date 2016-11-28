package com.kotkovets.fines.services.store.realm.model;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by igork on 4/2/16.
 */
public class FineRealmObject extends RealmObject {
    @PrimaryKey
    private int userUniqueKey;

    private int code;
    private String message;
    private Date date;
    private int uniqueKey;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(int uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    public int getUserUniqueKey() {
        return userUniqueKey;
    }

    public void setUserUniqueKey(int userUniqueKey) {
        this.userUniqueKey = userUniqueKey;
    }
}
