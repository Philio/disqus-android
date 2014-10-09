package me.philio.disqus.api;

/**
 * Topics api methods
 */
public class Topics extends AbstractApi {

    /**
     * Set api key and access token
     *
     * @param apiKey
     * @param accessToken
     */
    public Topics(String apiKey, String accessToken) {
        super(apiKey, accessToken);
    }

    public void follow() throws Exception {
        throw new Exception("Stub! Not implemented yet");
    }

    public void listFollowers() throws Exception {
        throw new Exception("Stub! Not implemented yet");
    }

    public void unfollow() throws Exception {
        throw new Exception("Stub! Not implemented yet");
    }

}
