package com.tokopedia.filter.view.external.utils

import org.junit.Assert.*
import org.junit.Test

class IntegerUtilTest {
    @Test
    fun convertIntToRupiahFormat() {
        val actual = IntegerUtil.convertIntToRupiahFormat(1490000)
        val expectation = "Rp 1.490.000"
        assertEquals(actual, expectation)
    }
}