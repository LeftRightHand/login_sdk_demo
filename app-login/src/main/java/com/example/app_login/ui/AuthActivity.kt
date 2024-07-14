package com.example.app_login.ui

import android.os.Bundle
import androidx.activity.viewModels
import com.example.app_login.core.LoginManager
import com.example.app_login.core.LoginResult
import com.example.app_login.databinding.ActivityAuthBinding
import com.example.app_login.viewmodel.AuthViewModel
import com.example.lib_common.base.BaseActivity
import com.example.lib_common.util.Logger

internal class AuthActivity() : BaseActivity() {
    private val logger = Logger.LoggerProvider.provide()
    private val viewModel: AuthViewModel by viewModels()
    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.sendActive()
        initData()
        initListener()
    }

    private fun initData() {
        viewModel.activeResult.observe(this) { active ->
            active?.let {

//                finish()
            }
        }
    }

    private fun initListener() {
        viewModel.createAccountResult.observe(this) { account ->
            val let = account?.let {
                logger.d("AuthActivity uesrname:${account.user}")
                logger.d("AuthActivity pass:${account.pass}")
                LoginManager.process?.resume(LoginResult(account.pass, account.user))
//                finish()
            }
        }
    }
}