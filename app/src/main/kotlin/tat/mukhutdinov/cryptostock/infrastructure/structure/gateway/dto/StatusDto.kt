package tat.mukhutdinov.cryptostock.infrastructure.structure.gateway.dto

import com.google.gson.annotations.SerializedName

class StatusDto(
    @SerializedName("error_code")
    val errorCode: Int?,
    @SerializedName("error_message")
    val errorMessage: String?
) {

    val isSuccessful = errorCode == null
}