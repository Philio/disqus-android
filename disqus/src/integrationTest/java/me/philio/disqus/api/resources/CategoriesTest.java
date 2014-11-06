package me.philio.disqus.api.resources;

import android.test.suitebuilder.annotation.LargeTest;

import java.util.List;

import me.philio.disqus.api.exception.ApiException;
import me.philio.disqus.api.model.Response;
import me.philio.disqus.api.model.category.Category;

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
        assertNotNull(categories.data);
        assertTrue(categories.data.size() > 0);
    }

}