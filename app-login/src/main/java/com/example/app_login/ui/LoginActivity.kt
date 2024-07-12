package com.example.app_login.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.app_login.R
import com.example.app_login.databinding.ActivityLoginBinding
import com.example.app_login.viewmodel.LoginViewModel
import com.example.lib_common.base.BaseActivity


class LoginActivity : BaseActivity() {

    private val viewModel: LoginViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this


        // 监听Google登录按钮的点击事件
        binding.loginButton.setOnClickListener {
            // 这里可以添加额外的逻辑
            println("login activity click")
            startActivity(Intent(this, AuthActivity::class.java))
        }
    }


}
