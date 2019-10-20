package com.mikhailbabenkov.wheather.data.api.model

import com.google.gson.annotations.SerializedName
import com.mikhailbabenkov.wheather.data.datasource.model.CityDO

data class City(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("coord") val coordinates: Coordinates,
    @SerializedName("main") val weatherData: WeatherData,
    @SerializedName("dt") val timestamp: Long,
    @SerializedName("wind") val windState: WindState,
    @SerializedName("weather") val weather: List<Weather>
) {
    companion object{
        fun toDO(city: City) = CityDO(
            id = city.id,
            currentTemp = city.weatherData.temp,
            timestamp = city.timestamp,
            longitude = city.coordinates.longitude,
            latitude = city.coordinates.latitude,
            iconRef = city.weather.first().iconRef,
            name = city.name
        )
    }
}