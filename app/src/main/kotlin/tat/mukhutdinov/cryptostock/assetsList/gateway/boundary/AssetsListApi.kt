package tat.mukhutdinov.cryptostock.assetsList.gateway.boundary

import retrofit2.http.GET
import retrofit2.http.Query
import tat.mukhutdinov.cryptostock.asset.gateway.dto.AssetDto
import tat.mukhutdinov.cryptostock.infrastructure.structure.gateway.dto.BaseDto

interface AssetsListApi {

    @GET("api/v2/assets?fields=id,name,symbol,metrics/market_data/price_usd,profile/general/overview/tagline,profile/general/overview/project_details,profile/general/overview/official_links")
    suspend fun fetchAssetsList(@Query("limit") limit: Int, @Query("page") page: Int): BaseDto<List<AssetDto>>
}