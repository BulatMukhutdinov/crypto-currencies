package tat.mukhutdinov.scalablesolutions.asset.domain.boundary

import tat.mukhutdinov.scalablesolutions.asset.domain.model.AssetTimeSeries

interface AssetDomain {

    suspend fun fetchTimeSeries(assetId: String): List<AssetTimeSeries>
}