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
package me.philio.disqus.api;

import android.net.Uri;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import me.philio.disqus.api.http.HttpResponse;
import me.philio.disqus.api.model.Response;
import me.philio.disqus.api.model.blacklist.BlacklistEntry;

/**
 * Blacklists api methods
 */
public class Blacklists extends AbstractApi {

    /**
     * Set api key and access token
     *
     * @param apiKey
     * @param accessToken
     */
    public Blacklists(String apiKey, String accessToken) {
        super(apiKey, accessToken);
    }

    /**
     * Set api key, secret and access token
     *
     * @param apiKey
     * @param apiSecret
     * @param accessToken
     */
    public Blacklists(String apiKey, String apiSecret, String accessToken) {
        super(apiKey, apiSecret, accessToken);
    }

    /**
     * Adds an entry to the blacklist.
     *
     * Requires secret key.
     *
     * @see <a href="https://disqus.com/api/docs/blacklists/add/">Documentation</a>
     * @param forum
     * @param type
     * @param value
     * @param retroactive
     * @param notes
     * @return
     * @throws IOException
     */
    public Response<List<BlacklistEntry>> add(String forum, BlacklistEntry.Type type, String value,
                                              boolean retroactive, String notes)
            throws IOException {
        return add(forum, type, new String[]{value}, retroactive, notes);
    }

    /**
     * Adds multiple entries to the blacklist.
     *
     * Requires secret key.
     *
     * @see <a href="https://disqus.com/api/docs/blacklists/add/">Documentation</a>
     * @param forum
     * @param type
     * @param values
     * @param retroactive
     * @param notes
     * @return
     * @throws IOException
     */
    public Response<List<BlacklistEntry>> add(String forum, BlacklistEntry.Type type,
                                              String[] values, boolean retroactive, String notes)
            throws IOException {
        // Build uri
        Uri.Builder builder = new Uri.Builder();
        appendAuth(builder);
        appendString(builder, "forum", forum);
        for (String value : values) {
            appendString(builder, type.name(), value);
        }
        if (retroactive) {
            appendInt(builder, "retroactive", 1, true);
        }
        appendString(builder, "notes", notes);

        // Send request
        HttpResponse response = mRequest.post(
                Uri.parse("https://disqus.com/api/3.0/blacklists/add.json"),
                builder.build().getQuery());

        // Parse JSON response
        Type jsonType = new TypeToken<Response<List<BlacklistEntry>>>() {}.getType();
        return mGson.fromJson(response.getBody(), jsonType);
    }

    public void list() throws Exception {
        throw new Exception("Stub! Not implemented yet");
    }

    public void remove() throws Exception {
        throw new Exception("Stub! Not implemented yet");
    }

}
