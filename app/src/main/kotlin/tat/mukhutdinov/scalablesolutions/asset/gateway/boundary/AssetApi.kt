package tat.mukhutdinov.scalablesolutions.asset.gateway.boundary

import retrofit2.http.GET
import tat.mukhutdinov.scalablesolutions.asset.gateway.dto.AssetDto
import tat.mukhutdinov.scalablesolutions.infrastructure.structure.gateway.dto.BaseDto

interface AssetApi {

    @GET("api/v2/assets?id,name,symbol,metrics/market_data/price_usd,profile/general/overview/tagline,profile/general/overview/project_details,profile/general/overview/official_links")
    suspend fun fetchAssetsList(): BaseDto<List<AssetDto>>
}