package com.example.app_login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.lib_common.base.BaseViewModel
import kotlinx.coroutines.launch

class LoginViewModel : BaseViewModel() {

    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> = _loginResult

    fun onLoginClicked() {
        println("login view model click")
        if (!username.value.isNullOrEmpty() && !password.value.isNullOrEmpty()) {
            // TODO: Add actual login logic here
            _loginResult.value = true
        } else {
            _loginResult.value = false
        }
    }

    fun onForgotPasswordClicked() {

    }

    fun login(userName: String, password: String) {
        viewModelScope.launch {

        }
    }

    fun register(
        userName: String,
        password: String,
        rePassword: String,
    ) {
        viewModelScope.launch {

        }

    }
}