package tat.mukhutdinov.cryptostock.asset.domain.model

import android.os.Parcelable
import android.text.Html
import android.text.Spanned
import kotlinx.android.parcel.Parcelize
import java.math.BigDecimal

@Parcelize
data class Asset(
    val id: String,
    val name: String,
    val symbol: String,
    val priceUsd: BigDecimal,
    val priceUsdCompact: String,
    val tagline: String,
    val projectDetails: String,
    val officialLinks: List<OfficialLink>
) : Parcelable {

    val projectDetailsWithHtml: Spanned
        get() = Html.fromHtml(projectDetails, Html.FROM_HTML_MODE_COMPACT)
}

@Parcelize
data class OfficialLink(
    val name: String,
    val link: String
) : Parcelable