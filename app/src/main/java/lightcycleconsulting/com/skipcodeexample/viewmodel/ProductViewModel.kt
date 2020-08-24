package lightcycleconsulting.com.skipcodeexample.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import lightcycleconsulting.com.skipcodeexample.model.ProductResults
import lightcycleconsulting.com.skipcodeexample.service.BestBuyApiManager
import lightcycleconsulting.com.skipcodeexample.service.Resource
import lightcycleconsulting.com.skipcodeexample.R
import lightcycleconsulting.com.skipcodeexample.model.Product


@ExperimentalSerializationApi
class ProductViewModel(application: Application) : AndroidViewModel(application) {
    private val productResults :MutableLiveData<Resource<ProductResults>> by lazy {
        MutableLiveData<Resource<ProductResults>>().also {
        }
    }

    val selected = MutableLiveData<Product>()
    val query = MutableLiveData<String>()

    fun select(item: Product?) {
        selected.value = item
    }

    fun setQuery(queryString: String?) {
        query.value = queryString
        productResults.value = Resource.loading(ProductResults())
    }

    fun getProducts(): LiveData<Resource<ProductResults>> {
        return productResults
    }

    fun loadProductResultsCoroutines() {
        loadProductResultsCoroutines(query.value)
    }

     private fun loadProductResultsCoroutines(query: String?) {
         var resource: Resource<ProductResults>
         viewModelScope.launch(Dispatchers.IO) {
            resource = try {
                val cursorMark = if (productResults.value?.data?.nextCursorMark != null) productResults.value?.data?.nextCursorMark else "*"
                Resource.success(data = BestBuyApiManager().getEmployeesCoroutines(query, cursorMark, getApplication()))
            } catch (exception: Exception) {
                Resource.error(data = ProductResults(), message = exception.message ?: "Error Occurred!")
            }
            withContext(Dispatchers.Main) {
                productResults.value = resource
            }
        }
    }
}
