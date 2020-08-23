package lightcycleconsulting.com.skipcodeexample.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Product(@SerialName("name") val name: String,
                   @SerialName("sku") val sku: Long? = null,
                   @SerialName("salePrice")val salePrice: Double,
                   @SerialName("image")  val image: String? = null,
                   @SerialName("longDescription")  val longDescription: String? = null,
                   @SerialName("url")  val url: String? = null
)