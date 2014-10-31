package me.philio.disqus.api.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import junit.framework.TestCase;

import me.philio.disqus.DisqusConstants;
import me.philio.disqus.api.model.blacklists.BlacklistEntry;
import me.philio.disqus.api.model.users.User;

public class BlacklistEntryDeserializerTest extends TestCase {

    /**
     * Gson instance
     */
    private Gson mGson;

    @Override
    protected void setUp() throws Exception {
        mGson = new GsonBuilder()
                .setDateFormat(DisqusConstants.DATE_FORMAT)
                .registerTypeAdapter(BlacklistEntry.class, new BlacklistEntryDeserializer())
                .create();
    }

    /**
     * Test parsing a domain blacklist response
     */
    public void testDomain() {
        String json = "{\"forum\":\"forandroid\",\"notes\":\"\",\"value\":\"test.com\"," +
                "\"conflictingWhitelistRemoved\":false,\"type\":\"domain\",\"id\":7111443," +
                "\"createdAt\":\"2014-10-30T23:52:00\"}";
        BlacklistEntry blacklistEntry = mGson.fromJson(json, BlacklistEntry.class);
        assertNotNull(blacklistEntry);
        assertEquals(blacklistEntry.forum, "forandroid");
        assertTrue(blacklistEntry.value instanceof String);
        assertEquals(blacklistEntry.value, "test.com");
        assertFalse(blacklistEntry.conflictingWhitelistRemoved);
        assertEquals(blacklistEntry.type, BlacklistEntry.Type.domain);
        assertEquals(blacklistEntry.id, 7111443l);
        assertNotNull(blacklistEntry.createdAt);
    }

    /**
     * Test parsing a word blacklist response
     */
    public void testWord() {
        String json = "{\"forum\":\"forandroid\",\"notes\":\"\",\"value\":\"rudeword\"," +
                "\"conflictingWhitelistRemoved\":false,\"type\":\"word\",\"id\":7111444," +
                "\"createdAt\":\"2014-10-30T23:52:00\"}";
        BlacklistEntry blacklistEntry = mGson.fromJson(json, BlacklistEntry.class);
        assertNotNull(blacklistEntry);
        assertEquals(blacklistEntry.forum, "forandroid");
        assertTrue(blacklistEntry.value instanceof String);
        assertEquals(blacklistEntry.value, "rudeword");
        assertFalse(blacklistEntry.conflictingWhitelistRemoved);
        assertEquals(blacklistEntry.type, BlacklistEntry.Type.word);
        assertEquals(blacklistEntry.id, 7111444l);
        assertNotNull(blacklistEntry.createdAt);
    }

    /**
     * Test parsing an email blacklist response
     */
    public void testEmail() {
        String json = "{\"forum\":\"forandroid\",\"notes\":\"\",\"value\":\"test@test.com\"," +
                "\"conflictingWhitelistRemoved\":false,\"type\":\"email\",\"id\":7111447," +
                "\"createdAt\":\"2014-10-30T23:52:01\"}";
        BlacklistEntry blacklistEntry = mGson.fromJson(json, BlacklistEntry.class);
        assertNotNull(blacklistEntry);
        assertEquals(blacklistEntry.forum, "forandroid");
        assertTrue(blacklistEntry.value instanceof String);
        assertEquals(blacklistEntry.value, "test@test.com");
        assertFalse(blacklistEntry.conflictingWhitelistRemoved);
        assertEquals(blacklistEntry.type, BlacklistEntry.Type.email);
        assertEquals(blacklistEntry.id, 7111447l);
        assertNotNull(blacklistEntry.createdAt);
    }

    /**
     * Test parsing an IP blacklist response
     */
    public void testIp() {
        String json = "{\"forum\":\"forandroid\",\"notes\":\"\",\"value\":\"254.254.254.254\"," +
                "\"conflictingWhitelistRemoved\":false,\"type\":\"ip\",\"id\":7111448," +
                "\"createdAt\":\"2014-10-30T23:52:02\"}";
        BlacklistEntry blacklistEntry = mGson.fromJson(json, BlacklistEntry.class);
        assertNotNull(blacklistEntry);
        assertEquals(blacklistEntry.forum, "forandroid");
        assertTrue(blacklistEntry.value instanceof String);
        assertEquals(blacklistEntry.value, "254.254.254.254");
        assertFalse(blacklistEntry.conflictingWhitelistRemoved);
        assertEquals(blacklistEntry.type, BlacklistEntry.Type.ip);
        assertEquals(blacklistEntry.id, 7111448l);
        assertNotNull(blacklistEntry.createdAt);
    }

    /**
     * Test parsing a user blacklist response
     */
    public void testUser() {
        String json = "{\"forum\":\"forandroid\",\"notes\":\"\"" +
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
        BlacklistEntry blacklistEntry = mGson.fromJson(json, BlacklistEntry.class);
        assertNotNull(blacklistEntry);
        assertEquals(blacklistEntry.forum, "forandroid");
        assertTrue(blacklistEntry.value instanceof User);
        assertEquals(((User) blacklistEntry.value).username, "Jason");
        assertFalse(blacklistEntry.conflictingWhitelistRemoved);
        assertEquals(blacklistEntry.type, BlacklistEntry.Type.user);
        assertEquals(blacklistEntry.id, 7111449l);
        assertNotNull(blacklistEntry.createdAt);
    }

}