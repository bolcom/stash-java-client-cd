package com.bol.cd.stash;

import feign.Feign;
import feign.RequestInterceptor;
import feign.auth.BasicAuthRequestInterceptor;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.jaxrs.JAXRSModule;

import java.util.Arrays;

import com.bol.cd.stash.model.JsonApplicationMediaTypeInterceptor;
import com.bol.cd.stash.request.DeleteBranch;

public class StashClient {


    public static StashApi createClient() {
        return Feign.builder().contract(new JAXRSModule.JAXRSContract()).decoder(new JacksonDecoder()).encoder(new JacksonEncoder())
                .requestInterceptors(getRequestInterceptors()).target(StashApi.class, "http://localhost:7990");
    }

    private static Iterable<RequestInterceptor> getRequestInterceptors() {
        return Arrays.asList(new BasicAuthRequestInterceptor("admin", "password"), new JsonApplicationMediaTypeInterceptor());
    }

}
