package lightcycleconsulting.com.skipcodeexample.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import lightcycleconsulting.com.skipcodeexample.model.ProductResults
import lightcycleconsulting.com.skipcodeexample.service.BestBuyApiManager
import lightcycleconsulting.com.skipcodeexample.service.Resource
import lightcycleconsulting.com.skipcodeexample.R



class ProductViewModel(application: Application) : AndroidViewModel(application) {
    private val productResults :MutableLiveData<Resource<ProductResults>> by lazy {
        MutableLiveData<Resource<ProductResults>>().also {
            loadProductResultsCoroutines()
        }
    }

    fun getEmployees(): LiveData<Resource<ProductResults>> {
        return productResults
    }

     fun loadProductResultsCoroutines() {
         var resource: Resource<ProductResults>
         viewModelScope.launch(Dispatchers.IO) {
            resource = try {
                val cursorMark = if (productResults.value?.data?.nextCursorMark != null) productResults.value?.data?.nextCursorMark else "*"
                Resource.success(data = BestBuyApiManager().getEmployeesCoroutines(cursorMark))
            } catch (exception: Exception) {
                Resource.error(data = ProductResults(), message = exception.message ?: "Error Occurred!")
            }
            withContext(Dispatchers.Main) {
                productResults.value = resource
            }
        }
    }
}
