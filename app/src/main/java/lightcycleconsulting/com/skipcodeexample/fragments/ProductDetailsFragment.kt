package lightcycleconsulting.com.skipcodeexample.fragments

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.transition.TransitionInflater
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import lightcycleconsulting.com.skipcodeexample.R
import lightcycleconsulting.com.skipcodeexample.databinding.ProductDetailsBinding
import lightcycleconsulting.com.skipcodeexample.viewmodel.ProductViewModel
import java.lang.Exception
import java.text.NumberFormat
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProductDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProductDetailsFragment : Fragment() {

    private val productViewModel: ProductViewModel by activityViewModels()
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: ProductDetailsBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)

        }
        sharedElementEnterTransition = DetailsTransition()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ProductDetailsBinding.inflate(inflater, container, false)
        binding.productViewModel = productViewModel
        val format: NumberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
        val currency: String = format.format(productViewModel.selected.value?.salePrice)
        binding.price.text = currency
        binding.productFullDescription.setMovementMethod(ScrollingMovementMethod())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProductDetailsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProductDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}