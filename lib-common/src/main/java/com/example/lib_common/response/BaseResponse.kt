package com.example.lib_common.response

data class BaseResponse<out T>(
    val data: T?,
    val msg: String = "",
    val code: Int = 0
) {
    fun isFailed(): Boolean {
        return code != 1
    }
}
