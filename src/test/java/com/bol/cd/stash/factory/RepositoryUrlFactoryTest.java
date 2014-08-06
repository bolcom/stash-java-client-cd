package com.bol.cd.stash.factory;

import com.bol.cd.stash.model.Repository;
import org.junit.Assert;
import org.junit.Test;

public class RepositoryUrlFactoryTest {

    @Test(expected = IllegalArgumentException.class)
    public void cantCreateStashRepositoryForGitLabUrl() {
        RepositoryUrlFactory.buildRepository("git@git.dev.bol.com:dpi/grimm.git");
    }

    @Test
    public void shouldSupportSshUrl() {
        Repository gitRepository = RepositoryUrlFactory.buildRepository("ssh://git@stash.dev.bol.com/tpt/tpt-service.git");
        Assert.assertNotNull(gitRepository);
        Assert.assertEquals("tpt", gitRepository.getProject().getKey());
        Assert.assertEquals("tpt-service", gitRepository.getSlug());
    }

    @Test
    public void canCreateStashRepositoryForHttpStashUrlWithPort() {
        Repository gitRepository = RepositoryUrlFactory.buildRepository("http://admin@stash.dev.bol.com:7990/scm/tpt/testrepository.git");
        Assert.assertNotNull(gitRepository);
        Assert.assertEquals("tpt", gitRepository.getProject().getKey());
        Assert.assertEquals("testrepository", gitRepository.getSlug());


    }

    @Test
    public void canCreateStashRepositoryForHttpsStashUrlWithPort() {
        Repository gitRepository = RepositoryUrlFactory.buildRepository("https://admin@stash.dev.bol.com:7990/scm/tpt/testrepository.git");
        Assert.assertNotNull(gitRepository);
        Assert.assertEquals("tpt", gitRepository.getProject().getKey());
        Assert.assertEquals("testrepository", gitRepository.getSlug());


    }

    @Test
    public void canCreateStashRepositoryForSSHStashUrlWithoutPort() {
        Repository gitRepository = RepositoryUrlFactory.buildRepository("ssh://admin@stash.dev.bol.com/tpt/testrepository.git");
        Assert.assertNotNull(gitRepository);
        Assert.assertEquals("tpt", gitRepository.getProject().getKey());
        Assert.assertEquals("testrepository", gitRepository.getSlug());


    }

}
