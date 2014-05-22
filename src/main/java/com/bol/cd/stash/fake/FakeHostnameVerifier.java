package com.bol.cd.stash.fake;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FakeHostnameVerifier implements HostnameVerifier {

    private final static Logger log = LoggerFactory.getLogger(FakeHostnameVerifier.class);

    public FakeHostnameVerifier() {
        log.warn("You are using a FakeHostnameVerifier, this is not recommended for production usage! Please use a valid verifier instead!");
    }

    public boolean verify(String s, SSLSession sslSession) {
        return true;
    }
}
