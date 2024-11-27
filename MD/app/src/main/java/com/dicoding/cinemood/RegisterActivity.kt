package com.dicoding.cinemood

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    private lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        userRepository = UserRepository(this)

        findViewById<Button>(R.id.btn_register).setOnClickListener {
            val name = findViewById<EditText>(R.id.ed_register_name).text.toString()
            val email = findViewById<EditText>(R.id.ed_register_email).text.toString()
            val password = findViewById<EditText>(R.id.ed_register_password).text.toString()

            if (!isValidEmail(email)) {
                findViewById<EditText>(R.id.ed_register_email).error = "Please enter a valid email address (e.g. user@gmail.com or user@yahoo.com)"
                return@setOnClickListener
            }

            if (password.length >= 8) {
                val result = userRepository.addUser(name, email, password)

                if (result != -1L) {
                    Toast.makeText(this, "Registered successfully", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "Registration failed. Please try again.", Toast.LENGTH_SHORT).show()
                }
            } else {
                findViewById<EditText>(R.id.ed_register_password).error = "Password must be at least 8 characters"
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@(gmail\\.com|yahoo\\.com)"
        return email.matches(Regex(emailPattern))
    }
}
