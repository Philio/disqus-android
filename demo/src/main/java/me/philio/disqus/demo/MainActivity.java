package me.philio.disqus.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Toast;

import me.philio.disqus.AuthorizeActivity;
import me.philio.disqus.AuthorizeUtils;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {

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
            Toast.makeText(this, getString(R.string.auth_success,
                    data.getStringExtra(AuthorizeActivity.EXTRA_USERNAME)), Toast.LENGTH_LONG)
                    .show();
        } else {
            Toast.makeText(this, R.string.auth_failure, Toast.LENGTH_LONG).show();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

}
