package com.bol.cd.stash.model.ssh

import com.bol.cd.stash.model.Project
import com.bol.cd.stash.model.Repository
import com.bol.cd.stash.model.RepositoryPermission
import spock.lang.Specification

class RepositoryAccessKeySpec extends Specification {

    def 'Equal is based on key, repository and permission'() {
        when:
        RepositoryAccessKey accessKey1 = createAccessKey('1', 'myRepo', 'myProject', RepositoryPermission.REPO_READ)
        RepositoryAccessKey accessKey2 = createAccessKey('1', 'myRepo', 'myProject', RepositoryPermission.REPO_READ)

        then:
        accessKey1 == accessKey2

        when:
        accessKey1 = createAccessKey('1', 'myRepo', 'myProject', RepositoryPermission.REPO_READ)
        accessKey2 = createAccessKey('2', 'myRepo', 'myProject', RepositoryPermission.REPO_READ)

        then:
        accessKey1 != accessKey2
    }

    private RepositoryAccessKey createAccessKey(String keyText, String repoSlug, String projectKey, RepositoryPermission permission) {
        def accessKey = new RepositoryAccessKey()
        def key = new SshKey()
        key.text = keyText
        accessKey.key = key
        def repo = new Repository()
        repo.slug = repoSlug
        def project = new Project()
        project.key = projectKey
        repo.project = project
        accessKey.repository = repo
        accessKey.permission = permission
        return accessKey
    }
}
