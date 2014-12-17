package com.bol.cd.stash

import feign.Client
import feign.Logger
import spock.lang.Specification

class StashClientSpec extends Specification {

    def "Can instantiate the StashClient directly"() {
        def client = new StashClient('url')

        expect:
        client
    }

    def "Can create a client"() {
        def client = new StashClient('url')

        expect:
        client.createClient() instanceof StashApi
    }

    def "Can create a client with authentication credentials"() {
        def client = new StashClient('url')
        client.authenticated('user', 'password')

        expect:
        client.createClient() instanceof StashApi
    }

    def "Can create a client with fake SSL support"() {
        def client = new StashClient('url')
        client.withFakeSSL()

        expect:
        client.createClient() instanceof StashApi
    }

    def "Can create a client with a Logger"() {
        def client = new StashClient('url')
        client.withLogger(new Logger.JavaLogger())

        expect:
        client.createClient() instanceof StashApi
    }

    def "Can create a client with a Logger and specify log level"() {
        def client = new StashClient('url')
        client.withLogger(new Logger.JavaLogger(), Logger.Level.FULL)

        expect:
        client.createClient() instanceof StashApi
    }

    def "Can create a client with a custom Client"() {
        def client = new StashClient('url')
        client.withClient(new Client.Default(null, null))

        expect:
        client.createClient() instanceof StashApi
    }
}
