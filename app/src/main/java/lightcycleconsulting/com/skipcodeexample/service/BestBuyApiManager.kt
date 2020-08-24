package lightcycleconsulting.com.skipcodeexample.service

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import lightcycleconsulting.com.skipcodeexample.R
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
    suspend fun getEmployeesCoroutines (query: String?, cursorMark: String?, context: Context): ProductResults {
        logging.level = HttpLoggingInterceptor.Level.BODY
        val baseUrl: String = context.getString(R.string.get_products_endpoint)
        val apiKey: String = context.getString(R.string.best_buy_api_key)
        val pageSize = "50"
        val showParams = "name,sku,salePrice,image,longDescription,url"
        val format = "json"

        httpClient.addInterceptor(logging)
        httpClient.connectTimeout(5, TimeUnit.MINUTES)
        httpClient.readTimeout(5, TimeUnit.MINUTES)
        httpClient.writeTimeout(5, TimeUnit.MINUTES)
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .client(httpClient.build())
            .build()
        return retrofit.create(ProductApi::class.java).getProductsCoroutines(query,format,showParams,cursorMark,pageSize,apiKey)
    }
}