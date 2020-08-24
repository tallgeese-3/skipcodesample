package lightcycleconsulting.com.skipcodeexample.service

import lightcycleconsulting.com.skipcodeexample.model.ProductResults
import retrofit2.http.*

interface ProductApi {

        @GET("/v1/products({search})")
        suspend fun getProductsCoroutines(@Path("search") search: String?,
                                          @Query("format", encoded=true) format: String,
                                          @Query("show", encoded=true) show: String,
                                          @Query("cursorMark", encoded=true) cursorMark: String?,
                                          @Query("pageSize", encoded=true) pageSize: String?,
                                          @Query("apiKey", encoded=true) apiKey: String) : ProductResults
}