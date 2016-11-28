package com.kotkovets.fines.services.network;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by igork on 2/7/16.
 */
public class NetworkService implements NetworkServiceInputInterface {

    @Override
    public void performCheck(String name, String surname, String patronymic, String series, String number, NetworkServiceOutputInterface listener) {
        JSONObject object = new JSONObject();
        String param1 = patronymic + " " + name + " " + surname;
        try {
            object.put("Param1", param1);
            object.put("GuidControl", "2091");
            object.put("Param2", series);
            object.put("number", number);
        } catch (JSONException e) {
            Log.e("Check fines request", "Ivalid request parameters");
            e.printStackTrace();
        }

        Headers headers = new Headers.Builder()
                .add("Content-Type", "Type:application/json; charset=UTF-8")
                .build();
        try {
            Request request = new Request.Builder()
                    .url("http://mvd.gov.by/Ajax.asmx/GetExt")
                    .post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), object.toString()))
                    .headers(headers)
                    .build();
            Response response = new OkHttpClient().newCall(request).execute();
            String stringResponse = response.body().string();

            Log.i("String response: ", stringResponse);
        } catch (IOException e) {

        }
    }

}
