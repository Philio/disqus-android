package me.philio.disqus.api.resource;

import me.philio.disqus.api.exception.ApiException;
import me.philio.disqus.api.model.Response;
import me.philio.disqus.api.model.application.Usage;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Applications resource
 */
public interface Applications {

    @GET("/applications/listUsage.json")
    public Response<Usage> listUsage(@Query("application") String application,
                                     @Query("days") Integer days) throws ApiException;

}
