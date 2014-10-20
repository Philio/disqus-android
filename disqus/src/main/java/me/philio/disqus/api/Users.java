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

import me.philio.disqus.api.exception.ApiException;
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
     * Set api key
     *
     * @param apiKey
     */
    public Users(String apiKey) {
        super(apiKey);
    }

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
     * Set api key, secret and access token
     *
     * @param apiKey
     * @param apiSecret
     * @param accessToken
     */
    public Users(String apiKey, String apiSecret, String accessToken) {
        super(apiKey, apiSecret, accessToken);
    }

    /**
     * Updates username for the user, fails if username does not meet requirements, or is taken by
     * another user.
     *
     * @see <a href="https://disqus.com/api/docs/users/checkUsername/">Documentation</a>
     * @param username
     * @return
     * @throws ApiException
     * @throws IOException
     */
    public Response<String> checkUsername(String username) throws ApiException, IOException {
        // Build uri
        Uri.Builder builder = new Uri.Builder();
        appendAuth(builder);
        appendString(builder, "username", username);

        // Send request
        HttpResponse response = mRequest.post(
                Uri.parse("https://disqus.com/api/3.0/users/checkUsername.json"),
                builder.build().getQuery());
        checkResponse(response);

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
     * @throws ApiException
     * @throws IOException
     */
    public Response<UserDetails> details(Long user) throws ApiException, IOException {
        // Build uri
        Uri.Builder builder = Uri.parse("https://disqus.com/api/3.0/users/details.json")
                .buildUpon();
        appendAuth(builder);
        appendLong(builder, "user", user, true);

        // Send request
        HttpResponse response = mRequest.get(builder.build());
        checkResponse(response);

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
     * @throws ApiException
     * @throws IOException
     */
    public Response<List<Object>> follow(long target) throws ApiException, IOException {
        // Build uri
        Uri.Builder builder = new Uri.Builder();
        appendAuth(builder);
        appendLong(builder, "target", target, true);

        // Send request
        HttpResponse response = mRequest.post(
                Uri.parse("https://disqus.com/api/3.0/users/follow.json"),
                builder.build().getQuery());
        checkResponse(response);

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
     * @throws ApiException
     * @throws IOException
     */
    public Response<List<ForumDetails>> listActiveForums(Integer sinceId, String cursor,
                                                         Integer limit, Long user, Order order)
            throws ApiException, IOException {
        // Build uri
        Uri.Builder builder = Uri.parse("https://disqus.com/api/3.0/users/listActiveForums.json")
                .buildUpon();
        appendAuth(builder);
        appendInt(builder, "since_id", sinceId, true);
        appendString(builder, "cursor", cursor);
        appendInt(builder, "limit", limit, true);
        appendLong(builder, "user", user, true);
        appendOrder(builder, order);

        // Send request
        HttpResponse response = mRequest.get(builder.build());
        checkResponse(response);

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
     * @throws ApiException
     * @throws IOException
     */
    public Response<List<ThreadDetails>> listActiveThreads(String[] forums, String since,
                                                           Related[] related, String cursor,
                                                           Integer limit, Long user,
                                                           Include[] includes, Order order)
            throws ApiException, IOException {
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
        appendLong(builder, "user", user, true);
        appendIncludes(builder, includes);
        appendOrder(builder, order);

        // Send request
        HttpResponse response = mRequest.get(builder.build());
        checkResponse(response);

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
     * @throws ApiException
     */
    public void listActivity(String since, Related[] related, String cursor, Integer limit,
                             Long user, String query, Include[] includes, String anonUser)
            throws ApiException {
        throw new ApiException("Stub! Not implemented yet");
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
     * @throws ApiException
     * @throws IOException
     */
    public Response<List<UserDetails>> listFollowers(Integer sinceId, String cursor, Integer limit,
                                                     Long user, Order order)
            throws ApiException, IOException {
        // Build uri
        Uri.Builder builder = Uri.parse("https://disqus.com/api/3.0/users/listFollowers.json")
                .buildUpon();
        appendAuth(builder);
        appendInt(builder, "since_id", sinceId, true);
        appendString(builder, "cursor", cursor);
        appendInt(builder, "limit", limit, true);
        appendLong(builder, "user", user, true);
        appendOrder(builder, order);

        // Send request
        HttpResponse response = mRequest.get(builder.build());
        checkResponse(response);

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
     * @throws ApiException
     * @throws IOException
     */
    public Response<List<UserDetails>> listFollowing(Integer sinceId, String cursor, Integer limit,
                                                     Long user, Order order)
            throws ApiException, IOException {
        // Build uri
        Uri.Builder builder = Uri.parse("https://disqus.com/api/3.0/users/listFollowing.json")
                .buildUpon();
        appendAuth(builder);
        appendInt(builder, "since_id", sinceId, true);
        appendString(builder, "cursor", cursor);
        appendInt(builder, "limit", limit, true);
        appendLong(builder, "user", user, true);
        appendOrder(builder, order);

        // Send request
        HttpResponse response = mRequest.get(builder.build());
        checkResponse(response);

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
     * @throws ApiException
     * @throws IOException
     */
    public Response<List<ForumDetails>> listFollowingForums(Integer sinceId, String cursor,
                                                            Integer limit, Long user, Order order)
            throws ApiException, IOException {
        // Build uri
        Uri.Builder builder = Uri.parse("https://disqus.com/api/3.0/users/listFollowingForums.json")
                .buildUpon();
        appendAuth(builder);
        appendInt(builder, "since_id", sinceId, true);
        appendString(builder, "cursor", cursor);
        appendInt(builder, "limit", limit, true);
        appendLong(builder, "user", user, true);
        appendOrder(builder, order);

        // Send request
        HttpResponse response = mRequest.get(builder.build());
        checkResponse(response);

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
     * @throws ApiException
     */
    public void listFollowingTopics(Integer sinceId, String cursor, Integer limit, Long user,
                                    Order order) throws ApiException {
        throw new ApiException("Stub! Not implemented yet");
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
     * @throws ApiException
     * @throws IOException
     */
    public Response<List<ForumDetails>> listForums(Integer sinceId, String cursor, Integer limit,
                                                   Long user, Order order)
            throws ApiException, IOException {
        // Build uri
        Uri.Builder builder = Uri.parse("https://disqus.com/api/3.0/users/listForums.json")
                .buildUpon();
        appendAuth(builder);
        appendInt(builder, "since_id", sinceId, true);
        appendString(builder, "cursor", cursor);
        appendInt(builder, "limit", limit, true);
        appendLong(builder, "user", user, true);
        appendOrder(builder, order);

        // Send request
        HttpResponse response = mRequest.get(builder.build());
        checkResponse(response);

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
     * @throws ApiException
     * @throws IOException
     */
    public Response<List<ForumDetails>> listMostActiveForums(Integer limit, Long user)
            throws ApiException, IOException {
        // Build uri
        Uri.Builder builder =
                Uri.parse("https://disqus.com/api/3.0/users/listMostActiveForums.json").buildUpon();
        appendAuth(builder);
        appendInt(builder, "limit", limit, true);
        appendLong(builder, "user", user, true);

        // Send request
        HttpResponse response = mRequest.get(builder.build());
        checkResponse(response);

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
     * @throws ApiException
     * @throws IOException
     */
    public Response<List<PostDetails>> listPosts(String since, Related[] related, String cursor,
                                                 Integer limit, Long user, Include[] includes,
                                                 Order order) throws ApiException, IOException {
        // Build uri
        Uri.Builder builder = Uri.parse("https://disqus.com/api/3.0/users/listPosts.json")
                .buildUpon();
        appendAuth(builder);
        appendString(builder, "since", since);
        appendRelated(builder, related);
        appendString(builder, "cursor", cursor);
        appendInt(builder, "limit", limit, true);
        appendLong(builder, "user", user, true);
        appendIncludes(builder, includes);
        appendOrder(builder, order);

        // Send request
        HttpResponse response = mRequest.get(builder.build());
        checkResponse(response);

        // Parse JSON response
        Type type = new TypeToken<Response<List<PostDetails>>>() {}.getType();
        return mGson.fromJson(response.getBody(), type);
    }

    /**
     * Remove a user from set of followers.
     *
     * Response is always an empty array and will result in an empty list and can be ignored
     *
     * @see <a href="https://disqus.com/api/docs/users/removeFollower/">Documentation</a>
     * @param follower
     * @return
     * @throws ApiException
     * @throws IOException
     */
    public Response<List<Object>> removeFollower(long follower) throws ApiException, IOException {
        // Build uri
        Uri.Builder builder = new Uri.Builder();
        appendAuth(builder);
        appendLong(builder, "follower", follower, true);

        // Send request
        HttpResponse response = mRequest.post(
                Uri.parse("https://disqus.com/api/3.0/users/removeFollower.json"),
                builder.build().getQuery());
        checkResponse(response);

        // Parse JSON response
        Type type = new TypeToken<Response<List<Object>>>() {}.getType();
        return mGson.fromJson(response.getBody(), type);
    }

    /**
     * Unfollow a user.
     *
     * Response is always an empty array and will result in an empty list and can be ignored
     *
     * @see <a href="https://disqus.com/api/docs/users/unfollow/">Documentation</a>
     * @param target
     * @return
     * @throws ApiException
     * @throws IOException
     */
    public Response<List<Object>> unfollow(long target) throws ApiException, IOException {
        // Build uri
        Uri.Builder builder = new Uri.Builder();
        appendAuth(builder);
        appendLong(builder, "target", target, true);

        // Send request
        HttpResponse response = mRequest.post(
                Uri.parse("https://disqus.com/api/3.0/users/unfollow.json"),
                builder.build().getQuery());
        checkResponse(response);

        // Parse JSON response
        Type type = new TypeToken<Response<List<Object>>>() {}.getType();
        return mGson.fromJson(response.getBody(), type);
    }

    /**
     * Updates user profile.
     *
     * All fields are optional, but any field not present will be updated as blank.
     *
     * @see <a href="https://disqus.com/api/docs/users/updateProfile/">Documentation</a>
     * @param about
     * @param name
     * @param url
     * @param location
     * @return
     * @throws ApiException
     * @throws IOException
     */
    public Response<UserDetails> updateProfile(String about, String name, String url,
                                               String location) throws ApiException, IOException {
        // Build uri
        Uri.Builder builder = new Uri.Builder();
        appendAuth(builder);
        appendString(builder, "about", about);
        appendString(builder, "name", name);
        appendString(builder, "url", url);
        appendString(builder, "location", location);

        // Send request
        HttpResponse response = mRequest.post(
                Uri.parse("https://disqus.com/api/3.0/users/updateProfile.json"),
                builder.build().getQuery());
        checkResponse(response);

        // Parse JSON response
        Type type = new TypeToken<Response<UserDetails>>() {}.getType();
        return mGson.fromJson(response.getBody(), type);
    }

}
