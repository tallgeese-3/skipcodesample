package lightcycleconsulting.com.skipcodeexample.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import lightcycleconsulting.com.skipcodeexample.R
import lightcycleconsulting.com.skipcodeexample.databinding.ProductDetailsBinding
import lightcycleconsulting.com.skipcodeexample.viewmodel.ProductViewModel
import java.text.NumberFormat
import java.util.*


class ProductDetailsFragment : BaseFragment() {
    private val productViewModel: ProductViewModel by activityViewModels()
    private var _binding: ProductDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = DetailsTransition()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ProductDetailsBinding.inflate(inflater, container, false)
        initViews()
        return binding.root
    }

    private fun initViews() {
        binding.productViewModel = productViewModel
        val format: NumberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
        val currency: String = format.format(productViewModel.selected.value?.salePrice)
        binding.price.text = currency
        binding.productFullDescription.movementMethod = ScrollingMovementMethod()
        (activity as AppCompatActivity).setSupportActionBar(binding.detailsToolbar)
        val supportActionBar = (activity as AppCompatActivity).supportActionBar
        supportActionBar?.title = "Product Details"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.openWebButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(productViewModel.selected.value?.url)
            startActivity(intent)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initTransitions()
        adjustToolbarMarginForNotch()
    }

    private fun initTransitions() {
        postponeEnterTransition()
        if (productViewModel.selected.value?.image == null) {
            Picasso.get().load(R.drawable.ic_best_buy_logo_2018)
                .placeholder(R.drawable.ic_best_buy_logo_2018)
                .error(R.drawable.ic_best_buy_logo_2018)
                .into(binding.imageView, object : Callback {
                    override fun onSuccess() {
                        startPostponedEnterTransition()
                    }

                    override fun onError(e: Exception?) {
                        startPostponedEnterTransition()
                    }
                })
        } else {
            Picasso.get().load(productViewModel.selected.value?.image)
                .placeholder(R.drawable.ic_best_buy_logo_2018)
                .into(binding.imageView, object : Callback {
                    override fun onSuccess() {
                        startPostponedEnterTransition()
                    }

                    override fun onError(e: Exception?) {
                        startPostponedEnterTransition()
                    }
                })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}