package com.mikhailbabenkov.wheather.data.datasource

import com.mikhailbabenkov.wheather.BuildConfig
import com.mikhailbabenkov.wheather.data.api.StorageService
import com.mikhailbabenkov.wheather.data.datasource.model.CityDO
import com.mikhailbabenkov.wheather.data.datasource.model.WeatherForecastDO

class WeatherLocalDataSource(private val service: StorageService) {

    suspend fun getCities(query: String): List<CityDO>? {
        return service.read(CITIES_KEY_PREFIX  + query)
    }

    suspend fun getForecast(cityId: Int): WeatherForecastDO? {
       return service.read(FORECAST_KEY_PREFIX + cityId)
    }

    suspend fun saveForecast(cityId: Int, forecast: WeatherForecastDO) {
        return service.write(FORECAST_KEY_PREFIX + cityId, forecast)
    }

    suspend fun saveCities(cities: List<CityDO>) {
        service.write(CITIES_KEY_PREFIX, cities)
    }

    suspend fun getDefaultCityId(): Int {
        return service.read(DEFAULT_CITY_KEY, BuildConfig.DEFAULT_CITY_ID)
    }

    suspend fun setDefaultCityId(cityId: Int) {
        service.write(DEFAULT_CITY_KEY, cityId)
    }

    companion object{
        private const val CITIES_KEY_PREFIX = "cities-key-prefix_"
        private const val FORECAST_KEY_PREFIX = "forecast-key-prefix_"
        private const val DEFAULT_CITY_KEY = "default-city"
    }
}