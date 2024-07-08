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
            "gameid" to "1000148",
            "tiem" to tiem,
        )
        val keyArray = listOf("gameid", "tiem")

        val sign = OriginalUtil.original(dict, keyArray)
        logger.i("当前sign：$sign")
        val requestModel = CommonModel(
            country = "CN",
            currency = "CNY",
            game_pkg = "1",
            game_ver = "1.0",
            gameId = "1000148",
            idfv = "1711504937131-9096988129687434595",
            language = "zh-CN",
            mac = "02:00:00:00:00:00",
            mode = "MHA-AL00",
            net_type = "wifi",
            os_ver = "9",
            sdk_ver = "2.0",
            sign = sign,
            time = tiem,
            uuid = "afid_1711504937131-9096988129687434595",
            wifi = "unknown",
            partner_type = "100002",
            partner_id = "2",
            referer = "0"
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