package com.mikhailbabenkov.wheather.data.datasource.model

import java.util.Date

data class ForecastDO(
    val timestamp: Long,
    val currentTemp: Float,
    val maxTemp: Float,
    val minTempt: Float,
    val pressure: Float,
    val humidity: Int,
    val iconsRefs: List<String>,
    val cloudiness: Int,
    val windSpeed: Float,
    val windDirection: Float,
    val date: Date
)