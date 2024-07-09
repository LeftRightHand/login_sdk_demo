package com.example.lib_common.data.model

import com.google.gson.annotations.SerializedName

data class BaseRequestModel(
    @SerializedName("country") val country: String,
    @SerializedName("currency") val currency: String,
    @SerializedName("game_pkg") val gamePkg: String,
    @SerializedName("game_ver") val gameVer: String,
    @SerializedName("gameid") val gameId: String,
    @SerializedName("idfv") val idfv: String,
    @SerializedName("language") val language: String,
    @SerializedName("mac") val mac: String,
    @SerializedName("mode") val mode: String,
    @SerializedName("net_type") val netType: String,
    @SerializedName("os_ver") val osVer: String,
    @SerializedName("partner_id") val partnerId: String,
    @SerializedName("partner_type") val partnerType: String,
    @SerializedName("referer") val referer: String,
    @SerializedName("sdk_ver") val sdkVer: String,
    @SerializedName("sign") val sign: String,
    @SerializedName("time") val time: String,
    @SerializedName("uuid") val uuid: String,
    @SerializedName("wifi") val wifi: String,

)