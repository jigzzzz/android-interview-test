package com.tokopedia.minimumpathsum

import org.junit.Test

import org.junit.Assert.*

class SolutionTest {

    @Test
    fun minimumPathSum() {
        var actual = Solution.minimumPathSum(arrayOf(
                intArrayOf(1, 2, 1, 9),
                intArrayOf(1, 5, 1, 1),
                intArrayOf(4, 2, 1, 1)), 2, 3)
        var expectation = 7
        assertEquals(actual, expectation)
    }
}