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
