package tat.mukhutdinov.scalablesolutions.asset.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

@Parcelize
data class Asset(
    val id: String,
    val name: String,
    val symbol: String,
    val priceUsd: BigDecimal,
    val tagline: String,
    val projectDetails: String,
    val officialLinks: List<OfficialLink>
) : Parcelable {

    val priceUsdPrettified: String = "$${NumberFormat.getNumberInstance(Locale.US).format(priceUsd)}"
}

@Parcelize
data class OfficialLink(
    val name: String,
    val link: String
) : Parcelable