package com.tokopedia.filter.view.data.model

import org.junit.Test

import org.junit.Assert.*

class MerchantTest {

    @Test
    fun hasCity() {
        val actual = Merchant(1, "Best Bakery", "Jakarta Barat").hasCity("Jakarta Barat")
        assertEquals(actual, true)
    }
}