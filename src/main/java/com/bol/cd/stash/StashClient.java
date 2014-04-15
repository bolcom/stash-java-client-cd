package com.bol.cd.stash;

import feign.Feign;
import feign.RequestInterceptor;
import feign.auth.BasicAuthRequestInterceptor;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.jaxrs.JAXRSModule;

import java.util.Arrays;

public class StashClient {

    private static String projectKey = "TPT";
    private static String repositorySlug = "testrepository";
    
    public static StashApi createClient() {
        return Feign.builder()
                .contract(new JAXRSModule.JAXRSContract())
                .decoder(new JacksonDecoder())
                .encoder(new JacksonEncoder())
                .requestInterceptors(getRequestInterceptors())
                .target(StashApi.class, "http://localhost:7990");
    }

    private static Iterable<RequestInterceptor> getRequestInterceptors() {
        return Arrays.asList(
                new BasicAuthRequestInterceptor("admin", "password"),
                new JsonApplicationMediaTypeInterceptor()
        );
    }
    
    public static void main(String[] args) {
        createClient().getRepositoryDefaultBranch(projectKey, repositorySlug);
    }
}
