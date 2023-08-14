package com.myworkshop.ecommerceapp.view.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.myworkshop.ecommerceapp.R
import com.myworkshop.ecommerceapp.model.preferences.SharedPref
import com.myworkshop.ecommerceapp.presenter.MVPInterfaces
import com.myworkshop.ecommerceapp.presenter.SplashPresenter

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity(),MVPInterfaces.Splash.View{
    private lateinit var presenter: MVPInterfaces.Splash.SplashPresenter
    private val SPLASH_DISPLAY_TIME_MS = 2000L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        presenter = SplashPresenter(this)
        Handler().postDelayed({presenter.fetchPref(this)}, SPLASH_DISPLAY_TIME_MS)
    }

    override fun goToIntroActivity() {
        val intent = Intent(this@SplashActivity, IntroScreenActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun goToLoginActivity() {
        val intent = Intent(this@SplashActivity, LoginSignUpActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun goToMainActivity() {
        val intent = Intent(this@SplashActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }


}