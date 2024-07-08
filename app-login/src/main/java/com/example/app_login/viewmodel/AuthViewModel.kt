package com.example.app_login.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.lib_common.base.BaseViewModel
import com.example.lib_common.constant.BASE_URL
import com.example.lib_common.data.model.CommonModel
import com.example.lib_common.data.model.ResponseModel
import com.example.lib_common.network.NetworkUtil
import kotlinx.coroutines.launch
import com.example.lib_common.util.Logger
import com.example.lib_common.util.OriginalUtil
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
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
            gameid = "1000148",
            idfv = "1711504937131-9096988129687434595",
            language = "zh-CN",
            mac = "02:00:00:00:00:00",
            mode = "MHA-AL00",
            net_type = "wifi",
            os_ver = "9",
            sdk_ver = "4.2.0",
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
            val connection = makeNetworkRequest(requestModel)
            try {
                val responseCode = connection.responseCode
                val responseStr = if (responseCode == HttpURLConnection.HTTP_OK) {
                    connection.inputStream.bufferedReader().use { it.readText() }
                } else {
                    connection.errorStream.bufferedReader().use { it.readText() }
                }

                val response = try {
                    Gson().fromJson(responseStr, ResponseModel::class.java)
                } catch (e: JsonSyntaxException) {
                    null
                }

                if (response != null) {
                    if (response.success) {
                        // 处理成功响应
                        logger.d("AuthViewModel请求成功: ${response.message}")
                    } else {
                        // 处理失败响应
                        logger.d("AuthViewModel请求失败: ${response.message}")
                    }
                } else {
                    // 处理解析错误或其他异常
                    logger.e("AuthViewModel 请求解析错误或其他异常")
                }
            } finally {
                connection.disconnect()
            }

//            try {
//                val response = apiService.createRandomAccount(requestModel)
//                if (response.isSuccessful) {
//                    // 处理成功的响应
//                    response.body()?.let { responseBody ->
//                        val responseModel: ResponseModel = responseBody
//                        // 处理解析后的数据模型
//                        logger.d("解析后的数据模型: $responseModel")
//                    }
//                } else {
//                    // 处理错误响应
//                    val errorBody = response.errorBody()?.string()
//                    logger.e("请求失败: $errorBody")
//                }
//            } catch (e: Exception) {
//                // 处理网络错误或异常
//                logger.e("网络错误: ${e.message}", e)
//            }
        }



    }

    fun makeNetworkRequest(requestModel: CommonModel): HttpURLConnection {
        val url = URL(BASE_URL + "/fb/createAccount")
        val jsonInputString = JSONObject().apply {
            put("country", requestModel.country)
            put("currency", requestModel.currency)
            put("game_pkg", requestModel.game_pkg)
            put("game_ver", requestModel.game_ver)
            put("gameid", requestModel.gameid)
            put("idfv", requestModel.idfv)
            put("language", requestModel.language)
            put("mac", requestModel.mac)
            put("mode", requestModel.mode)
            put("net_type", requestModel.net_type)
            put("os_ver", requestModel.os_ver)
            put("sdk_ver", requestModel.sdk_ver)
            put("sign", requestModel.sign)
            put("time", requestModel.time)
            put("uuid", requestModel.uuid)
            put("wifi", requestModel.wifi)
            put("partner_id", requestModel.partner_id)
            put("partner_type", requestModel.partner_type)
        }.toString()

        val connection = url.openConnection() as HttpURLConnection
        connection.apply {
            requestMethod = "POST"
            setRequestProperty("Content-Type", "application/json; utf-8")
            setRequestProperty("Accept", "application/json")
            doOutput = true

            outputStream.use { os ->
                val input = jsonInputString.toByteArray(Charsets.UTF_8)
                os.write(input, 0, input.size)
            }
        }
        return connection
    }
}

