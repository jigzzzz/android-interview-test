package com.tokopedia.filter.view.data.model.dto

import com.google.gson.annotations.SerializedName

data class ProductCollection(
    @SerializedName("products")
    val products : List<Product>
)