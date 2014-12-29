package com.bol.cd.stash.model.ssh

import spock.lang.Specification

class SshKeySpec extends Specification {

    def 'Equal is based on key text only'() {
        def key1 = new SshKey()
        key1.id = 'someIdForKey1'
        key1.label = 'someLabelForKey1'
        def key2 = new SshKey()
        key2.id = 'someIdForKey2'
        key2.label = 'someLabelForKey2'

        when: 'key texts are the same'
        key1.text = '1'
        key2.text = '1'

        then: 'keys are equal'
        key1 == key2

        when: 'key texts are different'
        key1.text = '1'
        key2.text = '2'

        then: 'keys are not equal'
        key1 != key2
    }
}
