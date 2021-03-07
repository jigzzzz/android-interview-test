package com.tokopedia.filter.view.data.model

data class Merchant(
    val id : Int,
    val name : String,
    val city : String
) {

    fun hasCity(cityFilter: String) : Boolean = city.equals(cityFilter)

}