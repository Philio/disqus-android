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
import me.philio.disqus.api.model.threads.Thread;
import retrofit.http.POST;
import retrofit.http.Query;
import retrofit.http.QueryMap;

/**
 * Threads resource
 *
 * @see <a href="https://disqus.com/api/docs/threads/">Documentation</a>
 */
public interface Threads {

    /**
     * Closes a thread
     *
     * @see <a href="https://disqus.com/api/docs/threads/close/">Documentation</a>
     * @param thread
     * @return
     */
    @POST("/threads/close.json")
    public Response<List<Thread>> close(@Query("thread") long thread) throws ApiException;

    /**
     * Closes a thread
     *
     * @see <a href="https://disqus.com/api/docs/threads/close/">Documentation</a>
     * @param thread
     * @return
     */
    @POST("/threads/close.json")
    public Response<List<Thread>> close(@Query("thread") long[] thread) throws ApiException;

    /**
     * Creates a new thread
     *
     * @param forum
     * @param title
     * @return
     * @throws ApiException
     */
    @POST("/threads/create.json")
    public Response<Thread> create(@Query("forum") String forum,
                                   @Query("title") String title) throws ApiException;

    /**
     * Creates a new thread
     *
     * @param forum
     * @param title
     * @param optionalParams
     * @return
     * @throws ApiException
     */
    @POST("/threads/create.json")
    public Response<Thread> create(@Query("forum") String forum,
                                   @Query("title") String title,
                                   @QueryMap Map<String, String> optionalParams)
            throws ApiException;

    @POST("/threads/details.json")
    public Response<Thread> details(@Query("thread") long thread) throws ApiException;

}
