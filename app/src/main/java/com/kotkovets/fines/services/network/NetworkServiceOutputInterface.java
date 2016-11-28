package com.kotkovets.fines.services.network;

import java.util.Date;

/**
 * Created by igork on 2/7/16.
 */
public interface NetworkServiceOutputInterface {
    void onError(int statusCode, String error, Date date);

    void onResult(int statusCode, String message, Date date);
}
