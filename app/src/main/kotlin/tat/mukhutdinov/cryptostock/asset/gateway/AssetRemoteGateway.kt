package tat.mukhutdinov.cryptostock.asset.gateway

import tat.mukhutdinov.cryptostock.asset.domain.boundary.AssetGateway
import tat.mukhutdinov.cryptostock.asset.domain.model.AssetTimeSeries
import tat.mukhutdinov.cryptostock.asset.gateway.boundary.AssetApi
import tat.mukhutdinov.cryptostock.asset.gateway.converter.AssetConverter
import javax.inject.Inject

class AssetRemoteGateway @Inject constructor(
    private val api: AssetApi,
    private val converter: AssetConverter
) : AssetGateway {

    override suspend fun fetchTimeSeries(assetId: String, startDate: String): List<AssetTimeSeries> {
        val response = api.fetchTimeSeries(assetId = assetId, start = startDate)

        if (response.data == null) {
            throw RuntimeException("${response.status?.errorMessage} Error code = ${response.status?.errorCode}")
        }

        val timeSeries = response.data

        return converter.convert(timeSeries)
    }
}