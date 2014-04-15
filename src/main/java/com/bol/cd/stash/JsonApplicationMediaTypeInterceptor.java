package com.bol.cd.stash;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class JsonApplicationMediaTypeInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        template.header("Content-Type", "application/json");
    }
}
