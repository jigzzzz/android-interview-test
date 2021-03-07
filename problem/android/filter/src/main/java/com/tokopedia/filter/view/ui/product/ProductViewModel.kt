package com.tokopedia.filter.view.ui.product

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.tokopedia.filter.view.data.model.PriceRange
import com.tokopedia.filter.view.data.model.Product
import com.tokopedia.filter.view.data.model.dto.ProductResponse
import com.tokopedia.filter.view.data.repository.ProductRepository
import com.tokopedia.filter.view.external.utils.JsonUtil

class ProductViewModel(private val productRepository: ProductRepository) : ViewModel() {

    private val _products : MutableLiveData<List<Product>> = MutableLiveData()
    val products : LiveData<List<Product>>
        get() = _products

    fun fetchAllProducts(context: Context, cityFilter : String = "", priceRange: PriceRange) {
        var result = productRepository.findAllProducts(context)
        if (cityFilter.isNotBlank())
            result = Product.filterByCity(result, cityFilter)
        if (priceRange.min > 0 && priceRange.max > 0)
            result = Product.filterByPrice(result, priceRange.min, priceRange.max)
        _products.postValue(result)
    }

}