package com.example.landmarkpay.common

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.example.landmarkpay.R
import com.example.landmarkpay.database.DatabaseHelper
import com.example.landmarkpay.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var signupButton:Button
    private lateinit var progessbar:ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)
        signupButton=findViewById(R.id.btnsgn)
        progessbar=findViewById(R.id.prog)

        loginButton.setOnClickListener {



            val enteredEmail = emailEditText.text.toString()
            val enteredPassword = passwordEditText.text.toString()

            if (enteredEmail.isEmpty() || enteredPassword.isEmpty()) {
                // Display an error message if any field is empty
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {

                val databaseHelper = DatabaseHelper(this)
                val isValidUser = databaseHelper.checkLoginCredentials(enteredEmail, enteredPassword)

                if (isValidUser) {
                    progessbar.visibility=View.VISIBLE
                    Handler(Looper.getMainLooper()).postDelayed({
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }, 2000)
                } else {
                    // Display an error message for invalid credentials
                    Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show()
                }
            }
        }

        signupButton.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}

