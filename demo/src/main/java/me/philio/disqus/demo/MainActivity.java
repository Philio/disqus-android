package me.philio.disqus.demo;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import me.philio.disqus.AuthorizeActivity;
import me.philio.disqus.AuthorizeUtils;
import me.philio.disqus.api.Users;
import me.philio.disqus.api.model.Response;
import me.philio.disqus.api.model.forum.ForumDetails;
import me.philio.disqus.api.model.oauth2.AccessToken;
import me.philio.disqus.api.model.post.PostDetails;
import me.philio.disqus.api.model.thread.ThreadDetails;
import me.philio.disqus.api.model.user.UserDetails;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_activity_demo:
                Intent intent = AuthorizeUtils.createIntent(this, BuildConfig.API_KEY,
                        BuildConfig.SCOPES, BuildConfig.REDIRECT_URI);
                startActivityForResult(intent, 0);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            final AccessToken accessToken = data.getParcelableExtra(AuthorizeActivity.EXTRA_ACCESS_TOKEN);
            Toast.makeText(this, getString(R.string.auth_success, accessToken.username),
                    Toast.LENGTH_LONG).show();

            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... params) {
                    try {
                        Users users = new Users(BuildConfig.API_KEY, accessToken.accessToken);
                        Response<List<ThreadDetails>> threadDetails = users.listActiveThreads(null, null, null, null, null, null, null, null);
                        for (ThreadDetails details : threadDetails.response) {
                            Log.d(TAG, details.feed);
                            Log.d(TAG, details.forum);
                        }
                        Response<List<UserDetails>> userDetails = users.listFollowers(null, null, null, null, null);
                        for (UserDetails details : userDetails.response) {
                            Log.d(TAG, details.username);
                            Log.d(TAG, details.profileUrl);
                        }
                        userDetails = users.listFollowing(null, null, null, null, null);
                        for (UserDetails details : userDetails.response) {
                            Log.d(TAG, details.username);
                            Log.d(TAG, details.profileUrl);
                        }
                        Response<List<ForumDetails>> forumDetails = users.listFollowingForums(null, null, null, null, null);
                        for (ForumDetails details : forumDetails.response) {
                            Log.d(TAG, details.url);
                            Log.d(TAG, details.language);
                        }

                        Response<List<PostDetails>> postDetails = users.listPosts(null, null, null, null, null, null, null);
                        for (PostDetails details : postDetails.response) {
                            Log.d(TAG, details.rawMessage);
                            Log.d(TAG, details.forum);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            }.execute();
        } else {
            Toast.makeText(this, R.string.auth_failure, Toast.LENGTH_LONG).show();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

}
