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

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import me.philio.disqus.api.model.oauth2.AccessToken;

import static me.philio.disqus.AuthorizeFragment.AuthorizeListener;

/**
 * OAuth authorization activity
 *
 * Loads the {@link AuthorizeFragment} and implements {@link AuthorizeListener} then passes the
 * result back to calling activity.
 */
public class AuthorizeActivity extends ActionBarActivity implements
        AuthorizeListener {

    /**
     * Extras that should be passed in the {@link Intent}
     */
    public static final String EXTRA_API_KEY = "api_key";
    public static final String EXTRA_SCOPES = "scopes";
    public static final String EXTRA_REDIRECT_URI = "redirect_uri";

    /**
     * Extras that are passed in the result
     */
    public static final String EXTRA_ACCESS_TOKEN = "access_token";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.disqus_activity_authorize);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            // Get extras
            String apiKey = extras.getString(EXTRA_API_KEY);
            String[] scopes = extras.getStringArray(EXTRA_SCOPES);
            String redirectUri = extras.getString(EXTRA_REDIRECT_URI);

            // Add fragment
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.disqus_authorize_fragment,
                            AuthorizeFragment.newInstance(apiKey, scopes, redirectUri))
                    .commit();
        } else {
            // Can't do anything without the right extras so finish
            // TODO Add some sort of error handling?
            finish();
        }
    }

    @Override
    public void onSuccess(AccessToken accessToken) {
        // Create a result intent
        Intent data = new Intent();
        data.putExtra(EXTRA_ACCESS_TOKEN, accessToken);
        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        super.onBackPressed();
    }

}
