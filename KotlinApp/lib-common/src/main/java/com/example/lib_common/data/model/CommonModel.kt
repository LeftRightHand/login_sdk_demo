package com.example.lib_common.data.model

import com.google.gson.annotations.SerializedName

data class CommonModel(
    @SerializedName("game_pkg") val gamePkg: Int,
    @SerializedName("game_ver") val gameVer: String,
    @SerializedName("partner_type") val partnerType: String,
    @SerializedName("partner_id") val partnerId: String,
    @SerializedName("net_type") val netType: String,
    @SerializedName("os_ver") val osVer: String,
    @SerializedName("sdk_ver") val sdkVer: String,
    val country: String,
    val currency: String,
    val gameid: Int,
    val idfv: String,
    val language: String,
    val mac: String,
    val mode: String,
    val sign: String,
    val time: Long,
    val uid: Int,
    val uuid: String,
    val wifi: String,
    val referer: String
)