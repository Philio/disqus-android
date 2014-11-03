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
                new ApiConfig("VOyNG8ABoNFyMj7KbAEyvPuQB6tvTPQb6hy4fK5U6kkluH1RePMEKfQw9EdLnaez",
                        "91ee5737b5b04465bc244ad036e3e3b2", "http://localhost/");
        mApiClient = new ApiClient(apiConfig);
    }

}
