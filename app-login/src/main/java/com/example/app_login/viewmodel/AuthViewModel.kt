package com.example.app_login.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.lib_common.base.BaseViewModel
import com.example.lib_common.data.model.ResponseModel
import com.example.lib_common.network.NetworkUtil
import com.example.lib_common.util.Logger
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AuthViewModel : BaseViewModel() {

    private val apiService = NetworkUtil.apiService

    private val gson = Gson()

    private val logger = Logger.LoggerProvider.provide()

    override fun onCleared() {
        super.onCleared()
        // 在这里清理资源或取消任何可能的异步操作
    }

    fun onFastLoginClicked() {
        sendRequest()
    }

    fun onGoogleLoginClicked() {
        // 处理Google登录逻辑
        sendHttpURLConnection()
    }

    fun onFacebookLoginClicked() {
        // 处理Facebook登录逻辑
    }

    fun sendHttpURLConnection() {
        viewModelScope.launch {
            makeNetworkRequest()
        }
    }

    fun sendRequest() {
        viewModelScope.launch {
            val call = apiService.createRandomAccount()
            call.enqueue(object : Callback<ResponseModel> {
                override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        logger.d("active 请求成功：$responseBody")
                        // 处理响应
                    } else {
                        // 处理错误
                        val responseBody = response.body()
                        logger.d("active 请求失败：$responseBody")
                    }
                }
                override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                    // 处理失败
                    logger.e("active 请求失败：${t.message}")
                }
            })
        }

    }
}

