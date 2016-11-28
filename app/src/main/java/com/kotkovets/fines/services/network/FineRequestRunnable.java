package com.kotkovets.fines.services.network;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.kotkovets.fines.services.network.core.Request;
import com.kotkovets.fines.services.network.core.RequestData;
import com.kotkovets.fines.services.network.core.Response;

/**
 * Created by igork on 5/23/16.
 */
public class FineRequestRunnable implements Runnable {
    private final String TAG = "RequestRunnable";
    private Output output;
    private RequestData parameters;
    private Context context;

    public FineRequestRunnable(Context context, RequestData params) {
        this.context = context;
        this.parameters = params;
    }

    public void setOutput(Output output) {
        this.output = output;
    }

    public interface Output {
        void didStartRequest(int tag);
        void didFinishRequest(int tag, Response response);
    }

    @Override
    public void run() {
        notifyOutputRequestStarted();
        Request request = new Request(context, parameters);
        Response response = request.send();
        notifyOutputRequestFinished(response);
    }

    private void notifyOutputRequestStarted() {
        Log.i(TAG, "notifyOutputRequestStarted");
        if (output != null) {
            Handler mainHandler = new Handler(Looper.getMainLooper());
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    output.didStartRequest(parameters.getTag());
                }
            });
        }
    }

    private void notifyOutputRequestFinished(Response response) {
        final Response finalResponse = response;
        Log.i(TAG, "notifyOutputRequestFinished");
        if (output != null) {
            Handler mainHandler = new Handler(Looper.getMainLooper());
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    output.didFinishRequest(parameters.getTag(), finalResponse);
                }
            });
        }
    }
}
