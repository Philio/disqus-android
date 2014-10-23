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
package me.philio.disqus.api;

/**
 * Configuration options
 */
public class ApiConfig {

    /**
     * Api key
     */
    private String mApiKey;

    /**
     * Api secret
     */
    private String mApiSecret;

    /**
     * Access token
     */
    private String mAccessToken;

    /**
     * Referrer
     */
    private String mReferrer;

    /**
     * Set api key
     *
     * @param apiKey
     */
    public ApiConfig(String apiKey) {
        mApiKey = apiKey;
    }

    /**
     * Set api key and access token
     *
     * @param apiKey
     * @param accessToken
     */
    public ApiConfig(String apiKey, String accessToken) {
        this(apiKey);
        mAccessToken = accessToken;
    }

    /**
     * Set api key, access token and referrer
     *
     * @param apiKey
     * @param accessToken
     * @param referrer
     */
    public ApiConfig(String apiKey, String accessToken, String referrer) {
        this(apiKey, accessToken);
        mReferrer = referrer;
    }

    /**
     * This constructor is here for completeness. Avoid using api secret as it's a security risk
     *
     * @param apiKey
     * @param apiSecret
     * @param accessToken
     * @param referrer
     */
    public ApiConfig(String apiKey, String apiSecret, String accessToken, String referrer) {
        this(apiKey, accessToken, referrer);
        mApiSecret = apiSecret;
    }

    /**
     * Get api key
     *
     * @return
     */
    public String getApiKey() {
        return mApiKey;
    }

    /**
     * Set api key
     *
     * @param apiKey
     */
    public void setApiKey(String apiKey) {
        this.mApiKey = apiKey;
    }

    /**
     * Get api secret
     *
     * @return
     */
    public String getApiSecret() {
        return mApiSecret;
    }

    /**
     * Set api secret. Avoid using api secret as it's a security risk
     *
     * @param apiSecret
     */
    public void setApiSecret(String apiSecret) {
        this.mApiSecret = apiSecret;
    }

    /**
     * Get access token
     *
     * @return
     */
    public String getAccessToken() {
        return mAccessToken;
    }

    /**
     * Set access token
     *
     * @param accessToken
     */
    public void setAccessToken(String accessToken) {
        this.mAccessToken = accessToken;
    }

    /**
     * Get referrer
     *
     * @return
     */
    public String getReferrer() {
        return mReferrer;
    }

    /**
     * Set referrer
     *
     * @param referrer
     */
    public void setReferrer(String referrer) {
        this.mReferrer = referrer;
    }

}
