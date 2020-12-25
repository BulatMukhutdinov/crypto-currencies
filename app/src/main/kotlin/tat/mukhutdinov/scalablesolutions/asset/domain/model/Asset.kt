package tat.mukhutdinov.scalablesolutions.asset.domain.model

import java.math.BigDecimal

class Asset(
    val id: String,
    val name: String,
    val symbol: String,
    val priceUsd: BigDecimal,
    val tagline: String,
    val projectDetails: String,
    val officialLinks: List<OfficialLink>
)

class OfficialLink(
    val name: String,
    val link: String
)