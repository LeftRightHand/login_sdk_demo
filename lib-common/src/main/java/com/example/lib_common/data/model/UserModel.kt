package com.example.lib_common.data.model

import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("areaCode") val areaCode: Int,
    @SerializedName("banding_username") val bandingUsername: String?,
    @SerializedName("bind_email") val bindEmail: String,
    @SerializedName("bind_phone_pop") val bindPhonePop: Int,
    @SerializedName("isRealName") val isRealName: Int,
    @SerializedName("is_bind_email") val isBindEmail: Int,
    @SerializedName("is_bind_partner_uid") val isBindPartnerUid: Int,
    @SerializedName("is_bind_phone") val isBindPhone: Int,
    @SerializedName("is_pop") val isPop: Int,
    @SerializedName("newuser") val newUser: Int,
    @SerializedName("pass") val pass: String,
    @SerializedName("realNameVerify") val realNameVerify: Int,
    @SerializedName("review") val review: Int,
    @SerializedName("sdk_token") val sdkToken: String,
    @SerializedName("status") val status: String?,
    @SerializedName("timestamp") val timestamp: Long,
    @SerializedName("token") val token: String,
    @SerializedName("uid") val uid: Int
)