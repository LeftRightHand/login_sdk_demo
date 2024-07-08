package com.example.lib_common.helper

import android.app.Application

object SumAppHelper {
    private lateinit var app: Application
    private var isDebug = true

    fun init(application: Application, isDebug: Boolean) {
        this.app = application
        this.isDebug = isDebug
    }
    /**
     * 获取全局应用
     */
    fun getApplication() = app
    /**
     * 是否为debug环境
     */
    fun isDebug() = isDebug
}