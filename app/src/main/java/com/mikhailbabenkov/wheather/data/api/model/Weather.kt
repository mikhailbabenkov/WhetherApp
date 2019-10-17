package com.mikhailbabenkov.wheather.data.api.model

import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("main") val group: String,
    @SerializedName("description") val desc: String,
    @SerializedName("icon") val iconRef: String
)