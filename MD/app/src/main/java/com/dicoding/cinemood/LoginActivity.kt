package com.dicoding.cinemood

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.cinemood.MainActivity
import com.dicoding.cinemood.R
import com.dicoding.cinemood.RegisterActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sharedPreferences = getSharedPreferences("user_session", Context.MODE_PRIVATE)
        userRepository = UserRepository(this)

        if (sharedPreferences.getBoolean("isLoggedIn", false)) {
            navigateToMain()
        }

        findViewById<Button>(R.id.btn_login).setOnClickListener {
            val email = findViewById<EditText>(R.id.ed_login_email).text.toString()
            val password = findViewById<EditText>(R.id.ed_login_password).text.toString()

            if (email.isNotEmpty() && password.length >= 8) {
                if (userRepository.getUser(email, password)) {
                    sharedPreferences.edit().apply {
                        putBoolean("isLoggedIn", true)
                        putString("token", "dummy_token")
                        apply()
                    }
                    navigateToMain()
                } else {
                    Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
                }
            } else {
                if (password.length < 8) {
                    findViewById<EditText>(R.id.ed_login_password).error = "Password must be at least 8 characters"
                }
            }
        }

        findViewById<TextView>(R.id.tvGoToRegister).setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun navigateToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
