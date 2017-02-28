package com.bol.cd.stash;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

import feign.Response;
import feign.Util;
import feign.codec.Decoder;

public class BinarySupportWrapperDecoder implements Decoder {
    
    private Decoder wrappedDecoder;
    
    public BinarySupportWrapperDecoder(Decoder wrappedDecoder) {
        this.wrappedDecoder = wrappedDecoder;
    }

    @Override
    // This implementation is largely copied from feign.codec.Decoder.Default, but now used as wrapper.
    public Object decode(Response response, Type type) throws IOException {
        if (response.status() == 404) {
            return Util.emptyValueOf(type);
        }
        if (response.body() == null) {
            return null;
        }
        if (InputStream.class.equals(type)) {
            return response.body().asInputStream();
        }
        if (byte[].class.equals(type)) {
            return Util.toByteArray(response.body().asInputStream());
        }
        return wrappedDecoder.decode(response, type);
    }
    
}