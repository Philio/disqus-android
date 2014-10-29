package me.philio.disqus.api.model.posts;

import com.google.gson.annotations.SerializedName;

/**
 * Vote
 */
public class Vote {

    @SerializedName("dislikesDelta")
    public int dislikesDelta;

    @SerializedName("likesDelta")
    public int likesDelta;

    @SerializedName("delta")
    public int delta;

    @SerializedName("vote")
    public int vote;

    @SerializedName("post")
    public Post post;

}
