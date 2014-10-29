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
import me.philio.disqus.api.model.posts.Post;
import me.philio.disqus.api.model.posts.Vote;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;
import retrofit.http.QueryMap;

/**
 * Posts resource
 *
 * @see <a href="https://disqus.com/api/docs/posts/">Documentation</a>
 */
public interface Posts {

    /**
     * Approves the requested post
     *
     * @see <a href="https://disqus.com/api/docs/posts/approve/">Documentation</a>
     * @param post
     * @return
     * @throws ApiException
     */
    @POST("/posts/approve.json")
    public Response<List<Post>> approve(@Query("post") long post) throws ApiException;

    /**
     * Approves the requested posts
     *
     * @see <a href="https://disqus.com/api/docs/posts/approve/">Documentation</a>
     * @param posts
     * @return
     * @throws ApiException
     */
    @POST("/posts/approve.json")
    public Response<List<Post>> approve(@Query("post") long[] posts) throws ApiException;

    /**
     * Creates a new post
     *
     * @see <a href="https://disqus.com/api/docs/posts/create/">Documentation</a>
     * @param message
     * @return
     * @throws ApiException
     */
    @POST("/posts/create.json")
    public Response<Post> create(@Query("message") String message) throws ApiException;

    /**
     * Creates a new post
     *
     * @see <a href="https://disqus.com/api/docs/posts/create/">Documentation</a>
     * @param message
     * @param optionalParams
     * @return
     * @throws ApiException
     */
    @POST("/posts/create.json")
    public Response<Post> create(@Query("message") String message,
                                 @QueryMap Map<String, String> optionalParams) throws ApiException;

    /**
     * Returns information about a post
     *
     * @see <a href="https://disqus.com/api/docs/posts/details/">Documentation</a>
     * @param post
     * @return
     * @throws ApiException
     */
    @GET("/posts/details.json")
    public Response<Post> details(@Query("post") long post) throws ApiException;

    /**
     * Returns information about a post
     *
     * @see <a href="https://disqus.com/api/docs/posts/details/">Documentation</a>
     * @param post
     * @param related
     * @return
     * @throws ApiException
     */
    @GET("/posts/details.json")
    public Response<Post> details(@Query("post") long post,
                                  @Query("related") String[] related) throws ApiException;

    /**
     * Returns the hierarchal tree of a post (all parents)
     *
     * @see <a href="https://disqus.com/api/docs/posts/getContext/">Documentation</a>
     * @param post
     * @return
     * @throws ApiException
     */
    @GET("/posts/getContext.json")
    public Response<List<Post>> getContext(@Query("post") long post) throws ApiException;

    /**
     * Returns the hierarchal tree of a post (all parents)
     *
     * @see <a href="https://disqus.com/api/docs/posts/getContext/">Documentation</a>
     * @param post
     * @return
     * @throws ApiException
     */
    @GET("/posts/getContext.json")
    public Response<List<Post>> getContext(@Query("post") long post,
                                           @Query("depth") Integer depth,
                                           @Query("related") String[] related) throws ApiException;

    /**
     * Returns a list of posts ordered by the date created
     *
     * @see <a href="https://disqus.com/api/docs/posts/list/">Documentation</a>
     * @return
     * @throws ApiException
     */
    @GET("/posts/list.json")
    public Response<List<Post>> list() throws ApiException;

    /**
     * Returns a list of posts ordered by the date created
     *
     * @see <a href="https://disqus.com/api/docs/posts/list/">Documentation</a>
     * @param optionalParams
     * @return
     * @throws ApiException
     */
    @GET("/posts/list.json")
    public Response<List<Post>> list(@QueryMap Map<String, String> optionalParams)
            throws ApiException;

    /**
     * Returns a list of posts ordered by the number of likes recently
     *
     * @see <a href="https://disqus.com/api/docs/posts/listPopular/">Documentation</a>
     * @return
     * @throws ApiException
     */
    @GET("/posts/listPopular.json")
    public Response<List<Post>> listPopular() throws ApiException;

    /**
     * Returns a list of posts ordered by the number of likes recently
     *
     * @see <a href="https://disqus.com/api/docs/posts/listPopular/">Documentation</a>
     * @param optionalParams
     * @return
     * @throws ApiException
     */
    @GET("/posts/listPopular.json")
    public Response<List<Post>> listPopular(@QueryMap Map<String, String> optionalParams)
            throws ApiException;

    /**
     * Deletes the requested post
     *
     * @see <a href="https://disqus.com/api/docs/posts/remove/">Documentation</a>
     * @param post
     * @return
     * @throws ApiException
     */
    @POST("/posts/remove.json")
    public Response<List<Post>> remove(@Query("post") long post) throws ApiException;

    /**
     * Deletes the requested posts
     *
     * @see <a href="https://disqus.com/api/docs/posts/remove/">Documentation</a>
     * @param posts
     * @return
     * @throws ApiException
     */
    @POST("/posts/remove.json")
    public Response<List<Post>> remove(@Query("post") long[] posts) throws ApiException;

    /**
     * Reports a post
     *
     * @see <a href="https://disqus.com/api/docs/posts/report/">Documentation</a>
     * @param post
     * @return
     * @throws ApiException
     */
    @POST("/posts/report.json")
    public Response<Post> report(@Query("post") long post) throws ApiException;

    /**
     * Undeletes the requested post
     *
     * @see <a href="https://disqus.com/api/docs/posts/restore/">Documentation</a>
     * @param post
     * @return
     * @throws ApiException
     */
    @POST("/posts/restore.json")
    public Response<List<Post>> restore(@Query("post") long post) throws ApiException;

    /**
     * Undeletes the requested posts
     *
     * @see <a href="https://disqus.com/api/docs/posts/restore/">Documentation</a>
     * @param posts
     * @return
     * @throws ApiException
     */
    @POST("/posts/restore.json")
    public Response<List<Post>> restore(@Query("post") long[] posts) throws ApiException;

    /**
     * Marks the requested post as spam
     *
     * @see <a href="https://disqus.com/api/docs/posts/spam/">Documentation</a>
     * @param post
     * @return
     * @throws ApiException
     */
    @POST("/posts/spam.json")
    public Response<List<Post>> spam(@Query("post") long post) throws ApiException;

    /**
     * Marks the requested posts as spam
     *
     * @see <a href="https://disqus.com/api/docs/posts/spam/">Documentation</a>
     * @param posts
     * @return
     * @throws ApiException
     */
    @POST("/posts/spam.json")
    public Response<List<Post>> spam(@Query("post") long[] posts) throws ApiException;

    /**
     * Updates information on a post
     *
     * @see <a href="https://disqus.com/api/docs/posts/update/">Documentation</a>
     * @param post
     * @param message
     * @return
     * @throws ApiException
     */
    @POST("/posts/update.json")
    public Response<Post> update(@Query("post") long post,
                                 @Query("message") String message) throws ApiException;

    /**
     * Register a vote on a post
     *
     * @see <a href="https://disqus.com/api/docs/posts/vote/">Documentation</a>
     * @param post
     * @param vote
     * @return
     * @throws ApiException
     */
    @POST("/posts/vote.json")
    public Response<Vote> vote(@Query("post") long post,
                               @Query("vote") int vote) throws ApiException;

}
