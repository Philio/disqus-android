package me.philio.disqus.api.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

import me.philio.disqus.api.model.blacklists.Entry;
import me.philio.disqus.api.model.forums.Forum;
import me.philio.disqus.api.model.users.User;

/**
 * Custom deserializer to convert Disqus blacklist entry
 */
public class BlacklistsEntryDeserializer implements JsonDeserializer<Entry> {

    @Override
    public Entry deserialize(JsonElement json, Type typeOfT,
                                      JsonDeserializationContext context)
            throws JsonParseException {
        // JSON should be an object
        if (json.isJsonObject()) {
            Entry entry = new Entry();

            // Iterate through fields and try and parse them
            for (Field field : Entry.class.getDeclaredFields()) {
                JsonObject jsonObject = json.getAsJsonObject();
                String name = getJsonName(field);

                // Parse field if it exists and is not null
                if (jsonObject.has(name) && !jsonObject.get(name).isJsonNull()) {
                    if (name.equals("forum")) {
                        // Forum can be a string or object, depending if related is used
                        JsonElement value = jsonObject.get(name);
                        if (value.isJsonObject()) {
                            // Deserialise forum object
                            entry.forum = context.deserialize(value, Forum.class);
                        } else if (value.isJsonPrimitive()) {
                            // Get as string
                            entry.forum = value.getAsString();
                        }
                    } else if (name.equals("value")) {
                        // Value is a special case string or user
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
