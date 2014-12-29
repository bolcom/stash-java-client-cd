package com.bol.cd.stash.model.ssh

import com.bol.cd.stash.model.Project
import com.bol.cd.stash.model.ProjectPermission
import spock.lang.Specification

class ProjectAccessKeySpec extends Specification {

    def 'Equal is based on key, project and permission'() {
        when:
        def accessKey1 = createAccessKey('1', 'myProject', ProjectPermission.PROJECT_READ)
        def accessKey2 = createAccessKey('1', 'myProject', ProjectPermission.PROJECT_READ)

        then:
        accessKey1 == accessKey2

        when:
        accessKey1 = createAccessKey('1', 'myProject', ProjectPermission.PROJECT_READ)
        accessKey2 = createAccessKey('2', 'myProject', ProjectPermission.PROJECT_READ)

        then:
        accessKey1 != accessKey2
    }

    private ProjectAccessKey createAccessKey(String keyText, String projectKey, ProjectPermission permission) {
        def accessKey = new ProjectAccessKey()
        def key = new SshKey()
        key.text = keyText
        accessKey.key = key
        def project = new Project()
        project.key = projectKey
        accessKey.project = project
        accessKey.permission = permission
        return accessKey
    }
}
