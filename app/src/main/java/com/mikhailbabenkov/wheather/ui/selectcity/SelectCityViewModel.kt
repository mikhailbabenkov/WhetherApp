package com.mikhailbabenkov.wheather.ui.selectcity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mikhailbabenkov.wheather.data.datasource.model.CityDO
import com.mikhailbabenkov.wheather.domain.usecase.GetCitiesUseCase
import com.mikhailbabenkov.wheather.domain.usecase.SetCurrentCityUseCase
import com.mikhailbabenkov.wheather.domain.utils.CoroutinesDispatcherProvider
import com.mikhailbabenkov.wheather.domain.utils.Event
import com.mikhailbabenkov.wheather.domain.utils.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SelectCityViewModel @Inject constructor(
    private val getCities: GetCitiesUseCase,
    private val selectCity: SetCurrentCityUseCase,
    private val dispatcherProvider: CoroutinesDispatcherProvider
) : ViewModel() {

    val uiModel: LiveData<CitiesUiModel>
        get() = _uiModel
    private val _uiModel = MutableLiveData<CitiesUiModel>()

    val selectCityResult: LiveData<Event<Unit>>
        get() = _selectCityResult
    private val _selectCityResult = MutableLiveData<Event<Unit>>()

    private val parentJob = Job()
    private val scope = CoroutineScope(dispatcherProvider.main + parentJob)
    private var query: String = ""

    fun searchForCity(query: String) {
        this.query = query
        loadData(true, false)
    }

    fun pullToRefresh() = loadData(false, true)

    private fun loadData(fromCache: Boolean, isPullToRefresh: Boolean) {
        scope.launch(dispatcherProvider.computation) {
            withContext(dispatcherProvider.main) {
                emitUiState(!isPullToRefresh, isPullToRefresh, _uiModel.value?.data)
            }
            val result = getCities.invoke(query, fromCache)
            withContext(dispatcherProvider.main) { handleResult(result) }
        }
    }

    private fun handleResult(result: Result<List<CityDO>>) {
        when (result) {
            is Result.Success -> emitUiState(data = result.data)
            is Result.Error -> emitUiState(error = result.exception)
        }
    }

    private fun emitUiState(
        isLoading: Boolean = false,
        isRefreshing: Boolean = false,
        data: List<CityDO>? = null,
        error: Exception? = null
    ) {
        _uiModel.value = CitiesUiModel(isLoading, isRefreshing, data, error)
    }

    override fun onCleared() {
        parentJob.cancel()
        super.onCleared()
    }

    fun setCitySelected(cityId: Int) {
        scope.launch(dispatcherProvider.computation) {
            selectCity.invoke(cityId)
            withContext(dispatcherProvider.main) {
                _selectCityResult.value = Event(Unit)
            }
        }
    }

    data class CitiesUiModel(
        val isLoading: Boolean,
        val isRefreshing: Boolean,
        val data: List<CityDO>?,
        val error: Exception?
    )
}