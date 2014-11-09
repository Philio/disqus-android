package me.philio.disqus.api.resources;

import android.test.suitebuilder.annotation.LargeTest;

import java.util.List;

import me.philio.disqus.api.exception.ApiException;
import me.philio.disqus.api.model.Response;
import me.philio.disqus.api.model.blacklists.Entry;
import me.philio.disqus.api.model.users.User;

import static me.philio.disqus.api.model.blacklists.Entry.Type;

public class BlacklistsTest extends ResourceTestCase {

    /**
     * Blacklists resource
     */
    private Blacklists mBlacklists;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mBlacklists = mApiClient.createBlacklists();

        // Clean up any existing blacklist records
        cleanUp();
    }

    @Override
    protected void tearDown() throws Exception {
        // Clean up any existing blacklist records
        cleanUp();
    }

    private void cleanUp() throws ApiException {
        Response<List<Entry>> entries =
                mBlacklists.list("disqusforandroidintegrationtesting");
        if (entries != null && entries.data != null && entries.data.size() > 0) {
            for (Entry entry : entries.data) {
                switch (entry.type) {
                    case domain:
                        mBlacklists.removeDomains("disqusforandroidintegrationtesting",
                                new String[]{entry.value.toString()});
                        break;
                    case email:
                        mBlacklists.removeEmails("disqusforandroidintegrationtesting",
                                new String[]{entry.value.toString()});
                        break;
                    case ip:
                        mBlacklists.removeIps("disqusforandroidintegrationtesting",
                                new String[]{entry.value.toString()});
                        break;
                    case user:
                        mBlacklists.removeUsers("disqusforandroidintegrationtesting",
                                new Long[]{((User) entry.value).id});
                        break;
                    case word:
                        mBlacklists.removeWords("disqusforandroidintegrationtesting",
                                new String[]{entry.value.toString()});
                        break;
                }
            }
        }
    }

    /**
     * Test adding domains to the blacklist
     *
     * @throws ApiException
     */
    @LargeTest
    public void testAddDomains() throws ApiException {
        Response<List<Entry>> entries =
                mBlacklists.addDomains("disqusforandroidintegrationtesting",
                        new String[]{"test.com", "test2.com", "test3.com"}, 0,
                        "Added by Disqus for Android");
        assertNotNull(entries);
        assertNotNull(entries.data);
        assertEquals(3, entries.data.size());
        assertEquals(Type.domain, entries.data.get(0).type);
        assertEquals("test.com", entries.data.get(0).value);
        assertEquals(Type.domain, entries.data.get(1).type);
        assertEquals("test2.com", entries.data.get(1).value);
        assertEquals(Type.domain, entries.data.get(2).type);
        assertEquals("test3.com", entries.data.get(2).value);
    }

    /**
     * Test adding words to the blacklist
     *
     * @throws ApiException
     */
    @LargeTest
    public void testAddWords() throws ApiException {
        Response<List<Entry>> entries =
                mBlacklists.addWords("disqusforandroidintegrationtesting",
                        new String[]{"naughty", "words"}, 0, "Added by Disqus for Android");
        assertNotNull(entries);
        assertNotNull(entries.data);
        assertEquals(2, entries.data.size());
        assertEquals(Type.word, entries.data.get(0).type);
        assertEquals("naughty", entries.data.get(0).value);
        assertEquals(Type.word, entries.data.get(1).type);
        assertEquals("words", entries.data.get(1).value);
    }

    /**
     * Test adding IPs to the blacklist
     *
     * @throws ApiException
     */
    @LargeTest
    public void testAddIps() throws ApiException {
        Response<List<Entry>> entries =
                mBlacklists.addIps("disqusforandroidintegrationtesting",
                        new String[]{"10.0.0.1", "10.0.0.2"}, 0, "Added by Disqus for Android");
        assertNotNull(entries);
        assertNotNull(entries.data);
        assertEquals(2, entries.data.size());
        assertEquals(Type.ip, entries.data.get(0).type);
        assertEquals("10.0.0.1", entries.data.get(0).value);
        assertEquals(Type.ip, entries.data.get(1).type);
        assertEquals("10.0.0.2", entries.data.get(1).value);
    }

    /**
     * Test adding users to the blacklist
     *
     * @throws ApiException
     */
    @LargeTest
    public void testAddUsers() throws ApiException {
        Response<List<Entry>> entries =
                mBlacklists.addUsers("disqusforandroidintegrationtesting",
                        new Long[]{1l, 2l, 3l}, 0, "Added by Disqus for Android");
        assertNotNull(entries);
        assertNotNull(entries.data);
        assertEquals(3, entries.data.size());
        assertEquals(Type.user, entries.data.get(0).type);
        assertEquals(1, ((User) entries.data.get(0).value).id);
        assertEquals(Type.user, entries.data.get(1).type);
        assertEquals(2, ((User) entries.data.get(1).value).id);
        assertEquals(Type.user, entries.data.get(2).type);
        assertEquals(3, ((User) entries.data.get(2).value).id);
    }

    /**
     * Test adding emails to the blacklist
     *
     * @throws ApiException
     */
    @LargeTest
    public void testAddEmails() throws ApiException {
        Response<List<Entry>> entries =
                mBlacklists.addEmails("disqusforandroidintegrationtesting",
                        new String[]{"mail@test.com", "mail@test2.com", "mail@test3.com"}, 0,
                        "Added by Disqus for Android");
        assertNotNull(entries);
        assertNotNull(entries.data);
        assertEquals(3, entries.data.size());
        assertEquals(Type.email, entries.data.get(0).type);
        assertEquals("mail@test.com", entries.data.get(0).value);
        assertEquals(Type.email, entries.data.get(1).type);
        assertEquals("mail@test2.com", entries.data.get(1).value);
        assertEquals(Type.email, entries.data.get(2).type);
        assertEquals("mail@test3.com", entries.data.get(2).value);
    }

    /**
     * Test listing blacklisted items
     *
     * @throws ApiException
     */
    @LargeTest
    public void testList() throws ApiException {
        mBlacklists.addWords("disqusforandroidintegrationtesting",
                new String[]{"some", "naughty", "words"}, 0, "Added by Disqus for Android");
        mBlacklists.addIps("disqusforandroidintegrationtesting", new String[]{"10.0.0.1"}, 0,
                "Added by Disqus for Android");
        Response<List<Entry>> entries =
                mBlacklists.list("disqusforandroidintegrationtesting");
        assertNotNull(entries);
        assertNotNull(entries.data);
        assertEquals(4, entries.data.size());
        assertEquals(Type.word, entries.data.get(0).type);
        assertEquals("some", entries.data.get(0).value);
        assertEquals(Type.word, entries.data.get(1).type);
        assertEquals("naughty", entries.data.get(1).value);
        assertEquals(Type.word, entries.data.get(2).type);
        assertEquals("words", entries.data.get(2).value);
        assertEquals(Type.ip, entries.data.get(3).type);
        assertEquals("10.0.0.1", entries.data.get(3).value);
    }

    /**
     * Test removing domains from the blacklist
     *
     * @throws ApiException
     */
    @LargeTest
    public void testRemoveDomains() throws ApiException {
        mBlacklists.addDomains("disqusforandroidintegrationtesting",
                new String[]{"test.com", "test2.com", "test3.com"}, 0,
                "Added by Disqus for Android");
        Response<List<Entry>> entries =
                mBlacklists.removeDomains("disqusforandroidintegrationtesting",
                        new String[]{"test.com", "test2.com", "test3.com"});
        assertNotNull(entries);
        assertNotNull(entries.data);
        assertEquals(3, entries.data.size());
        assertEquals(Type.domain, entries.data.get(0).type);
        assertEquals("test.com", entries.data.get(0).value);
        assertEquals(Type.domain, entries.data.get(1).type);
        assertEquals("test2.com", entries.data.get(1).value);
        assertEquals(Type.domain, entries.data.get(2).type);
        assertEquals("test3.com", entries.data.get(2).value);
    }

    /**
     * Test removing words from the blacklist
     *
     * @throws ApiException
     */
    @LargeTest
    public void testRemoveWords() throws ApiException {
        mBlacklists.addWords("disqusforandroidintegrationtesting", new String[]{"naughty", "words"},
                0, "Added by Disqus for Android");
        Response<List<Entry>> entries =
                mBlacklists.removeWords("disqusforandroidintegrationtesting",
                        new String[]{"naughty", "words"});
        assertNotNull(entries);
        assertNotNull(entries.data);
        assertEquals(2, entries.data.size());
        assertEquals(Type.word, entries.data.get(0).type);
        assertEquals("naughty", entries.data.get(0).value);
        assertEquals(Type.word, entries.data.get(1).type);
        assertEquals("words", entries.data.get(1).value);
    }

    /**
     * Test removing ips from the blacklist
     *
     * @throws ApiException
     */
    @LargeTest
    public void testRemoveIps() throws ApiException {
        mBlacklists.addIps("disqusforandroidintegrationtesting",
                new String[]{"10.0.0.1", "10.0.0.2"}, 0, "Added by Disqus for Android");
        Response<List<Entry>> entries =
                mBlacklists.removeIps("disqusforandroidintegrationtesting",
                        new String[]{"10.0.0.1", "10.0.0.2"});
        assertNotNull(entries);
        assertNotNull(entries.data);
        assertEquals(2, entries.data.size());
        assertEquals(Type.ip, entries.data.get(0).type);
        assertEquals("10.0.0.1", entries.data.get(0).value);
        assertEquals(Type.ip, entries.data.get(1).type);
        assertEquals("10.0.0.2", entries.data.get(1).value);
    }

    /**
     * Test removing users from the blacklist
     *
     * @throws ApiException
     */
    @LargeTest
    public void testRemoveUsers() throws ApiException {
        mBlacklists.addUsers("disqusforandroidintegrationtesting", new Long[]{1l, 2l, 3l}, 0,
                "Added by Disqus for Android");
        Response<List<Entry>> entries =
                mBlacklists.removeUsers("disqusforandroidintegrationtesting",
                        new Long[]{1l, 2l, 3l});
        assertNotNull(entries);
        assertNotNull(entries.data);
        assertEquals(3, entries.data.size());
        assertEquals(Type.user, entries.data.get(0).type);
        assertEquals(1, ((User) entries.data.get(0).value).id);
        assertEquals(Type.user, entries.data.get(1).type);
        assertEquals(2, ((User) entries.data.get(1).value).id);
        assertEquals(Type.user, entries.data.get(2).type);
        assertEquals(3, ((User) entries.data.get(2).value).id);
    }

    /**
     * Test removing emails from the blacklist
     *
     * @throws ApiException
     */
    @LargeTest
    public void testRemoveEmails() throws ApiException {
        mBlacklists.addEmails("disqusforandroidintegrationtesting",
                new String[]{"mail@test.com", "mail@test2.com", "mail@test3.com"}, 0,
                "Added by Disqus for Android");
        Response<List<Entry>> entries =
                mBlacklists.removeEmails("disqusforandroidintegrationtesting",
                        new String[]{"mail@test.com", "mail@test2.com", "mail@test3.com"});
        assertNotNull(entries);
        assertNotNull(entries.data);
        assertEquals(3, entries.data.size());
        assertEquals(Type.email, entries.data.get(0).type);
        assertEquals("mail@test.com", entries.data.get(0).value);
        assertEquals(Type.email, entries.data.get(1).type);
        assertEquals("mail@test2.com", entries.data.get(1).value);
        assertEquals(Type.email, entries.data.get(2).type);
        assertEquals("mail@test3.com", entries.data.get(2).value);
    }

}