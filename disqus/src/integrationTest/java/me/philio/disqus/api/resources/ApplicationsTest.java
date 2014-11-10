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

import me.philio.disqus.api.exception.ApiException;
import me.philio.disqus.api.model.Response;
import me.philio.disqus.api.model.applications.Usage;

public class ApplicationsTest extends ResourceTestCase {

    /**
     * Applications resource
     */
    private Applications mApplications;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mApplications = mApiClient.createApplications();
    }

    /**
     * Test that listUsage returns some valid data
     *
     * @throws ApiException
     */
    @LargeTest
    public void testListUsage() throws ApiException {
        Response<Usage> usage = mApplications.listUsage();
        assertNotNull(usage);
        assertNotNull(usage.data);
        assertTrue(usage.data.size() > 0);
    }

    /**
     * Test that listUsage returns the requested number of days data
     *
     * API always returns days 1 extra day than requested
     *
     * @throws ApiException
     */
    @LargeTest
    public void testListUsageDays() throws ApiException {
        Response<Usage> usage = mApplications.listUsage(6);
        assertNotNull(usage);
        assertNotNull(usage.data);
        assertEquals(7, usage.data.size());
    }

    /**
     * Test that listUsage returns the requested number of days data for the specified application
     *
     * API always returns days 1 extra day than requested
     *
     * @throws ApiException
     */
    @LargeTest
    public void testListUsageAppDays() throws ApiException {
        Response<Usage> usage = mApplications.listUsage(3318003, 6);
        assertNotNull(usage);
        assertNotNull(usage.data);
        assertEquals(7, usage.data.size());
    }

}