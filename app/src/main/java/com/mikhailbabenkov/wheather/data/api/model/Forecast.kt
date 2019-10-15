package com.mikhailbabenkov.wheather.data.api.model

import com.google.gson.annotations.SerializedName
import com.mikhailbabenkov.wheather.data.datasource.model.ForecastDO
import java.util.Date

data class Forecast(
    @SerializedName("dt") val timestamp: Long,
    @SerializedName("main") val weatherData: WeatherData,
    @SerializedName("weather") val weather: List<Weather>,
    @SerializedName("clouds") val cloudState: CloudState,
    @SerializedName("wind") val windState: WindState,
    @SerializedName("dt_txt") val date: Date
) {
    companion object{
        fun toDO(forecast: Forecast) = ForecastDO(
            timestamp = forecast.timestamp,
            cloudiness = forecast.cloudState.cloudiness,
            currentTemp = forecast.weatherData.temp,
            humidity = forecast.weatherData.humidity,
            maxTemp = forecast.weatherData.maxTemp,
            minTempt = forecast.weatherData.minTemp,
            pressure = forecast.weatherData.pressure,
            windDirection = forecast.windState.direction,
            windSpeed = forecast.windState.speed,
            date = forecast.date,
            iconsRefs = forecast.weather.map { it.iconRef }
        )
    }
}