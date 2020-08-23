package lightcycleconsulting.com.skipcodeexample.model

import kotlinx.serialization.Serializable

@Serializable
data class ProductResults(val from: Int =0, val to: Int = 0,
                          val currentPage: Int = 0, val total: Int = 0,
                          val totalPages: Int = 0, val queryTime: Double = 0.0,
                          val totalTime: Double = 0.0, val partial: Boolean = false,
                          val nextCursorMark: String? = null,
                          val canonicalUrl: String =  "", val products: List<Product> = ArrayList())