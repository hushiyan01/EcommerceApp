package com.myworkshop.ecommerceapp.presenter

import android.content.Context
import com.myworkshop.ecommerceapp.model.remote.dto.login_signup.LoginResult

interface MVPInterfaces {
    interface Splash{
        interface SplashPresenter{
            fun fetchPref(context:Context)
        }

        interface View{
            fun goToIntroActivity()
            fun goToLoginActivity()
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
        }
        interface View{
            fun loginSuccess(loginResult: LoginResult)
            fun loginFailed(errorMsg:String)
        }
    }


}