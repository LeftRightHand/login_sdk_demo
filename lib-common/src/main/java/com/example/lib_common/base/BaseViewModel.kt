package com.example.lib_common.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lib_common.error.ApiException
import com.example.lib_common.response.BaseResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout

open class BaseViewModel : ViewModel() {

    /**
     * 运行在主线程中，可直接调用
     * @param errorBlock 错误回调
     * @param responseBlock 请求函数
     */
    fun launchUI(errorBlock: (Int?, String?) -> Unit, responseBlock: suspend () -> Unit) {
        viewModelScope.launch(Dispatchers.Main) {
            safeApiCall(errorBlock = errorBlock, responseBlock)
        }
    }

    /**
     * 需要运行在协程作用域中
     * @param errorBlock 错误回调
     * @param responseBlock 请求函数
     */
    suspend fun <T> safeApiCall(
        errorBlock: suspend (Int?, String?) -> Unit,
        responseBlock: suspend () -> T?
    ): T? {
        try {
            return responseBlock()
        } catch (e: Exception) {
            e.printStackTrace()
            errorBlock(e.hashCode(), e.message)
        }
        return null
    }



}