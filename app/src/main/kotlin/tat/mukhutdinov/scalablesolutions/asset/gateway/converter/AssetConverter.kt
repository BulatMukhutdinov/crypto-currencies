package tat.mukhutdinov.scalablesolutions.asset.gateway.converter

import tat.mukhutdinov.scalablesolutions.asset.domain.model.Asset
import tat.mukhutdinov.scalablesolutions.asset.domain.model.OfficialLink
import tat.mukhutdinov.scalablesolutions.asset.gateway.dto.AssetDto
import tat.mukhutdinov.scalablesolutions.asset.gateway.dto.AssetProfileGeneralOverviewOfficialLinkDto
import java.math.BigDecimal

class AssetConverter {

    fun convert(dto: AssetDto): Asset =
        Asset(
            id = dto.id ?: "",
            name = dto.name ?: "",
            symbol = dto.symbol ?: "",
            priceUsd = BigDecimal(dto.metrics?.marketData?.priceUsd ?: "0"),
            tagline = dto.profile?.general?.overview?.tagline ?: "",
            projectDetails = dto.profile?.general?.overview?.projectDetails ?: "",
            officialLinks = dto.profile?.general?.overview?.officialLinks?.map(::convert) ?: emptyList()
        )

    private fun convert(dto: AssetProfileGeneralOverviewOfficialLinkDto): OfficialLink =
        OfficialLink(
            name = dto.name ?: "",
            link = dto.link ?: ""
        )
}