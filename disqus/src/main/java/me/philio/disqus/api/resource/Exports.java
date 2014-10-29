package me.philio.disqus.api.resource;

import me.philio.disqus.api.exception.ApiException;
import me.philio.disqus.api.model.Response;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Exports resource
 */
public interface Exports {

    /**
     * Triggers an export of the forum which is sent via email. API returns an empty object for this
     * request. Response param format only has a single option so has been omitted.
     *
     * @param forum
     * @return
     * @throws ApiException
     */
    @POST("/exports/exportForum.json")
    public Response<Object> exportForum(@Query("forum") String forum) throws ApiException;

}
