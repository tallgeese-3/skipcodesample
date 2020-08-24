package lightcycleconsulting.com.skipcodeexample.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import lightcycleconsulting.com.skipcodeexample.R
import lightcycleconsulting.com.skipcodeexample.databinding.ProductItemBinding
import lightcycleconsulting.com.skipcodeexample.model.Product
import java.text.NumberFormat
import java.util.*

class ProductViewHolder(private val binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(product: Product?, listener: (Product?, Int) -> Unit) {
        binding.product = product
        binding.executePendingBindings()
        Picasso.get().load(product?.image)
            .placeholder(R.drawable.ic_best_buy_logo_2018)
            .into(binding.avatarImage)
        val format: NumberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
        val currency: String = format.format(binding.product?.salePrice)
        binding.price.text = currency
        itemView.setOnClickListener { listener( binding.product, adapterPosition) }
        binding.avatarImage.transitionName = itemView.resources.getString(R.string.imageTransitionName) + adapterPosition
    }
}