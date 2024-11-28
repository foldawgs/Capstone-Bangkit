package com.dicoding.cinemood

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        sharedPreferences = getSharedPreferences("user_session", Context.MODE_PRIVATE)


        findViewById<Button>(R.id.btnSearch).setOnClickListener {
            navigateToDashboard()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        logoutAndNavigateToLogin()
    }


    private fun navigateToDashboard() {
        val intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)
    }


    private fun logoutAndNavigateToLogin() {
        sharedPreferences.edit().apply {
            putBoolean("isLoggedIn", false)
            remove("token")
            apply()
        }
        val intent = Intent(this, LoginActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        }
        startActivity(intent)
        finish()
    }
}
