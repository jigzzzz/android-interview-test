package com.tokopedia.filter.view.data.model.dto

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("data")
    val data : ProductCollection
)