package com.example.lib_common.data.model

data class ResponseModel<T>(
    val data: T,
    val code: Int,
    val msg: String,
)

data class Data(
    val id: Int,
    val name: String
)
