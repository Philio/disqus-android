package me.philio.disqus.api;

public abstract class AbstractApi {

    /**
     * Api key
     */
    protected String mApiKey;

    /**
     * Access token
     */
    protected String mAccessToken;

    /**
     * Set api key and access token
     *
     * @param apiKey
     * @param accessToken
     */
    public AbstractApi(String apiKey, String accessToken) {
        mApiKey = apiKey;
        mAccessToken = accessToken;
    }

}
