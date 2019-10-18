package com.mikhailbabenkov.wheather.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mikhailbabenkov.wheather.data.datasource.model.WeatherForecastDO
import com.mikhailbabenkov.wheather.domain.usecase.GetForecastUseCase
import com.mikhailbabenkov.wheather.domain.utils.CoroutinesDispatcherProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.mikhailbabenkov.wheather.domain.utils.Result
import java.lang.Exception
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getForecast: GetForecastUseCase,
    private val dispatcherProvider: CoroutinesDispatcherProvider
) : ViewModel() {

    val uiModel: LiveData<ForecastViewModel>
        get() = _uiModel
    private val _uiModel = MutableLiveData<ForecastViewModel>()

    private val parentJob = Job()
    private val scope = CoroutineScope(dispatcherProvider.main + parentJob)

    fun initLoadData() = loadData(true, false)

    fun pullToRefresh() = loadData(false, true)

    private fun loadData(fromCache: Boolean, isPullToRefresh: Boolean) {
        scope.launch(dispatcherProvider.computation) {
            withContext(dispatcherProvider.main) {
                emitUiState(!isPullToRefresh, isPullToRefresh, _uiModel.value?.data)
            }
            val result = getForecast.invoke(fromCache)
            withContext(dispatcherProvider.main) { handleResult(result) }
        }
    }

    private fun handleResult(result: Result<WeatherForecastDO>) {
        when (result) {
            is Result.Success -> emitUiState(data = result.data)
            is Result.Error -> emitUiState(error = result.exception)
        }
    }

    private fun emitUiState(
        isLoading: Boolean = false,
        isRefreshing: Boolean = false,
        data: WeatherForecastDO? = null,
        error: Exception? = null
    ) {
        _uiModel.value = ForecastViewModel(isLoading, isRefreshing, data, error)
    }

    override fun onCleared() {
        parentJob.cancel()
        super.onCleared()
    }

    data class ForecastViewModel(
        val isLoading: Boolean,
        val isRefreshing: Boolean,
        val data: WeatherForecastDO?,
        val error: Exception?
    )
}
