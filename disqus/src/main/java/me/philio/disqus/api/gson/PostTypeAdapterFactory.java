package me.philio.disqus.api.gson;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

import me.philio.disqus.api.model.forums.Forum;
import me.philio.disqus.api.model.posts.Post;

/**
 * A {@link TypeAdapterFactory} to handle different {@link Post} responses
 */
public class PostTypeAdapterFactory implements TypeAdapterFactory {

    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        // Return null if not a post object
        if (!type.getType().equals(Post.class)) {
            return null;
        }

        // Get adapters
        final TypeAdapter<T> delegate = gson.getDelegateAdapter(this, type);
        final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
        final TypeAdapter<Forum> forumAdapter = gson.getAdapter(Forum.class);
        final TypeAdapter<Thread> threadAdapter = gson.getAdapter(Thread.class);

        // Return adapter
        return new TypeAdapter<T>() {

            @Override
            public void write(JsonWriter out, T value) throws IOException {
                delegate.write(out, value);
            }

            @Override
            public T read(JsonReader in) throws IOException {
                JsonElement jsonTree = elementAdapter.read(in);
                JsonElement forum = jsonTree.getAsJsonObject().get("forum");
                JsonElement thread = jsonTree.getAsJsonObject().get("thread");

                // Process the post with the delegate
                T post = delegate.fromJsonTree(jsonTree);

                // Process forum and thread if needed
                if (forum.isJsonObject()) {
                    ((Post) post).forum = forumAdapter.fromJsonTree(forum);
                }
                if (thread.isJsonObject()) {
                    ((Post) post).thread = threadAdapter.fromJsonTree(thread);
                }

                // Return post
                return post;
            }

        };

    }

}
