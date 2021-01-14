package tat.mukhutdinov.cryptostock.assetsList.domain.boundary

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import tat.mukhutdinov.cryptostock.asset.domain.model.Asset

interface AssetsListGateway {

    fun getAssets(): LiveData<PagingData<Asset>>
}