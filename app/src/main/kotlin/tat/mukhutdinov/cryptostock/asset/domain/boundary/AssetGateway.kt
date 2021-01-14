package tat.mukhutdinov.cryptostock.asset.domain.boundary

import tat.mukhutdinov.cryptostock.asset.domain.model.AssetTimeSeries

interface AssetGateway {

    suspend fun fetchTimeSeries(assetId: String, startDate: String): List<AssetTimeSeries>
}