package com.mikhailbabenkov.wheather.domain.usecase

import com.mikhailbabenkov.wheather.data.repository.WeatherRepository
import com.mikhailbabenkov.wheather.domain.utils.Result

class SetCurrentCityUseCase(private val repository: WeatherRepository) {
    suspend operator fun invoke(cityId: Int): Result<Unit> {
        return Result.Success(repository.setCurrentCity(cityId))
    }
}