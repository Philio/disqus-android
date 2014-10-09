package me.philio.disqus.api;

/**
 * Exports api methods
 */
public class Exports extends AbstractApi {

    /**
     * Set api key and access token
     *
     * @param apiKey
     * @param accessToken
     */
    public Exports(String apiKey, String accessToken) {
        super(apiKey, accessToken);
    }

    public void exportForum() throws Exception {
        throw new Exception("Stub! Not implemented yet");
    }

}
