package com.example.app_login.core

import android.net.Uri

internal interface ILoginProcess {
    val onFinish: ((SealedLoginResult) -> Unit)?

    fun start()
    fun resume(result: LoginResult)
    fun fail(loginError: String)
}