package com.tokopedia.filter.view.data.model

data class Product (
    val id : Int,
    val name : String,
    val imageUrl : String,
    val price : Int,
    val discountPercentage : Int,
    val slashedPrice : Int,
    val hasDiscount : Boolean,
    val merchant : Merchant
) {

    companion object {
        fun filterByPrice(products : List<Product>, minPrice: Int, maxPrice: Int) : List<Product> {
            return products.filter { it.price in minPrice..maxPrice }
        }

        fun filterByCity(products : List<Product>, city: String) : List<Product> {
            return products.filter { it.merchant.hasCity(city) }
        }
    }

}