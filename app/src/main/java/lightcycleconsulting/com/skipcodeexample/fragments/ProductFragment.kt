package lightcycleconsulting.com.skipcodeexample.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import lightcycleconsulting.com.skipcodeexample.adapter.PhotoInfiniteScrollListener
import lightcycleconsulting.com.skipcodeexample.adapter.ProductAdapter
import lightcycleconsulting.com.skipcodeexample.databinding.ProductFragmentBinding
import lightcycleconsulting.com.skipcodeexample.model.Product
import lightcycleconsulting.com.skipcodeexample.service.Status
import lightcycleconsulting.com.skipcodeexample.viewmodel.ProductViewModel
import java.io.UnsupportedEncodingException


class ProductFragment: Fragment() {
    private val productViewModel: ProductViewModel by activityViewModels()
    private var _binding: ProductFragmentBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var adapter = ProductAdapter(ArrayList<Product>())
    private var page = 0
    private var lastPageLoaded: Boolean = false
    private var mInfiniteScrollListener: PhotoInfiniteScrollListener? = null
    private var hasScrolled: Boolean = false
    val LOADING_POINT = 0.5f

    companion object {
        const val RECORDS_PER_QUERY = 50
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ProductFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.list.layoutManager = LinearLayoutManager(context)
        binding.list.setHasFixedSize(true)
        (binding.list.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        binding.list.adapter = adapter
        mInfiniteScrollListener = createInfiniteScrollListener()
        binding.list.addOnScrollListener(mInfiniteScrollListener!!)
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productViewModel.getEmployees().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.progressIndicator.visibility = View.GONE
                        if (it.data?.products?.size ==  0 && adapter.itemCount == 0) {
                            binding.noData.errorView.visibility = View.GONE
                            binding.noData.emptyView.visibility = View.VISIBLE
                        } else {
                            if (it.data?.products?.size == 0) {
                                lastPageLoaded = true
                            } else {
                                adapter.add(it.data?.products)
                            }
                            binding.noData.errorView.visibility = View.GONE
                            binding.noData.emptyView.visibility = View.GONE
                        }
                        hasScrolled = false
                        binding.scrollngLoadingIndicator.visibility = View.GONE
                    }
                    Status.ERROR -> {
                        binding.progressIndicator.visibility = View.GONE
                        binding.noData.errorMeesage.text = resource.message
                        binding.noData.errorView.visibility = View.VISIBLE
                        binding.noData.emptyView.visibility = View.GONE
                        binding.scrollngLoadingIndicator.visibility = View.GONE
                        hasScrolled = false
                    }
                    Status.LOADING -> {
                        binding.progressIndicator.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun createInfiniteScrollListener(): PhotoInfiniteScrollListener {
        return object : PhotoInfiniteScrollListener(RECORDS_PER_QUERY, LOADING_POINT, binding.list.layoutManager as LinearLayoutManager) {
            override fun onScrolledToEnd(firstVisibleItemPosition: Int) {
                if (!hasScrolled && !lastPageLoaded) {
                    try {
                        hasScrolled = true
                       binding.scrollngLoadingIndicator.visibility = View.VISIBLE
                        productViewModel.loadProductResultsCoroutines()
                    } catch (e: UnsupportedEncodingException) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }
}