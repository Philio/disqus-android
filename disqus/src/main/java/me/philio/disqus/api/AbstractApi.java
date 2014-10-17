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

import me.philio.disqus.api.http.HttpRequest;

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
    protected Gson mGson = new Gson();

    /**
     * Api key
     */
    protected String mApiKey;

    /**
     * Access token
     */
    protected String mAccessToken;

    /**
     * Set api key and access token
     *
     * @param apiKey
     * @param accessToken
     */
    public AbstractApi(String apiKey, String accessToken) {
        mApiKey = apiKey;
        mAccessToken = accessToken;
    }

    /**
     * Add authentication params
     *
     * @param builder
     */
    protected void appendAuth(Uri.Builder builder) {
        if (mApiKey != null) {
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

}
