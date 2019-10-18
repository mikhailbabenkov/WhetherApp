package com.mikhailbabenkov.wheather.data.datasource.model

data class WeatherForecastDO(
    val cityName: String,
    val latitude: Double,
    val longitude: Double,
    val country: String,
    val forecasts: Map<String, List<ForecastDO>>
)