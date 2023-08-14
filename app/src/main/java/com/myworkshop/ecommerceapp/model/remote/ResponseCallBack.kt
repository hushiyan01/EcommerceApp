package com.myworkshop.ecommerceapp.model.remote

import com.myworkshop.ecommerceapp.model.remote.dto.login_signup.LoginResult
import com.myworkshop.ecommerceapp.model.remote.dto.login_signup.RegisterResult
import com.myworkshop.ecommerceapp.model.remote.dto.search.CategoryResult
import org.json.JSONObject

interface ResponseCallBack {
    interface LoginResponseCallBack{
        fun loginSuccess(result: LoginResult)
        fun loginFailed(errorMsg:String)
    }

    interface RegisterResponseCallBack{
        fun registerSuccess(registerResult: RegisterResult)
        fun registerFailed(errorMsg: String)
    }

    interface FetchCategoryCallBack{
        fun fetchCategorySuccess(categoryResult: CategoryResult)
        fun fetchCategoryFailed(errorMsg: String)
    }
}