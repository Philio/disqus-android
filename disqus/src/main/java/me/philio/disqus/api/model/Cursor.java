package me.philio.disqus.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * Response cursor
 */
public class Cursor {

    @SerializedName("prev")
    public String previous;

    @SerializedName("hasNext")
    public boolean hasNext;

    @SerializedName("next")
    public String next;

    @SerializedName("hasPrev")
    public boolean hasPrevious;

    @SerializedName("total")
    public int total;

    @SerializedName("id")
    public String id;

    @SerializedName("more")
    public boolean more;

}
