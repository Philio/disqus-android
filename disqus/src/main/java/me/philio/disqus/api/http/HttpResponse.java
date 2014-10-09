package me.philio.disqus.api.http;

/**
 * HTTP response wrapper that encapsulates the response code and body
 */
public class HttpResponse {

    /**
     * Response code
     */
    private int mCode;

    /**
     * Repsonse body
     */
    private String mBody;

    /**
     * Constructor
     *
     * @param code
     * @param body
     */
    public HttpResponse(int code, String body) {
        mCode = code;
        mBody = body;
    }

    /**
     * Get the response code
     *
     * @return
     */
    public int getCode() {
        return mCode;
    }

    /**
     * Get the response body
     *
     * @return
     */
    public String getBody() {
        return mBody;
    }

}
