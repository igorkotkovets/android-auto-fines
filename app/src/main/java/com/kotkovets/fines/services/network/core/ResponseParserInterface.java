package com.kotkovets.fines.services.network.core;

/**
 * Created by igork on 5/8/16.
 */
public interface ResponseParserInterface {
    ParserResult parse(int statusCode, byte[] body);
}
