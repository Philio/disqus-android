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
package me.philio.disqus.api.resources;

import java.util.List;
import java.util.Map;

import me.philio.disqus.api.exception.ApiException;
import me.philio.disqus.api.model.Response;
import me.philio.disqus.api.model.category.Category;
import me.philio.disqus.api.model.forums.Forum;
import me.philio.disqus.api.model.forums.Moderator;
import me.philio.disqus.api.model.posts.Post;
import me.philio.disqus.api.model.threads.Thread;
import me.philio.disqus.api.model.users.User;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;
import retrofit.http.QueryMap;

/**
 * Forums resource
 *
 * @see <a href="https://disqus.com/api/docs/forums/">Documentation</a>
 */
public interface Forums {

    /**
     * Adds a moderator to a forum
     *
     * @see <a href="https://disqus.com/api/docs/forums/addModerator/">Documentation</a>
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
     * @see <a href="https://disqus.com/api/docs/forums/create/">Documentation</a>
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
     * @see <a href="https://disqus.com/api/docs/forums/create/">Documentation</a>
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
                                  @Query("guidelines") String guidelines) throws ApiException;

    /**
     * Returns forum details
     *
     * @see <a href="https://disqus.com/api/docs/forums/details/">Documentation</a>
     * @param forum
     * @return
     * @throws ApiException
     */
    @GET("/forums/details.json")
    public Response<Forum> details(@Query("forum") String forum) throws ApiException;

    /**
     * Returns forum details
     *
     * @see <a href="https://disqus.com/api/docs/forums/details/">Documentation</a>
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
     * @see <a href="https://disqus.com/api/docs/forums/follow/">Documentation</a>
     * @param target
     * @return
     * @throws ApiException
     */
    @POST("/forums/follow.json")
    public Response<Object[]> follow(@Query("target") String target) throws ApiException;

    /**
     * Returns a list of categories within a forum
     *
     * @see <a href="https://disqus.com/api/docs/forums/listCategories/">Documentation</a>
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
     * @see <a href="https://disqus.com/api/docs/forums/listCategories/">Documentation</a>
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
     * @see <a href="https://disqus.com/api/docs/forums/listFollowers/">Documentation</a>
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
     * @see <a href="https://disqus.com/api/docs/forums/listFollowers/">Documentation</a>
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
     * @see <a href="https://disqus.com/api/docs/forums/listModerators/">Documentation</a>
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
     * @see <a href="https://disqus.com/api/docs/forums/listMostActiveUsers/">Documentation</a>
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
     * @see <a href="https://disqus.com/api/docs/forums/listMostActiveUsers/">Documentation</a>
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
     * @see <a href="https://disqus.com/api/docs/forums/listMostLikedUsers/">Documentation</a>
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
     * @see <a href="https://disqus.com/api/docs/forums/listMostLikedUsers/">Documentation</a>
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
     * @see <a href="https://disqus.com/api/docs/forums/listPosts/">Documentation</a>
     * @param forum
     * @return
     * @throws ApiException
     */
    @GET("/forums/listPosts.json")
    public Response<List<Post>> listPosts(@Query("forum") String forum) throws ApiException;

    /**
     * Returns a list of posts within a forum
     *
     * @see <a href="https://disqus.com/api/docs/forums/listPosts/">Documentation</a>
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
     * @see <a href="https://disqus.com/api/docs/forums/listThreads/">Documentation</a>
     * @param forum
     * @return
     * @throws ApiException
     */
    @GET("/forums/listThreads.json")
    public Response<List<Thread>> listThreads(@Query("forum") String forum) throws ApiException;

    /**
     * Returns a list of threads within a forum sorted by the date created
     *
     * @see <a href="https://disqus.com/api/docs/forums/listThreads/">Documentation</a>
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
     * @see <a href="https://disqus.com/api/docs/forums/listUsers/">Documentation</a>
     * @param forum
     * @return
     * @throws ApiException
     */
    @GET("/forums/listUsers.json")
    public Response<List<User>> listUsers(@Query("forum") String forum) throws ApiException;

    /**
     * Returns a list of users active within a forum
     *
     * @see <a href="https://disqus.com/api/docs/forums/listUsers/">Documentation</a>
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
     * @see <a href="https://disqus.com/api/docs/forums/removeModerator/">Documentation</a>
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
     * @see <a href="https://disqus.com/api/docs/forums/unfollow/">Documentation</a>
     * @param target
     * @return
     * @throws ApiException
     */
    @POST("/forums/unfollow.json")
    public Response<List<Object>> unfollow(@Query("target") String target) throws ApiException;

    /**
     * Updates forum details
     *
     * @see <a href="https://disqus.com/api/docs/forums/update/">Documentation</a>
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
