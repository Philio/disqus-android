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
import me.philio.disqus.api.model.posts.Post;
import me.philio.disqus.api.model.threads.Thread;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;
import retrofit.http.QueryMap;

/**
 * Categories resource
 *
 * @see <a href="https://disqus.com/api/docs/categories/">Documentation</a>
 */
public interface Categories {

    /**
     * Creates a new category
     *
     * @param forum The forum short name
     * @param title The category title
     * @return The added category
     * @throws ApiException
     * @see <a href="https://disqus.com/api/docs/categories/create/">Documentation</a>
     */
    @FormUrlEncoded
    @POST("/categories/create.json")
    public Response<Category> create(@Field("forum") String forum,
                                     @Field("title") String title) throws ApiException;

    /**
     * Creates a new category
     *
     * @param forum     The forum short name
     * @param title     The category title
     * @param isDefault Is this the default category
     * @return The added category
     * @throws ApiException
     * @see <a href="https://disqus.com/api/docs/categories/create/">Documentation</a>
     */
    @FormUrlEncoded
    @POST("/categories/create.json")
    public Response<Category> create(@Field("forum") String forum,
                                     @Field("title") String title,
                                     @Field("default") int isDefault) throws ApiException;

    /**
     * Returns category details
     *
     * @param category The category id
     * @return The category details
     * @throws ApiException
     * @see <a href="https://disqus.com/api/docs/categories/details/">Documentation</a>
     */
    @GET("/categories/details.json")
    public Response<Category> details(@Query("category") long category) throws ApiException;

    /**
     * Returns a list of categories within a forum
     *
     * @param forum The forum short name
     * @return A list of categories
     * @throws ApiException
     * @see <a href="https://disqus.com/api/docs/categories/list/">Documentation</a>
     */
    @GET("/categories/list.json")
    public Response<List<Category>> list(@Query("forum") String forum) throws ApiException;

    /**
     * Returns a list of categories within a forum
     *
     * @param forum          The forum short name
     * @param optionalParams A map of optional parameters
     * @return A list of categories
     * @throws ApiException
     * @see <a href="https://disqus.com/api/docs/categories/list/">Documentation</a>
     */
    @GET("/categories/list.json")
    public Response<List<Category>> list(@Query("forum") String forum,
                                         @QueryMap Map<String, String> optionalParams)
            throws ApiException;

    /**
     * Returns a list of categories within multiple forums
     *
     * @param forums The forum short names
     * @return A list of categories
     * @throws ApiException
     * @see <a href="https://disqus.com/api/docs/categories/list/">Documentation</a>
     */
    @GET("/categories/list.json")
    public Response<List<Category>> list(@Query("forum") String[] forums)
            throws ApiException;

    /**
     * Returns a list of categories within multiple forums
     *
     * @param forums         The forum short names
     * @param optionalParams A map of optional parameters
     * @return A list of categories
     * @throws ApiException
     * @see <a href="https://disqus.com/api/docs/categories/list/">Documentation</a>
     */
    @GET("/categories/list.json")
    public Response<List<Category>> list(@Query("forum") String[] forums,
                                         @QueryMap Map<String, String> optionalParams)
            throws ApiException;

    /**
     * Returns a list of posts within a category
     *
     * @param category The category id
     * @return A list of posts
     * @throws ApiException
     * @see <a href="https://disqus.com/api/docs/categories/listPosts/">Documentation</a>
     */
    @GET("/categories/listPosts.json")
    public Response<List<Post>> listPosts(@Query("category") long category) throws ApiException;

    /**
     * Returns a list of posts within a category
     *
     * @param category       The category id
     * @param optionalParams A map of optional parameters
     * @return A list of posts
     * @throws ApiException
     * @see <a href="https://disqus.com/api/docs/categories/listPosts/">Documentation</a>
     */
    @GET("/categories/listPosts.json")
    public Response<List<Post>> listPosts(@Query("category") long category,
                                          @QueryMap Map<String, String> optionalParams)
            throws ApiException;

    /**
     * Returns a list of threads within a category sorted by the date created
     *
     * @param category The category id
     * @return A list of threads
     * @throws ApiException
     * @see <a href="https://disqus.com/api/docs/categories/listThreads/">Documentation</a>
     */
    @GET("/categories/listThreads.json")
    public Response<List<Thread>> listThreads(@Query("category") long category)
            throws ApiException;

    /**
     * Returns a list of threads within a category sorted by the date created
     *
     * @param category       The category id
     * @param optionalParams A map of optional parameters
     * @return A list of threads
     * @throws ApiException
     * @see <a href="https://disqus.com/api/docs/categories/listThreads/">Documentation</a>
     */
    @GET("/categories/listThreads.json")
    public Response<List<Thread>> listThreads(@Query("category") long category,
                                              @QueryMap Map<String, String> optionalParams)
            throws ApiException;

}
