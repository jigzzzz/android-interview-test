package com.tokopedia.climbingstairs

import org.junit.Assert.*
import org.junit.Test

class SolutionTest {
    @Test
    fun climbStairs(){
        val actual = Solution.climbStairs(5)
        val expectation : Long = 8
        assertEquals(actual, expectation)
    }
}