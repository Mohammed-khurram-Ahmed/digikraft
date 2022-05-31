package com.mohammed.khurram.digikraft.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.mohammed.khurram.digikraft.R

object Utility {
    fun createSmallMarker(context: Context?): Bitmap {
        val height = 100
        val width = 100
        val bitmap = BitmapFactory.decodeResource(context?.resources, R.drawable.ic_bike_marker)
        return Bitmap.createScaledBitmap(bitmap, width, height, false)
    }
}