package com.tokopedia.oilreservoir

/**
 * Created by fwidjaja on 2019-09-24.
 */
object Solution {
    fun collectOil(height: IntArray): Int {
        // TODO, return the amount of oil blocks that could be collected
        // below is stub
        var prev = 0
        var res = 0
        for (h in height) {
            if (h <= prev)
                res += Math.abs(h - prev)
            prev = h
        }
        return res
    }
}
