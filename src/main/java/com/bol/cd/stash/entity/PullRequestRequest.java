package com.bol.cd.stash.entity;

import com.atlassian.stash.rest.client.api.entity.Repository;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * https://developer.atlassian.com/static/rest/stash/2.12.1/stash-rest.html <br/>
 * /rest/api/1.0/projects/{projectKey}/repos/{repositorySlug}/pull-requests
 * 
 * <pre>
 * {
 *     "title": "Talking Nerdy",
 *     "description": "It’s a kludge, but put the tuple from the database in the cache.",
 *     "state": "OPEN",
 *     "open": true,
 *     "closed": false,
 *     "fromRef": {
 *         "id": "refs/heads/feature-ABC-123",
 *         "repository": {
 *             "slug": "my-repo",
 *             "name": null,
 *             "project": {
 *                 "key": "PRJ"
 *             }
 *         }
 *     },
 *     "toRef": {
 *         "id": "refs/heads/master",
 *         "repository": {
 *             "slug": "my-repo",
 *             "name": null,
 *             "project": {
 *                 "key": "PRJ"
 *             }
 *         }
 *     },
 *     "reviewers": [
 *         {
 *             "user": {
 *                 "name": "charlie"
 *             }
 *         }
 *     ]
 * }
 * </pre>
 */
public class PullRequestRequest {

    private final String title;
    private final String description;
    private final Ref fromRef;
    private final Ref toRef;
    private final String[] reviewers;

    public PullRequestRequest(String title, String description, Ref fromRef, Ref toRef, String[] reviewers) {
        this.title = title;
        this.description = description;
        this.fromRef = fromRef;
        this.toRef = toRef;
        this.reviewers = reviewers;
    }

    public JsonObject toJson() {

        JsonObject payload = new JsonObject();
        payload.addProperty("title", title);
        payload.addProperty("description", description);
        payload.addProperty("state", "OPEN");
        payload.addProperty("open", true);
        payload.addProperty("closed", false);

        payload.add("fromRef", fromRef.toJson());
        payload.add("toRef", toRef.toJson());
        payload.add("reviewers", getReviewers());

        return payload;
    }

    public static class Ref {
        protected final String branchId;
        protected final Repository repository;

        public Ref(String branchId, Repository repository) {
            this.branchId = branchId;
            this.repository = repository;
        }

        /**
         * <pre>
         * {
         *         "id": "refs/heads/feature-ABC-123",
         *         "repository": {
         *             "slug": "my-repo",
         *             "name": null,
         *             "project": {
         *                 "key": "PRJ"
         *             }
         *         }
         * }
         * </pre>
         * 
         * @return
         */
        public JsonObject toJson() {

            JsonObject repositoryRefPayload = new JsonObject();
            repositoryRefPayload.addProperty("id", branchId);

            JsonObject repositoryPayload = new JsonObject();
            repositoryPayload.addProperty("slug", repository.getSlug());
            repositoryPayload.addProperty("name", repository.getName());

            JsonObject projectPayload = new JsonObject();
            projectPayload.addProperty("key", repository.getProject().getKey());
            repositoryPayload.add("project", projectPayload);

            repositoryRefPayload.add("repository", repositoryPayload);
            return repositoryRefPayload;
        }

    }

    public JsonArray getReviewers() {
        JsonArray reviewersArray = new JsonArray();
        for (String reviewer : reviewers) {
            JsonObject userDetailsPayload = new JsonObject();
            userDetailsPayload.addProperty("name", reviewer);
            JsonObject userPayload = new JsonObject();
            userPayload.add("user", userDetailsPayload);
            reviewersArray.add(userPayload);
        }
        return reviewersArray;
    }

}
