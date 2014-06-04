package com.bol.cd.stash.factory;

import com.bol.cd.stash.model.Project;
import com.bol.cd.stash.model.Repository;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RepositoryUrlFactory {
    private final static Pattern gitUrlPattern = Pattern.compile(".*/scm/(.*)/(.*).git");
    private RepositoryUrlFactory() {
    }

    public static Repository buildRepository(String gitUrl) {
        Matcher matcher = gitUrlPattern.matcher(gitUrl);
        if (matcher.matches()) {
            String projectKey = matcher.group(1);
            String repositorySlug = matcher.group(2);
            Repository result = new Repository();
            Project p = new Project();
            p.setKey(projectKey);
            result.setProject(p);
            result.setSlug(repositorySlug);
            return result;
        } else {
            throw new IllegalArgumentException("Invalid url '" + gitUrl + "'.");
        }
    }
}
