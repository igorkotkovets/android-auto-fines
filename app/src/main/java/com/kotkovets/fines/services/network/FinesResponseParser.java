package com.kotkovets.fines.services.network;

import com.kotkovets.fines.common.model.Fine;
import com.kotkovets.fines.services.network.core.ParserError;
import com.kotkovets.fines.services.network.core.ParserResult;
import com.kotkovets.fines.services.network.core.ResponseParserInterface;

import org.apache.commons.lang3.StringEscapeUtils;

import java.util.Date;

/**
 * Created by igork on 5/9/16.
 */
public class FinesResponseParser implements ResponseParserInterface {

    /**
     * ResponseParserInterface
     */

    @Override
    public ParserResult parse(int statusCode, byte[] body) {
        ParserResult response;
        String messageString = null;

        if (statusCode == 200 && body != null) {
            messageString = new String(body);
            messageString = StringEscapeUtils.unescapeJava(messageString);
            // remove HTML tags (ex. <br>****</br>, <h2></h2>)
            messageString = messageString.replaceAll("\\<(.*)?\\>(.*)\\</\\1\\>", "$2");
            // remove double quotas at the begin and end "some string"
            messageString = messageString.replaceAll("^\"|\"$", "");

            Fine fine = new Fine(messageString, new Date());
            response = new ParserResult(ParserError.NO_ERROR, null, fine);
        }
        else {
            response = new ParserResult(ParserError.UNEXPECTED_DATA, "Server return unexpected data", null);
        }

        return response;
    }
}
