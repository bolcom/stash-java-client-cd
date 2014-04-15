package com.bol.cd.stash.client;

import static com.bol.cd.stash.client.JsonUtil.getJsonFromResources;

import java.net.MalformedURLException;
import java.net.URL;
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
import com.atlassian.stash.rest.client.httpclient.HttpClientConfig;
import com.atlassian.stash.rest.client.httpclient.HttpClientHttpExecutor;
import com.google.gson.JsonElement;

@SuppressWarnings("unchecked")
public class BranchStashClientImplTest {

    private String projectKey = "TPT";
    private String repositorySlug = "testrepository";

    @Mocked
    private HttpExecutor httpExecutor;

    private ExtendedStashClient extendedStashClient;
    private BranchStashClientImpl branchStashClient;

    @Before
    public void before() throws MalformedURLException {
        // HttpClientConfig httpClientConfig = new HttpClientConfig(new URL("http://localhost:7990/"), "admin", "password");
        // HttpClientHttpExecutor httpClientHttpExecutor = new HttpClientHttpExecutor(httpClientConfig);
        // extendedStashClient = new ExtendedStashClientImpl(httpClientHttpExecutor);
        extendedStashClient = new ExtendedStashClientImpl(httpExecutor);
        branchStashClient = extendedStashClient.getBranchStashClient();
    }

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
        Optional<Branch> branch = branchStashClient.getRepositoryBranchBId(projectKey, repositorySlug, "feature/ABC-555");
        Assert.assertTrue(branch.isPresent());
        Assert.assertEquals("feature/ABC-555", branch.get().getDisplayId());
    }

    @Test
    public void testCreateBranchFromMaster() {
        new Expectations() {
            {
                JsonElement featureBranch = getJsonFromResources("/com/bol/cd/stash/client/branch_feature.json");
                // @formatter:off
                httpExecutor.execute((HttpRequest) any, (HttpResponseProcessor<JsonElement>) any); result = featureBranch;
                // @formatter:on
            }
        };
        Branch branch = branchStashClient.createBranchFromMaster(projectKey, repositorySlug, "feature/ABC-999");
        Assert.assertEquals("feature/ABC-999", branch.getDisplayId());
    }

    @Test
    public void testCreateBranchFromDefaultBranch() {
        new Expectations() {
            {
                JsonElement defaultBranch = getJsonFromResources("/com/bol/cd/stash/client/branch.json");
                JsonElement featureBranch = getJsonFromResources("/com/bol/cd/stash/client/branch_feature.json");
                // @formatter:off
                httpExecutor.execute((HttpRequest) any, (HttpResponseProcessor<JsonElement>) any); result = defaultBranch;
                httpExecutor.execute((HttpRequest) any, (HttpResponseProcessor<JsonElement>) any); result = featureBranch;
                // @formatter:on
            }
        };
        Branch branch = branchStashClient.createBranchFromDefaultBranch(projectKey, repositorySlug, "feature/ABC-999");
        Assert.assertEquals("feature/ABC-999", branch.getDisplayId());
    }

}
