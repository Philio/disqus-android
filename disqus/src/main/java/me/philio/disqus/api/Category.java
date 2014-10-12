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
 * Category api methods
 */
public class Category extends AbstractApi {

    /**
     * Set api key and access token
     *
     * @param apiKey
     * @param accessToken
     */
    public Category(String apiKey, String accessToken) {
        super(apiKey, accessToken);
    }

    public void create() throws Exception {
        throw new Exception("Stub! Not implemented yet");
    }

    public void details() throws Exception {
        throw new Exception("Stub! Not implemented yet");
    }

    public void list() throws Exception {
        throw new Exception("Stub! Not implemented yet");
    }

    public void listPosts() throws Exception {
        throw new Exception("Stub! Not implemented yet");
    }

    public void listThreads() throws Exception {
        throw new Exception("Stub! Not implemented yet");
    }

}