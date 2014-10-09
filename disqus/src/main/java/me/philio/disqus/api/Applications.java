package me.philio.disqus.api;

/**
 * Applications api methods
 */
public class Applications extends AbstractApi {

    /**
     * Set api key and access token
     *
     * @param apiKey
     * @param accessToken
     */
    public Applications(String apiKey, String accessToken) {
        super(apiKey, accessToken);
    }

    public void listUsage() throws Exception {
        throw new Exception("Stub! Not implemented yet");
    }

}
