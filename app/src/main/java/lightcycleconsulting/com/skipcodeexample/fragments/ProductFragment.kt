package lightcycleconsulting.com.skipcodeexample.fragments

import android.graphics.drawable.ClipDrawable.HORIZONTAL
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import kotlinx.android.synthetic.main.error_layout.view.*
import kotlinx.serialization.ExperimentalSerializationApi
import lightcycleconsulting.com.skipcodeexample.R
import lightcycleconsulting.com.skipcodeexample.utils.QueryUtils.convertQueryToSearchParameter
import lightcycleconsulting.com.skipcodeexample.adapter.PhotoInfiniteScrollListener
import lightcycleconsulting.com.skipcodeexample.adapter.ProductAdapter
import lightcycleconsulting.com.skipcodeexample.databinding.ProductBinding
import lightcycleconsulting.com.skipcodeexample.model.Product
import lightcycleconsulting.com.skipcodeexample.service.Status
import lightcycleconsulting.com.skipcodeexample.viewmodel.ProductViewModel
import java.io.UnsupportedEncodingException


@ExperimentalSerializationApi
class ProductFragment: BaseFragment(), (Product?, Int) -> Unit {
    private val productViewModel: ProductViewModel by activityViewModels()
    private var _binding: ProductBinding? = null
    private val binding get() = _binding!!
    private var adapter = ProductAdapter(ArrayList<Product>(), this)
    private var lastPageLoaded: Boolean = false
    lateinit var mInfiniteScrollListener: PhotoInfiniteScrollListener
    private var hasScrolled: Boolean = false
    val LOADING_POINT = 0.5f

    companion object {
        const val RECORDS_PER_QUERY = 50
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.toolbar, menu)
        val search = menu.findItem(R.id.action_search).actionView as SearchView
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                adapter.clear()
                productViewModel.setQuery(convertQueryToSearchParameter(query))
                search.clearFocus()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = ProductBinding.inflate(inflater, container, false)
        val view = binding.root
        initViews()
        return view
    }

    private fun initViews() {
        binding.list.layoutManager = LinearLayoutManager(context)
        binding.list.setHasFixedSize(true)
        (binding.list.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        binding.list.adapter = adapter
        val itemDecor = DividerItemDecoration(context, HORIZONTAL)
        binding.list.addItemDecoration(itemDecor)
        mInfiniteScrollListener = createInfiniteScrollListener()
        binding.list.addOnScrollListener(mInfiniteScrollListener)
        binding.list.apply {
            postponeEnterTransition()
            viewTreeObserver
                .addOnPreDrawListener {
                    startPostponedEnterTransition()
                    true
                }
        }
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        val supportActionBar = (activity as AppCompatActivity).supportActionBar
        supportActionBar?.title = "Skip Coding Challenge"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adjustToolbarMarginForNotch()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        initObservers()
    }

    private fun initObservers() {
        productViewModel.getProducts().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.progressIndicator.visibility = View.GONE
                        if (it.data?.products?.size == 0 && adapter.itemCount == 0) {
                            binding.noData.errorView.visibility = View.GONE
                            binding.noData.emptyView.empty_message.text = getString(R.string.no_results)
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
                        binding.noData.errorView.visibility = View.GONE
                        binding.noData.emptyView.visibility = View.GONE
                        binding.progressIndicator.visibility = View.VISIBLE
                    }
                }
            }
        })

        productViewModel.query.observe(this, Observer {
            productViewModel.loadProductResultsCoroutines()
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding?.list?.adapter = null
        _binding = null
    }

    private fun createInfiniteScrollListener(): PhotoInfiniteScrollListener {
        return object : PhotoInfiniteScrollListener(
            RECORDS_PER_QUERY,
            LOADING_POINT,
            binding.list.layoutManager as LinearLayoutManager
        ) {
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

    override fun invoke(product: Product?, position: Int) {
        productViewModel.select(product)
        val navController = Navigation.findNavController(binding.root)
        val extras = FragmentNavigatorExtras(
            binding.list.findViewHolderForAdapterPosition(position)?.itemView?.findViewById<ImageView>(
                R.id.avatar_image
            )!! to getString(R.string.imageTransitionName)
        )
        navController.navigate(R.id.action_productFragment_to_productDetailsFragment, null, null, extras)
    }
}