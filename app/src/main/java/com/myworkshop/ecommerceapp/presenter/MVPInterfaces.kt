package com.myworkshop.ecommerceapp.presenter

import android.content.Context

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

        }
        interface View{

        }
    }


}