package me.philio.disqus.api.model.forums;

import com.google.gson.annotations.SerializedName;

import me.philio.disqus.api.model.Image;

/**
 * Avatar
 */
public class ForumAvatar {

    @SerializedName("small")
    public Image small;

    @SerializedName("large")
    public Image large;

}
