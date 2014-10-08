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
package me.philio.disqus;

/**
 * Disqus specific constants
 */
public class DisqusConstants {

    /**
     * Authorize URL
     */
    public static final String AUTHORIZE_URL = "https://disqus.com/api/oauth/2.0/authorize/";

    /**
     * Currently available scopes
     */
    public static final String SCOPE_READ = "read";
    public static final String SCOPE_WRITE = "write";
    public static final String SCOPE_EMAIL = "email";
    public static final String SCOPE_ADMIN = "admin";

    /**
     * Params used in Disqus urls
     */
    public static final String PARAM_CLIENT_ID = "client_id";
    public static final String PARAM_SCOPE = "scope";
    public static final String PARAM_RESPONSE_TYPE = "response_type";
    public static final String PARAM_REDIRECT_URI = "redirect_uri";
    public static final String PARAM_ACCESS_TOKEN = "access_token";
    public static final String PARAM_EXPIRES_IN = "expires_in";
    public static final String PARAM_TOKEN_TYPE = "token_type";
    public static final String PARAM_USER_ID = "user_id";
    public static final String PARAM_USERNAME = "username";

    /**
     * Authorization response types
     */
    public static final String RESPONSE_TYPE_TOKEN = "token";

}
