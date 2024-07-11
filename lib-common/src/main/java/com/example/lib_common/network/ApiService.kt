package com.example.lib_common.network

import com.example.lib_common.data.model.AccountModel
import com.example.lib_common.data.model.ActiveModel
import com.example.lib_common.data.model.UserModel
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import com.example.lib_common.response.BaseResponse

interface ApiService {

    @FormUrlEncoded
    @POST("/user/login")
    suspend fun login(
        @Field("username") username: String,
        @Field("passwork") passwork: String,
    ): BaseResponse<UserModel>

    @POST("/fb/createAccount")
    suspend fun createRandomAccount(): BaseResponse<AccountModel>

    @FormUrlEncoded
    @POST("active")
    suspend fun active(
        @Field("install") install: String,
        @Field("wpixels") wPixels: String,
        @Field("hpixels") hPixels: String
    ): BaseResponse<ActiveModel?>?

}


