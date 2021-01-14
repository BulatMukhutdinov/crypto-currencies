package tat.mukhutdinov.cryptostock.assetsList.domain

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import tat.mukhutdinov.cryptostock.asset.domain.model.Asset
import tat.mukhutdinov.cryptostock.assetsList.domain.boundary.AssetsListDomain
import tat.mukhutdinov.cryptostock.assetsList.domain.boundary.AssetsListGateway
import javax.inject.Inject

class AssetsListInteractor @Inject constructor(private val gateway: AssetsListGateway) :
    AssetsListDomain {

    override fun getAssets(): LiveData<PagingData<Asset>> =
        gateway.getAssets()
}