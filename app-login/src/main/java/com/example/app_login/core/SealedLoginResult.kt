package com.example.app_login.core


sealed class SealedLoginResult {
    data class Success(val loginResult: LoginResult) : SealedLoginResult()
    data class Failure(val loginError: String) : SealedLoginResult()
}