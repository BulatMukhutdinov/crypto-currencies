package tat.mukhutdinov.scalablesolutions.asset.gateway

import tat.mukhutdinov.scalablesolutions.asset.domain.boundary.AssetGateway
import tat.mukhutdinov.scalablesolutions.asset.domain.model.AssetTimeSeries
import tat.mukhutdinov.scalablesolutions.asset.gateway.boundary.AssetApi
import tat.mukhutdinov.scalablesolutions.asset.gateway.converter.AssetConverter
import javax.inject.Inject

class AssetRemoteGateway @Inject constructor(
    private val api: AssetApi,
    private val converter: AssetConverter
) : AssetGateway {

    override suspend fun fetchTimeSeries(assetId: String, startDate: String): List<AssetTimeSeries> {
        val response = api.fetchTimeSeries(assetId = assetId, start = startDate)

        if (response.data == null) {
            throw RuntimeException("Failed to fetch time series for asset id = $assetId. Error code = ${response.errorCode}")
        }

        val timeSeries = response.data

        return converter.convert(timeSeries)
    }
}