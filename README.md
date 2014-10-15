# Disqus for Android

## Important

This library is incomplete/in the early stages of development and is subject to change.

## Download

### Gradle

Add the following to your `build.gradle`:

    compile 'me.philio.disqus:disqus:0.0.1'

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