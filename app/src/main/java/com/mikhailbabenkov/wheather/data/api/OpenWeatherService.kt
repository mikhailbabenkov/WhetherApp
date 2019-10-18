package com.mikhailbabenkov.wheather.data.api

import com.mikhailbabenkov.wheather.data.api.model.CityResponse
import com.mikhailbabenkov.wheather.data.api.model.ForecastResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherService {
    @GET("find")
    suspend fun getCities(@Query("q") query: String, @Query("type") type: String = "like"): CityResponse

    @GET("forecast")
    suspend fun getForecast(@Query("id") cityId: Int): ForecastResponse
}