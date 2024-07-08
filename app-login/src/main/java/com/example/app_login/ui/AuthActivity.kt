package com.example.app_login.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.app_login.R
import com.example.app_login.databinding.ActivityAuthBinding
import com.example.app_login.viewmodel.AuthViewModel
import com.example.lib_common.base.BaseActivity

class AuthActivity() : BaseActivity() {

    private val viewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityAuthBinding = DataBindingUtil.setContentView(this, R.layout.activity_auth)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

}