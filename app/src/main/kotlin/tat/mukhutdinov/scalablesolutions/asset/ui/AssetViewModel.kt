package tat.mukhutdinov.scalablesolutions.asset.ui

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import kotlinx.coroutines.launch
import tat.mukhutdinov.scalablesolutions.asset.domain.boundary.AssetDomain
import tat.mukhutdinov.scalablesolutions.asset.domain.model.Asset
import tat.mukhutdinov.scalablesolutions.asset.domain.model.AssetTimeSeries
import tat.mukhutdinov.scalablesolutions.asset.ui.boundary.AssetBindings
import tat.mukhutdinov.scalablesolutions.infrastructure.structure.ui.BaseViewModel

class AssetViewModel @ViewModelInject constructor(
    private val domain: AssetDomain,
    @Assisted savedStateHandle: SavedStateHandle
) : BaseViewModel(savedStateHandle), AssetBindings {

    private val args: AssetFragmentArgs by navArgs()

    override val asset: Asset = args.asset

    val timeSeries: LiveData<List<AssetTimeSeries>>
        get() = _timeSeries

    private val _timeSeries = MutableLiveData<List<AssetTimeSeries>>()

    init {
        launch {
            _timeSeries.postValue(domain.fetchTimeSeries(asset.id))
        }
    }
}