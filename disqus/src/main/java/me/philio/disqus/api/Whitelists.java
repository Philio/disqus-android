package me.philio.disqus.api;

/**
 * Whitelists api methods
 */
public class Whitelists extends AbstractApi {

    /**
     * Set api key and access token
     *
     * @param apiKey
     * @param accessToken
     */
    public Whitelists(String apiKey, String accessToken) {
        super(apiKey, accessToken);
    }

    public void add() throws Exception {
        throw new Exception("Stub! Not implemented yet");
    }

    public void list() throws Exception {
        throw new Exception("Stub! Not implemented yet");
    }

    public void remove() throws Exception {
        throw new Exception("Stub! Not implemented yet");
    }

}
