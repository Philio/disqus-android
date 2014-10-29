package me.philio.disqus.api.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import me.philio.disqus.DisqusConstants;
import me.philio.disqus.api.model.blacklist.BlacklistValue;
import me.philio.disqus.api.model.user.User;

/**
 * Custom deserializer to convert Disqus blacklist entry value nicely
 *
 * Expects either a {@link String} or a {@link me.philio.disqus.api.model.user.User} object
 */
public class BlacklistValueDeserializer implements JsonDeserializer<BlacklistValue> {

    @Override
    public BlacklistValue deserialize(JsonElement json, Type typeOfT,
                                      JsonDeserializationContext context)
            throws JsonParseException {
        BlacklistValue value = null;
        if (json.isJsonPrimitive()) {
            // For strings, just set the string value
            value = new BlacklistValue(json.getAsString());
        } else if (json.isJsonObject()) {
            // For user details, parse and set user details and id as string value
            Gson gson = new GsonBuilder().setDateFormat(DisqusConstants.DATE_FORMAT).create();
            User user = gson.fromJson(json, User.class);
            value = new BlacklistValue(Long.toString(user.id), user);
        }
        return value;
    }

}
