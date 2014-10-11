package me.philio.disqus.api.model.users;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * User details
 */
public class UserDetails {

    @SerializedName("isFollowing")
    public boolean isFollowing;

    @SerializedName("disable3rdPartyTrackers")
    public boolean disable3rdPartyTrackers;

    @SerializedName("isFollowedBy")
    public boolean isFollowedBy;

    @SerializedName("connections")
    public Connections connections;

    @SerializedName("isPrimary")
    public boolean isPrimary;

    @SerializedName("id")
    public int id;

    @SerializedName("numFollowers")
    public int numFollowers;

    @SerializedName("rep")
    public double rep;

    @SerializedName("numFollowing")
    public int numFollowing;

    @SerializedName("numPosts")
    public int numPosts;

    @SerializedName("location")
    public String location;

    @SerializedName("isPrivate")
    public boolean isPrivate;

    @SerializedName("joinedAt")
    public String joinedAt;

    @SerializedName("username")
    public String username;

    @SerializedName("numLikesReceived")
    public int numLikesReceived;

    @SerializedName("about")
    public String about;

    @SerializedName("name")
    public String name;

    @SerializedName("url")
    public String url;

    @SerializedName("numForumsFollowing")
    public int numForumsFollowing;

    @SerializedName("profileUrl")
    public String profileUrl;

    @SerializedName("reputation")
    public double reputation;

    @SerializedName("avatar")
    public UserAvatar avatar;

    @SerializedName("isAnonymous")
    public boolean isAnonymous;

}
