package com.myworkshop.ecommerceapp.presenter

import android.content.Context
import com.myworkshop.ecommerceapp.model.preferences.SharedPref

class SplashPresenter(private val view:MVPInterfaces.Splash.View): MVPInterfaces.Splash.SplashPresenter{
    override fun fetchPref(context: Context) {
        val pref = SharedPref.getSecuredSharedPreferences(context)
        val isFirstLaunch = pref.getBoolean("is_first_launch",true)
        val isLogin = pref.getBoolean("is_login",false)
        if(isFirstLaunch){
            pref.edit().putBoolean("is_first_launch",false).apply()
            view.goToIntroActivity()
        }else{
            if(isLogin){
                view.goToMainActivity()
            }else{
                view.goToLoginActivity()
            }

        }
    }
}