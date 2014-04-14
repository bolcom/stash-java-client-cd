package com.bol.cd.stash.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class JsonUtil {

    private static JsonParser jsonParser = new JsonParser();

    public static JsonElement getJsonFromResources(String name) {
        JsonElement result = null;
        try (InputStream is = JsonUtil.class.getResourceAsStream(name); InputStreamReader isr = new InputStreamReader(is);) {
            result = jsonParser.parse(isr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;

    }
}
