package com.kotkovets.fines.services.network.core;

/**
 * Created by igork on 5/8/16.
 */
public class ParserResult {
    private int code;
    private String error;
    private Object result;

    public ParserResult(int code, String error, Object result) {
        this.code = code;
        this.result = result;
        this.error = error;
    }

    public int getCode() {
        return code;
    }

    public Object getResult() {
        return result;
    }

    public String getError() {
        return error;
    }
}
