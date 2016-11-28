package com.kotkovets.fines.services.network.core;

import android.content.Context;

import com.kotkovets.fines.R;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

/**
 * Created by igork on 5/9/16.
 */
public class Request {
    private Context context;
    private RequestData parameters;

    public Request(Context context, RequestData parameters) {
        this.context = context;
        this.parameters = parameters;
    }

    public Response send() {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse(parameters.getHeaders().get(RequestData.Contract.ContentType));
        RequestBody body = RequestBody.create(mediaType, parameters.getBody());
        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(parameters.getUrlString())
                .post(body)
                .headers(Headers.of(parameters.getHeaders()))
                .build();

        Response response;
        try {
            okhttp3.Response okhttpResponse = client.newCall(request).execute();
            response = createResponse(okhttpResponse.code(), okhttpResponse.body().bytes(), parameters.getParser());

        } catch (IOException e) {
            response = new Response(e.hashCode(), context.getString(R.string.network_data_loading_error), null);
        }

        return response;
    }

    private Response createResponse(int statusCode, byte[] responseBody, ResponseParserInterface parser) {
        if (parser != null) {
            ParserResult parserResult = parser.parse(statusCode, responseBody);
            return new Response(parserResult.getCode(), parserResult.getError(), parserResult.getResult());
        }

        return new Response(0, null, responseBody);
    }


}
