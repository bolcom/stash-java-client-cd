package com.bol.cd.stash.client;

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

    @Test
    public void testCreateBranchFromMaster() {

        new Expectations() {
            {
                // @formatter:off
                httpExecutor.execute((HttpRequest) any, (HttpResponseProcessor) any); result = null;
                // @formatter:on
            }
        };
        Optional<Branch> branch = extendedStashClient.getBranchStashClient().getRepositoryBranch(projectKey, repositorySlug, "ABC-123");
        Assert.assertTrue(branch.isPresent());

    }
}
