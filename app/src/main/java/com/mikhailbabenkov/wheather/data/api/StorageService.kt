package com.mikhailbabenkov.wheather.data.api

import com.mikhailbabenkov.wheather.data.datasource.model.CityDO
import com.mikhailbabenkov.wheather.data.datasource.model.WeatherForecastDO

interface StorageService {
    suspend fun getCities(query: String): List<CityDO>?
    suspend fun getForecast(cityId: String): WeatherForecastDO?
    suspend fun saveForecast(cityId: String, forecast: WeatherForecastDO)
    suspend fun getDefaultCityId(): Int
    suspend fun setDefaultCityId(cityId: Int)
    suspend fun saveCities(cities: List<CityDO>)
}