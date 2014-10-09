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

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import me.philio.disqus.api.model.oauth2.AccessToken;

/**
 * OAuth authorization fragment
 * <p/>
 * Sets up a {@link WebView} to start the OAuth2 process, captures the result and passes it to an
 * {@link AuthorizeListener}.
 */
public class AuthorizeFragment extends Fragment {

    /**
     * Logging tag
     */
    private static final String TAG = AuthorizeFragment.class.getName();

    /**
     * Arguments
     */
    private static final String ARG_API_KEY = "api_key";
    private static final String ARG_SCOPES = "scope";
    private static final String ARG_REDIRECT_URI = "redirect_uri";

    /**
     * The Disqus API key
     */
    private String mApiKey;

    /**
     * Scopes
     */
    private String[] mScopes;

    /**
     * Authorize redirect URI
     */
    private String mRedirectUri;

    /**
     * Authorization listener
     */
    private AuthorizeListener mListener;

    /**
     * A {@link WebView} to display the Disqus login
     */
    private WebView mWebView;

    /**
     * Get a new instance of this fragment
     *
     * @param apiKey
     * @param redirectUri
     * @return
     */
    public static AuthorizeFragment newInstance(String apiKey, String[] scopes, String redirectUri) {
        AuthorizeFragment fragment = new AuthorizeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_API_KEY, apiKey);
        args.putStringArray(ARG_SCOPES, scopes);
        args.putString(ARG_REDIRECT_URI, redirectUri);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(ARG_API_KEY)) {
            mApiKey = getArguments().getString(ARG_API_KEY);
        }
        if (getArguments().containsKey(ARG_SCOPES)) {
            mScopes = getArguments().getStringArray(ARG_SCOPES);
        }
        if (getArguments().containsKey(ARG_REDIRECT_URI)) {
            mRedirectUri = getArguments().getString(ARG_REDIRECT_URI);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (AuthorizeListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement AuthorizeCallback");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.disqus_fragment_authorize, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mWebView = (WebView) view.findViewById(R.id.disqus_authorize_webview);

        // Setup custom client to catch the redirect
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith(mRedirectUri)) {
                    // Get fragment from url
                    Log.d(TAG, "Processing redirect");
                    Uri uri = Uri.parse(url);
                    String uriFragment = uri.getFragment();

                    // Extract data from fragment and pass to callback
                    Uri queryUri = new Uri.Builder().encodedQuery(uriFragment).build();
                    AccessToken accessToken = new AccessToken();
                    accessToken.username = queryUri.getQueryParameter(DisqusConstants.PARAM_USERNAME);
                    accessToken.userId = Long.parseLong(queryUri.getQueryParameter(DisqusConstants.PARAM_USER_ID));
                    accessToken.accessToken = queryUri.getQueryParameter(DisqusConstants.PARAM_ACCESS_TOKEN);
                    accessToken.expiresIn = Long.parseLong(queryUri.getQueryParameter(DisqusConstants.PARAM_EXPIRES_IN));
                    accessToken.tokenType = queryUri.getQueryParameter(DisqusConstants.PARAM_TOKEN_TYPE);
                    accessToken.state = queryUri.getQueryParameter(DisqusConstants.PARAM_STATE);
                    accessToken.scope = queryUri.getQueryParameter(DisqusConstants.PARAM_SCOPE);
                    mListener.onSuccess(accessToken);
                    return true;
                }
                return super.shouldOverrideUrlLoading(view, url);
            }
        });

        // Load authorize url
        Log.d(TAG, "Loading authorize url");
        String scope = AuthorizeUtils.buildScope(mScopes);
        Uri uri = AuthorizeUtils.buildAuthorizeUri(mApiKey, scope, mRedirectUri);
        mWebView.loadUrl(uri.toString());
    }

    /**
     * Listener interface, must be implemented by calling activity
     * 
     * TODO Should handle failures too but at time of writing the cancel button on the Disqus site
     * TODO is broken so only way to cancel is via back button which can be handled in the activity
     */
    public interface AuthorizeListener {

        public void onSuccess(AccessToken accessToken);

    }

}
