package com.tokopedia.filter.view.data.repository

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.tokopedia.filter.R
import com.tokopedia.filter.view.data.model.Merchant
import com.tokopedia.filter.view.data.model.PriceRange
import com.tokopedia.filter.view.data.model.Product
import com.tokopedia.filter.view.data.model.dto.ProductResponse
import com.tokopedia.filter.view.external.utils.IntegerUtil
import com.tokopedia.filter.view.external.utils.JsonUtil
import java.io.FileReader

class ProductRepository {

    fun findAllProducts(context: Context) : List<Product> {
        val products = mutableListOf<Product>()
        val data = JsonUtil.convertAssetToJson(context)
        val response = Gson().fromJson(data, ProductResponse::class.java)
        response.data.products.forEach {
            val merchant = Merchant(
                    it.merchant.id,
                    it.merchant.name,
                    it.merchant.city
            )
            products.add(Product(
                    it.id,
                    it.name,
                    it.imageUrl,
                    it.price,
                    it.discountPercentage,
                    it.slashedPrice,
                    it.discountPercentage > 0,
                    merchant
            ))
        }
        return products
    }

    fun findTop3FreqLocations(context: Context) : List<String> {
        val locations = mutableListOf<String>()
        val locationMaps = hashMapOf<String, Int>()
        val data = JsonUtil.convertAssetToJson(context)
        val response = Gson().fromJson(data, ProductResponse::class.java)
        response.data.products.forEach {
            if (locationMaps.containsKey(it.merchant.city)) {
                val value : Int = locationMaps[it.merchant.city]!!
                locationMaps[it.merchant.city] = (value + 1)
            } else
                locationMaps[it.merchant.city] = 1
        }
        val sortedMap = locationMaps.toList().sortedByDescending { (_, value) -> value }.toMap()
        var i = 0
        for (map in sortedMap) {
            locations.add(map.key)
            i++
            if (i > 2) break
        }
        return locations
    }

    fun findRangePriceFromAllProducts(context: Context) : PriceRange {
        var min = Int.MAX_VALUE
        var max = Int.MIN_VALUE
        val data = JsonUtil.convertAssetToJson(context)
        val response = Gson().fromJson(data, ProductResponse::class.java)
        response.data.products.forEach {
            if (it.price < min)
                min = it.price
            if (it.price > max)
                max = it.price
        }
        return PriceRange(min, max)
    }

}