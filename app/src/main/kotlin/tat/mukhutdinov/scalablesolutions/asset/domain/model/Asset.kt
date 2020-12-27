package tat.mukhutdinov.scalablesolutions.asset.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.math.BigDecimal

@Parcelize
data class Asset(
    val id: String,
    val name: String,
    val symbol: String,
    val priceUsd: String,
    val tagline: String,
    val projectDetails: String,
    val officialLinks: List<OfficialLink>
) : Parcelable

@Parcelize
data class OfficialLink(
    val name: String,
    val link: String
) : Parcelable