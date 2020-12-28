package tat.mukhutdinov.scalablesolutions.asset.gateway.dto

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

class AssetTimeSeriesDto(
    @SerializedName("values")
    val values: List<List<BigDecimal>>?
)