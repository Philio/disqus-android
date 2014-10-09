package me.philio.disqus.api.http;

import android.content.Context;
import android.net.Uri;
import android.net.http.AndroidHttpClient;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * A simple wrapper around {@link AndroidHttpClient} for Froyo upwards support. Only currently
 * implements GET and POST as this is all the Disqus API uses currently. No threading support,
 * should be called from a background thread.
 */
public class HttpRequest {

    /**
     * User agent
     */
    private static final String USER_AGENT = "Disqus Android/0.1";

    /**
     * Instance of the HTTP client
     */
    private AndroidHttpClient mClient;

    public HttpRequest(Context context) {
        mClient = AndroidHttpClient.newInstance(USER_AGENT, context);
    }

    /**
     * Get request
     *
     * @param uri
     * @return
     * @throws IOException
     */
    public HttpResponse get(Uri uri) throws IOException {
        HttpGet get = new HttpGet(uri.toString());
        return execute(get);
    }

    /**
     * Post request
     *
     * @param uri
     * @param data
     * @return
     * @throws IOException
     */
    public HttpResponse post(Uri uri, String data) throws IOException {
        HttpPost post = new HttpPost(uri.toString());
        post.setEntity(new StringEntity(data));
        return execute(post);
    }

    /**
     * Close client
     */
    public void close() {
        mClient.close();
    }

    /**
     * Execute a request
     *
     * @param request
     * @return
     * @throws IOException
     */
    private HttpResponse execute(HttpUriRequest request) throws IOException {
        org.apache.http.HttpResponse response = mClient.execute(request);
        HttpEntity entity = response.getEntity();
        String body = EntityUtils.toString(entity);
        return new HttpResponse(response.getStatusLine().getStatusCode(), body);
    }

}
