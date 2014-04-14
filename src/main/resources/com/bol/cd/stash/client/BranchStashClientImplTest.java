package com.bol.cd.stash.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;

import mockit.Expectations;
import mockit.Mocked;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.atlassian.stash.rest.client.api.entity.Branch;
import com.atlassian.stash.rest.client.core.http.HttpExecutor;
import com.atlassian.stash.rest.client.core.http.HttpRequest;
import com.atlassian.stash.rest.client.core.http.HttpResponseProcessor;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class BranchStashClientImplTest {

    private String projectKey = "TPT";
    private String repositorySlug = "testrepository";

    @Mocked
    private HttpExecutor httpExecutor;

    private ExtendedStashClient extendedStashClient;

    private JsonParser jsonParser;

    @Before
    public void before() {
        jsonParser = new JsonParser();
        extendedStashClient = new ExtendedStashClientImpl(httpExecutor);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testGetRepositoryBranch() {

        
        
        new Expectations() {
        
            {
                JsonElement jsonElement = getJsonTestObject("/com/bol/cd/stash/client/branches.json");
                // @formatter:off
                httpExecutor.execute((HttpRequest) any, (HttpResponseProcessor<JsonElement>) any); result = jsonElement;
                // @formatter:on
            }
        };
        Optional<Branch> branch = extendedStashClient.getBranchStashClient().getRepositoryBranch(projectKey, repositorySlug, "ABC-123");
        Assert.assertTrue(branch.isPresent());
        Assert.assertEquals("ABC-123", branch.get().getDisplayId());

    }

    private JsonElement getJsonTestObject(String name) {

        
        
        JsonElement result = null;
        try (InputStream is = this.getClass().getResourceAsStream(name); InputStreamReader isr = new InputStreamReader(is);) {

            result = jsonParser.parse(isr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;

    }
}
