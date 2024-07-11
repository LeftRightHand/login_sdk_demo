package com.example.app_login.repository

import com.example.lib_common.data.model.AccountModel
import com.example.lib_common.data.model.ActiveModel

import com.example.lib_common.network.NetworkUtil
import com.example.lib_common.reqository.BaseRepository

class UserRepository() : BaseRepository() {

    suspend fun createRandomAccount(): AccountModel? {
        return requestResponse { NetworkUtil.apiService.createRandomAccount() }
    }

    suspend fun active(install: String, wPixels: String, hPixels: String): ActiveModel? {
        return requestResponse { NetworkUtil.apiService.active(install, wPixels, hPixels) }
    }
}