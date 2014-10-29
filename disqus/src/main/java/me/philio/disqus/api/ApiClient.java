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
package me.philio.disqus.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import me.philio.disqus.DisqusConstants;
import me.philio.disqus.api.exception.ApiException;
import me.philio.disqus.api.exception.BadRequestException;
import me.philio.disqus.api.exception.ForbiddenException;
import me.philio.disqus.api.gson.BlacklistValueDeserializer;
import me.philio.disqus.api.gson.UsageDeserializer;
import me.philio.disqus.api.model.applications.Usage;
import me.philio.disqus.api.model.blacklists.BlacklistValue;
import me.philio.disqus.api.resources.Applications;
import me.philio.disqus.api.resources.Blacklists;
import me.philio.disqus.api.resources.Categories;
import me.philio.disqus.api.resources.Exports;
import me.philio.disqus.api.resources.Feeds;
import me.philio.disqus.api.resources.Forums;
import me.philio.disqus.api.resources.Imports;
import me.philio.disqus.api.resources.Media;
import me.philio.disqus.api.resources.Notes;
import me.philio.disqus.api.resources.Posts;
import me.philio.disqus.api.resources.Users;
import me.philio.disqus.api.resources.notes.Templates;
import retrofit.ErrorHandler;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

/**
 * A wrapper round the Retrofit {@link RestAdapter}
 */
public class ApiClient {

    /**
     * Base URL for all Disqus endpoints
     */
    private static final String BASE_URL = "https://disqus.com/api/3.0";

    /**
     * User agent
     */
    private static final String USER_AGENT = "Disqus Android/0.1";

    /**
     * Configuration
     */
    private ApiConfig mConfig;

    /**
     * Rest adapter
     */
    private RestAdapter mAdapter;

    /**
     * Set config and set up the {@link RestAdapter}
     *
     * @param config
     */
    public ApiClient(ApiConfig config) {
        // Set config
        mConfig = config;

        // Build Gson with Disqus date format and type adapters
        Gson gson = new GsonBuilder()
                .setDateFormat(DisqusConstants.DATE_FORMAT)
                .registerTypeAdapter(Usage.class, new UsageDeserializer())
                .registerTypeAdapter(BlacklistValue.class, new BlacklistValueDeserializer())
                .create();

        // Build RestAdapter
        mAdapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .setLogLevel(RestAdapter.LogLevel.BASIC)
                .setErrorHandler(new ErrorHandler() {
                    @Override
                    public Throwable handleError(RetrofitError cause) {
                        Response response = cause.getResponse();
                        if (response != null) {
                            switch (response.getStatus()) {
                                case 400:
                                    return new BadRequestException(cause);
                                case 401:
                                    return new ForbiddenException(cause);
                            }
                        }
                        return new ApiException(cause);
                    }
                })
                .setRequestInterceptor(new RequestInterceptor() {

                    @Override
                    public void intercept(RequestFacade request) {
                        request.addHeader("User-Agent", USER_AGENT);
                        if (mConfig != null) {
                            // Public/secret key query params
                            if (mConfig.getApiSecret() != null) {
                                request.addQueryParam("api_secret", mConfig.getApiSecret());
                            } else if (mConfig.getApiKey() != null) {
                                request.addQueryParam("api_key", mConfig.getApiKey());
                            }

                            // Access token query param
                            if (mConfig.getAccessToken() != null) {
                                request.addQueryParam("access_token", mConfig.getAccessToken());
                            }

                            // Referrer
                            if (mConfig.getReferrer() != null) {
                                request.addHeader("Referer", mConfig.getReferrer());
                            }
                        }
                    }

                })
                .setConverter(new GsonConverter(gson))
                .build();
    }

    /**
     * Create applications resource
     *
     * @return
     */
    public Applications createApplications() {
        return mAdapter.create(Applications.class);
    }

    /**
     * Create blacklists resource
     *
     * @return
     */
    public Blacklists createBlacklists() {
        return mAdapter.create(Blacklists.class);
    }

    /**
     * Create categories resource
     *
     * @return
     */
    public Categories createCategories() {
        return mAdapter.create(Categories.class);
    }

    /**
     * Create exports resource
     *
     * @return
     */
    public Exports createExports() {
        return mAdapter.create(Exports.class);
    }

    /**
     * Create feeds resource
     *
     * @return
     */
    public Feeds createFeeds() {
        return mAdapter.create(Feeds.class);
    }

    /**
     * Create forums resource
     *
     * @return
     */
    public Forums createForums() {
        return mAdapter.create(Forums.class);
    }

    /**
     * Create imports resource
     *
     * @return
     */
    public Imports createImports() {
        return mAdapter.create(Imports.class);
    }

    /**
     * Create media resource
     *
     * @return
     */
    public Media createMedia() {
        return mAdapter.create(Media.class);
    }

    /**
     * Create notes resource
     *
     * @return
     */
    public Notes createNotes() {
        return mAdapter.create(Notes.class);
    }

    /**
     * Create notes/templates resource
     *
     * @return
     */
    public Templates createNotesTemplates() {
        return mAdapter.create(Templates.class);
    }

    /**
     * Create posts resource
     *
     * @return
     */
    public Posts createPosts() {
        return mAdapter.create(Posts.class);
    }

    /**
     * Create users resource
     *
     * @return
     */
    public Users createUsers() {
        return mAdapter.create(Users.class);
    }

}
