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

import android.net.Uri;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.HttpURLConnection;

import me.philio.disqus.api.exception.ApiException;
import me.philio.disqus.api.exception.BadRequestException;
import me.philio.disqus.api.exception.ForbiddenException;
import me.philio.disqus.api.http.HttpRequest;
import me.philio.disqus.api.http.HttpResponse;
import me.philio.disqus.api.model.Response;

public abstract class AbstractApi {

    /**
     * Order
     */
    public enum Order {
        asc, desc
    }

    /**
     * Related
     */
    public enum Related {
        forum,
        author,
        category
    }

    /**
     * Include
     */
    public enum Include {
        open,
        closed,
        killed
    }

    /**
     * Http request instance
     */
    protected HttpRequest mRequest = new HttpRequest();

    /**
     * Gson instance
     */
    protected Gson mGson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();

    /**
     * Api key
     */
    protected String mApiKey;

    /**
     * Api secret
     */
    protected String mApiSecret;

    /**
     * Access token
     */
    protected String mAccessToken;

    /**
     * Configure api
     *
     * @param config
     */
    public AbstractApi(ApiConfig config) {
        if (config != null) {
            if (config.getApiKey() != null) {
                mApiKey = config.getApiKey();
            }
            if (config.getApiSecret() != null) {
                mApiSecret = config.getApiSecret();
            }
            if (config.getAccessToken() != null) {
                mAccessToken = config.getAccessToken();
            }
            if (config.getReferrer() != null) {
                mRequest.setReferrer(config.getReferrer());
            }
        }
    }

    /**
     * Add authentication params
     *
     * @param builder
     */
    protected void appendAuth(Uri.Builder builder) {
        if (mApiSecret != null) {
            builder.appendQueryParameter("api_secret", mApiSecret);
        } else if (mApiKey != null) {
            builder.appendQueryParameter("api_key", mApiKey);
        }
        if (mAccessToken != null) {
            builder.appendQueryParameter("access_token", mAccessToken);
        }
    }

    /**
     * Add an int query param
     *
     * @param builder
     * @param key
     * @param value
     * @param positive
     */
    protected void appendInt(Uri.Builder builder, String key, Integer value, boolean positive) {
        if (value != null && (!positive || value > 0)) {
            builder.appendQueryParameter(key, value.toString());
        }
    }

    /**
     * Add a long query param
     *
     * @param builder
     * @param key
     * @param value
     * @param positive
     */
    protected void appendLong(Uri.Builder builder, String key, Long value, boolean positive) {
        if (value != null && (!positive || value > 0)) {
            builder.appendQueryParameter(key, value.toString());
        }
    }

    /**
     * Add a string query param
     *
     * @param builder
     * @param key
     * @param value
     */
    protected void appendString(Uri.Builder builder, String key, String value) {
        if (value != null) {
            builder.appendQueryParameter(key, value);
        }
    }

    /**
     * Add order query param
     *
     * @param builder
     * @param order
     */
    protected void appendOrder(Uri.Builder builder, Order order) {
        if (order != null) {
            builder.appendQueryParameter("order", order.name());
        }
    }

    /**
     * Add related query param
     *
     * @param builder
     * @param relateds
     */
    protected void appendRelated(Uri.Builder builder, Related[] relateds) {
        if (relateds != null) {
            for (Related related : relateds) {
                builder.appendQueryParameter("related", related.name());
            }
        }
    }

    /**
     * Add include query param
     *
     * @param builder
     * @param includes
     */
    protected void appendIncludes(Uri.Builder builder, Include[] includes) {
        if (includes != null) {
            for (Include include : includes) {
                builder.appendQueryParameter("include", include.name());
            }
        }
    }

    /**
     * Check the api response for errors
     *
     * @param httpResponse
     * @throws ApiException
     */
    protected void checkResponse(HttpResponse httpResponse) throws ApiException {
        // If HTTP/200 request was successful
        if (httpResponse.getCode() == HttpURLConnection.HTTP_OK) {
            return;
        }

        // Parse error
        Type type = new TypeToken<Response<String>>() {}.getType();
        Response<String> response = mGson.fromJson(httpResponse.getBody(), type);

        // Throw exception based on HTTP response
        switch (httpResponse.getCode()) {
            case HttpURLConnection.HTTP_BAD_REQUEST:
                throw new BadRequestException(response.response);
            case HttpURLConnection.HTTP_FORBIDDEN:
                throw new ForbiddenException(response.response);
            default:
                throw new ApiException(response.response);
        }
    }

}
