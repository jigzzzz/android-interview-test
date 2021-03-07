package com.tokopedia.filter.view.ui.product

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tokopedia.filter.R
import com.tokopedia.filter.view.data.model.PriceRange
import com.tokopedia.filter.view.external.adapter.ProductAdapter
import com.tokopedia.filter.view.external.factory.ViewModelFactory
import com.tokopedia.filter.view.external.utils.FilterUtil
import com.tokopedia.filter.view.ui.filter.FilterBottomSheetFragment

class ProductActivity : AppCompatActivity() {

    lateinit var viewModel: ProductViewModel
    lateinit var filterUtil: FilterUtil

    companion object {
        private fun provideViewModel(activity: ProductActivity) : ProductViewModel {
            val viewModelFactory = ViewModelFactory.getInstance()
            return ViewModelProvider(activity, viewModelFactory).get(ProductViewModel::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        viewModel = provideViewModel(this)
        setFiltering()
        setAdapter()


        findViewById<TextView>(R.id.btn_filter).setOnClickListener {
            val filterBottomSheetFragment = FilterBottomSheetFragment()
            filterBottomSheetFragment.show(supportFragmentManager, filterBottomSheetFragment.tag)
        }
    }

    fun setFiltering(location: String = "", price: PriceRange = PriceRange(0,0)) {
        filterUtil = FilterUtil(this)
        viewModel.fetchAllProducts(this, location, price)
    }

    fun setAdapter() {
        val adapter = ProductAdapter()
        viewModel.products.observe(this, Observer {
            adapter.setProducts(it)
        })
        findViewById<RecyclerView>(R.id.product_list).apply {
            layoutManager = GridLayoutManager(this@ProductActivity, 2)
            setAdapter(adapter)
        }
    }

}