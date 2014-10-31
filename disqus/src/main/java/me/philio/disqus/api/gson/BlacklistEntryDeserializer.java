package me.philio.disqus.api.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

import me.philio.disqus.DisqusConstants;
import me.philio.disqus.api.model.blacklists.BlacklistEntry;
import me.philio.disqus.api.model.users.User;

/**
 * Custom deserializer to convert Disqus blacklist entry
 */
public class BlacklistEntryDeserializer implements JsonDeserializer<BlacklistEntry> {

    @Override
    public BlacklistEntry deserialize(JsonElement json, Type typeOfT,
                                      JsonDeserializationContext context)
            throws JsonParseException {
        // JSON should be an object
        if (json.isJsonObject()) {
            Gson gson = new GsonBuilder().setDateFormat(DisqusConstants.DATE_FORMAT).create();
            BlacklistEntry entry = new BlacklistEntry();

            // Iterate through fields and try and parse them
            for (Field field : BlacklistEntry.class.getDeclaredFields()) {
                JsonObject jsonObject = json.getAsJsonObject();
                String name = getJsonName(field);

                // Parse field if it exists and is not null
                if (jsonObject.has(name) && !jsonObject.get(name).isJsonNull()) {
                    // Value is special case string or user
                    if (name.equals("value")) {
                        JsonElement value = jsonObject.get(name);
                        if (value.isJsonObject()) {
                            // Deserialise user object
                            entry.value = context.deserialize(value, User.class);
                        } else if (value.isJsonPrimitive()) {
                            // Get as string
                            entry.value = value.getAsString();
                        }
                    } else {
                        try {
                            // Try and deserialise
                            field.set(entry, context.deserialize(jsonObject.get(name),
                                    field.getType()));
                        } catch (IllegalAccessException e) {
                            throw new JsonParseException(e);
                        }
                    }
                }
            }
            return entry;
        }
        return null;
    }

    /**
     * Get the name of the JSON field
     *
     * @param field
     * @return
     * @throws NoSuchFieldException
     */
    private String getJsonName(Field field) {
        SerializedName serializedName = field.getAnnotation(SerializedName.class);
        if (serializedName != null) {
            return serializedName.value();
        }
        return field.getName();
    }

}
