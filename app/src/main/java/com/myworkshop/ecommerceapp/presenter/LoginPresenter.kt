package com.myworkshop.ecommerceapp.presenter

import android.content.Context
import com.myworkshop.ecommerceapp.model.preferences.SharedPref
import com.myworkshop.ecommerceapp.model.remote.ResponseCallBack
import com.myworkshop.ecommerceapp.model.remote.dto.login_signup.LoginResult
import com.myworkshop.ecommerceapp.model.remote.util.VolleyHandler

class LoginPresenter(
    private val volleyHandler: VolleyHandler,
    private val view: MVPInterfaces.SignIn.View
) : MVPInterfaces.SignIn.Presenter {
    override fun login(userName: String, password: String) {
        volleyHandler.userLogin(
            userName,
            password,
            object : ResponseCallBack.LoginResponseCallBack {
                override fun loginSuccess(result: LoginResult) {
                    view.loginSuccess(result)
                }

                override fun loginFailed(errorMsg: String) {
                    view.loginFailed(errorMsg)
                }
            })
    }

    override fun updatePref(context: Context, fullName: String, emailId: String, mobileNo: String) {
        val pref = SharedPref.getSecuredSharedPreferences(context)
        pref.edit().putBoolean("is_login", true)
            .putString("logged_in_full_name", fullName)
            .putString("logged_in_email_id", emailId)
            .putString("logged_in_mobile_no", mobileNo).apply()
    }


}