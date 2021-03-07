package com.tokopedia.filter.view.ui.filter

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tokopedia.filter.view.data.model.PriceRange
import com.tokopedia.filter.view.data.model.Product
import com.tokopedia.filter.view.data.repository.ProductRepository

class FilterBottomSheetViewModel(private val productRepository: ProductRepository) : ViewModel() {

    private val _productPriceRange : MutableLiveData<PriceRange> = MutableLiveData()
    val productPriceRange : LiveData<PriceRange>
        get() = _productPriceRange

    private val _locations : MutableLiveData<List<String>> = MutableLiveData()
    val locations : LiveData<List<String>>
        get() = _locations

    fun fetchProductPriceRange(context: Context) {
        _productPriceRange.postValue(productRepository.findRangePriceFromAllProducts(context))
    }

    fun fetchMostProductLocations(context: Context) {
        _locations.postValue(productRepository.findTop3FreqLocations(context))
    }

}