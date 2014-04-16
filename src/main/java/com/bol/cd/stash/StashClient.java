package com.bol.cd.stash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.bol.cd.stash.internal.JsonApplicationMediaTypeInterceptor;

import feign.Feign;
import feign.RequestInterceptor;
import feign.auth.BasicAuthRequestInterceptor;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.jaxrs.JAXRSModule;

public class StashClient {

    private String username;
    private String password;
    private boolean requiresAuthentication = false;
    private final String url;

    public static StashApi create(final String url) {
        return new StashClient(url).createClient();
    }

    public static StashApi create(final String url, final String username, final String password) {
        return new StashClient(url).authenticated(username, password).createClient();
    }

    private StashClient(final String url) {
        Objects.requireNonNull(url, "url must be provided");
        this.url = url;
    }

    public StashClient authenticated(final String username, final String password) {
        Objects.requireNonNull(username, "username must be provided");
        Objects.requireNonNull(password, "password must be provided");
        this.username = username;
        this.password = password;
        this.requiresAuthentication = true;
        return this;
    }

    private StashApi createClient() {
        return Feign.builder()
                .contract(new JAXRSModule.JAXRSContract())
                .decoder(new JacksonDecoder())
                .encoder(new JacksonEncoder())
                .requestInterceptors(getRequestInterceptors())
                .target(StashApi.class, url);
    }

    private Iterable<RequestInterceptor> getRequestInterceptors() {
        final List<RequestInterceptor> base = new ArrayList<RequestInterceptor>(Arrays.asList(
                new JsonApplicationMediaTypeInterceptor()
                ));
        if (requiresAuthentication) {
            base.add(new BasicAuthRequestInterceptor(username, password));
        }
        return base;
    }
}
