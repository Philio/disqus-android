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
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import me.philio.disqus.api.http.HttpResponse;
import me.philio.disqus.api.model.Response;

/**
 * Applications api methods
 */
public class Applications extends AbstractApi {

    /**
     * Set api key and access token
     *
     * @param apiKey
     * @param accessToken
     */
    public Applications(String apiKey, String accessToken) {
        super(apiKey, accessToken);
    }

    /**
     *
     * @param application
     * @param days
     * @return
     * @throws IOException
     */
    public Response<Map<Date, Integer>> listUsage(Integer application, Integer days) throws IOException {
        // Build uri
        Uri.Builder builder = Uri.parse("https://disqus.com/api/3.0/applications/listUsage.json")
                .buildUpon();
        appendAuth(builder);
        appendInt(builder, "application", application, true);
        appendInt(builder, "days", days, true);

        // Send request
        HttpResponse response = mRequest.get(builder.build());

        // Create parser with custom type adapter to handle the nested multi-type arrays
        Type adapterType = new TypeToken<Map<Date, Integer>>() {}.getType();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .registerTypeAdapter(adapterType, new UsageDeserializer()).create();

        // Parse JSON response
        Type type = new TypeToken<Response<Map<Date, Integer>>>() {}.getType();
        return gson.fromJson(response.getBody(), type);
    }

    /**
     * Custom deserializer to process usage data
     */
    private class UsageDeserializer implements JsonDeserializer<Map<Date, Integer>> {

        @Override
        public Map<Date, Integer> deserialize(JsonElement json, Type typeOfT,
                                                JsonDeserializationContext context)
                throws JsonParseException {
            // Create map
            Map<Date, Integer> map = new HashMap<Date, Integer>();

            // JSON element shoud be an array
            if (json.isJsonArray()) {
                for (JsonElement element : json.getAsJsonArray()) {
                    // Each element should be an array containing a date and int
                    if (element.isJsonArray() && element.getAsJsonArray().size() == 2) {
                        JsonArray array = element.getAsJsonArray();
                        map.put(mGson.fromJson(array.get(0), Date.class), array.get(1).getAsInt());
                    }
                }
            }
            return map;
        }

    }

}
