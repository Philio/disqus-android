package me.philio.disqus.api.model.imports;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Import details
 */
public class Import {

    @SerializedName("forum")
    public String forum;

    @SerializedName("chunksTotal")
    public int chunksTotal;

    @SerializedName("platform")
    public String platform;

    @SerializedName("finishedAt")
    public Date finishedAt;

    @SerializedName("startedAt")
    public Date startedAt;

    @SerializedName("statusName")
    public String statusName;

    @SerializedName("chunksDone")
    public int chunksDone;

    @SerializedName("id")
    public long id;

    @SerializedName("statusCode")
    public int statusCode;

}