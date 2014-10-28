package me.philio.disqus.demo;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.Date;
import java.util.List;

import me.philio.disqus.AuthorizeActivity;
import me.philio.disqus.AuthorizeUtils;
import me.philio.disqus.api.ApiClient;
import me.philio.disqus.api.ApiConfig;
import me.philio.disqus.api.exception.ApiException;
import me.philio.disqus.api.model.Response;
import me.philio.disqus.api.model.application.Usage;
import me.philio.disqus.api.model.blacklist.BlacklistEntry;
import me.philio.disqus.api.model.oauth2.AccessToken;
import me.philio.disqus.api.model.user.UserDetails;
import me.philio.disqus.api.resource.Applications;
import me.philio.disqus.api.resource.Blacklists;
import me.philio.disqus.api.resource.Users;

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
                        ApiConfig config = new ApiConfig(BuildConfig.API_KEY, accessToken.accessToken, BuildConfig.REDIRECT_URI);
                        ApiClient client = new ApiClient(config);

                        Applications applications = client.createApplications();
                        Response<Usage> usage = applications.listUsage(null, null);
                        for (Date date : usage.data.keySet()) {
                            Log.d(TAG, date.toString() + " " + usage.data.get(date));
                        }

                        Blacklists blacklists = client.createBlacklists();
                        Response<List<BlacklistEntry>> domains = blacklists.addDomains("philio", new String[]{"test.com"}, 0, null);
                        for (BlacklistEntry entry : domains.data) {
                            Log.d(TAG, entry.type.toString() + " " + entry.value);
                        }
                        Response<List<BlacklistEntry>> words = blacklists.addWords("philio", new String[]{"fuck", "fucker", "cunt"}, 0, null);
                        for (BlacklistEntry entry : words.data) {
                            Log.d(TAG, entry.type.toString() + " " + entry.value);
                        }
                        Response<List<BlacklistEntry>> emails = blacklists.addEmails("philio", new String[]{"test@test.com"}, 0, null);
                        for (BlacklistEntry entry : emails.data) {
                            Log.d(TAG, entry.type.toString() + " " + entry.value);
                        }
                        Response<List<BlacklistEntry>> ips = blacklists.addIps("philio", new String[]{"254.254.254.254"}, 0, null);
                        for (BlacklistEntry entry : ips.data) {
                            Log.d(TAG, entry.type.toString() + " " + entry.value);
                        }
                        Response<List<BlacklistEntry>> bUsers = blacklists.addUsers("philio", new Long[]{1l}, 0, null);
                        for (BlacklistEntry entry : bUsers.data) {
                            Log.d(TAG, entry.type.toString() + " " + entry.value);
                        }

                        Users users = client.createUsers();
                        Response<String> check = users.checkUsername("bayf");
                        Log.d(TAG, check.data);
                        Response<UserDetails> details = users.details(null);
                        Log.d(TAG, details.data.name);
                    } catch (ApiException e) {
                        Log.e(TAG, e.getMessage());
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
