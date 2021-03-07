package com.tokopedia.filter.view.external.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tokopedia.filter.view.data.repository.ProductRepository
import com.tokopedia.filter.view.di.Injection
import com.tokopedia.filter.view.ui.filter.FilterBottomSheetViewModel
import com.tokopedia.filter.view.ui.product.ProductViewModel

class ViewModelFactory(private val productRepository: ProductRepository) : ViewModelProvider.Factory {

    companion object {
        private var INSTANCE : ViewModelFactory? = null
        fun getInstance() : ViewModelFactory {
            if (INSTANCE == null)
                synchronized(ViewModelFactory::class) {
                    if (INSTANCE == null)
                        INSTANCE = ViewModelFactory(Injection.provideProductRepository)
                }
            return INSTANCE!!
        }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(ProductViewModel::class.java) -> ProductViewModel(productRepository)
            modelClass.isAssignableFrom(FilterBottomSheetViewModel::class.java) -> FilterBottomSheetViewModel(productRepository)
            else -> throw IllegalArgumentException("Unknown ViewModel Class: " + modelClass.name)
        } as T
    }
}