package com.mikhailbabenkov.wheather.data.datasource

import com.mikhailbabenkov.wheather.data.api.StorageService
import com.mikhailbabenkov.wheather.data.datasource.model.CityDO
import com.mikhailbabenkov.wheather.data.datasource.model.WeatherForecastDO

class WeatherLocalDataSource(private val service: StorageService) {
    suspend fun getCities(query: String): List<CityDO>? {
        return service.getCities(query)
    }

    suspend fun getForecast(cityId: String): WeatherForecastDO? {
       return service.getForecast(cityId)
    }

    suspend fun saveForecast(cityId: String, forecast: WeatherForecastDO) {
        return service.saveForecast(cityId, forecast)
    }

    suspend fun saveCities(cities: List<CityDO>) {
        service.saveCities(cities)
    }

    suspend fun getDefaultCityId(): Int {
        return service.getDefaultCityId()
    }

    suspend fun setDefaultCityId(city: Int) {
        service.setDefaultCityId(city)
    }
}