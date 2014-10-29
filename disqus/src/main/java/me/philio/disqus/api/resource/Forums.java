package me.philio.disqus.api.resource;

import java.util.List;
import java.util.Map;

import me.philio.disqus.api.exception.ApiException;
import me.philio.disqus.api.model.Response;
import me.philio.disqus.api.model.category.Category;
import me.philio.disqus.api.model.forum.Forum;
import me.philio.disqus.api.model.forum.Moderator;
import me.philio.disqus.api.model.post.Post;
import me.philio.disqus.api.model.thread.Thread;
import me.philio.disqus.api.model.user.User;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;
import retrofit.http.QueryMap;

/**
 * Forums resource
 */
public interface Forums {

    /**
     * Adds a moderator to a forum
     *
     * @param user
     * @param forum
     * @return
     * @throws ApiException
     */
    @POST("/forums/addModerator.json")
    public Response<Moderator> addModerator(@Query("user") long user, @Query("forum") String forum)
            throws ApiException;

    /**
     * Creates a new forum
     *
     * @param website
     * @param name
     * @param shortName
     * @return
     * @throws ApiException
     */
    @POST("/forums/create.json")
    public Response<Forum> create(@Query("website") String website,
                                  @Query("name") String name,
                                  @Query("short_name") String shortName) throws ApiException;

    /**
     * Creates a new forum
     *
     * @param website
     * @param name
     * @param shortName
     * @param guidelines
     * @return
     * @throws ApiException
     */
    @POST("/forums/create.json")
    public Response<Forum> create(@Query("website") String website,
                                         @Query("name") String name,
                                         @Query("short_name") String shortName,
                                         @Query("guidelines") String guidelines)
            throws ApiException;

    /**
     * Returns forum details
     *
     * @param forum
     * @return
     * @throws ApiException
     */
    @GET("/forums/details.json")
    public Response<Forum> details(@Query("forum") String forum) throws ApiException;

    /**
     * Returns forum details
     *
     * @param forum
     * @param related
     * @return
     * @throws ApiException
     */
    @GET("/forums/details.json")
    public Response<Forum> details(@Query("forum") String forum,
                                   @Query("related") String[] related) throws ApiException;

    /**
     * Follow a forum
     *
     * @param target
     * @return
     * @throws ApiException
     */
    @POST("/forums/follow.json")
    public Response<Object[]> follow(@Query("target") String target) throws ApiException;

    /**
     * Returns a list of categories within a forum
     *
     * @param forum
     * @return
     * @throws ApiException
     */
    @GET("/forums/listCategories.json")
    public Response<List<Category>> listCategories(@Query("forum") String forum)
            throws ApiException;

    /**
     * Returns a list of categories within a forum
     *
     * @param forum
     * @param optionalParams
     * @return
     * @throws ApiException
     */
    @GET("/forums/listCategories.json")
    public Response<List<Category>> listCategories(@Query("forum") String forum,
                                                   @QueryMap Map<String, String> optionalParams)
            throws ApiException;

    /**
     * Returns a list of users following a forum
     *
     * @param forum
     * @return
     * @throws ApiException
     */
    @GET("/forums/listFollowers.json")
    public Response<List<User>> listFollowers(@Query("forum") String forum)
            throws ApiException;

    /**
     * Returns a list of users following a forum
     *
     * @param forum
     * @param optionalParams
     * @return
     * @throws ApiException
     */
    @GET("/forums/listFollowers.json")
    public Response<List<User>> listFollowers(@Query("forum") String forum,
                                              @QueryMap Map<String, String> optionalParams)
            throws ApiException;

    /**
     * Returns a list of all moderators on a forum
     *
     * @param forum
     * @return
     * @throws ApiException
     */
    @GET("/forums/listModerators.json")
    public Response<List<Moderator>> listModerators(@Query("forum") String forum)
            throws ApiException;

    /**
     * Returns a list of users active within a forum ordered by most comments made
     *
     * @param forum
     * @return
     * @throws ApiException
     */
    @GET("/forums/listMostActiveUsers.json")
    public Response<List<User>> listMostActiveUsers(@Query("forum") String forum)
            throws ApiException;

    /**
     * Returns a list of users active within a forum ordered by most comments made
     *
     * @param forum
     * @param optionalParams
     * @return
     * @throws ApiException
     */
    @GET("/forums/listMostActiveUsers.json")
    public Response<List<User>> listMostActiveUsers(@Query("forum") String forum,
                                                    @QueryMap Map<String, String> optionalParams)
            throws ApiException;

    /**
     * Returns a list of users active within a forum ordered by most likes received
     *
     * @param forum
     * @return
     * @throws ApiException
     */
    @GET("/forums/listMostLikedUsers.json")
    public Response<List<User>> listMostLikedUsers(@Query("forum") String forum)
            throws ApiException;

    /**
     * Returns a list of users active within a forum ordered by most likes received
     *
     * @param forum
     * @param optionalParams
     * @return
     * @throws ApiException
     */
    @GET("/forums/listMostLikedUsers.json")
    public Response<List<User>> listMostLikedUsers(@Query("forum") String forum,
                                                   @QueryMap Map<String, String> optionalParams)
            throws ApiException;

    /**
     * Returns a list of posts within a forum
     *
     * @param forum
     * @return
     * @throws ApiException
     */
    @GET("/forums/listPosts.json")
    public Response<List<Post>> listPosts(@Query("forum") String forum) throws ApiException;

    /**
     * Returns a list of posts within a forum
     *
     * @param forum
     * @param optionalParams
     * @return
     * @throws ApiException
     */
    @GET("/forums/listPosts.json")
    public Response<List<Post>> listPosts(@Query("forum") String forum,
                                          @QueryMap Map<String, String> optionalParams)
            throws ApiException;

    /**
     * Returns a list of threads within a forum sorted by the date created
     *
     * @param forum
     * @return
     * @throws ApiException
     */
    @GET("/forums/listThreads.json")
    public Response<List<Thread>> listThreads(@Query("forum") String forum) throws ApiException;

    /**
     * Returns a list of threads within a forum sorted by the date created
     *
     * @param forum
     * @param optionalParams
     * @return
     * @throws ApiException
     */
    @GET("/forums/listThreads.json")
    public Response<List<Thread>> listThreads(@Query("forum") String forum,
                                              @QueryMap Map<String, String> optionalParams)
            throws ApiException;

    /**
     * Returns a list of users active within a forum
     *
     * @param forum
     * @return
     * @throws ApiException
     */
    @GET("/forums/listUsers.json")
    public Response<List<User>> listUsers(@Query("forum") String forum) throws ApiException;

    /**
     * Returns a list of users active within a forum
     *
     * @param forum
     * @param optionalParams
     * @return
     * @throws ApiException
     */
    @GET("/forums/listUsers.json")
    public Response<List<User>> listUsers(@Query("forum") String forum,
                                          @QueryMap Map<String, String> optionalParams)
            throws ApiException;

    /**
     * Removes a moderator from a forum
     *
     * @param moderator
     * @return
     * @throws ApiException
     */
    @POST("/forums/removeModerator.json")
    public Response<Moderator> removeModerator(@Query("moderator") long moderator)
        throws ApiException;

    /**
     * Unfollow a forum
     *
     * @param target
     * @return
     * @throws ApiException
     */
    @POST("/forums/unfollow.json")
    public Response<Object[]> unfollow(@Query("target") String target) throws ApiException;

    /**
     * Updates forum details
     *
     * @param forum
     * @param website
     * @param name
     * @param guidelines
     * @return
     * @throws ApiException
     */
    @POST("/forums/update.json")
    public Response<Forum> update(@Query("forum") String forum,
                                  @Query("website") String website,
                                  @Query("name") String name,
                                  @Query("guidelines") String guidelines) throws ApiException;

}
