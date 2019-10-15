package com.mikhailbabenkov.wheather.data.api

import android.content.Context
import com.mikhailbabenkov.wheather.BuildConfig
import com.mikhailbabenkov.wheather.data.datasource.model.CityDO
import com.mikhailbabenkov.wheather.data.datasource.model.ForecastDO
import com.mikhailbabenkov.wheather.data.datasource.model.WeatherForecastDO
import io.paperdb.Paper

class StorageServiceImpl(context: Context): StorageService {

    init {
        Paper.init(context)
    }

    override suspend fun getCities(query: String): List<CityDO>? {
        return Paper.book().read<List<CityDO>>(CITIES_KEY_PREFIX  + query)
    }

    override suspend fun saveCities(cities: List<CityDO>) {
        Paper.book().write(CITIES_KEY_PREFIX, cities)
    }

    override suspend fun getForecast(cityId: String): WeatherForecastDO? {
        return Paper.book().read<WeatherForecastDO>(FORECAST_KEY_PREFIX + cityId)
    }

    override suspend fun saveForecast(cityId: String, forecast: WeatherForecastDO) {
        Paper.book().write(FORECAST_KEY_PREFIX + cityId, forecast)
    }

    override suspend fun getDefaultCityId(): Int {
        return Paper.book().read(DEFAULT_CITY_KEY, BuildConfig.DEFAULT_CITY_ID)
    }

    override suspend fun setDefaultCityId(cityId: Int) {
        Paper.book().write(DEFAULT_CITY_KEY, cityId)
    }

    companion object{
        private const val CITIES_KEY_PREFIX = "cities-key-prefix_"
        private const val FORECAST_KEY_PREFIX = "forecast-key-prefix_"
        private const val DEFAULT_CITY_KEY = "forecast-key-prefix_"
    }
}