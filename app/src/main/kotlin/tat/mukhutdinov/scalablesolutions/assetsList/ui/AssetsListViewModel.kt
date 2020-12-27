package tat.mukhutdinov.scalablesolutions.assetsList.ui

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavDirections
import androidx.paging.PagingData
import androidx.paging.cachedIn
import tat.mukhutdinov.android.utils.SingleLiveData
import tat.mukhutdinov.scalablesolutions.asset.domain.model.Asset
import tat.mukhutdinov.scalablesolutions.assetsList.domain.boundary.AssetsListDomain
import tat.mukhutdinov.scalablesolutions.assetsList.ui.boundary.AssetsListBindings
import tat.mukhutdinov.scalablesolutions.assetsList.ui.recycler.AssetsListAdapter
import tat.mukhutdinov.scalablesolutions.assetsList.ui.recycler.AssetsListLoadStateAdapter
import tat.mukhutdinov.scalablesolutions.infrastructure.structure.ui.BaseViewModel

class AssetsListViewModel @ViewModelInject constructor(
    domain: AssetsListDomain,
    @Assisted savedStateHandle: SavedStateHandle
) : BaseViewModel(savedStateHandle), AssetsListBindings {

    val navigation = SingleLiveData<NavDirections>()

    val assets: LiveData<PagingData<Asset>> = domain.getAssets().cachedIn(this)

    private var adapter: AssetsListAdapter? = null

    fun createAssetsListAdapter(): AssetsListAdapter =
        AssetsListAdapter(this).also {
            adapter = it
        }

    fun createAssetsListLoadStateAdapter(): AssetsListLoadStateAdapter =
        AssetsListLoadStateAdapter(this)

    override fun onAssetClicked(asset: Asset) {
        navigation.value = AssetsListFragmentDirections.toAsset(asset)
    }

    override fun onRetryClicked() {
        adapter?.retry()
    }
}