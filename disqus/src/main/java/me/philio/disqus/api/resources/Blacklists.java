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
import me.philio.disqus.api.model.blacklists.Entry;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;
import retrofit.http.QueryMap;

/**
 * Blacklists resource
 *
 * @see <a href="https://disqus.com/api/docs/blacklists/">Documentation</a>
 */
public interface Blacklists {

    /**
     * Adds a domain entry/entries to the blacklist
     *
     * @param forum       The forum short name
     * @param domains     An array of domains to add
     * @param retroactive Apply to dates in the past
     * @param notes       Note to add to the entry
     * @return A list of the entries added
     * @throws ApiException
     * @see <a href="https://disqus.com/api/docs/blacklists/add/">Documentation</a>
     */
    @POST("/blacklists/add.json")
    public Response<List<Entry>> addDomains(@Query("forum") String forum,
                                            @Query("domain") String[] domains,
                                            @Query("retroactive") int retroactive,
                                            @Query("notes") String notes)
            throws ApiException;

    /**
     * Adds a word entry/entries to the blacklist
     *
     * @param forum       The forum short name
     * @param words       An array of words to add
     * @param retroactive Apply to dates in the past
     * @param notes       Note to add to the entry
     * @return A list of the entries added
     * @throws ApiException
     * @see <a href="https://disqus.com/api/docs/blacklists/add/">Documentation</a>
     */
    @POST("/blacklists/add.json")
    public Response<List<Entry>> addWords(@Query("forum") String forum,
                                          @Query("word") String[] words,
                                          @Query("retroactive") int retroactive,
                                          @Query("notes") String notes)
            throws ApiException;

    /**
     * Adds an IP entry/entries to the blacklist
     *
     * @param forum       The forum short name
     * @param ips         An array of ips to add
     * @param retroactive Apply to dates in the past
     * @param notes       Note to add to the entry
     * @return A list of the entries added
     * @throws ApiException
     * @see <a href="https://disqus.com/api/docs/blacklists/add/">Documentation</a>
     */
    @POST("/blacklists/add.json")
    public Response<List<Entry>> addIps(@Query("forum") String forum,
                                        @Query("ip") String[] ips,
                                        @Query("retroactive") int retroactive,
                                        @Query("notes") String notes) throws ApiException;

    /**
     * Adds a user entry/entries to the blacklist
     *
     * @param forum       The forum short name
     * @param users       An array of user ids to add
     * @param retroactive Apply to dates in the past
     * @param notes       Note to add to the entry
     * @return A list of the entries added
     * @throws ApiException
     * @see <a href="https://disqus.com/api/docs/blacklists/add/">Documentation</a>
     */
    @POST("/blacklists/add.json")
    public Response<List<Entry>> addUsers(@Query("forum") String forum,
                                          @Query("user") Long[] users,
                                          @Query("retroactive") int retroactive,
                                          @Query("notes") String notes)
            throws ApiException;

    /**
     * Adds an email entry/entries to the blacklist
     *
     * @param forum       The forum short name
     * @param emails      An array of emails to add
     * @param retroactive Apply to dates in the past
     * @param notes       Note to add to the entry
     * @return A list of the entries added
     * @throws ApiException
     * @see <a href="https://disqus.com/api/docs/blacklists/add/">Documentation</a>
     */
    @POST("/blacklists/add.json")
    public Response<List<Entry>> addEmails(@Query("forum") String forum,
                                           @Query("email") String[] emails,
                                           @Query("retroactive") int retroactive,
                                           @Query("notes") String notes)
            throws ApiException;

    /**
     * Returns a list of all blacklist entries
     *
     * @param forum The forum short name
     * @return A list of the entries
     * @throws ApiException
     * @see <a href="https://disqus.com/api/docs/blacklists/list/">Documentation</a>
     */
    @GET("/blacklists/list.json")
    public Response<List<Entry>> list(@Query("forum") String forum) throws ApiException;

    /**
     * Returns a list of all blacklist entries
     *
     * @param forum          The forum short name
     * @param optionalParams A map of optional parameters
     * @return A list of the entries
     * @throws ApiException
     * @see <a href="https://disqus.com/api/docs/blacklists/list/">Documentation</a>
     */
    @GET("/blacklists/list.json")
    public Response<List<Entry>> list(@Query("forum") String forum,
                                      @QueryMap Map<String, String> optionalParams)
            throws ApiException;

    /**
     * Returns a list of all blacklist entries
     *
     * @param forum          The forum short name
     * @param related        Get relations in response
     * @param optionalParams A map of optional parameters
     * @return A list of the entries
     * @throws ApiException
     * @see <a href="https://disqus.com/api/docs/blacklists/list/">Documentation</a>
     */
    @GET("/blacklists/list.json")
    public Response<List<Entry>> list(@Query("forum") String forum,
                                      @Query("related") String[] related,
                                      @QueryMap Map<String, String> optionalParams)
            throws ApiException;

    /**
     * Removes a domain entry/entries to the blacklist
     *
     * @param forum   The forum short name
     * @param domains An array of domains to remove
     * @return A list of the entries removed
     * @throws ApiException
     * @see <a href="https://disqus.com/api/docs/blacklists/remove/">Documentation</a>
     */
    @POST("/blacklists/remove.json")
    public Response<List<Entry>> removeDomains(@Query("forum") String forum,
                                               @Query("domain") String[] domains)
            throws ApiException;

    /**
     * Removes a word entry/entries to the blacklist
     *
     * @param forum The forum short name
     * @param words An array of words to remove
     * @return A list of the entries removed
     * @throws ApiException
     * @see <a href="https://disqus.com/api/docs/blacklists/remove/">Documentation</a>
     */
    @POST("/blacklists/remove.json")
    public Response<List<Entry>> removeWords(@Query("forum") String forum,
                                             @Query("word") String[] words)
            throws ApiException;

    /**
     * Removes an IP entry/entries to the blacklist
     *
     * @param forum The forum short name
     * @param ips   An array of ips to remove
     * @return A list of the entries removed
     * @throws ApiException
     * @see <a href="https://disqus.com/api/docs/blacklists/remove/">Documentation</a>
     */
    @POST("/blacklists/remove.json")
    public Response<List<Entry>> removeIps(@Query("forum") String forum,
                                           @Query("ip") String[] ips) throws ApiException;

    /**
     * Removes a user entry/entries to the blacklist
     *
     * @param forum The forum short name
     * @param users An array of user ids to remove
     * @return A list of the entries removed
     * @throws ApiException
     * @see <a href="https://disqus.com/api/docs/blacklists/remove/">Documentation</a>
     */
    @POST("/blacklists/remove.json")
    public Response<List<Entry>> removeUsers(@Query("forum") String forum,
                                             @Query("user") Long[] users)
            throws ApiException;

    /**
     * Removes an email entry/entries to the blacklist
     *
     * @param forum  The forum short name
     * @param emails An array of emails to remove
     * @return A list of the entries removed
     * @throws ApiException
     * @see <a href="https://disqus.com/api/docs/blacklists/remove/">Documentation</a>
     */
    @POST("/blacklists/remove.json")
    public Response<List<Entry>> removeEmails(@Query("forum") String forum,
                                              @Query("email") String[] emails)
            throws ApiException;

}
