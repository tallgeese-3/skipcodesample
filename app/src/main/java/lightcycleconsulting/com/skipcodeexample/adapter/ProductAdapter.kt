package lightcycleconsulting.com.skipcodeexample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import lightcycleconsulting.com.skipcodeexample.adapter.viewholder.ProductViewHolder
import lightcycleconsulting.com.skipcodeexample.databinding.ProductItemBinding
import lightcycleconsulting.com.skipcodeexample.model.Product


class ProductAdapter(private var products: ArrayList<Product>?): RecyclerView.Adapter<ProductViewHolder>() {

    fun add (products: List<Product>?) {
        products?.let { this.products?.addAll(it) }
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val employee = products?.get(position)
        holder.bind(employee)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: ProductItemBinding = ProductItemBinding.inflate(
            layoutInflater,
            parent,
            false
        )
        return ProductViewHolder(itemBinding)
    }

    override fun getItemCount(): Int { return products?.size!! }
}