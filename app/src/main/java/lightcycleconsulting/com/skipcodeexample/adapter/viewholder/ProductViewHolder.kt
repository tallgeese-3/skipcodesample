package lightcycleconsulting.com.skipcodeexample.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import lightcycleconsulting.com.skipcodeexample.databinding.ProductItemBinding
import lightcycleconsulting.com.skipcodeexample.model.Product
import java.text.NumberFormat
import java.util.*

class ProductViewHolder(val binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(product: Product?) {
        binding.product = product
        binding.executePendingBindings()
        Picasso.get().load(product?.image)
            .into(binding.avatarImage)
        val format: NumberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
        val currency: String = format.format(binding.product?.salePrice)
        binding.price.text = currency
    }
}