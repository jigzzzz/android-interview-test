package com.tokopedia.minimumpathsum

import java.util.ArrayList

object Solution {
    fun minimumPathSum(matrix: Array<IntArray>, m: Int, n: Int): Int {
        // TODO, find a path from top left to bottom right which minimizes the sum of all numbers along its path, and return the sum
        // below is stub
        var tempMatrix = Array(matrix.size+1) {
            IntArray(matrix.size+1)
        }

        tempMatrix[0][0] = matrix[0][0]

        for (i in 1..m) {
            tempMatrix[i][0] = tempMatrix[i-1][0] + matrix[i][0]
        }

        for (j in 1..n) {
            tempMatrix[0][j] = tempMatrix[0][j-1] + matrix[0][j]
        }

        for (i in 1..m)
            for (j in 1..n) {
                tempMatrix[i][j] = Math.min(tempMatrix[i-1][j], tempMatrix[i][j-1]) + matrix[i][j]
            }

        return tempMatrix[m][n]
    }

}
