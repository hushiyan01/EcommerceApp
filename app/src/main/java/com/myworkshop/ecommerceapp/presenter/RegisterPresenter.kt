package com.myworkshop.ecommerceapp.presenter

import com.myworkshop.ecommerceapp.model.remote.ResponseCallBack
import com.myworkshop.ecommerceapp.model.remote.dto.login_signup.RegisterResult
import com.myworkshop.ecommerceapp.model.remote.util.VolleyHandler

class RegisterPresenter(
    private val view: MVPInterfaces.Register.View,
    private val volleyHandler: VolleyHandler
) : MVPInterfaces.Register.Presenter {
    override fun register(fullName: String, mobileNo: String, emailId: String, password: String) {
        volleyHandler.userRegister(
            fullName = fullName,
            mobileNo = mobileNo,
            emailId = emailId,
            password = password,
            registerResponseCallBack = object : ResponseCallBack.RegisterResponseCallBack {
                override fun registerSuccess(registerResult: RegisterResult) {
                    view.registerSuccess(registerResult)
                }

                override fun registerFailed(errorMsg: String) {
                    view.registerFailed(errorMsg)
                }
            }
        )
    }

}