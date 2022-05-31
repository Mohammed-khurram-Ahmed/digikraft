package com.mohammed.khurram.digikraft.models
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PropertiesX(
    val bike_racks: String,
    val bikes: String,
    val free_racks: String,
    val label: String,
    val updated: String
) : Parcelable