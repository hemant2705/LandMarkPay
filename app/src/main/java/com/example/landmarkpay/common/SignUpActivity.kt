package com.example.landmarkpay.common

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.landmarkpay.R
import com.example.landmarkpay.database.DatabaseHelper
import com.example.landmarkpay.database.Enterprise
import java.util.UUID

class SignUpActivity : AppCompatActivity() {


        private lateinit var enterpriseNameEditText: EditText
        private lateinit var addressEditText: EditText
        private lateinit var emailEditText: EditText
        private lateinit var tinEditText: EditText
        private lateinit var passwordEditText: EditText
        private lateinit var signUpButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

            enterpriseNameEditText = findViewById(R.id.enterpriseNameEditText)
            addressEditText = findViewById(R.id.addressEditText)
            emailEditText = findViewById(R.id.emailEditText)
            tinEditText = findViewById(R.id.tinEditText)
            passwordEditText = findViewById(R.id.passwordEditText)
            signUpButton = findViewById(R.id.signUpButton)

            signUpButton.setOnClickListener {

                val uniqueId = UUID.randomUUID().toString()

                val enterprise = Enterprise(
                    id = 0, // Auto-generated ID in SQLite
                  //  userId = uniqueId,
                    name = enterpriseNameEditText.text.toString(),
                    address = addressEditText.text.toString(),
                    email = emailEditText.text.toString(),
                    tin = tinEditText.text.toString(),
                    password = passwordEditText.text.toString()
                )
                val databaseHelper = DatabaseHelper(this)
                databaseHelper.insertEnterprise(enterprise)

                val intent = Intent(this, Login::class.java)
                startActivity(intent)
            }
        }
    }



