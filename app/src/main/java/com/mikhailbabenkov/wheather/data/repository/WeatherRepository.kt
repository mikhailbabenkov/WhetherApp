package com.mikhailbabenkov.wheather.data.repository

import com.mikhailbabenkov.wheather.data.datasource.WeatherLocalDataSource
import com.mikhailbabenkov.wheather.data.datasource.WeatherRemoteDataSource
import com.mikhailbabenkov.wheather.data.datasource.model.CityDO
import com.mikhailbabenkov.wheather.data.datasource.model.WeatherForecastDO
import com.mikhailbabenkov.wheather.domain.utils.Result

class WeatherRepository(
    private val remoteDataSource: WeatherRemoteDataSource,
    private val localDataSource: WeatherLocalDataSource
) {

    suspend fun getCities(query: String, checkCache: Boolean): Result<List<CityDO>> {
        return if(checkCache) {
            localDataSource.getCities(query)?.let {
                Result.Success(it)
            } ?: getCities(query)
        } else {
            getCities(query)
        }
    }

    private suspend fun getCities(query: String): Result<List<CityDO>> {
        val result =  remoteDataSource.getCities(query)
        if(result is Result.Success) {
            localDataSource.saveCities(result.data)
        }
        return result
    }

    suspend fun getForecast(cityId: String, checkCache: Boolean): Any {
        return if(checkCache) {
            localDataSource.getForecast(cityId)?.let {
                Result.Success(it)
            } ?: getForecast(cityId)
        } else {
            getForecast(cityId)
        }
    }

    private suspend fun getForecast(cityId: String): Result<WeatherForecastDO> {
        val result =  remoteDataSource.getForecast(cityId)
        if(result is Result.Success) {
            localDataSource.saveForecast(cityId, result.data)
        }
        return result
    }

    suspend fun getCurrentCity(): Int {
        return localDataSource.getDefaultCityId()
    }

    suspend fun setCurrentCity(cityId: Int) {
        localDataSource.setDefaultCityId(cityId)
    }
}