package com.mikhailbabenkov.wheather.data.api.model

import com.google.gson.annotations.SerializedName

data class WeatherData(
    @SerializedName("temp") val temp: Float,
    @SerializedName("temp_min") val minTemp: Float,
    @SerializedName("temp_max") val maxTemp: Float,
    @SerializedName("pressure") val pressure: Int,
    @SerializedName("humidity") val humidity: Int
)