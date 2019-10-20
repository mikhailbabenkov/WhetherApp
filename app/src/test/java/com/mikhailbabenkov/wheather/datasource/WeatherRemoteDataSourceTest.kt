package com.mikhailbabenkov.wheather.datasource

import com.mikhailbabenkov.wheather.data.api.OpenWeatherService
import com.mikhailbabenkov.wheather.data.datasource.WeatherRemoteDataSource
import com.mikhailbabenkov.wheather.mock.MockApiConfig
import com.mikhailbabenkov.wheather.utils.FakeResponse
import com.mikhailbabenkov.wheather.utils.MockServerHelper
import com.mikhailbabenkov.wheather.utils.ServiceHelper
import kotlinx.coroutines.runBlocking
import com.mikhailbabenkov.wheather.domain.utils.Result
import org.junit.jupiter.api.Test

class WeatherRemoteDataSourceTest {
    private val config = MockApiConfig()
    private val service = ServiceHelper.getRetrofitService(OpenWeatherService::class.java, config)
    private val dataSource = WeatherRemoteDataSource(service)

    @Test
    fun testGetForecastSuccess()  = runBlocking{
        MockServerHelper.mockAPIResponse(FakeResponse.Forecast)
        val result = dataSource.getForecast(111)
        assert(result is Result.Success)
        assert((result as Result.Success).data.cityName  == "Auckland")
        //etc
    }

    @Test
    fun testGetForecastFailed()  = runBlocking{
        MockServerHelper.mockAPIResponse(responseCode = 500)
        val result = dataSource.getForecast(111)
        assert(result is Result.Error)
        //etc
    }

    @Test
    fun testGetCitiesSuccess()  = runBlocking{
        MockServerHelper.mockAPIResponse(FakeResponse.Cities)
        val result = dataSource.getCities("Talin")
        assert(result is Result.Success)
        assert((result as Result.Success).data.isNotEmpty())
        //etc
    }

    @Test
    fun testGetCitiesFailed()  = runBlocking{
        MockServerHelper.mockAPIResponse(responseCode = 500)
        val result = dataSource.getCities("Talin")
        assert(result is Result.Error)
        //etc
    }
}