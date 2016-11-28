package com.kotkovets.fines;

import android.test.InstrumentationTestCase;

import com.kotkovets.fines.services.network.FinesRequestParametersBuilder;
import com.kotkovets.fines.services.network.core.Request;
import com.kotkovets.fines.services.network.core.RequestData;
import com.kotkovets.fines.services.network.core.Response;

/**
 * Created by igork on 5/10/16.
 */
public class FinesRequestTest extends InstrumentationTestCase {
    public void testThatRequestDoesWork() throws Exception {
        // given
        FinesRequestParametersBuilder builder = new FinesRequestParametersBuilder();
        RequestData parameters = builder.setName("Александр").setSurname("Сергеевич").setPatronymic("Пушкин").setSeries("ААА").setNumber("123456").build();
        Request request = new Request(getInstrumentation().getContext(), parameters);

        // when
        Response response = request.send();

        // then
        assertEquals("The request is invalid", response.getCode(), 0);
    }
}
