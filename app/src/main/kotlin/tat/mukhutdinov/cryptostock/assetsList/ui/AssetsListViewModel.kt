package tat.mukhutdinov.cryptostock.assetsList.ui

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavDirections
import androidx.paging.PagingData
import androidx.paging.cachedIn
import tat.mukhutdinov.android.utils.SingleLiveData
import tat.mukhutdinov.cryptostock.asset.domain.model.Asset
import tat.mukhutdinov.cryptostock.assetsList.domain.boundary.AssetsListDomain
import tat.mukhutdinov.cryptostock.assetsList.ui.boundary.AssetsListBindings
import tat.mukhutdinov.cryptostock.assetsList.ui.recycler.AssetsListAdapter
import tat.mukhutdinov.cryptostock.assetsList.ui.recycler.AssetsListLoadStateAdapter
import tat.mukhutdinov.cryptostock.infrastructure.structure.ui.BaseViewModel
import tat.mukhutdinov.cryptostock.infrastructure.structure.ui.boundary.ErrorBindings

class AssetsListViewModel @ViewModelInject constructor(
    domain: AssetsListDomain,
    @Assisted savedStateHandle: SavedStateHandle
) : BaseViewModel(savedStateHandle), AssetsListBindings {

    val navigation = SingleLiveData<NavDirections>()

    val assets: LiveData<PagingData<Asset>> = domain.getAssets().cachedIn(this)

    private var adapter: AssetsListAdapter? = null

    override val errorBindings = ErrorBindings {
        adapter?.retry()
    }

    fun createAssetsListAdapter(): AssetsListAdapter =
        AssetsListAdapter(this).also {
            adapter = it
        }

    fun createAssetsListLoadStateAdapter(): AssetsListLoadStateAdapter =
        AssetsListLoadStateAdapter(this)

    override fun onAssetClicked(asset: Asset) {
        navigation.value = AssetsListFragmentDirections.toAsset(asset)
    }
}