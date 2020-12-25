package tat.mukhutdinov.scalablesolutions.asset.gateway.dto

import com.google.gson.annotations.SerializedName

class AssetProfileDto(
    @SerializedName("general")
    val general: AssetProfileGeneralDto?
)

class AssetProfileGeneralDto(
    @SerializedName("overview")
    val overview: AssetProfileGeneralOverviewDto?
)

class AssetProfileGeneralOverviewDto(
    @SerializedName("tagline")
    val tagline: String?,
    @SerializedName("project_details")
    val projectDetails: String?,
    @SerializedName("official_links")
    val officialLinks: List<AssetProfileGeneralOverviewOfficialLinkDto>?
)

class AssetProfileGeneralOverviewOfficialLinkDto(
    @SerializedName("name")
    val name: String?,
    @SerializedName("link")
    val link: String?
)