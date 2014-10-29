package me.philio.disqus.api.model.forum;

import com.google.gson.annotations.SerializedName;

import me.philio.disqus.api.model.user.User;

/**
 * Forum moderator
 */
public class Moderator {

    @SerializedName("id")
    public long id;

    @SerializedName("forum")
    public String forum;

    @SerializedName("user")
    public User user;

}
