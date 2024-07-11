package com.example.app_login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.app_login.repository.AuthRepository
import com.example.app_login.repository.UserRepository
import com.example.lib_common.base.BaseViewModel
import com.example.lib_common.data.model.AccountModel
import com.example.lib_common.data.model.ActiveModel
import com.example.lib_common.response.BaseResponse
import com.example.lib_common.util.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



class AuthViewModel() : BaseViewModel() {

    private val logger = Logger.LoggerProvider.provide()
    private val userRepository by lazy { UserRepository() }

    private val _createAccountResult = MutableLiveData<BaseResponse<AccountModel>>()
    val createAccountResult: LiveData<BaseResponse<AccountModel>> = _createAccountResult

    private val _activeResult = MutableLiveData<BaseResponse<ActiveModel>>()
    val activeResult: LiveData<BaseResponse<ActiveModel>> = _activeResult

    fun login(username: String, password: String) {

    }

    fun createRandomAccount() {
        viewModelScope.launch {
            val response = userRepository.createRandomAccount()
            response?.let {
                _createAccountResult.value = BaseResponse(data = it, msg = "Success", code = 1)
            } ?: run {
                _createAccountResult.value = BaseResponse(msg = "Creation failed", code = -1, data = null)
            }
        }
    }

    fun active(install: String, wPixels: String, hPixels: String) {

        launchUI(errorBlock = { code, error ->
            logger.d("AuthViewModel errorBlock")
        }) {
            val response = userRepository.active(install, wPixels, hPixels)
            logger.d("AuthViewModel active: $response")
        }
    }
    fun onFastLoginClicked() {
        sendRequest()
    }

    fun onGoogleLoginClicked() {
        // 处理Google登录逻辑
        sendHttpURLConnection()
    }

    fun onFacebookLoginClicked() {
        // 处理Facebook登录逻辑
    }

    fun sendHttpURLConnection() {
        viewModelScope.launch {
            makeNetworkRequest()
        }
    }

    fun sendRequest() {
        viewModelScope.launch {

        }
    }

    fun sendActive() {
        active("0", "0", "0")
    }
}

