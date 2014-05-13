package com.bol.cd.stash.internal;

import com.bol.cd.stash.StashClientException;
import com.bol.cd.stash.StashException;
import com.bol.cd.stash.StashServerException;
import feign.Response;
import org.junit.Test;

import java.nio.charset.Charset;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StashErrorDecoderTest {

    private StashErrorDecoder decoder = new StashErrorDecoder();

    @Test
    public void throwsStashClientErrorExceptionOnStatus4xxResponse() {
        String methodKey = null;
        int httpStatus = 400;
        Response response = getResponseWithStatus(httpStatus);

        Exception exception = decoder.decode(methodKey, response);

        assertTrue(exception instanceof StashClientException);
        StashException stashEx = (StashException) exception;
        assertEquals(httpStatus, stashEx.getStatus());
    }

    @Test
    public void throwsStashServerErrorExceptionOnStatus5xxResponse() {
        String methodKey = null;
        int httpStatus = 500;
        Response response = getResponseWithStatus(httpStatus);

        Exception exception = decoder.decode(methodKey, response);

        assertTrue(exception instanceof StashServerException);
        StashException stashEx = (StashException) exception;
        assertEquals(httpStatus, stashEx.getStatus());
    }

    private Response getResponseWithStatus(int httpStatus) {
        Map<String, Collection<String>> headers = new LinkedHashMap<>();
        String bodyText = null;
        Charset charset = null;
        return Response.create(
                httpStatus,
                "Some error description", headers, bodyText, charset
        );
    }
}
