package tat.mukhutdinov.scalablesolutions.assetsList.ui

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import kotlinx.coroutines.launch
import tat.mukhutdinov.android.utils.SingleLiveData
import tat.mukhutdinov.scalablesolutions.asset.domain.model.Asset
import tat.mukhutdinov.scalablesolutions.assetsList.domain.boundary.AssetsListDomain
import tat.mukhutdinov.scalablesolutions.assetsList.ui.boundary.AssetsListBindings
import tat.mukhutdinov.scalablesolutions.infrastructure.structure.ui.BaseViewModel

class AssetsListViewModel @ViewModelInject constructor(
    private val domain: AssetsListDomain,
    @Assisted savedStateHandle: SavedStateHandle
) : BaseViewModel(savedStateHandle), AssetsListBindings {

    val navigation = SingleLiveData<NavDirections>()


}