package me.philio.disqus.api.resources;

import android.test.suitebuilder.annotation.LargeTest;

import me.philio.disqus.api.model.Response;

public class ExportsTest extends ResourceTestCase {

    /**
     * Categories resource
     */
    private Exports mExports;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mExports = mApiClient.createExports();
    }

    /**
     * Test export forum
     *
     * @throws Exception
     */
    @LargeTest
    public void testExportForum() throws Exception {
        Response<Object> response = mExports.exportForum("disqusforandroidintegrationtesting");
        assertNotNull(response);
        assertEquals(0, response.code);
    }

}