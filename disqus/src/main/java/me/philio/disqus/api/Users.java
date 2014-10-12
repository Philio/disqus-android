/*
 * Copyright 2014 Phil Bayfield
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.philio.disqus.api;

import android.net.Uri;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import me.philio.disqus.api.http.HttpResponse;
import me.philio.disqus.api.model.Response;
import me.philio.disqus.api.model.forum.ForumDetails;
import me.philio.disqus.api.model.post.PostDetails;
import me.philio.disqus.api.model.thread.ThreadDetails;
import me.philio.disqus.api.model.user.UserDetails;

/**
 * Users api methods
 */
public class Users extends AbstractApi {

    /**
     * Set api key and access token
     *
     * @param apiKey
     * @param accessToken
     */
    public Users(String apiKey, String accessToken) {
        super(apiKey, accessToken);
    }

    /**
     * Updates username for the user, fails if username does not meet requirements, or is taken by
     * another user.
     *
     * @see <a href="https://disqus.com/api/docs/users/checkUsername/">Documentation</a>
     * @param username
     * @return
     * @throws Exception
     */
    public Response<String> checkUsername(String username) throws IOException {
        // Build uri
        Uri.Builder builder = new Uri.Builder();
        appendAuth(builder);
        appendString(builder, "username", username);

        // Send request
        HttpResponse response = mRequest.post(
                Uri.parse("https://disqus.com/api/3.0/users/checkUsername.json"),
                builder.build().getQuery());

        // Parse JSON response
        Type type = new TypeToken<Response<String>>() {}.getType();
        return mGson.fromJson(response.getBody(), type);
    }

    /**
     * Returns details of a user. [BETA]
     *
     * @see <a href="https://disqus.com/api/docs/users/details/">Documentation</a>
     * @param user
     * @return
     * @throws IOException
     */
    public Response<UserDetails> details(Integer user) throws IOException {
        // Build uri
        Uri.Builder builder = Uri.parse("https://disqus.com/api/3.0/users/details.json")
                .buildUpon();
        appendAuth(builder);
        appendInt(builder, "user", user, true);

        // Send request
        HttpResponse response = mRequest.get(builder.build());

        // Parse JSON response
        Type type = new TypeToken<Response<UserDetails>>() {}.getType();
        return mGson.fromJson(response.getBody(), type);
    }

    /**
     * Follow a user.
     *
     * Response is always an empty array and will result in an empty list and can be ignored
     *
     * @see <a href="https://disqus.com/api/docs/users/follow/">Documentation</a>
     * @param target
     * @return
     * @throws IOException
     */
    public Response<List<Object>> follow(int target) throws IOException {
        // Build uri
        Uri.Builder builder = new Uri.Builder();
        appendAuth(builder);
        appendInt(builder, "target", target, true);

        // Send request
        HttpResponse response = mRequest.post(
                Uri.parse("https://disqus.com/api/3.0/users/follow.json"),
                builder.build().getQuery());

        // Parse JSON response
        Type type = new TypeToken<Response<List<Object>>>() {}.getType();
        return mGson.fromJson(response.getBody(), type);
    }

    /**
     * Returns a list of forums a user has been active on.
     *
     * @see <a href="https://disqus.com/api/docs/users/listActiveForums/">Documentation</a>
     * @param sinceId
     * @param cursor
     * @param limit
     * @param user
     * @param order
     * @return
     * @throws IOException
     */
    public Response<List<ForumDetails>> listActiveForums(Integer sinceId, String cursor,
                                                         Integer limit, Integer user, Order order)
            throws IOException {
        // Build uri
        Uri.Builder builder = Uri.parse("https://disqus.com/api/3.0/users/listActiveForums.json")
                .buildUpon();
        appendAuth(builder);
        appendInt(builder, "since_id", sinceId, true);
        appendString(builder, "cursor", cursor);
        appendInt(builder, "limit", limit, true);
        appendInt(builder, "user", user, true);
        appendOrder(builder, order);

        // Send request
        HttpResponse response = mRequest.get(builder.build());

        // Parse JSON response
        Type type = new TypeToken<Response<List<ForumDetails>>>() {}.getType();
        return mGson.fromJson(response.getBody(), type);
    }

    /**
     * Returns a list of threads a user has participated in sorted by last activity. [BETA]
     *
     * @see <a href="https://disqus.com/api/docs/users/listActiveThreads/">Documentation</a>
     * @param forums
     * @param since
     * @param related
     * @param cursor
     * @param limit
     * @param user
     * @param includes
     * @param order
     * @return
     * @throws IOException
     */
    public Response<List<ThreadDetails>> listActiveThreads(String[] forums, String since,
                                                           Related[] related, String cursor,
                                                           Integer limit, Integer user,
                                                           Include[] includes, Order order)
            throws IOException {
        // Build uri
        Uri.Builder builder = Uri.parse("https://disqus.com/api/3.0/users/listActiveThreads.json")
                .buildUpon();
        appendAuth(builder);
        if (forums != null) {
            for (String forum : forums) {
                appendString(builder, "forum", forum);
            }
        }
        appendString(builder, "since", since);
        appendRelated(builder, related);
        appendString(builder, "cursor", cursor);
        appendInt(builder, "limit", limit, true);
        appendInt(builder, "user", user, true);
        appendIncludes(builder, includes);
        appendOrder(builder, order);

        // Send request
        HttpResponse response = mRequest.get(builder.build());

        // Parse JSON response
        Type type = new TypeToken<Response<List<ThreadDetails>>>() {}.getType();
        return mGson.fromJson(response.getBody(), type);
    }

    /**
     * Returns a list of various activity types made by the user. [BETA]
     *
     * TODO The response of this method is complex, will be added in a future version
     *
     * @see <a href="https://disqus.com/api/docs/users/listActivity/">Documentation</a>
     * @param since
     * @param related
     * @param cursor
     * @param limit
     * @param user
     * @param query
     * @param includes
     * @param anonUser
     * @throws Exception
     */
    public void listActivity(String since, Related[] related, String cursor, Integer limit,
                             Integer user, String query, Include[] includes, String anonUser)
            throws Exception {
        throw new Exception("Stub! Not implemented yet");
    }

    /**
     * Returns a list of users a user is being followed by. [BETA]
     *
     * @see <a href="https://disqus.com/api/docs/users/listFollowers/">Documentation</a>
     * @param sinceId
     * @param cursor
     * @param limit
     * @param user
     * @param order
     * @return
     * @throws IOException
     */
    public Response<List<UserDetails>> listFollowers(Integer sinceId, String cursor, Integer limit,
                                                     Integer user, Order order) throws IOException {
        // Build uri
        Uri.Builder builder = Uri.parse("https://disqus.com/api/3.0/users/listFollowers.json")
                .buildUpon();
        appendAuth(builder);
        appendInt(builder, "since_id", sinceId, true);
        appendString(builder, "cursor", cursor);
        appendInt(builder, "limit", limit, true);
        appendInt(builder, "user", user, true);
        appendOrder(builder, order);

        // Send request
        HttpResponse response = mRequest.get(builder.build());

        // Parse JSON response
        Type type = new TypeToken<Response<List<UserDetails>>>() {}.getType();
        return mGson.fromJson(response.getBody(), type);
    }

    /**
     * Returns a list of users a user is following. [BETA]
     *
     * @see <a href="https://disqus.com/api/docs/users/listFollowing/">Documentation</a>
     * @param sinceId
     * @param cursor
     * @param limit
     * @param user
     * @param order
     * @return
     * @throws IOException
     */
    public Response<List<UserDetails>> listFollowing(Integer sinceId, String cursor, Integer limit,
                                                     Integer user, Order order) throws IOException {
        // Build uri
        Uri.Builder builder = Uri.parse("https://disqus.com/api/3.0/users/listFollowing.json")
                .buildUpon();
        appendAuth(builder);
        appendInt(builder, "since_id", sinceId, true);
        appendString(builder, "cursor", cursor);
        appendInt(builder, "limit", limit, true);
        appendInt(builder, "user", user, true);
        appendOrder(builder, order);

        // Send request
        HttpResponse response = mRequest.get(builder.build());

        // Parse JSON response
        Type type = new TypeToken<Response<List<UserDetails>>>() {}.getType();
        return mGson.fromJson(response.getBody(), type);
    }

    /**
     * Returns a list of forums a user is following. [BETA]
     *
     * @see <a href="https://disqus.com/api/docs/users/listFollowingForums/">Documentation</a>
     * @param sinceId
     * @param cursor
     * @param limit
     * @param user
     * @param order
     * @return
     * @throws IOException
     */
    public Response<List<ForumDetails>> listFollowingForums(Integer sinceId, String cursor,
                                                            Integer limit, Integer user,
                                                            Order order) throws IOException {
        // Build uri
        Uri.Builder builder = Uri.parse("https://disqus.com/api/3.0/users/listFollowingForums.json")
                .buildUpon();
        appendAuth(builder);
        appendInt(builder, "since_id", sinceId, true);
        appendString(builder, "cursor", cursor);
        appendInt(builder, "limit", limit, true);
        appendInt(builder, "user", user, true);
        appendOrder(builder, order);

        // Send request
        HttpResponse response = mRequest.get(builder.build());

        // Parse JSON response
        Type type = new TypeToken<Response<List<ForumDetails>>>() {}.getType();
        return mGson.fromJson(response.getBody(), type);
    }

    /**
     * Returns a list of topics a user is following. [BETA]
     *
     * TODO Unable to find any topics to test this with at present as new/beta feature
     *
     * @see <a href="https://disqus.com/api/docs/users/listFollowingTopics/">Documentation</a>
     * @param sinceId
     * @param cursor
     * @param limit
     * @param user
     * @param order
     * @throws Exception
     */
    public void listFollowingTopics(Integer sinceId, String cursor, Integer limit, Integer user,
                                    Order order) throws Exception {
        throw new Exception("Stub! Not implemented yet");
    }

    /**
     * Returns a list of forums a user owns.
     *
     * @see <a href="https://disqus.com/api/docs/users/listForums/">Documentation</a>
     *
     * @param sinceId
     * @param cursor
     * @param limit
     * @param user
     * @param order
     * @return
     * @throws IOException
     */
    public Response<List<ForumDetails>> listForums(Integer sinceId, String cursor, Integer limit,
                                            Integer user, Order order) throws IOException {
        // Build uri
        Uri.Builder builder = Uri.parse("https://disqus.com/api/3.0/users/listForums.json")
                .buildUpon();
        appendAuth(builder);
        appendInt(builder, "since_id", sinceId, true);
        appendString(builder, "cursor", cursor);
        appendInt(builder, "limit", limit, true);
        appendInt(builder, "user", user, true);
        appendOrder(builder, order);

        // Send request
        HttpResponse response = mRequest.get(builder.build());

        // Parse JSON response
        Type type = new TypeToken<Response<List<ForumDetails>>>() {}.getType();
        return mGson.fromJson(response.getBody(), type);
    }

    /**
     * Returns a list of forums a user has been active on recently, sorted by the user's activity.
     *
     * @see <a href="https://disqus.com/api/docs/users/listMostActiveForums/">Documentation</a>
     *
     * @param limit
     * @param user
     * @return
     * @throws IOException
     */
    public Response<List<ForumDetails>> listMostActiveForums(Integer limit, Integer user)
            throws IOException {
        // Build uri
        Uri.Builder builder =
                Uri.parse("https://disqus.com/api/3.0/users/listMostActiveForums.json").buildUpon();
        appendAuth(builder);
        appendInt(builder, "limit", limit, true);
        appendInt(builder, "user", user, true);

        // Send request
        HttpResponse response = mRequest.get(builder.build());

        // Parse JSON response
        Type type = new TypeToken<Response<List<ForumDetails>>>() {}.getType();
        return mGson.fromJson(response.getBody(), type);
    }

    /**
     * Returns a list of posts made by the user.
     *
     * @see <a href="https://disqus.com/api/docs/users/listPosts/">Documentation</a>
     * @param since
     * @param related
     * @param cursor
     * @param limit
     * @param user
     * @param includes
     * @param order
     * @return
     * @throws IOException
     */
    public Response<List<PostDetails>> listPosts(String since, Related[] related, String cursor,
                                                 Integer limit, Integer user, Include[] includes,
                                                 Order order) throws IOException {
        // Build uri
        Uri.Builder builder = Uri.parse("https://disqus.com/api/3.0/users/listPosts.json")
                .buildUpon();
        appendAuth(builder);
        appendString(builder, "since", since);
        appendRelated(builder, related);
        appendString(builder, "cursor", cursor);
        appendInt(builder, "limit", limit, true);
        appendInt(builder, "user", user, true);
        appendIncludes(builder, includes);
        appendOrder(builder, order);

        // Send request
        HttpResponse response = mRequest.get(builder.build());

        // Parse JSON response
        Type type = new TypeToken<Response<List<PostDetails>>>() {}.getType();
        return mGson.fromJson(response.getBody(), type);
    }

    public void removeFollower() throws Exception {
        throw new Exception("Stub! Not implemented yet");
    }

    /**
     * Unfollow a user.
     *
     * @see <a href="https://disqus.com/api/docs/users/unfollow/">Documentation</a>
     *
     * Response is always an empty array and will result in an empty list and can be ingored
     *
     * @param target
     * @return
     * @throws IOException
     */
    public Response<List<Object>> unfollow(int target) throws IOException {
        // Build uri
        Uri.Builder builder = new Uri.Builder();
        appendAuth(builder);
        appendInt(builder, "target", target, true);

        // Send request
        HttpResponse response = mRequest.post(
                Uri.parse("https://disqus.com/api/3.0/users/unfollow.json"),
                builder.build().getQuery());

        // Parse JSON response
        Type type = new TypeToken<Response<List<Object>>>() {}.getType();
        return mGson.fromJson(response.getBody(), type);
    }

    public void updateProfile() throws Exception {
        throw new Exception("Stub! Not implemented yet");
    }

}
