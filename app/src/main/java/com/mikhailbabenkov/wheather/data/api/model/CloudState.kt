package com.mikhailbabenkov.wheather.data.api.model

import com.google.gson.annotations.SerializedName

data class CloudState(
    @SerializedName("all") val cloudiness: Int
)