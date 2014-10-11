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
package me.philio.disqus.api.http;

/**
 * HTTP response wrapper that encapsulates the response code and body
 */
public class HttpResponse {

    /**
     * Response code
     */
    private int mCode;

    /**
     * Repsonse body
     */
    private String mBody;

    /**
     * Constructor
     *
     * @param code
     * @param body
     */
    public HttpResponse(int code, String body) {
        mCode = code;
        mBody = body;
    }

    /**
     * Get the response code
     *
     * @return
     */
    public int getCode() {
        return mCode;
    }

    /**
     * Get the response body
     *
     * @return
     */
    public String getBody() {
        return mBody;
    }

}
