package com.mikhailbabenkov.wheather.data.api.model

import com.google.gson.annotations.SerializedName

data class CityResponse(
    @SerializedName("list") val cities: List<City>
)