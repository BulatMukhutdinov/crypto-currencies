package tat.mukhutdinov.cryptostock.asset.domain.boundary

import tat.mukhutdinov.cryptostock.asset.domain.model.AssetTimeSeries

interface AssetDomain {

    suspend fun fetchTimeSeries(assetId: String): List<AssetTimeSeries>
}