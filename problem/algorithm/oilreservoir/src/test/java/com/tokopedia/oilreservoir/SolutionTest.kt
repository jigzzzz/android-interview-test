package com.tokopedia.oilreservoir

import org.junit.Test

import org.junit.Assert.*

class SolutionTest {

    @Test
    fun collectOil() {
        var actual = Solution.collectOil(intArrayOf(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1))
        var expectation = 6
        assertEquals(actual, expectation)
    }
}