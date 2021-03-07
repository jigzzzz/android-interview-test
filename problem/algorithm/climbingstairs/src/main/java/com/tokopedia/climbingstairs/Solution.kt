package com.tokopedia.climbingstairs

object Solution {
    fun climbStairs(n: Int): Long {
        // TODO, return in how many distinct ways can you climb to the top. Each time you can either climb 1 or 2 steps.
        // 1 <= n < 90
        var first : Long = 0
        var second : Long  = 1
        var next : Long = 0

        for (i in 0 until n) {
            next = first + second
            first = second
            second = next
        }
        return next
    }
}
