package com.example.lib_common.error

open class ApiException : Exception {
    var code: Int
    var msg: String


    constructor(code: Int, msg: String, e: Throwable? = null) : super(e) {
        this.code = code
        this.msg = msg
    }
}