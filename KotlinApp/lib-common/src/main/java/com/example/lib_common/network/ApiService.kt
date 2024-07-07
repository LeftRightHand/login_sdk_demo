package com.example.lib_common.network

import com.example.lib_common.data.model.CommonModel
import com.example.lib_common.data.model.LoginRequest
import com.example.lib_common.data.model.LoginResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    ///user/register
    ///fb/createAccount
    @POST("/user/login")
    suspend fun login(@Body requset: LoginRequest): Response<LoginResponse> // 根据你的 API 调整

    @POST("/fb/createAccount")
    suspend fun createRandomAccount(@Body requestModel: CommonModel): Response<ResponseBody>
}