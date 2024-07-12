package com.example.kotlinapp

import android.os.Bundle
import androidx.activity.ComponentActivity

import com.example.app_login.core.LoginListener
import com.example.app_login.core.LoginManager
import com.example.app_login.core.LoginResult


class MainActivity : ComponentActivity(), LoginListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        LoginManager.setLoginListener(this)
//        val button = findViewById<Button>(R.id.button1)
//        button.setOnClickListener {
//            LoginManager.login(this)
//        }
    }

    override fun onLoginSuccess(loginResult: LoginResult) {
        println("user: ${loginResult.user}, pass: ${loginResult.token}")
    }

    override fun onLoginFailure(loginError: String) {

    }
}
