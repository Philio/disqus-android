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
