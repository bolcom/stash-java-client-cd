package com.bol.cd.stash;

import com.bol.cd.stash.fake.FakeHostnameVerifier;
import com.bol.cd.stash.fake.FakeSSLSocketFactory;
import com.bol.cd.stash.internal.JsonApplicationMediaTypeInterceptor;
import com.bol.cd.stash.internal.StashErrorDecoder;
import dagger.Lazy;
import feign.Client;
import feign.Feign;
import feign.RequestInterceptor;
import feign.auth.BasicAuthRequestInterceptor;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.jaxrs.JAXRSModule;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class StashClient {
    private String username;
    private String password;
    private boolean requiresAuthentication = false;
    private final String url;

    private Client client;

    public static StashApi create(final String url) {
        return new StashClient(url).createClient();
    }

    public static StashApi create(final String url, final String username, final String password) {
        return new StashClient(url).authenticated(username, password).createClient();
    }

    public static StashApi create(Client client, final String url, final String username, final String password) {
        return new StashClient(url).withClient(client).authenticated(username, password).createClient();
    }

    public static StashApi createFakeSSL(final String url, final String username, final String password) {
        return new StashClient(url).authenticated(username, password).withFakeSSL().createClient();
    }

    private StashClient(final String url) {
        Objects.requireNonNull(url, "url must be provided");
        this.url = url.replaceAll("/\\z", "");
    }

    public StashClient withClient(Client client) {
        Objects.requireNonNull(client, "username must be provided");
        this.client = client;
        return this;
    }

    public StashClient withFakeSSL() {
        Lazy<SSLSocketFactory> sslContextFactory = new Lazy<SSLSocketFactory>() {

            private FakeSSLSocketFactory sslSocketFactory = new FakeSSLSocketFactory();

            @Override
            public SSLSocketFactory get() {
                return sslSocketFactory;
            }
        };

        Lazy<HostnameVerifier> hostnameVerifier = new Lazy<HostnameVerifier>() {

            private FakeHostnameVerifier fakeHostnameVerifier = new FakeHostnameVerifier();

            @Override
            public HostnameVerifier get() {
                return fakeHostnameVerifier;
            }
        };

        client = new Client.Default(sslContextFactory, hostnameVerifier);
        return this;
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
        Feign.Builder builder = Feign.builder();
        if (client != null) {
            builder.client(client);
        }
        //@formatter:off
        builder.contract(new JAXRSModule.JAXRSContract())
                .decoder(new JacksonDecoder())
                .encoder(new JacksonEncoder())
                .errorDecoder(new StashErrorDecoder())
                .requestInterceptors(getRequestInterceptors())
                .target(StashApi.class, url);
        //@formatter:on
        return builder.target(StashApi.class, url);
    }

    private Iterable<RequestInterceptor> getRequestInterceptors() {
        final List<RequestInterceptor> base = new ArrayList<RequestInterceptor>(Arrays.asList(new JsonApplicationMediaTypeInterceptor()));
        if (requiresAuthentication) {
            base.add(new BasicAuthRequestInterceptor(username, password));
        }
        return base;
    }
}
