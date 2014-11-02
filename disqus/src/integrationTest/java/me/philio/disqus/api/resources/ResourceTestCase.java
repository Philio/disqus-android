package me.philio.disqus.api.resources;

import junit.framework.TestCase;

import me.philio.disqus.api.ApiClient;
import me.philio.disqus.api.ApiConfig;

/**
 * Resource test abstract class
 */
public abstract class ResourceTestCase extends TestCase {

    /**
     * ApiClient instance
     */
    protected ApiClient mApiClient;

    @Override
    protected void setUp() throws Exception {
        ApiConfig apiConfig =
                new ApiConfig("3ymeKsGF7H03ga4vIzvwCrM5aFU2Dkx7uaRxBV36Z3e3IJQwbfjVcz6XjrNgf4lH",
                        "46a082db3ad5418d940cd449f542b075", "http://localhost/");
        mApiClient = new ApiClient(apiConfig);
    }

}
