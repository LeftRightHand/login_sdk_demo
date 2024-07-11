package com.example.lib_common.data.model

import retrofit2.http.Field

data class ActiveModel(
    val data: Any,
    val debug: Int,
    val review: String,
    val reviewUrl: String,
    val redirectUrl: String,
    val ios: String,
    val iosRedirectUrl: String,
    val iosReviewUrlUrl: String, // 这个字段名可能需要根据实际JSON调整，因为它看起来像是一个错误
    val isSandBoxPayReportAf: Int,
    val isBanShuPkg: Int,
    val obbUrl: String,
    val fb_fans_url: String,
    val webUrl: String,
    val commentsTime: Int,
    val isCacheCountryCurrency: Int,
    val buoy: String,
    val languageConf: List<Any>, // 使用Any来表示未知类型的列表
    val zfalertVC: Int,
    val isOuterPayment: Int,
    val areaCode: Int,
    val webStoreUrl: String,
    val kloginId: Int,
    val isPrivacyConfirmEnabled: Int,
    val isVisitorConfirmEnabled: Int,
)