package me.philio.disqus.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * Response from api
 *
 * Response can be an object or an array of objects. Single objects will be converted into an array
 * for consistency
 */
public class Response<T> {

    @SerializedName("cursor")
    public Cursor cursor;

    @SerializedName("code")
    public int code;

    @SerializedName("response")
    public T[] response;

}
