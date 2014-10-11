package me.philio.disqus.api.model.users;

import com.google.gson.annotations.SerializedName;

/**
 * Connection
 */
public class Connection {

    @SerializedName("url")
    public String url;

    @SerializedName("id")
    public String id;

    @SerializedName("name")
    public String name;

}
