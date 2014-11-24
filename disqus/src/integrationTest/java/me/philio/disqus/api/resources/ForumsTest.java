/*
 * Copyright 2014 Phil Bayfield
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.philio.disqus.api.resources;

import android.test.suitebuilder.annotation.LargeTest;

import java.util.List;

import me.philio.disqus.api.exception.ApiException;
import me.philio.disqus.api.model.Response;
import me.philio.disqus.api.model.category.Category;
import me.philio.disqus.api.model.forums.Forum;
import me.philio.disqus.api.model.forums.Moderator;
import me.philio.disqus.api.model.posts.Post;
import me.philio.disqus.api.model.threads.Thread;
import me.philio.disqus.api.model.users.User;

public class ForumsTest extends ResourceTestCase {

    /**
     * Forums resource
     */
    private Forums mForums;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mForums = mApiClient.createForums();
    }

    /**
     * Test adding a moderator
     *
     * @throws ApiException
     */
    @LargeTest
    public void testAddModerator() throws ApiException {
        Response<Moderator> moderator = mForums.addModerator(1,
                "disqusforandroidintegrationtesting");
        assertNotNull(moderator);
        assertEquals(0, moderator.code);
        assertNotNull(moderator.data);
        assertTrue(moderator.data.id > 0);
    }

    /**
     * Test creating a forum
     *
     * @throws ApiException
     */
    @LargeTest
    public void testCreate() throws ApiException {
        String timestamp = Long.toString(System.currentTimeMillis());
        Response<Forum> forum = mForums.create("http://test.com/", "Integration test " + timestamp,
                "integration-test-" + timestamp);
        assertNotNull(forum);
        assertEquals(0, forum.code);
        assertNotNull(forum.data);
        assertEquals("http://test.com/", forum.data.url);
        assertEquals("Integration test " + timestamp, forum.data.name);
        assertEquals("integration-test-" + timestamp, forum.data.id);
    }

    /**
     * Test creating a forum with guidelines
     *
     * @throws ApiException
     */
    @LargeTest
    public void testCreateGuidelines() throws ApiException {
        String timestamp = Long.toString(System.currentTimeMillis());
        Response<Forum> forum = mForums.create("http://test.com/", "Integration test " + timestamp,
                "integration-test-" + timestamp, "Forum guidelines");
        assertNotNull(forum);
        assertEquals(0, forum.code);
        assertNotNull(forum.data);
        assertEquals("http://test.com/", forum.data.url);
        assertEquals("Integration test " + timestamp, forum.data.name);
        assertEquals("integration-test-" + timestamp, forum.data.id);
        assertEquals("Forum guidelines", forum.data.rawGuidelines);
        assertTrue(forum.data.guidelines.contains("Forum guidelines"));
    }

    /**
     * Test get details of a forum
     *
     * @throws ApiException
     */
    @LargeTest
    public void testDetails() throws ApiException {
        String timestamp = Long.toString(System.currentTimeMillis());
        mForums.create("http://test.com/", "Integration test " + timestamp,
                "integration-test-" + timestamp);
        Response<Forum> forum = mForums.details("integration-test-" + timestamp);
        assertNotNull(forum);
        assertEquals(0, forum.code);
        assertNotNull(forum.data);
        assertEquals("http://test.com/", forum.data.url);
        assertEquals("Integration test " + timestamp, forum.data.name);
        assertEquals("integration-test-" + timestamp, forum.data.id);
    }

    /**
     * Test get details of a forum with related author
     *
     * @throws ApiException
     */
    @LargeTest
    public void testDetailsRelatedAuthor() throws ApiException {
        String timestamp = Long.toString(System.currentTimeMillis());
        mForums.create("http://test.com/", "Integration test " + timestamp,
                "integration-test-" + timestamp);
        Response<Forum> forum = mForums.details("integration-test-" + timestamp,
                new String[]{"author"});
        assertNotNull(forum);
        assertEquals(0, forum.code);
        assertNotNull(forum.data);
        assertEquals("http://test.com/", forum.data.url);
        assertEquals("Integration test " + timestamp, forum.data.name);
        assertEquals("integration-test-" + timestamp, forum.data.id);
        assertNotNull(forum.data.author);
        assertEquals(forum.data.founder, forum.data.author.id);
    }

    /**
     * Test following a forum
     *
     * @throws ApiException
     */
    @LargeTest
    public void testFollow() throws ApiException {
        Response<List<Object>> response = mForums.follow("disqusforandroidintegrationtesting");
        assertNotNull(response);
        assertEquals(0, response.code);
        assertNotNull(response.data);
    }

    /**
     * Test list categories
     *
     * @throws ApiException
     */
    @LargeTest
    public void testListCategories() throws ApiException {
        Response<List<Category>> categories =
                mForums.listCategories("disqusforandroidintegrationtesting");
        assertNotNull(categories);
        assertEquals(0, categories.code);
        assertNotNull(categories.data);
    }

    /**
     * Test list followers
     *
     * @throws ApiException
     */
    @LargeTest
    public void testListFollowers() throws ApiException {
        Response<List<User>> users = mForums.listFollowers("disqusforandroidintegrationtesting");
        assertNotNull(users);
        assertEquals(0, users.code);
        assertNotNull(users.data);
    }

    /**
     * Test list moderators
     *
     * @throws ApiException
     */
    @LargeTest
    public void testListModerators() throws ApiException {
        Response<List<Moderator>> moderators =
                mForums.listModerators("disqusforandroidintegrationtesting");
        assertNotNull(moderators);
        assertEquals(0, moderators.code);
        assertNotNull(moderators.data);
    }

    /**
     * Test list most active users
     *
     * @throws ApiException
     */
    @LargeTest
    public void testListMostActiveUsers() throws ApiException {
        Response<List<User>> users =
                mForums.listMostActiveUsers("disqusforandroidintegrationtesting");
        assertNotNull(users);
        assertEquals(0, users.code);
        assertNotNull(users.data);
    }

    /**
     * Test list most liked users
     *
     * @throws ApiException
     */
    @LargeTest
    public void testListMostLikedUsers() throws ApiException {
        Response<List<User>> users =
                mForums.listMostLikedUsers("disqusforandroidintegrationtesting");
        assertNotNull(users);
        assertEquals(0, users.code);
        assertNotNull(users.data);
    }

    /**
     * Test list posts
     *
     * @throws ApiException
     */
    @LargeTest
    public void testListPosts() throws ApiException {
        Response<List<Post>> posts = mForums.listPosts("disqusforandroidintegrationtesting");
        assertNotNull(posts);
        assertEquals(0, posts.code);
        assertNotNull(posts.data);
    }

    /**
     * Test list threads
     *
     * @throws ApiException
     */
    @LargeTest
    public void testListThreads() throws ApiException {
        Response<List<Thread>> threads = mForums.listThreads("disqusforandroidintegrationtesting");
        assertNotNull(threads);
        assertEquals(0, threads.code);
        assertNotNull(threads.data);
    }

    /**
     * Test list users
     *
     * @throws ApiException
     */
    @LargeTest
    public void testListUsers() throws ApiException {
        Response<List<User>> users = mForums.listUsers("disqusforandroidintegrationtesting");
        assertNotNull(users);
        assertEquals(0, users.code);
        assertNotNull(users.data);
    }

    /**
     * Test remove moderator
     *
     * @throws ApiException
     */
    @LargeTest
    public void testRemoveModerator() throws ApiException {
        Response<Moderator> moderator = mForums.addModerator(1,
                "disqusforandroidintegrationtesting");
        Response<Moderator> removed = mForums.removeModerator(moderator.data.id);
        assertNotNull(removed);
        assertEquals(0, removed.code);
        assertNotNull(removed.data);
    }

    @LargeTest
    public void testUnfollow() throws ApiException {
        mForums.unfollow("disqusforandroidintegrationtesting");
    }

    @LargeTest
    public void testUpdate() throws ApiException {

    }

}