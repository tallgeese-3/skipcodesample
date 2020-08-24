package lightcycleconsulting.com.skipcodeexample.service

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import lightcycleconsulting.com.skipcodeexample.model.ProductResults
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

class BestBuyApiManager {
    private val logging = HttpLoggingInterceptor()
    private val httpClient = OkHttpClient.Builder()
    private val contentType = "application/json".toMediaType()


    @ExperimentalSerializationApi
    suspend fun getEmployeesCoroutines (cursorMark: String?): ProductResults {
        logging.level = HttpLoggingInterceptor.Level.BODY
        val baseUrl: String = "https://api.bestbuy.com/"
        httpClient.addInterceptor(logging)
        httpClient.connectTimeout(5, TimeUnit.MINUTES)
        httpClient.readTimeout(5, TimeUnit.MINUTES)
        httpClient.writeTimeout(5, TimeUnit.MINUTES)
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .client(httpClient.build())
            .build()
        return retrofit.create(ProductApi::class.java).getProductsCoroutines("search=playstation","json","name,sku,salePrice,image,longDescription,url",cursorMark,"50", "7Ob7hGyGMBma1ilGiq7tc2XZ")
    }
}