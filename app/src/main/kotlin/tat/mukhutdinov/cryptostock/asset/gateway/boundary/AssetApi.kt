package tat.mukhutdinov.cryptostock.asset.gateway.boundary

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import tat.mukhutdinov.cryptostock.asset.gateway.dto.AssetTimeSeriesDto
import tat.mukhutdinov.cryptostock.infrastructure.structure.gateway.dto.BaseDto

interface AssetApi {

    @GET("api/v1/assets/{assetId}/metrics/price/time-series?interval=1d&columns=timestamp,close")
    suspend fun fetchTimeSeries(
        @Path("assetId") assetId: String,
        @Query("start") start: String
    ): BaseDto<AssetTimeSeriesDto>
}