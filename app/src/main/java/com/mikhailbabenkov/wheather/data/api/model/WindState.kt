package com.mikhailbabenkov.wheather.data.api.model

import com.google.gson.annotations.SerializedName

data class WindState(
    @SerializedName("speed") val speed: Float,
    @SerializedName("deg") val direction: Float
)