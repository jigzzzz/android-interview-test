package com.tokopedia.filter.view.data.model.dto

import com.google.gson.annotations.SerializedName

data class Product (
    @SerializedName("id")
    val id : Int,
    @SerializedName("name")
    val name : String,
    @SerializedName("imageUrl")
    val imageUrl : String,
    @SerializedName("priceInt")
    val price : Int,
    @SerializedName("discountPercentage")
    val discountPercentage : Int,
    @SerializedName("slashedPriceInt")
    val slashedPrice : Int,
    @SerializedName("shop")
    val merchant : Merchant
)