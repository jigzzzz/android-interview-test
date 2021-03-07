package com.tokopedia.filter.view.external.utils

import android.content.Context
import android.content.SharedPreferences
import com.tokopedia.filter.view.data.model.PriceRange

class FilterUtil(private val context: Context) {
    private var sharedPreferences : SharedPreferences = context.getSharedPreferences("filter", Context.MODE_PRIVATE)

    companion object {
        const val CITY = "CITY"
        const val MIN_PRICE = "MIN_PRICE"
        const val MAX_PRICE = "MAX_PRICE"
    }

    fun save(cityFilter: String, priceRange: PriceRange) {
        val editor = sharedPreferences.edit()
        editor.putString(CITY, cityFilter)
        editor.putInt(MIN_PRICE, priceRange.min)
        editor.putInt(MAX_PRICE, priceRange.max)
    }

    fun loadCity() : String {
        return sharedPreferences.getString(CITY, "")!!
    }

    fun loadPriceRange() : PriceRange {
        return PriceRange(sharedPreferences.getInt(MIN_PRICE, 0), sharedPreferences.getInt(MAX_PRICE, 0))
    }
}