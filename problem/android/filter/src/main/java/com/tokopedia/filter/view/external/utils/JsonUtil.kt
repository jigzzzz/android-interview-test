package com.tokopedia.filter.view.external.utils

import android.content.Context
import android.util.Log
import com.tokopedia.filter.R
import java.io.IOException

object JsonUtil {
    fun convertAssetToJson(context: Context) : String? {
        val json: String
        try {
            val inputStream = context.resources.openRawResource(R.raw.products)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.use { it.read(buffer) }
            json = String(buffer)
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        // print the data
        Log.i("data", json)
        return json
    }
}