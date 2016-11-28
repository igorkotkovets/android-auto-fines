package com.kotkovets.fines.services.network.core;

/**
 * Created by igork on 5/9/16.
 */
public class Response {
    private int code;
    private String error;
    private Object data;

    public Response(int code, String error, Object data) {
        this.code = code;
        this.error = error;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getError() {
        return error;
    }

    public Object getData() {
        return data;
    }
}
