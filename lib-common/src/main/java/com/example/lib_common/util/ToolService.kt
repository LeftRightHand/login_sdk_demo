package com.example.lib_common.util

import com.example.lib_common.constant.SECRECT_KEY
import java.security.MessageDigest

fun String.toMD5(): String {
    val bytes = this.toByteArray()
    val md = MessageDigest.getInstance("MD5")
    val digest = md.digest(bytes)
    return digest.joinToString("") { "%02x".format(it) }
}


object OriginalUtil {
    private val logger = Logger.LoggerProvider.provide()
    fun original(dict: Map<String, String>, keyArray: List<String>): String {
        var temp = ""
        for (i in keyArray.indices) {
            temp += dict[keyArray[i]]
            temp += if (i == keyArray.size - 1) {
                "@"
            } else {
                "yah$"
            }
        }
        val finalStr = temp + SECRECT_KEY
        logger.i("OriginalUtil , finalStr:$finalStr")
        return finalStr.toMD5()
    }
}
