package me.philio.disqus.api.model.forums;

import com.google.gson.annotations.SerializedName;

/**
 * Settings
 */
public class Settings {

    @SerializedName("canEnableSponsoredComments")
    public boolean canEnableSponsoredComments;

    @SerializedName("hasCustomAvatar")
    public boolean hasCustomAvatar;

    @SerializedName("allowAnonPost")
    public boolean allowAnonPost;

    @SerializedName("allowMedia")
    public boolean allowMedia;

    @SerializedName("disable3rdPartyTrackers")
    public boolean disable3rdPartyTrackers;

    @SerializedName("audienceSyncEnabled")
    public boolean audienceSyncEnabled;

    @SerializedName("discoveryLocked")
    public boolean discoveryLocked;

    @SerializedName("ssoRequired")
    public boolean ssoRequired;

    @SerializedName("sponsoredCommentsEnabled")
    public boolean sponsoredCommentsEnabled;

}
