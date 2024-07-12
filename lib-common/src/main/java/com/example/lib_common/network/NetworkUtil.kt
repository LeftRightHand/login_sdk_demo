package com.example.lib_common.network

import com.example.lib_common.constant.BASE_URL
import com.example.lib_common.data.model.BaseRequestModel
import com.example.lib_common.helper.SumAppHelper
import com.example.lib_common.interceptor.HeaderInterceptor
import com.example.lib_common.interceptor.PublicParameterInterceptor
import com.example.lib_common.util.Logger
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkUtil {

    private val logger = Logger.LoggerProvider.provide()

    val requestModel = BaseRequestModel(
        country = "CN",
        currency = "CNY",
        gamePkg = "1",
        gameVer = "1.0.0",
        gameId = "1000148",
        idfv = "340EFD36-BC12-4128-A775-E7AD77FCBABB",
        language = "zh-CN",
        mac = "02:00:00:00:00:00",
        mode = "iPhone9,2",
        netType = "Unknown",
        osVer = "IOS15.7.9",
        partnerId = "3",
        partnerType = "100003",
        referer = "appstore",
        sdkVer = "4.2.0",
        sign = "6ce9fd8b071b75e249f65eaaba158c5d",
        time = "1720536196",
        uuid = "00000000-0000-0000-0000-000000000000",
        wifi = "",
    )

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(initOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * 初始化OkHttp
     */
    private fun initOkHttpClient(): OkHttpClient {
        val  build = OkHttpClient.Builder()
            .connectTimeout(12, TimeUnit.SECONDS)
            .writeTimeout(12, TimeUnit.SECONDS)
            .readTimeout(12, TimeUnit.SECONDS)
        // 添加参数拦截器
        build.addInterceptor(HeaderInterceptor())


        build.addInterceptor(PublicParameterInterceptor(requestModel))
        //日志拦截器
        val logInterceptor = HttpLoggingInterceptor { message: String ->
            logger.i("okttp, data:$message")
        }
        if (SumAppHelper.isDebug()) {
            logInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            logInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        }
        build.addInterceptor(logInterceptor)
        build.addInterceptor(Interceptor { chain ->
            val request = chain.request()
            chain.proceed(request)
        })
        return build.build()
    }

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}

