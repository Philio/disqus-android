package me.philio.disqus.api.model.users;

import com.google.gson.annotations.SerializedName;

/**
 * Connections
 */
public class Connections {

    @SerializedName("twitter")
    public Connection twitter;

    @SerializedName("google")
    public Connection google;

    @SerializedName("facebook")
    public Connection facebook;

}
