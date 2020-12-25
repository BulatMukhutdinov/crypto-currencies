package tat.mukhutdinov.scalablesolutions.asset.gateway.dto

import com.google.gson.annotations.SerializedName

class MarketDataDto(
    @SerializedName("price_usd")
    val priceUsd: String?
)