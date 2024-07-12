package com.example.app_login.core

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.example.app_login.ui.AuthActivity

internal class LoginProcess(
    private val context: Context,
    override val onFinish: ((SealedLoginResult) -> Unit)?,
) : ILoginProcess {


    override fun fail(loginError: String) {
        val response = SealedLoginResult.Failure("")
        onFinish?.invoke(response)
    }
    override fun start() {
        context.startActivity(Intent(context, AuthActivity::class.java))
    }

    override fun resume(result: LoginResult) {
        val response = SealedLoginResult.Success(
            result
        )
        onFinish?.invoke(response)
    }
}