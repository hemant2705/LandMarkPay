package com.example.landmarkpay.common

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import com.example.landmarkpay.R

class SplashScreen : AppCompatActivity() {

    lateinit var imglogo : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        imglogo = findViewById(R.id.imageView)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent =
                Intent(this, Login::class.java)
            startActivity(intent)
        }, 4000)
    }
}