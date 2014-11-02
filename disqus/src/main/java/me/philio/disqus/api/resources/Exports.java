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
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Exports resource
 *
 * @see <a href="https://disqus.com/api/docs/exports/">Documentation</a>
 */
public interface Exports {

    /**
     * Triggers an export of the forum which is sent via email. API returns an empty object for this
     * request. Response param format only has a single option so has been omitted.
     *
     * @see <a href="https://disqus.com/api/docs/exports/exportForum/">Documentation</a>
     * @param forum
     * @return
     * @throws ApiException
     */
    @POST("/exports/exportForum.json")
    public Response<Object> exportForum(@Query("forum") String forum) throws ApiException;

}