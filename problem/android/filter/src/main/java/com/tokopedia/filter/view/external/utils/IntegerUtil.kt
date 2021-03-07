package com.tokopedia.filter.view.external.utils

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

object IntegerUtil {
    fun convertIntToRupiahFormat(money: Int) : String {
        val format = DecimalFormat("#,###")
        return "Rp ${format.format(money).replace(',', '.')}"
    }
}