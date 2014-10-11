package me.philio.disqus.api.model.users;

import com.google.gson.annotations.SerializedName;

import me.philio.disqus.api.model.Image;

/**
 * Avatar
 */
public class UserAvatar extends Image {

    @SerializedName("isCustom")
    public boolean isCustom;

    @SerializedName("small")
    public Image small;

    @SerializedName("large")
    public Image large;

}
