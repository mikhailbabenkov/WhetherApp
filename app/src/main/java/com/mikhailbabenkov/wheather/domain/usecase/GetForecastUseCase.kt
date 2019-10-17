package com.mikhailbabenkov.wheather.domain.usecase

import com.mikhailbabenkov.wheather.data.datasource.model.WeatherForecastDO
import com.mikhailbabenkov.wheather.data.repository.WeatherRepository
import com.mikhailbabenkov.wheather.domain.utils.Result

class GetForecastUseCase(private val repository: WeatherRepository) {
    suspend operator fun invoke(fromCache: Boolean = true): Result<WeatherForecastDO> {
        return repository.getForecast(repository.getCurrentCity(), fromCache)
    }
}