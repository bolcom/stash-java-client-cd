package com.bol.cd.stash

import feign.Feign
import spock.lang.IgnoreIf
import spock.lang.Specification

/**
 * Integration test specification to try stuff out against a real Stash instance.
 * <p>
 * When the following environment variables are set, this test will run:
 * <ul>
 *     <li>STASH_URL</li>
 *     <li>STASH_USER</li>
 *     <li>STASH_PASSWORD</li>
 * </ul>
 */
@IgnoreIf({ !System.getenv('STASH_URL') || !System.getenv('STASH_USER') || !System.getenv('STASH_PASSWORD') })
class StashClientIntegrationSpec extends Specification {

    def stashUrl = System.getenv('STASH_URL')
    def stashUser = System.getenv('STASH_USER')
    def stashPassword = System.getenv('STASH_PASSWORD')

    def "Just try stuff out"() {
//        def api = Feign.builder()
//            .target(StashApi, stashUrl)
//        Feign.create(StashApi, stashUrl)
//        def client = new FeignClientProvider(stashUrl)
//        client.withFakeSSL()
//        client.authenticated(stashUser, stashPassword)
//        def api = client.createClient()

        expect:
        true
    }
}
