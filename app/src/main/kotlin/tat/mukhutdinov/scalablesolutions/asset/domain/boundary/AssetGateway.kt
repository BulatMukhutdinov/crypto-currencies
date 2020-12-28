package tat.mukhutdinov.scalablesolutions.asset.domain.boundary

import tat.mukhutdinov.scalablesolutions.asset.domain.model.AssetTimeSeries

interface AssetGateway {

    suspend fun fetchTimeSeries(assetId: String, startDate: String): List<AssetTimeSeries>
}