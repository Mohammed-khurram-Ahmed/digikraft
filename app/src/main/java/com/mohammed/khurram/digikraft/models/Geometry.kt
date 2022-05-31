package com.mohammed.khurram.digikraft.models
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Geometry(
    val coordinates: List<Double>,
    val type: String
) : Parcelable