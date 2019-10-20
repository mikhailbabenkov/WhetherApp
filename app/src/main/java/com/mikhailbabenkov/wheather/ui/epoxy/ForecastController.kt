package com.mikhailbabenkov.wheather.ui.epoxy

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.airbnb.epoxy.TypedEpoxyController
import com.mikhailbabenkov.wheather.*
import com.mikhailbabenkov.wheather.data.datasource.model.ForecastDO
import com.mikhailbabenkov.wheather.data.datasource.model.WeatherForecastDO
import com.mikhailbabenkov.wheather.domain.utils.DateUtils
import com.mikhailbabenkov.wheather.domain.utils.WeatherUtils
import com.mikhailbabenkov.wheather.ui.main.MainViewModel
import java.lang.Exception

class ForecastController(
    liveModel: LiveData<MainViewModel.ForecastUiModel>,
    lifecycleOwner: LifecycleOwner
) : TypedEpoxyController<MainViewModel.ForecastUiModel>() {

    private val modelObserver = Observer<MainViewModel.ForecastUiModel> {
        setData(it)
    }

    init {
        liveModel.observe(lifecycleOwner, modelObserver)
    }

    override fun buildModels(data: MainViewModel.ForecastUiModel) {
        when {
            data.isLoading -> bindLoading()
            data.data != null -> bindForecast(data.data)
            data.error != null -> bindError(data.error)
        }
    }

    private fun bindError(error: Exception) {
        viewHolderError {
            id("_error")
            text(error.message)
        }
    }

    private fun bindForecast(data: WeatherForecastDO) {
        bindCityHeader(data.cityName, data.country, data.latitude, data.longitude)
        data.forecasts.forEach {item->
            bindDailyForecast(item.key, item.value)
        }
    }

    private fun bindDailyForecast(date: String, forecasts: List<ForecastDO>) {
        bindForecastHeader(date)
        forecasts.forEach {
            bindForecastItem(it)
        }
    }

    private fun bindForecastItem(forecast: ForecastDO) {
        viewHolderForecastItem {
            id("_id${forecast.timestamp}")
            currentTemp(WeatherUtils.convertToCel(forecast.currentTemp))
            minTemp(WeatherUtils.convertToCel(forecast.minTempt))
            maxTemp(WeatherUtils.convertToCel(forecast.maxTemp))
            time(DateUtils.formatTimeOnly(forecast.date))
            windSpeed(forecast.windSpeed.toString())
            icon(WeatherUtils.composeImageLink(forecast.iconsRefs.first()))
            windDirection(WeatherUtils.parseWindDirection(forecast.windDirection))
        }
    }

    private fun bindForecastHeader(date: String) {
        viewHolderForecastHeader {
            id("_header$date")
            date(date)
        }
    }

    private fun bindCityHeader(
        cityName: String,
        country: String,
        latitude: Double,
        longitude: Double
    ) {
        viewHolderCityHeader {
            id("_cityHeader")
            cityName(cityName)
            country(country)
            latitude(latitude.toString())
            longitude(longitude.toString())
        }
    }

    private fun bindLoading() {
        viewHolderLoading {
            id("_loading")
        }
    }
}