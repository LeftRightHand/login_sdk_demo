package com.example.kotlinapp

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.app_login.core.LoginListener
import com.example.app_login.core.LoginManager
import com.example.app_login.core.LoginResult
import com.example.kotlinapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), LoginListener {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        LoginManager.setLoginListener(this)
        val button = findViewById<Button>(R.id.button1)
        button.setOnClickListener {
            LoginManager.login(this)
        }

    }

    override fun onLoginSuccess(loginResult: LoginResult) {
        println("user: ${loginResult.user}, pass: ${loginResult.token}")
    }

    override fun onLoginFailure(loginError: String) {

    }
}
