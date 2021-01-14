package tat.mukhutdinov.cryptostock.asset.gateway.converter

import tat.mukhutdinov.cryptostock.asset.domain.model.Asset
import tat.mukhutdinov.cryptostock.asset.domain.model.AssetTimeSeries
import tat.mukhutdinov.cryptostock.asset.domain.model.OfficialLink
import tat.mukhutdinov.cryptostock.asset.gateway.dto.AssetDto
import tat.mukhutdinov.cryptostock.asset.gateway.dto.AssetProfileGeneralOverviewOfficialLinkDto
import tat.mukhutdinov.cryptostock.asset.gateway.dto.AssetTimeSeriesDto
import java.math.BigDecimal
import java.text.NumberFormat
import javax.inject.Inject

class AssetConverter @Inject constructor(private val numberFormat: NumberFormat) {

    private val minVisiblePrice: BigDecimal

    private val defaultMaximumFractionDigits = numberFormat.maximumFractionDigits

    init {
        val minVisiblePriceVal = "0.${"0".repeat(defaultMaximumFractionDigits - 1)}1"
        minVisiblePrice = BigDecimal(minVisiblePriceVal)
    }

    fun convert(dto: AssetTimeSeriesDto): List<AssetTimeSeries> {
        val values = dto.values ?: emptyList()

        validateTimeSeries(values)

        return values.map { AssetTimeSeries(it.first().toFloat(), it.last().toFloat()) }
    }

    private fun validateTimeSeries(values: List<List<BigDecimal>>) {
        values.forEach {
            if (it.size != 2) {
                throw RuntimeException("All time series have to have exactly 2 values: timestamp and close price")
            }
        }
    }

    fun convert(dto: AssetDto): Asset =
        Asset(
            id = dto.id ?: "",
            name = dto.name ?: "",
            symbol = dto.symbol ?: "",
            priceUsd = BigDecimal(dto.metrics?.marketData?.priceUsd ?: "0"),
            priceUsdCompact = convertPriceUsd(dto.metrics?.marketData?.priceUsd),
            tagline = dto.profile?.general?.overview?.tagline ?: "",
            projectDetails = dto.profile?.general?.overview?.projectDetails ?: "",
            officialLinks = dto.profile?.general?.overview?.officialLinks?.map(::convert) ?: emptyList()
        )

    private fun convertPriceUsd(priceUsdDto: String?): String {
        val price = BigDecimal(priceUsdDto ?: "0")

        if (price < minVisiblePrice) {
            numberFormat.minimumFractionDigits = price.scale() - price.precision() + 1
        } else {
            numberFormat.maximumFractionDigits = defaultMaximumFractionDigits
        }

        return numberFormat.format(price)
    }

    private fun convert(dto: AssetProfileGeneralOverviewOfficialLinkDto): OfficialLink =
        OfficialLink(
            name = dto.name ?: "",
            link = dto.link ?: ""
        )
}