package tat.mukhutdinov.scalablesolutions.assetsList.domain.boundary

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import tat.mukhutdinov.scalablesolutions.asset.domain.model.Asset

interface AssetsListGateway {

    fun getAssets(): LiveData<PagingData<Asset>>
}