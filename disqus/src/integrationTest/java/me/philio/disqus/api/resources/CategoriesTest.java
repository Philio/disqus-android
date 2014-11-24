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
import me.philio.disqus.api.model.posts.Post;
import me.philio.disqus.api.model.threads.Thread;

public class CategoriesTest extends ResourceTestCase {

    /**
     * Categories resource
     */
    private Categories mCategories;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mCategories = mApiClient.createCategories();
    }

    /**
     * Test create category
     *
     * @throws ApiException
     */
    @LargeTest
    public void testCreate() throws ApiException {
        String timestamp = Long.toString(System.currentTimeMillis());
        Response<Category> category = mCategories.create("disqusforandroidintegrationtesting",
                "Integration test " + timestamp);
        assertNotNull(category);
        assertEquals(0, category.code);
        assertNotNull(category.data);
        assertEquals("disqusforandroidintegrationtesting", category.data.forum);
        assertEquals("Integration test " + timestamp, category.data.title);
        assertFalse(category.data.isDefault);
    }

    /**
     * Test create default category
     *
     * @throws ApiException
     */
    @LargeTest
    public void testCreateDefault() throws ApiException {
        String timestamp = Long.toString(System.currentTimeMillis());
        Response<Category> category = mCategories.create("disqusforandroidintegrationtesting",
                "Integration test " + timestamp, 1);
        assertNotNull(category);
        assertEquals(0, category.code);
        assertNotNull(category.data);
        assertEquals("disqusforandroidintegrationtesting", category.data.forum);
        assertEquals("Integration test " + timestamp, category.data.title);
        assertTrue(category.data.isDefault);
    }

    /**
     * Test getting details of a known category
     *
     * @throws ApiException
     */
    @LargeTest
    public void testDetails() throws ApiException {
        String timestamp = Long.toString(System.currentTimeMillis());
        Response<Category> category = mCategories.create("disqusforandroidintegrationtesting",
                "Integration test " + timestamp);
        long id = category.data.id;
        Response<Category> details = mCategories.details(id);
        assertNotNull(details);
        assertEquals(0, details.code);
        assertNotNull(category.data);
        assertEquals(id, category.data.id);
        assertEquals("disqusforandroidintegrationtesting", category.data.forum);
        assertEquals("Integration test " + timestamp, category.data.title);
        assertFalse(category.data.isDefault);
    }

    /**
     * Test that list returns a valid list
     *
     * @throws ApiException
     */
    @LargeTest
    public void testList() throws ApiException {
        Response<List<Category>> categories = mCategories.list("disqusforandroidintegrationtesting");
        assertNotNull(categories);
        assertEquals(0, categories.code);
        assertNotNull(categories.data);
        assertTrue(categories.data.size() > 0);
    }

    /**
     * Test that list posts
     *
     * @throws ApiException
     */
    @LargeTest
    public void testListPosts() throws ApiException {
        Response<List<Post>> posts = mCategories.listPosts(1);
        assertNotNull(posts);
        assertEquals(0, posts.code);
        assertNotNull(posts.data);
    }

    /**
     * Test that list threads
     *
     * @throws ApiException
     */
    @LargeTest
    public void testListThreads() throws ApiException {
        Response<List<Thread>> threads = mCategories.listThreads(1);
        assertNotNull(threads);
        assertEquals(0, threads.code);
        assertNotNull(threads.data);
    }

}