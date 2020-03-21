package com.example.myapplicationrw.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ForecastWeather (
    val expectedWeather: Double,
    val mainWeather: String,
    val descriptionWeather: String,
    val humidity: Int,
    val dt: Long,
    val thumbnail: String
): Parcelable {
}