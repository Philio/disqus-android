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

import me.philio.disqus.api.exception.ApiException;
import me.philio.disqus.api.model.Response;
import me.philio.disqus.api.model.applications.Usage;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Applications resource
 *
 * @see <a href="https://disqus.com/api/docs/applications/">Documentation</a>
 */
public interface Applications {

    /**
     * Returns the API usage per day for this application
     *
     * @see <a href="https://disqus.com/api/docs/applications/listUsage/">Documentation</a>
     * @param application
     * @param days
     * @return
     * @throws ApiException
     */
    @GET("/applications/listUsage.json")
    public Response<Usage> listUsage(@Query("application") String application,
                                     @Query("days") Integer days) throws ApiException;

}
