package com.mikhailbabenkov.wheather.data.datasource

import com.mikhailbabenkov.wheather.data.api.OpenWeatherService
import com.mikhailbabenkov.wheather.data.api.model.City
import com.mikhailbabenkov.wheather.data.api.model.ForecastResponse
import com.mikhailbabenkov.wheather.data.datasource.model.CityDO
import com.mikhailbabenkov.wheather.data.datasource.model.WeatherForecastDO
import com.mikhailbabenkov.wheather.domain.utils.Result

class WeatherRemoteDataSource(private val service: OpenWeatherService) {
    suspend fun getCities(query: String): Result<List<CityDO>> {
        return try {
            Result.Success(service.getCities(query).cities.map { City.toDO(it) })
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    suspend fun getForecast(cityId: Int): Result<WeatherForecastDO> {
        return try {
            Result.Success(ForecastResponse.toDO(service.getForecast(cityId)))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}