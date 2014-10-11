package me.philio.disqus.api.model.forums;

import com.google.gson.annotations.SerializedName;

import me.philio.disqus.api.model.Image;

/**
 * Forum
 */
public class ForumDetails {

    @SerializedName("name")
    public String name;

    @SerializedName("founder")
    public int founder;

    @SerializedName("settings")
    public Settings settings;

    @SerializedName("url")
    public String url;

    @SerializedName("guidelines")
    public String guidelines;

    @SerializedName("favicon")
    public Image favicon;

    @SerializedName("language")
    public String language;

    @SerializedName("avatar")
    public ForumAvatar avatar;

    @SerializedName("id")
    public String id;

    @SerializedName("channel")
    public String channel;

}
