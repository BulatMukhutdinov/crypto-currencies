package tat.mukhutdinov.scalablesolutions.assetsList.gateway

import tat.mukhutdinov.scalablesolutions.asset.domain.model.Asset
import tat.mukhutdinov.scalablesolutions.asset.gateway.boundary.AssetApi
import tat.mukhutdinov.scalablesolutions.asset.gateway.converter.AssetConverter
import tat.mukhutdinov.scalablesolutions.assetsList.domain.boundary.AssetsListGateway
import javax.inject.Inject

class AssetsListRemoteGateway @Inject constructor(
    private val api: AssetApi,
    private val converter: AssetConverter
) : AssetsListGateway {

    override suspend fun getAssetsList(): List<Asset> {
        val response = api.fetchAssetsList()

        if (response.data == null) {
            throw RuntimeException("Failed to fetch assets list. Error code = ${response.errorCode}")
        }

        return response.data.map(converter::convert)
    }
}