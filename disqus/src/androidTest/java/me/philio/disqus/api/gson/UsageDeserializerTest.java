package me.philio.disqus.api.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import junit.framework.TestCase;

import me.philio.disqus.api.model.applications.Usage;

/**
 * Test cases for UsageDeserializer
 */
public class UsageDeserializerTest extends TestCase {

    /**
     * Test deserialisation of JSON array to a usage object
     */
    public void testArray() {
        String json = "[[\"2011-10-03T00:00:00\",0],[\"2011-10-04T00:00:00\",0]," +
                "[\"2011-10-05T00:00:00\",0],[\"2011-10-06T00:00:00\",0]," +
                "[\"2011-10-07T00:00:00\",0],[\"2011-10-08T00:00:00\",0]," +
                "[\"2011-10-09T00:00:00\",0],[\"2011-10-10T00:00:00\",0]," +
                "[\"2011-10-11T00:00:00\",0],[\"2011-10-12T00:00:00\",0]," +
                "[\"2011-10-13T00:00:00\",0],[\"2011-10-14T00:00:00\",0]," +
                "[\"2011-10-15T00:00:00\",0],[\"2011-10-16T00:00:00\",0]," +
                "[\"2011-10-17T00:00:00\",0],[\"2011-10-18T00:00:00\",0]," +
                "[\"2011-10-19T00:00:00\",0],[\"2011-10-20T00:00:00\",0]," +
                "[\"2011-10-21T00:00:00\",0],[\"2011-10-22T00:00:00\",0]," +
                "[\"2011-10-23T00:00:00\",0],[\"2011-10-24T00:00:00\",0]," +
                "[\"2011-10-25T00:00:00\",0],[\"2011-10-26T00:00:00\",0]," +
                "[\"2011-10-27T00:00:00\",0],[\"2011-10-28T00:00:00\",0]," +
                "[\"2011-10-29T00:00:00\",0],[\"2011-10-30T00:00:00\",0]," +
                "[\"2011-10-31T00:00:00\",0],[\"2011-11-01T00:00:00\",823]," +
                "[\"2011-11-02T00:00:00\",274]]";

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Usage.class, new UsageDeserializer())
                .create();
        Usage usage = gson.fromJson(json, Usage.class);
        assertNotNull(usage);
        assertEquals(usage.size(), 31);
    }

    /**
     * Test deserialisation of an empty JSON array
     */
    public void testEmptyArray() {
        String json = "[]";

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Usage.class, new UsageDeserializer())
                .create();
        Usage usage = gson.fromJson(json, Usage.class);
        assertNotNull(usage);
        assertEquals(usage.size(), 0);
    }

    /**
     * Test empty string
     */
    public void testEmptyString() {
        String json = "";

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Usage.class, new UsageDeserializer())
                .create();
        Usage usage = gson.fromJson(json, Usage.class);
        assertNull(usage);
    }

}
