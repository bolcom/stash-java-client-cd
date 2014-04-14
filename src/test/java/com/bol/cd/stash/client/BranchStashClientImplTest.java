package com.bol.cd.stash.client;

import static com.bol.cd.stash.client.JsonUtil.getJsonFromResources;

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

public class BranchStashClientImplTest {

    private String projectKey = "TPT";
    private String repositorySlug = "testrepository";

    @Mocked
    private HttpExecutor httpExecutor;

    private ExtendedStashClient extendedStashClient;

    @Before
    public void before() {
        extendedStashClient = new ExtendedStashClientImpl(httpExecutor);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testGetRepositoryBranch() {

        new Expectations() {
            {
                JsonElement jsonElement = getJsonFromResources("/com/bol/cd/stash/client/branches.json");
                // @formatter:off
                httpExecutor.execute((HttpRequest) any, (HttpResponseProcessor<JsonElement>) any); result = jsonElement;
                // @formatter:on
            }
        };
        Optional<Branch> branch = extendedStashClient.getBranchStashClient().getRepositoryBranch(projectKey, repositorySlug, "ABC-123");
        Assert.assertTrue(branch.isPresent());
        Assert.assertEquals("ABC-123", branch.get().getDisplayId());
    }

}
