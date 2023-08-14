package com.myworkshop.ecommerceapp.presenter

import com.myworkshop.ecommerceapp.model.remote.ResponseCallBack
import com.myworkshop.ecommerceapp.model.remote.dto.login_signup.LoginResult
import com.myworkshop.ecommerceapp.model.remote.util.VolleyHandler
import org.json.JSONObject

class LoginPresenter(private val volleyHandler: VolleyHandler,
    private val view:MVPInterfaces.SignIn.View)
    :MVPInterfaces.SignIn.Presenter {
    override fun login(userName: String, password: String) {
        volleyHandler.login(userName, password, object :ResponseCallBack.LoginResponseCallBack{
            override fun loginSuccess(result: LoginResult) {
                view.loginSuccess(result)
            }

            override fun loginFailed(errorMsg: String) {
                view.loginFailed(errorMsg)
            }

        })
    }


}