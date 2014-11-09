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
package me.philio.disqus.api.model.blacklists;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

import me.philio.disqus.api.model.users.User;

/**
 * Entry in the blacklist
 */
public class Entry {

    /**
     * Entry types
     */
    public enum Type {
        domain,
        word,
        ip,
        user,
        email
    }

    @SerializedName("forum")
    public Object forum;

    @SerializedName("notes")
    public String notes;

    @SerializedName("value")
    public Object value;

    @SerializedName("conflictingWhitelistRemoved")
    public boolean conflictingWhitelistRemoved;

    @SerializedName("type")
    public Type type;

    @SerializedName("id")
    public long id;

    @SerializedName("createdAt")
    public Date createdAt;

}
