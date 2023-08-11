package com.myworkshop.ecommerceapp.view.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.myworkshop.ecommerceapp.R
import com.myworkshop.ecommerceapp.model.preferences.SharedPref

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private val SPLASH_DISPLAY_TIME_MS = 2000L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val sharedPref = SharedPref.getSecuredSharedPreferences(this)

        Handler().postDelayed({
            val isFistLaunch = sharedPref.getBoolean("is_first_launch",true)
            if(isFistLaunch){
                val intent = Intent(this@SplashActivity, IntroScreenActivity::class.java)
                startActivity(intent)
            }else{
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
            }
        }, SPLASH_DISPLAY_TIME_MS)
    }
}