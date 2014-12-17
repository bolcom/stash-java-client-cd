package com.bol.cd.stash.logging;

import feign.Logger;
import org.slf4j.LoggerFactory;

public class Slf4JLogger extends Logger {
    final org.slf4j.Logger logger = LoggerFactory.getLogger(Slf4JLogger.class);

    @Override
    protected void log(String configKey, String format, Object... args) {
        logger.trace(String.format(methodTag(configKey) + format, args));
    }

    static String methodTag(String configKey) {
        return new StringBuilder().append('[').append(configKey.substring(0, configKey.indexOf('('))).append("] ").toString();
    }
}
