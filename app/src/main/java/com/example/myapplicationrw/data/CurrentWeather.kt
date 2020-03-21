package com.example.myapplicationrw.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CurrentWeather (
    val id: String,
    val city: String,
    val currentWeather: Double,
    val mainWeather: String,
    val descriptionWeather: String,
    val humidity: Int,
    val dt: Long,
    val thumbnail: String
) :Parcelable {

}