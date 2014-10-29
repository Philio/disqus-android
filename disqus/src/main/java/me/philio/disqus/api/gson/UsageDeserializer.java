package me.philio.disqus.api.gson;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import me.philio.disqus.DisqusConstants;
import me.philio.disqus.api.model.applications.Usage;

/**
 * Custom deserializer to convert Disqus usage nested arrays into a map
 */
public class UsageDeserializer implements JsonDeserializer<Usage> {

    @Override
    public Usage deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        // Create usage
        Usage usage = new Usage();

        // JSON element should be an array
        if (json.isJsonArray()) {
            // Use SimpleDateFormat to format dates
            SimpleDateFormat dateFormat = new SimpleDateFormat(DisqusConstants.DATE_FORMAT);
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

            for (JsonElement element : json.getAsJsonArray()) {
                // Each element should be an array containing a date and int
                if (element.isJsonArray() && element.getAsJsonArray().size() == 2) {
                    try {
                        JsonArray jsonArray = element.getAsJsonArray();
                        Date date = dateFormat.parse(jsonArray.get(0).getAsString());
                        int count = jsonArray.get(1).getAsInt();
                        usage.put(date, count);
                    } catch (ParseException e) {
                        throw new JsonParseException(e);
                    }
                }
            }
        }
        return usage;
    }

}
