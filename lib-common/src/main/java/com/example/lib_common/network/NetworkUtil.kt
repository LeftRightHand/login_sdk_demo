package com.example.lib_common.network

import com.example.lib_common.constant.BASE_URL
import com.example.lib_common.helper.SumAppHelper
import com.example.lib_common.util.Logger
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkUtil {

    private val logger = Logger.LoggerProvider.provide()

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
        val interceptors = mutableListOf<Interceptor>()
//        build.addInterceptor(CookiesInterceptor())
//        build.addInterceptor(HeaderInterceptor())
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

