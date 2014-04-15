package com.bol.cd.stash;

import feign.Feign;
import feign.RequestInterceptor;
import feign.auth.BasicAuthRequestInterceptor;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.jaxrs.JAXRSModule;

import java.util.Arrays;

public class StashClient {

    public static StashApi createClient() {
        return Feign.builder()
                .contract(new JAXRSModule.JAXRSContract())
                .decoder(new JacksonDecoder())
                .encoder(new JacksonEncoder())
                .requestInterceptors(getRequestInterceptors())
                .target(StashApi.class, "http://192.168.56.101:7990");
    }

    private static Iterable<RequestInterceptor> getRequestInterceptors() {
        return Arrays.asList(
                new BasicAuthRequestInterceptor("test", "test123"),
                new JsonApplicationMediaTypeInterceptor()
        );
    }
}
