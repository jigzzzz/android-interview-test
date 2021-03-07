package com.tokopedia.filter.view.data.model.dto

import com.google.gson.annotations.SerializedName

data class Merchant(
    @SerializedName("id")
    val id : Int,
    @SerializedName("name")
    val name : String,
    @SerializedName("city")
    val city : String
)