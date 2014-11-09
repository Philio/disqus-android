package me.philio.disqus.api.gson;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

import me.philio.disqus.api.model.blacklists.Entry;
import me.philio.disqus.api.model.users.User;

/**
 * A {@link TypeAdapterFactory} to handle different {@link Entry} responses
 */
public class BlacklistsEntryTypeAdapterFactory implements TypeAdapterFactory {

    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        // Return null if not a blacklist entry object
        if (!type.getType().equals(Entry.class)) {
            return null;
        }

        // Get adapters
        final TypeAdapter<T> delegate = gson.getDelegateAdapter(this, type);
        final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
        final TypeAdapter<User> userAdapter = gson.getAdapter(User.class);

        // Return adapter
        return new TypeAdapter<T>() {

            @Override
            public void write(JsonWriter out, T value) throws IOException {
                delegate.write(out, value);
            }

            @Override
            public T read(JsonReader in) throws IOException {
                JsonElement jsonTree = elementAdapter.read(in);
                JsonElement value = jsonTree.getAsJsonObject().get("value");

                // Process the entry with the delegate
                T entry = delegate.fromJsonTree(jsonTree);

                // Process value if needed
                if (value.isJsonObject()) {
                    ((Entry) entry).value = userAdapter.fromJsonTree(value);
                }

                // Return entry
                return entry;
            }

        };
    }

}
