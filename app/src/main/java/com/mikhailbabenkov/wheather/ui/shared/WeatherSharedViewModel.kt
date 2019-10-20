package com.mikhailbabenkov.wheather.ui.shared

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mikhailbabenkov.wheather.domain.utils.Event
import javax.inject.Inject

class WeatherSharedViewModel @Inject constructor() : ViewModel() {

    val citySelected: LiveData<Event<Unit>>
        get() = _citySelected
    private val _citySelected = MutableLiveData<Event<Unit>>()

    fun notifyCitySelected() {
        _citySelected.value = Event(Unit)
    }
}