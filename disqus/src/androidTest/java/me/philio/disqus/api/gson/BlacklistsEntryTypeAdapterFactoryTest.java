package me.philio.disqus.api.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import junit.framework.TestCase;

import me.philio.disqus.DisqusConstants;
import me.philio.disqus.api.model.blacklists.Entry;
import me.philio.disqus.api.model.users.User;

import static me.philio.disqus.api.model.blacklists.Entry.Type;

public class BlacklistsEntryTypeAdapterFactoryTest extends TestCase {

    /**
     * Gson instance
     */
    private Gson mGson;

    @Override
    protected void setUp() throws Exception {
        mGson = new GsonBuilder()
                .setDateFormat(DisqusConstants.DATE_FORMAT)
                .registerTypeAdapterFactory(new BlacklistsEntryTypeAdapterFactory())
                .create();
    }

    /**
     * Test parsing a domain blacklist response
     */
    public void testDomain() {
        String json = "{\"forum\":\"disqusforandroidintegrationtesting\",\"notes\":\"\"," +
                "\"value\":\"test.com\",\"conflictingWhitelistRemoved\":false," +
                "\"type\":\"domain\",\"id\":7111443,\"createdAt\":\"2014-10-30T23:52:00\"}";
        Entry entry = mGson.fromJson(json, Entry.class);
        assertNotNull(entry);
        assertEquals("disqusforandroidintegrationtesting", entry.forum);
        assertNotNull(entry.value);
        assertEquals("test.com", entry.value);
        assertFalse(entry.conflictingWhitelistRemoved);
        assertEquals(Type.domain, entry.type);
        assertEquals(7111443l, entry.id);
        assertNotNull(entry.createdAt);
    }

    /**
     * Test parsing a word blacklist response
     */
    public void testWord() {
        String json = "{\"forum\":\"disqusforandroidintegrationtesting\",\"notes\":\"\"," +
                "\"value\":\"rudeword\",\"conflictingWhitelistRemoved\":false,\"type\":\"word\"," +
                "\"id\":7111444,\"createdAt\":\"2014-10-30T23:52:00\"}";
        Entry entry = mGson.fromJson(json, Entry.class);
        assertNotNull(entry);
        assertEquals("disqusforandroidintegrationtesting", entry.forum);
        assertNotNull(entry.value);
        assertEquals("rudeword", entry.value);
        assertFalse(entry.conflictingWhitelistRemoved);
        assertEquals(Type.word, entry.type);
        assertEquals(7111444l, entry.id);
        assertNotNull(entry.createdAt);
    }

    /**
     * Test parsing an email blacklist response
     */
    public void testEmail() {
        String json = "{\"forum\":\"disqusforandroidintegrationtesting\",\"notes\":\"\"," +
                "\"value\":\"test@test.com\",\"conflictingWhitelistRemoved\":false," +
                "\"type\":\"email\",\"id\":7111447,\"createdAt\":\"2014-10-30T23:52:01\"}";
        Entry entry = mGson.fromJson(json, Entry.class);
        assertNotNull(entry);
        assertEquals("disqusforandroidintegrationtesting", entry.forum);
        assertNotNull(entry.value);
        assertEquals("test@test.com", entry.value);
        assertFalse(entry.conflictingWhitelistRemoved);
        assertEquals(Type.email, entry.type);
        assertEquals(7111447l, entry.id);
        assertNotNull(entry.createdAt);
    }

    /**
     * Test parsing an IP blacklist response
     */
    public void testIp() {
        String json = "{\"forum\":\"disqusforandroidintegrationtesting\"," +
                "\"notes\":\"\",\"value\":\"254.254.254.254\"," +
                "\"conflictingWhitelistRemoved\":false,\"type\":\"ip\",\"id\":7111448," +
                "\"createdAt\":\"2014-10-30T23:52:02\"}";
        Entry entry = mGson.fromJson(json, Entry.class);
        assertNotNull(entry);
        assertEquals("disqusforandroidintegrationtesting", entry.forum);
        assertNotNull(entry.value);
        assertEquals("254.254.254.254", entry.value);
        assertFalse(entry.conflictingWhitelistRemoved);
        assertEquals(Type.ip, entry.type);
        assertEquals(7111448l, entry.id);
        assertNotNull(entry.createdAt);
    }

    /**
     * Test parsing a user blacklist response
     */
    public void testUser() {
        String json = "{\"forum\":\"disqusforandroidintegrationtesting\",\"notes\":\"\"" +
                ",\"value\":{\"username\":\"Jason\"" +
                ",\"about\":\"CTO and Co-Founder of DISQUS.  Lurker.\",\"name\":\"Jason\"," +
                "\"disable3rdPartyTrackers\":false,\"url\":\"http://tail.im/\"," +
                "\"isAnonymous\":false,\"rep\":3.5666850000000005,\"isFollowing\":false," +
                "\"isFollowedBy\":false,\"profileUrl\":\"http://disqus.com/Jason/\"," +
                "\"reputation\":3.5666850000000005,\"location\":\"San Francisco, CA\"," +
                "\"isPrivate\":false,\"isPrimary\":true,\"joinedAt\":\"2007-07-31T18:07:43\"," +
                "\"id\":\"1\"," +
                "\"avatar\":{\"small\":{\"permalink\":\"https://disqus.com/api/users/avatars/Jason.jpg\"," +
                "\"cache\":\"//a.disquscdn.com/uploads/users/1/avatar32.jpg?1281549414\"}," +
                "\"isCustom\":true," +
                "\"permalink\":\"https://disqus.com/api/users/avatars/Jason.jpg\"," +
                "\"cache\":\"//a.disquscdn.com/uploads/users/1/avatar92.jpg?1281549414\"," +
                "\"large\":{\"permalink\":\"https://disqus.com/api/users/avatars/Jason.jpg\"," +
                "\"cache\":\"//a.disquscdn.com/uploads/users/1/avatar92.jpg?1281549414\"}}}," +
                "\"conflictingWhitelistRemoved\":false,\"type\":\"user\",\"id\":7111449," +
                "\"createdAt\":\"2014-10-30T23:52:02\"}";
        Entry entry = mGson.fromJson(json, Entry.class);
        assertNotNull(entry);
        assertEquals("disqusforandroidintegrationtesting", entry.forum);
        assertNotNull(entry.value);
        assertEquals("Jason", ((User) entry.value).username);
        assertFalse(entry.conflictingWhitelistRemoved);
        assertEquals(Type.user, entry.type);
        assertEquals(7111449l, entry.id);
        assertNotNull(entry.createdAt);
    }

}