package com.example.lib_common.network

import com.example.lib_common.data.model.ResponseModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("/user/login")
    suspend fun login(
        @Field("country") country: String,
        @Field("currency") currency: String,
        @Field("game_pkg") gamePkg: String,
        @Field("game_ver") gameVer: String,
        @Field("gameid") gameId: String,
        @Field("idfv") idfv: String,
        @Field("language") language: String,
        @Field("mac") mac: String,
        @Field("mode") mode: String,
        @Field("net_type") netType: String,
        @Field("os_ver") osVer: String,
        @Field("partner_id") partnerId: String,
        @Field("partner_type") partnerType: String,
        @Field("referer") referer: String,
        @Field("sdk_ver") sdkVer: String,
        @Field("sign") sign: String,
        @Field("time") time: String,
        @Field("uuid") uuid: String,
        @Field("wifi") wifi: String,
        @Field("username") username: String,
        @Field("passwork") passwork: String,
    ):  Call<ResponseModel>

    @POST("/fb/createAccount")
    fun createRandomAccount(): Call<ResponseModel>

    @FormUrlEncoded
    @POST("active")
    fun active(
        @Field("country") country: String,
        @Field("currency") currency: String,
        @Field("game_pkg") gamePkg: String,
        @Field("game_ver") gameVer: String,
        @Field("gameid") gameId: String,
        @Field("idfv") idfv: String,
        @Field("install") install: String,
        @Field("language") language: String,
        @Field("mac") mac: String,
        @Field("mode") mode: String,
        @Field("net_type") netType: String,
        @Field("os_ver") osVer: String,
        @Field("partner_id") partnerId: String,
        @Field("partner_type") partnerType: String,
        @Field("referer") referer: String,
        @Field("sdk_ver") sdkVer: String,
        @Field("sign") sign: String,
        @Field("time") time: String,
        @Field("uuid") uuid: String,
        @Field("wifi") wifi: String,
        @Field("wpixels") wPixels: String,
        @Field("hpixels") hPixels: String
    ): Call<ResponseModel>
}


