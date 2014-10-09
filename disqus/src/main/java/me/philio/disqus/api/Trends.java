package me.philio.disqus.api;

/**
 * Trends api methods
 */
public class Trends extends AbstractApi {

    /**
     * Set api key and access token
     *
     * @param apiKey
     * @param accessToken
     */
    public Trends(String apiKey, String accessToken) {
        super(apiKey, accessToken);
    }

    public void listThreads() throws Exception {
        throw new Exception("Stub! Not implemented yet");
    }

}
