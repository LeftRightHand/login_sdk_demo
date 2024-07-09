package com.example.lib_common.interceptor

import okhttp3.Interceptor
import okhttp3.Response


/**
 * @desc   头信息拦截器
 * 添加头信息
 */
class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newBuilder = request.newBuilder()
        newBuilder.addHeader("Content-type", "application/json; charset=utf-8")
        newBuilder.addHeader("Accept","application/json")
        return chain.proceed(newBuilder.build())
    }
}