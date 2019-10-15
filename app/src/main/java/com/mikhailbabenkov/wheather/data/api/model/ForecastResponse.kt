package com.mikhailbabenkov.wheather.data.api.model

import com.google.gson.annotations.SerializedName
import com.mikhailbabenkov.wheather.data.datasource.model.WeatherForecastDO

data class ForecastResponse (
    @SerializedName("list")
    val data: List<Forecast>,
    @SerializedName("city")
    val city: ForecastCity
) {
    companion object{
        fun toDO(forecast: ForecastResponse) = WeatherForecastDO(
            cityName = forecast.city.name,
            latitude = forecast.city.coordinates.latitude,
            longitude = forecast.city.coordinates.longitude,
            country = forecast.city.country,
            forecasts = forecast.data.map { Forecast.toDO(it) }
        )
    }
}