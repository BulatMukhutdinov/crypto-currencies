package tat.mukhutdinov.cryptostock.asset.gateway.dto

import com.google.gson.annotations.SerializedName

class AssetDto(
    @SerializedName("id")
    val id: String?,
    @SerializedName("symbol")
    val symbol: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("metrics")
    val metrics: AssetMetricsDto?,
    @SerializedName("profile")
    val profile: AssetProfileDto?
)