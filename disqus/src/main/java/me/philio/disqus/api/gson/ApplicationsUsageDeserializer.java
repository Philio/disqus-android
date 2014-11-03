package me.philio.disqus.api.gson;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Date;

import me.philio.disqus.api.model.applications.Usage;

/**
 * Custom deserializer to convert Disqus usage nested arrays into a map
 */
public class ApplicationsUsageDeserializer implements JsonDeserializer<Usage> {

    @Override
    public Usage deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        // JSON element should be an array
        if (json.isJsonArray()) {
            // Create usage
            Usage usage = new Usage();

            // Iterate the array
            for (JsonElement element : json.getAsJsonArray()) {
                // Each element should be an array containing a date and int
                if (element.isJsonArray() && element.getAsJsonArray().size() == 2) {
                    JsonArray jsonArray = element.getAsJsonArray();
                    Date date = context.deserialize(jsonArray.get(0), Date.class);
                    int count = jsonArray.get(1).getAsInt();
                    usage.put(date, count);
                }
            }
            return usage;
        }
        return null;
    }

}
