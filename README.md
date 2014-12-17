Java Client for Atlassian Stash
===============================

A *not complete* Java client for Atlassian Stash. Currently we extend it with
only what we need, but we happily apply your pull-request :D

Basic usage
-----------

In its simplest form you do:

```
StashApi api = StashClient.create("url");
Page<Project> pagedProjects = api.getProjects();
List<Project> projects = pagesProjects.values();
```

Each call on `api` results in a HTTP(s) call.
The StashClient is a direct wrapper around the Stash APIs, without any beautification' whatsoever.

Follow the code-completion of your favorite IDE, it should speak for itself :)

Atlassian Stash API docs
------------------------
- [Stash Java API docs](https://developer.atlassian.com/stash/docs/latest/reference/java-api.html)
- [Stash REST API docs](https://developer.atlassian.com/static/rest/stash/latest/stash-rest.html)
