package com.example.app_login.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.app_login.repository.UserRepository
import com.example.lib_common.base.BaseViewModel
import com.example.lib_common.data.model.AccountModel
import com.example.lib_common.data.model.ActiveModel
import com.example.lib_common.response.BaseResponse
import com.example.lib_common.util.Logger
import kotlinx.coroutines.launch



internal class AuthViewModel() : BaseViewModel() {

    private val logger = Logger.LoggerProvider.provide()
    private val userRepository by lazy { UserRepository() }

    val createAccountResult = MutableLiveData<AccountModel>()
//    val createAccountResult: LiveData<BaseResponse<AccountModel>> = _createAccountResult

    val activeResult = MutableLiveData<ActiveModel>()
//    private val activeResult: LiveData<ActiveModel?> = _activeResult

    fun login(username: String, password: String) {

    }

    fun createRandomAccount() {
        launchUI(errorBlock = { code, error ->
            logger.d("AuthViewModel errorBlock")
        }) {
            val response = userRepository.createRandomAccount()
            response.also { this.createAccountResult.value = it }
            logger.d("AuthViewModel active: $response")
        }
    }

    fun active(install: String, wPixels: String, hPixels: String) {
        launchUI(errorBlock = { code, error ->
            logger.d("AuthViewModel errorBlock")
        }) {
            val response = userRepository.active(install, wPixels, hPixels)
            response.also { this.activeResult.value = it }
            logger.d("AuthViewModel active: $response")
        }
    }
    fun onFastLoginClicked() {
        createRandomAccount()
    }

    fun onGoogleLoginClicked() {
        // 处理Google登录逻辑

    }

    fun onFacebookLoginClicked() {
        // 处理Facebook登录逻辑
    }

    fun sendHttpURLConnection() {
        viewModelScope.launch {
            makeNetworkRequest()
        }
    }

    fun sendActive() {
        active("0", "0", "0")
    }
}

