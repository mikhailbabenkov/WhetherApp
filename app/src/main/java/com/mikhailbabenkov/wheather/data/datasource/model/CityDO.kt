package com.mikhailbabenkov.wheather.data.datasource.model

data class CityDO(
    val id: Int,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val timestamp: Long,
    val iconRef: String?,
    val currentTemp: Float
)