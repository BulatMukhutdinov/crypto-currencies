package tat.mukhutdinov.scalablesolutions.asset.gateway.converter

import tat.mukhutdinov.scalablesolutions.asset.domain.model.Asset
import tat.mukhutdinov.scalablesolutions.asset.domain.model.OfficialLink
import tat.mukhutdinov.scalablesolutions.asset.gateway.dto.AssetDto
import tat.mukhutdinov.scalablesolutions.asset.gateway.dto.AssetProfileGeneralOverviewOfficialLinkDto
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale
import javax.inject.Inject

class AssetConverter @Inject constructor() {

    fun convert(dto: AssetDto): Asset =
        Asset(
            id = dto.id ?: "",
            name = dto.name ?: "",
            symbol = dto.symbol ?: "",
            priceUsd = convertPriceUsd(dto.metrics?.marketData?.priceUsd),
            tagline = dto.profile?.general?.overview?.tagline ?: "",
            projectDetails = dto.profile?.general?.overview?.projectDetails ?: "",
            officialLinks = dto.profile?.general?.overview?.officialLinks?.map(::convert) ?: emptyList()
        )

    private fun convertPriceUsd(priceUsdDto: String?): String {
        val price = BigDecimal(priceUsdDto ?: "0")

        return "$${NumberFormat.getNumberInstance(Locale.getDefault()).format(price)}"
    }

    private fun convert(dto: AssetProfileGeneralOverviewOfficialLinkDto): OfficialLink =
        OfficialLink(
            name = dto.name ?: "",
            link = dto.link ?: ""
        )
}