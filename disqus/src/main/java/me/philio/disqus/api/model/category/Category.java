package me.philio.disqus.api.model.category;

import com.google.gson.annotations.SerializedName;

/**
 * Category details
 */
public class Category {

    @SerializedName("id")
    public long id;

    @SerializedName("forum")
    public String forum;

    @SerializedName("order")
    public int order;

    @SerializedName("isDefault")
    public boolean isDefault;

    @SerializedName("title")
    public String title;

}
