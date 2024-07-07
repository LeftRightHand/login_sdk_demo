package com.example.app_login.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.lib_common.base.BaseViewModel
import com.example.lib_common.data.model.CommonModel
import com.example.lib_common.data.model.ResponseModel
import com.example.lib_common.network.NetworkUtil
import kotlinx.coroutines.launch
import com.example.lib_common.util.Logger
import com.example.lib_common.util.OriginalUtil
import com.google.gson.Gson
import java.time.Instant

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
    }

    fun onFacebookLoginClicked() {
        // 处理Facebook登录逻辑
    }

    fun sendRequest() {

        val tiem = Instant.now().epochSecond.toString()
        logger.i("当前时间戳：$tiem")
        val dict = mapOf(
            "gameid" to tiem,
            "item" to "1000148",
        )
        val keyArray = listOf("gameid", "item")

        val sign = OriginalUtil.original(dict, keyArray)
        logger.i("当前sign：$sign")
        val requestModel = CommonModel(
            country = "CN",
            currency = "CNY",
            gamePkg = 1,
            gameVer = "1.0",
            gameid = 1000148,
            idfv = "EA078AA9-1BFF-483C-88B3-4CE1888FFEBA",
            language = "zh-CN",
            mac = "02:00:00:00:00:00",
            mode = "iPhone9,1",
            netType = "wifi",
            osVer = "iOS15.5",
            sdkVer = "2.0",
            sign = sign,
            time = 1718900443,
            uid = 2146570,
            uuid = "00000000-0000-0000-0000-000000000000",
            wifi = "",
            partnerType = "100003",
            partnerId = "3"
        )
        logger.i("当前 request model：$requestModel")
        viewModelScope.launch {
            try {
                val response = apiService.createRandomAccount(requestModel)
                if (response.isSuccessful) {
                    // 处理成功的响应
                    response.body()?.let { responseBody ->
                        val responseModel: ResponseModel = responseBody
                        // 处理解析后的数据模型
                        logger.d("解析后的数据模型: $responseModel")
                    }
                } else {
                    // 处理错误响应
                    val errorBody = response.errorBody()?.string()
                    logger.e("请求失败: $errorBody")
                }
            } catch (e: Exception) {
                // 处理网络错误或异常
                logger.e("网络错误: ${e.message}", e)
            }
        }
    }
}