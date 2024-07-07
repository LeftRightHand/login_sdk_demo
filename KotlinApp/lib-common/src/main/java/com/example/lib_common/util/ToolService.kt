package com.example.lib_common.util

import java.security.MessageDigest

object ToolService {

    fun MD5(md5Str: String): String {
        val md = MessageDigest.getInstance("MD5")
        val digest = md.digest(md5Str.toByteArray())
        return digest.joinToString("") { "%02x".format(it) }.toUpperCase()
    }

    fun GetAppConfig(): AppConfig {
        return AppConfig("YourSecretKey")
    }
}

object OriginalUtil {
    fun original(dict: Map<String, String>, keyArray: List<String>): String {
        var temp = ""
        for (i in keyArray.indices) {
            temp += dict[keyArray[i]]
            if (i != keyArray.size - 1) {
                temp += "yah$"
            }
            if (i == keyArray.size - 1) {
                temp += "@"
            }
        }
        val finalStr = temp + ToolService.GetAppConfig().secrectKey
        return ToolService.MD5(finalStr).toLowerCase()
    }
}

data class AppConfig(val secrectKey: String)