package com.example.app_login.viewmodel

import com.example.lib_common.data.model.ResponseModel
import com.example.lib_common.util.Logger
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

 suspend fun makeNetworkRequest(): HttpURLConnection {
    return withContext(Dispatchers.IO) {
        val logger = Logger.LoggerProvider.provide()
        val params = mapOf(
            "country" to "CN",
            "currency" to "CNY",
            "game_pkg" to "1",
            "game_ver" to "1.0.0",
            "gameid" to "1000148",
            "hpixels" to "736.000000",
            "idfv" to "340EFD36-BC12-4128-A775-E7AD77FCBABB",
            "install" to "0",
            "language" to "zh-CN",
            "mac" to "02:00:00:00:00:00",
            "mode" to "iPhone9,2",
            "net_type" to "Unknown",
            "os_ver" to "IOS15.7.9",
            "partner_id" to "3",
            "partner_type" to "100003",
            "referer" to "appstore",
            "sdk_ver" to "4.2.0",
            "sign" to "6ce9fd8b071b75e249f65eaaba158c5d",
            "time" to "1720536196",
            "uuid" to "00000000-0000-0000-0000-000000000000",
            "wifi" to "",
            "wpixels" to "414.000000"
        )

        val query = params.map{(key,value) ->
            "${URLEncoder.encode(key,"UTF-8")}=${URLEncoder.encode(value,"UTF-8")}"
        }.joinToString("&")
        val url = URL("https://sdk.wonderent.net/active?$query")

        val connection = url.openConnection() as HttpURLConnection
        return@withContext try {
            connection.apply {
                requestMethod = "POST"
                setRequestProperty("Content-Type", "application/json; utf-8")
                setRequestProperty("Accept", "application/json")
                outputStream.use { os ->
                    os.write(ByteArray(0))
                }

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    inputStream.bufferedReader().use { reader ->
                        val responseStr = reader.readText()
                        val response = try {
                            Gson().fromJson(responseStr, ResponseModel::class.java)
                        } catch (e: JsonSyntaxException) {
                            null
                        }
                        logger.d("AuthViewModel response string : ${responseStr}")
                        logger.d("AuthViewModel请求成功: ${response}")
                    }
                } else {
                    errorStream.bufferedReader().use { reader ->
                        val responseStr = reader.readText()
                        try {
                            Gson().fromJson(responseStr, ResponseModel::class.java)
                        } catch (e: JsonSyntaxException) {
                            null
                        }
                    }
                }
            }
        } finally {
            connection.disconnect()
        }
    }
}