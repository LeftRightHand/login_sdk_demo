package com.example.app_login.core

import android.annotation.SuppressLint
import android.content.Context

object LoginManager {

    private var listener: LoginListener? = null
    internal var process: LoginProcess? = null

    fun login(
        context: Context,
    ) {
        login(
            context,
            listener
        )
    }

    private fun login(
        context: Context,
        listener: LoginListener?
    ) {
        process = LoginProcess(context, onFinish = {
            when (it) {
                is SealedLoginResult.Success -> {
                    listener?.onLoginSuccess(it.loginResult)
                }
                is SealedLoginResult.Failure -> {
                    listener?.onLoginFailure(it.loginError)
                }
            }
            process = null
        })
    }
    fun setLoginListener(listener: LoginListener) {
        this.listener = listener
    }
}