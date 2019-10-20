package com.mikhailbabenkov.wheather.ui.epoxy

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.airbnb.epoxy.TypedEpoxyController
import com.mikhailbabenkov.wheather.data.datasource.model.CityDO
import com.mikhailbabenkov.wheather.domain.utils.WeatherUtils
import com.mikhailbabenkov.wheather.ui.selectcity.SelectCityViewModel
import com.mikhailbabenkov.wheather.viewHolderCityItem
import com.mikhailbabenkov.wheather.viewHolderError
import com.mikhailbabenkov.wheather.viewHolderLoading
import com.mikhailbabenkov.wheather.viewHolderNoResults

class CitiesController(
    liveModel: LiveData<SelectCityViewModel.CitiesUiModel>,
    lifecycleOwner: LifecycleOwner,
    private val callback: Callback
) : TypedEpoxyController<SelectCityViewModel.CitiesUiModel>() {

    private val modelObserver = Observer<SelectCityViewModel.CitiesUiModel> {
        setData(it)
    }

    init {
        liveModel.observe(lifecycleOwner, modelObserver)
    }

    override fun buildModels(data: SelectCityViewModel.CitiesUiModel) {
        when {
            data.isLoading -> bindLoading()
            data.data != null -> bindCities(data.data)
            data.error != null -> bindError(data.error)
        }
    }

    private fun bindError(error: Exception) {
        viewHolderError {
            id("_error")
            text(error.message)
        }
    }

    private fun bindCities(data: List<CityDO>) {
        if(data.isEmpty()) {
            bindNoResults()
        } else {
            data.forEach {city->
                bindCity(city)
            }
        }
    }

    private fun bindNoResults() {
        viewHolderNoResults {
            id("_noResults")
        }
    }

    private fun bindCity(city: CityDO) {
        viewHolderCityItem {
            id("_city${city.timestamp}")
            cityName(city.name)
            currentTemp(WeatherUtils.convertToCel(city.currentTemp))
            latitude(city.latitude.toString())
            longitude(city.longitude.toString())
            icon(WeatherUtils.composeImageLink(city.iconRef))
            clickListener { _->
                callback.onCitySelected(city.id)
            }
        }
    }

    private fun bindLoading() {
        viewHolderLoading {
            id("_loading")
        }
    }

    interface Callback{
        fun onCitySelected(cityId: Int)
    }
}