package com.bol.cd.stash;

import com.bol.cd.stash.model.Page;
import com.bol.cd.stash.model.PullRequest;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class StashClientTest {

    @Test
    public void canCreateStashClient() {
        StashApi api = StashClient.create("url");
        assertNotNull(api);
    }

    @Test
    public void canCreateAuthenticatedStashClient() {
        StashApi api = StashClient.create("url", "username", "password");
        assertNotNull(api);
    }

    @Ignore("Find way to test this easily")
    @Test
    public void canGetAllPullRequests() {
        StashApi api = StashClient.create("url", "username", "password");
        Page<PullRequest> pagedPRs = api.getPullRequests("TST", "test");

        assertNotNull(pagedPRs);
        assertEquals(0, pagedPRs.getValues().size());
    }
}
