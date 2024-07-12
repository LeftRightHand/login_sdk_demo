package com.example.app_login.core

interface LoginListener {
    fun onLoginSuccess(loginResult: LoginResult)
    fun onLoginFailure(loginError: String)
}