package tat.mukhutdinov.scalablesolutions.asset.ui

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import tat.mukhutdinov.scalablesolutions.asset.domain.boundary.AssetDomain
import tat.mukhutdinov.scalablesolutions.asset.domain.model.Asset
import tat.mukhutdinov.scalablesolutions.asset.domain.model.AssetTimeSeries
import tat.mukhutdinov.scalablesolutions.asset.ui.boundary.AssetBindings
import tat.mukhutdinov.scalablesolutions.infrastructure.structure.ui.BaseViewModel
import tat.mukhutdinov.scalablesolutions.infrastructure.structure.ui.boundary.ErrorBindings

class AssetViewModel @ViewModelInject constructor(
    private val domain: AssetDomain,
    @Assisted savedStateHandle: SavedStateHandle
) : BaseViewModel(savedStateHandle), AssetBindings {

    private val args: AssetFragmentArgs by navArgs()

    override val asset: Asset = args.asset

    val timeSeries: LiveData<Result<List<AssetTimeSeries>>>
        get() = _timeSeries

    private val _timeSeries = MutableLiveData<Result<List<AssetTimeSeries>>>()

    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _isLoading = MutableLiveData<Boolean>()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _timeSeries.postValue(Result.failure(throwable))
        _isLoading.postValue(false)
    }

    override val errorBindings = ErrorBindings {
        loadTimeSeries()
    }

    init {
        loadTimeSeries()
    }

    private fun loadTimeSeries() {
        _isLoading.value = true

        launch(exceptionHandler) {
            _timeSeries.postValue(Result.success(domain.fetchTimeSeries(asset.id)))

            _isLoading.postValue(false)
        }
    }
}