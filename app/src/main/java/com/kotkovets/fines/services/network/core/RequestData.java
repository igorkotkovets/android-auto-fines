package com.kotkovets.fines.services.network.core;

import java.util.Map;

/**
 * Created by igork on 5/8/16.
 */
public class RequestData {
    private String httpMethod;
    private byte[] body;
    private Map<String, String> headers;
    private String urlString;
    private ResponseParserInterface parser;
    private int tag;

//    private


    public RequestData(String urlString, String httpMethod, Map<String, String> headers, byte[] body, ResponseParserInterface parser, int tag) {
        this.httpMethod = httpMethod;
        this.body = body;
        this.headers = headers;
        this.urlString = urlString;
        this.parser = parser;
        this.tag = tag;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public byte[] getBody() {
        return body;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getUrlString() {
        return urlString;
    }

    public ResponseParserInterface getParser() {
        return parser;
    }

    public int getTag() {
        return tag;
    }

    public static final class Contract {
        public static final String ContentType = "Content-Type";
    }
}
