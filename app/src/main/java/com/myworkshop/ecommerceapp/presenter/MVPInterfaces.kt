package com.myworkshop.ecommerceapp.presenter

import android.content.Context
import com.myworkshop.ecommerceapp.model.remote.ResponseCallBack
import com.myworkshop.ecommerceapp.model.remote.dto.login_signup.LoginResult
import com.myworkshop.ecommerceapp.model.remote.dto.login_signup.RegisterResult

interface MVPInterfaces {
    interface Splash{
        interface SplashPresenter{
            fun fetchPref(context:Context)
        }

        interface View{
            fun goToIntroActivity()
            fun goToLoginActivity()
            fun goToMainActivity()
        }
    }

    interface Intro{
        interface Presenter{
            fun updatePref()
        }
        interface View{
            fun goToSignUpLogin()
        }
    }

    interface SignIn{
        interface Presenter{
            fun login(userName:String, password:String)
            fun updatePref(context: Context, fullName: String, emailId: String, mobileNo: String)
        }
        interface View{
            fun loginSuccess(loginResult: LoginResult)
            fun loginFailed(errorMsg:String)
        }
    }

    interface Register{
        interface Presenter{
            fun register(fullName:String, mobileNo:String, emailId:String, password: String)
        }
        interface View{
            fun registerSuccess(registerResult: RegisterResult)
            fun registerFailed(errorMsg:String)
        }
    }


}