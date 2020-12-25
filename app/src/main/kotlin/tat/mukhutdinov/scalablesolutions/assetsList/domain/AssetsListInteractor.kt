package tat.mukhutdinov.scalablesolutions.assetsList.domain

import tat.mukhutdinov.scalablesolutions.asset.domain.model.Asset
import tat.mukhutdinov.scalablesolutions.assetsList.domain.boundary.AssetsListDomain
import tat.mukhutdinov.scalablesolutions.assetsList.domain.boundary.AssetsListGateway

class AssetsListInteractor(private val gateway: AssetsListGateway) : AssetsListDomain {

    override suspend fun getAssetsList(): List<Asset> =
        gateway.getAssetsList()
}