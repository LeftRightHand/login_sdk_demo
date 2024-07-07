package com.example.lib_common.data.model

data class ResponseModel(
    val status: Int,
    val data: Any,
    val msg: String
)

data class Data(
    val id: Int,
    val name: String
)
