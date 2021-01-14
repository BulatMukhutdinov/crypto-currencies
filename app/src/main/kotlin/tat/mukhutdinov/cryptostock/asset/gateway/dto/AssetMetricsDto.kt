package tat.mukhutdinov.cryptostock.asset.gateway.dto

import com.google.gson.annotations.SerializedName

class AssetMetricsDto(
    @SerializedName("market_data")
    val marketData: MarketDataDto?
)