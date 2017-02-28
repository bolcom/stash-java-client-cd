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
    // This decoder implementation wraps another decoder and only triggers on the binary
    // return types of byte[] and InputStream.
    public Object decode(Response response, Type type) throws IOException {
        if (response.status() == 404 || response.body() == null) {
            // Use the default behavior of the wrapped decoder.
            return wrappedDecoder.decode(response, type);
        }
        if (InputStream.class.equals(type)) {
            // Return the actual input stream.
            return response.body().asInputStream();
        }
        if (byte[].class.equals(type)) {
            // Convert the input stream to a byte[].
            return Util.toByteArray(response.body().asInputStream());
        }
        // All other cases: use the default behavior of the wrapped decoder.
        return wrappedDecoder.decode(response, type);
    }
    
}