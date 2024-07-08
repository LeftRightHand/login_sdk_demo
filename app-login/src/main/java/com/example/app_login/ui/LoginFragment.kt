package com.example.app_login.ui


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.app_login.databinding.FragmentLoginBinding
import com.example.app_login.viewmodel.LoginViewModel
import com.example.lib_common.base.BaseFragment

class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    private val viewModel: LoginViewModel by viewModels()

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentLoginBinding {
        return FragmentLoginBinding.inflate(inflater, container, false).apply {
            viewModel = this@LoginFragment.viewModel
            lifecycleOwner = viewLifecycleOwner
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = LoginFragment()
    }
}