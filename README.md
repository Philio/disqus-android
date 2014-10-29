# Disqus for Android

## About

This library implements the Disqus API for use in Android applications. The library is currently
incomplete and under development so is subject to change.

## Download

### Gradle

Add the following to your `build.gradle`:

    compile 'me.philio.disqus:disqus:0.0.6'

## Authentication

### Using AuthorizeActivity

1. Use `AuthorizeUtils.createIntent` to create a new `Intent` with your application settings.

2. Start the activity with `startActivityForResult`.

3. Implement 'onActivityResult' to get the access token object:

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (resultCode == RESULT_OK) {
                // Auth completed, get the access token
                AccessToken accessToken = data.getParcelableExtra(AuthorizeActivity.EXTRA_ACCESS_TOKEN);
            } else {
                // Auth failed
            }

            super.onActivityResult(requestCode, resultCode, data);
        }

### Using AuthorizeFragment

1. Add the fragment to your activity via `AuthorizeFragment.newInstance`.

2. Your activity should implement the `AuthorizeFragment.AuthorizeListener` callback.

3. You may need to handle failures/cancellations within your activity, e.g. if back is pressed as
    currently the cancel button on the Disqus auth page doesn't seem to work.

### Google, Facebook, Twitter

Third party logins are not supported yet, I need to investigate this further and see if it's
possible to implement SSO or not. Should be added in the near future.

## Basic usage

### Create configuration

Use the `ApiConfig` class to set your app configuration

Options:

* API key - mandatory for all requests.
* API secret - intended for server to server requests as an alternative to the API key/access token,
can be used from a mobile app but this presents security risks and is not recommended.
* Access token - required for requests that require authentication.
* Referrer - required for some requests that perform domain checks, should match a domain in your
Disqus app settings.

### Create client

As of version 0.0.5 the library is using [Retrofit](http://square.github.io/retrofit/) for requests.

The `ApiClient` can be used to create Disqus resource objects based on the Retrofit interfaces
defined in the `me.philio.disqus.api.resource` package. It works as a wrapper to the Retrofit
`RestAdapter` and configures the adapter and deserialisation options for Gson.

    ApiConfig apiConfig = new ApiConfig("MyApiKey", "AccessToken");
    ApiClient apiClient = new ApiClient(apiConfig);

### Get resource and make requests

    Applications applications = apiClient.createApplications();
    Response<Usage> usage = applications.listUsage("MyApp", 7);

Most requests will exactly match the Disqus API documentation, with minor differences:

* Some methods perform multiple functions, so have been split into single functions for simplicity,
e.g. add to/remove from blacklist/whitelist

* Some methods have a lot of optional parameters so use a map to avoid long method calls full of
nulls.