package com.myworkshop.ecommerceapp.presenter

import android.content.Context
import com.myworkshop.ecommerceapp.model.preferences.SharedPref

class IntroPresenter(private val context: Context, private val view:MVPInterfaces.Intro.View):MVPInterfaces.Intro.Presenter {
    override fun updatePref() {
        val sharedPref = SharedPref.getSecuredSharedPreferences(context = context)
        sharedPref.edit().putBoolean("is_first_launch", false).apply()
        view.goToSignUpLogin()
    }
}