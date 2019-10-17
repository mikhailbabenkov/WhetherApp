package com.mikhailbabenkov.wheather.domain.usecase

import com.mikhailbabenkov.wheather.data.datasource.model.CityDO
import com.mikhailbabenkov.wheather.data.repository.WeatherRepository
import com.mikhailbabenkov.wheather.domain.utils.Result

class GetCitiesUseCase(private val repository: WeatherRepository) {
    suspend operator fun invoke(query: String, fromCache: Boolean = true): Result<List<CityDO>> {
        return repository.getCities(query, fromCache)
    }
}