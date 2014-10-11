package me.philio.disqus.api;

import android.net.Uri;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import me.philio.disqus.api.http.HttpResponse;
import me.philio.disqus.api.model.Response;
import me.philio.disqus.api.model.forums.ForumDetails;
import me.philio.disqus.api.model.users.UserDetails;

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
     * BETA Returns details of a user.
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

    public void listActiveThreads() throws Exception {
        throw new Exception("Stub! Not implemented yet");
    }

    public void listActivity() throws Exception {
        throw new Exception("Stub! Not implemented yet");
    }

    public void listFollowers() throws Exception {
        throw new Exception("Stub! Not implemented yet");
    }

    public void listFollowing() throws Exception {
        throw new Exception("Stub! Not implemented yet");
    }

    public void listFollowingForums() throws Exception {
        throw new Exception("Stub! Not implemented yet");
    }

    public void listFollowingTopics() throws Exception {
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
    public Response<List<ForumDetails>> listMostActiveForums(Integer limit, Integer user) throws
            IOException {
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

    public void listPosts() throws Exception {
        throw new Exception("Stub! Not implemented yet");
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
