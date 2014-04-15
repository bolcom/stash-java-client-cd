package com.bol.cd.stash;

import org.junit.Test;

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
}
