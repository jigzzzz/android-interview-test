package com.tokopedia.filter.view.ui.filter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.slider.Slider
import com.tokopedia.filter.R
import com.tokopedia.filter.view.data.model.PriceRange
import com.tokopedia.filter.view.external.factory.ViewModelFactory
import com.tokopedia.filter.view.external.utils.FilterUtil
import com.tokopedia.filter.view.external.utils.IntegerUtil
import com.tokopedia.filter.view.ui.product.ProductActivity
import com.tokopedia.filter.view.ui.product.ProductViewModel
import kotlinx.android.synthetic.main.fragment_filter_bottom_sheet.*
import java.util.*

class FilterBottomSheetFragment : BottomSheetDialogFragment() {

    lateinit var filterUtil: FilterUtil
    lateinit var viewModel: FilterBottomSheetViewModel

    companion object {
        private fun provideViewModel(fragment: FilterBottomSheetFragment) : FilterBottomSheetViewModel {
            val viewModelFactory = ViewModelFactory.getInstance()
            return ViewModelProvider(fragment, viewModelFactory).get(FilterBottomSheetViewModel::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = provideViewModel(this)
        viewModel.fetchProductPriceRange(activity!!.baseContext)
        viewModel.fetchMostProductLocations(activity!!.baseContext)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_filter_bottom_sheet, container,  false)
        filterUtil = FilterUtil(activity!!.baseContext)

        val slider : Slider = view.findViewById(R.id.filter_price)
        val min : TextView = view.findViewById(R.id.min_price)
        val max : TextView = view.findViewById(R.id.max_price)
        val chipGroup : ChipGroup = view.findViewById(R.id.location_group)
        val buttonApply : Button = view.findViewById(R.id.btn_apply)

        setChips(view)

        val city = filterUtil.loadCity()
        if (city.isNotBlank()) {
            chipGroup.checkedChipIds.forEach {
                val chip = view.findViewById<Chip>(it)
                if (chip.text.toString() == city)
                    chip.isChecked = true
            }
        }

        var prevCheckedId = 0
        var location = ""
        chipGroup.setOnCheckedChangeListener { group, checkedId ->
            if (prevCheckedId != checkedId) {
                val chip = group.findViewById<Chip>(checkedId)
                location = chip.text.toString()
                buttonApply.visibility = View.VISIBLE
            }
        }

        val priceRange = filterUtil.loadPriceRange()
        var minPrice = 0
        var maxPrice = 0
        if (priceRange.min < 1 && priceRange.max < 1)
            viewModel.productPriceRange.observe(this, androidx.lifecycle.Observer {
                min.text = IntegerUtil.convertIntToRupiahFormat(it.min)
                max.text = IntegerUtil.convertIntToRupiahFormat(it.max)
                minPrice = it.min
                slider.valueTo = it.max.toFloat()
                slider.valueFrom = it.min.toFloat()
            })

        slider.setOnChangeListener { _, value ->
            max_price.text = IntegerUtil.convertIntToRupiahFormat(value.toInt())
            maxPrice = value.toInt()
            buttonApply.visibility = View.VISIBLE
        }

        view.findViewById<Button>(R.id.btn_apply).setOnClickListener {
            filterUtil.save(location, PriceRange(minPrice, maxPrice))
            (activity as ProductActivity).setFiltering(location, PriceRange(minPrice, maxPrice))
            (activity as ProductActivity).setAdapter()
            dismiss()
        }

        return view
    }

    private fun setChips(view: View) {
        viewModel.locations.observe(this, androidx.lifecycle.Observer {
            view.findViewById<Chip>(R.id.first_location).text = it[0]
            view.findViewById<Chip>(R.id.second_location).text = it[1]
            view.findViewById<Chip>(R.id.third_location).text = it[2]
        })
    }

}