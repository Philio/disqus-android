/*
 * Copyright 2014 Phil Bayfield
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.philio.disqus.api.model.post;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import me.philio.disqus.api.model.user.UserDetails;

/**
 * Post details
 */
public class PostDetails {

    @SerializedName("isHighlighted")
    public boolean isHighlighted;

    @SerializedName("isFlagged")
    public boolean isFlagged;

    @SerializedName("forum")
    public String forum;

    @SerializedName("parent")
    public long parent;

    @SerializedName("author")
    public UserDetails author;

    @SerializedName("media")
    public List<Media> media;

    @SerializedName("isApproved")
    public boolean isApproved;

    @SerializedName("dislikes")
    public int dislikes;

    @SerializedName("raw_message")
    public String rawMessage;

    @SerializedName("isSpam")
    public boolean isSpam;

    @SerializedName("userScore")
    public int userScore;

    @SerializedName("thread")
    public String thread;

    @SerializedName("points")
    public int points;

    @SerializedName("createdAt")
    public String createdAt;

    @SerializedName("isEdited")
    public boolean isEdited;

    @SerializedName("message")
    public String message;

    @SerializedName("numReports")
    public int numReports;

    @SerializedName("id")
    public long id;

    @SerializedName("isDeleted")
    public boolean isDeleted;

    @SerializedName("likes")
    public int likes;

}
