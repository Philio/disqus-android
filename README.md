# Disqus for Android

## About

This library implements the Disqus API for use in Android applications. The library is currently
incomplete and under development so is subject to change.

## Download

### Gradle

Add the following to your `build.gradle`:

    compile 'me.philio.disqus:disqus:0.0.8'

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

Third party logins are not supported at this time.

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
defined in the `me.philio.disqus.api.resources` package. It works as a wrapper to the Retrofit
`RestAdapter` and configures the adapter and deserialisation options for Gson.

    ApiConfig apiConfig = new ApiConfig("MyApiKey", "AccessToken");
    ApiClient apiClient = new ApiClient(apiConfig);

### Create resource and make requests

    Applications applications = apiClient.createApplications();
    Response<Usage> usage = applications.listUsage("MyApp", 7);

All resources and requests match the naming conventions defined in the Disqus API documentation, but
often method signatures are kept as simple as possible.

In general:

* Named parameters are required and should not be null.

* Optional parameters can be provided as a `Map` where applicable, refer to the Disqus documentation
for details of optional parameters.

### Response format

All requests will return a `Response` object. Typical responses contain an error code (which will
always be 0 as errors throw exceptions), an optional cursor (which can be used for pagination) and
some data which is a generic type.

The data is usually an object or list of objects and for the majority of requests will be one of the
models defined in the API package. Some requests return empty structures so to avoid parsing issues
the data type is either `Object` or `List<Object>`, the response data for these requests can be
disregarded.