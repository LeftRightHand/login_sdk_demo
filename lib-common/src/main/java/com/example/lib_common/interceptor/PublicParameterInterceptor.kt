package com.example.lib_common.interceptor

import com.example.lib_common.data.model.BaseRequestModel
import com.example.lib_common.util.OriginalUtil
import okhttp3.FormBody
import okhttp3.Interceptor
import okhttp3.Response


/**
 * @desc   公共参数拦截器
 */

//class RequestInterceptor(private val requestModel: BaseRequestModel) : Interceptor {
//    override fun intercept(chain: Interceptor.Chain): Response {
//        val originalRequest = chain.request()
//        val formBody = FormBody.Builder()
//            .add("country", requestModel.country)
//            .add("currency", requestModel.currency)
//            .add("game_pkg", requestModel.gamePkg)
//            .add("game_ver", requestModel.gameVer)
//            .add("gameid", requestModel.gameId)
//            .add("idfv", requestModel.idfv)
//            .add("language", requestModel.language)
//            .add("mac", requestModel.mac)
//            .add("mode", requestModel.mode)
//            .add("net_type", requestModel.netType)
//            .add("os_ver", requestModel.osVer)
//            .add("partner_id", requestModel.partnerId)
//            .add("partner_type", requestModel.partnerType)
//            .add("referer", requestModel.referer)
//            .add("sdk_ver", requestModel.sdkVer)
//            .add("sign", requestModel.sign)
//            .add("time", requestModel.time)
//            .add("uuid", requestModel.uuid)
//            .add("wifi", requestModel.wifi)
//            .build()
//
//        val newRequest = originalRequest.newBuilder()
//            .post(formBody)
//            .build()
//
//        return chain.proceed(newRequest)
//    }
//}

class PublicParameterInterceptor(private var requestModel: BaseRequestModel) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url

        val currentTimeSeconds = System.currentTimeMillis() / 1000
        val time = currentTimeSeconds.toString()
        val dict = mapOf(
            "gameid" to requestModel.gameId,
            "time" to time,
        )
        val keyArray = listOf("gameid", "time")
        val sign = OriginalUtil.original(dict, keyArray)

        val newUrl = originalUrl.newBuilder()
            .addQueryParameter("country", requestModel.country)
            .addQueryParameter("currency", requestModel.currency)
            .addQueryParameter("game_pkg", requestModel.gamePkg)
            .addQueryParameter("game_ver", requestModel.gameVer)
            .addQueryParameter("gameid", requestModel.gameId)
            .addQueryParameter("idfv", requestModel.idfv)
            .addQueryParameter("language", requestModel.language)
            .addQueryParameter("mac", requestModel.mac)
            .addQueryParameter("mode", requestModel.mode)
            .addQueryParameter("net_type", requestModel.netType)
            .addQueryParameter("os_ver", requestModel.osVer)
            .addQueryParameter("partner_id", requestModel.partnerId)
            .addQueryParameter("partner_type", requestModel.partnerType)
            .addQueryParameter("referer", requestModel.referer)
            .addQueryParameter("sdk_ver", requestModel.sdkVer)
            .addQueryParameter("sign", sign)
            .addQueryParameter("time", time)
            .addQueryParameter("uuid", requestModel.uuid)
            .addQueryParameter("wifi", requestModel.wifi)
            .build()

        val newRequest = originalRequest.newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }
}
