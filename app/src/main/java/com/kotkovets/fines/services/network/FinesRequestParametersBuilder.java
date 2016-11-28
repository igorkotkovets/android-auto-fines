package com.kotkovets.fines.services.network;

import android.util.Log;

import com.kotkovets.fines.services.network.core.RequestData;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by igork on 5/9/16.
 */
public class FinesRequestParametersBuilder {
    private final static String TAG = "FinesRequestBuilder";
    private String name;
    private String surname;
    private String patronymic;
    private String series;
    private String number;
    private int tag;

    public RequestData build() {
        Map<String,String> headers =  new HashMap();
        headers.put("Content-Type", "application/json; charset=utf-8");

        JSONObject object = new JSONObject();
        String param1 = patronymic + " " + name + " " + surname;
        try {
            object.put("Param1", param1);
            object.put("GuidControl", "2091");
            object.put("Param2", series);
            object.put("Param3", number);
        } catch (JSONException e) {
            Log.e(TAG, "Ivalid request parameters");
            e.printStackTrace();
        }
        byte[] body = object.toString().getBytes();

        RequestData parameters = new RequestData("http://mvd.gov.by/Ajax.asmx/GetExt", "POST", headers, body, new FinesResponseParser(), tag);

        return parameters;
    }

    public FinesRequestParametersBuilder setTag(int tag) {
        this.tag = tag;
        return this;
    }

    public FinesRequestParametersBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public FinesRequestParametersBuilder setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public FinesRequestParametersBuilder setPatronymic(String patronymic) {
        this.patronymic = patronymic;
        return this;
    }

    public FinesRequestParametersBuilder setSeries(String series) {
        this.series = series;
        return this;
    }

    public FinesRequestParametersBuilder setNumber(String number) {
        this.number = number;
        return this;
    }
}
