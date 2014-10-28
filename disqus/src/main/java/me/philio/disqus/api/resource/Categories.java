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
package me.philio.disqus.api.resource;

import java.util.List;
import java.util.Map;

import me.philio.disqus.api.exception.ApiException;
import me.philio.disqus.api.model.Response;
import me.philio.disqus.api.model.category.CategoryDetails;
import me.philio.disqus.api.model.post.PostDetails;
import me.philio.disqus.api.model.thread.ThreadDetails;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;
import retrofit.http.QueryMap;

/**
 * Categories resource
 */
public interface Categories {

    /**
     * Creates a new category
     *
     * @param forum
     * @param title
     * @return
     * @throws ApiException
     */
    @POST("/categories/create.json")
    public Response<CategoryDetails> create(@Query("forum") String forum,
                                            @Query("title") String title) throws ApiException;

    /**
     * Creates a new category
     *
     * @param forum
     * @param title
     * @param isDefault
     * @return
     * @throws ApiException
     */
    @POST("/categories/create.json")
    public Response<CategoryDetails> create(@Query("forum") String forum,
                                            @Query("title") String title,
                                            @Query("default") int isDefault) throws ApiException;

    /**
     * Returns category details
     *
     * @param category
     * @return
     * @throws ApiException
     */
    @GET("/categories/details.json")
    public Response<CategoryDetails> details(@Query("category") long category) throws ApiException;

    /**
     * Returns a list of categories within a forum
     *
     * @param forum
     * @return
     * @throws ApiException
     */
    @GET("/categories/list.json")
    public Response<List<CategoryDetails>> list(@Query("forum") String forum) throws ApiException;

    /**
     * Returns a list of categories within a forum
     *
     * @param forums
     * @return
     * @throws ApiException
     */
    @GET("/categories/list.json")
    public Response<List<CategoryDetails>> list(@Query("forum") String[] forums)
            throws ApiException;

    /**
     * Returns a list of categories within a forum
     *
     * @param forum
     * @param optionalParams
     * @return
     * @throws ApiException
     */
    @GET("/categories/list.json")
    public Response<List<CategoryDetails>> list(@Query("forum") String forum,
                                                @QueryMap Map<String, String> optionalParams)
            throws ApiException;

    /**
     * Returns a list of categories within a forum
     *
     * @param forums
     * @param optionalParams
     * @return
     * @throws ApiException
     */
    @GET("/categories/list.json")
    public Response<List<CategoryDetails>> list(@Query("forum") String[] forums,
                                                @QueryMap Map<String, String> optionalParams)
            throws ApiException;

    /**
     * Returns a list of posts within a category
     *
     * @param category
     * @return
     * @throws ApiException
     */
    @GET("/categories/listPosts.json")
    public Response<List<PostDetails>> listPosts(@Query("category") long category)
            throws ApiException;

    /**
     * Returns a list of posts within a category
     *
     * @param category
     * @param optionalParams
     * @return
     * @throws ApiException
     */
    @GET("/categories/listPosts.json")
    public Response<List<PostDetails>> listPosts(@Query("category") long category,
                                                 @QueryMap Map<String, String> optionalParams)
            throws ApiException;

    /**
     * Returns a list of threads within a category sorted by the date created
     *
     * @param category
     * @return
     * @throws ApiException
     */
    @GET("/categories/listThreads.json")
    public Response<List<ThreadDetails>> listThreads(@Query("category") long category)
            throws ApiException;

    /**
     * Returns a list of threads within a category sorted by the date created
     * 
     * @param category
     * @param optionalParams
     * @return
     * @throws ApiException
     */
    @GET("/categories/listThreads.json")
    public Response<List<ThreadDetails>> listThreads(@Query("category") long category,
                                                     @QueryMap Map<String, String> optionalParams)
            throws ApiException;

}
