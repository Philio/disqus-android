package me.philio.disqus.api.resources;

import java.util.List;

import me.philio.disqus.api.exception.ApiException;
import me.philio.disqus.api.model.Response;
import me.philio.disqus.api.model.imports.Import;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Imports resource
 *
 * @see <a href="https://disqus.com/api/docs/imports/">Documentation</a>
 */
public interface Imports {

    /**
     * Get import details
     *
     * @see <a href="https://disqus.com/api/docs/imports/">Documentation</a>
     * @param forum
     * @param group
     * @return
     * @throws ApiException
     */
    @GET("/imports/details.json")
    public Response<Import> details(@Query("forum") String forum,
                                    @Query("group") long group) throws ApiException;

    /**
     * Get list of imports
     *
     * @see <a href="https://disqus.com/api/docs/imports/">Documentation</a>
     * @param forum
     * @return
     * @throws ApiException
     */
    @GET("/imports/list.json")
    public Response<List<Import>> list(@Query("forum") String forum) throws ApiException;

}
