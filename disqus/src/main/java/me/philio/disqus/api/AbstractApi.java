package me.philio.disqus.api;

import android.net.Uri;

import com.google.gson.Gson;

import me.philio.disqus.api.http.HttpRequest;

public abstract class AbstractApi {

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
     * @return
     */
    protected void appendInt(Uri.Builder builder, String key, Integer value, boolean positive) {
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
     * Add order query param based on enum value
     *
     * @param builder
     * @param order
     */
    protected void appendOrder(Uri.Builder builder, Order order) {
        if (order != null) {
            builder.appendQueryParameter("order", order.name().toLowerCase());
        }
    }

    /**
     * Orders
     */
    public enum Order {
        ASC, DESC
    }

}
