package me.philio.disqus.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * Response
 */
public class Response<T> {

    /**
     * Disqus cursor
     */
    @SerializedName("cursor")
    public Cursor cursor;

    /**
     * Error code
     */
    @SerializedName("code")
    public int code;

    /**
     * Response object
     * <p/>
     * Object, array of objects or string for errors
     */
    @SerializedName("response")
    public T response;

}
