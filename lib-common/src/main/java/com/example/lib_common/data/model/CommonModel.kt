package com.example.lib_common.data.model

import com.google.gson.annotations.SerializedName

data class CommonModel(
    @SerializedName("country") val country: String,
    @SerializedName("currency") val currency: String,
    @SerializedName("game_pkg") val game_pkg: String,
    @SerializedName("game_ver") val game_ver: String,
    @SerializedName("gameid") val gameId: String,
    @SerializedName("idfv") val idfv: String,
    @SerializedName("language") val language: String,
    @SerializedName("mac") val mac: String,
    @SerializedName("mode") val mode: String,
    @SerializedName("net_type") val net_type: String,
    @SerializedName("os_ver") val os_ver: String,
    @SerializedName("sdk_ver") val sdk_ver: String,
    @SerializedName("sign") val sign: String,
    @SerializedName("time") val time: String,
    @SerializedName("uuid") val uuid: String,
    @SerializedName("wifi") val wifi: String,
    @SerializedName("partner_id") val partner_id: String,
    @SerializedName("partner_type") val partner_type: String,
    @SerializedName("referer") val referer: String
)