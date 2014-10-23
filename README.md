# Disqus for Android

## About

This library implements the Disqus API for use in Android applications. The library is currently
incomplete and under development so is subject to change.

## Download

### Gradle

Add the following to your `build.gradle`:

    compile 'me.philio.disqus:disqus:0.0.3'

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

### Create resource

Instantiate any of the API resources with your `ApiConfig`:

    ApiConfig apiConfig = new ApiConfig("apiKey", "accessToken", "http://referrer/");
    Users users = new Users(apiConfig);

### Execute requests

Currently implemented methods can be called using all params specified in the Disqus documentation.
This is probably not the most optimal implementation and usage will be reviewed at a later date.

A combination of primitive and object types have been used and represent optional and required
parameters for each method e.g.:

* Integer represents a nullable optional integer param.
* int represents a required integer param which generally requires a positive integer value.