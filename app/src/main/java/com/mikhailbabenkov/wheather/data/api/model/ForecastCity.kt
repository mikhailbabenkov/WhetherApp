package com.mikhailbabenkov.wheather.data.api.model

import com.google.gson.annotations.SerializedName

data class ForecastCity(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("coord") val coordinates: Coordinates,
    @SerializedName("country") val country: String
)