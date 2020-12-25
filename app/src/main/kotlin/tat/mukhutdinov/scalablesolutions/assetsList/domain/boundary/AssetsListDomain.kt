package tat.mukhutdinov.scalablesolutions.assetsList.domain.boundary

import tat.mukhutdinov.scalablesolutions.asset.domain.model.Asset

interface AssetsListDomain {

    suspend fun getAssetsList(): List<Asset>
}