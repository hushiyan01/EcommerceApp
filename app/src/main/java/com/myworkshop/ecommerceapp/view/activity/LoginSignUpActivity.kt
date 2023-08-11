package com.myworkshop.ecommerceapp.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.myworkshop.ecommerceapp.R
import com.myworkshop.ecommerceapp.databinding.ActivityLoginSignUpBinding
import com.myworkshop.ecommerceapp.view.fragment.sign_in_register.LoginFragment
import com.myworkshop.ecommerceapp.view.fragment.sign_in_register.SignUpFragment

class LoginSignUpActivity : AppCompatActivity(),OnSignInNRegisterChanged {
    private lateinit var binding: ActivityLoginSignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginSignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initLoginSignUpFragments()
    }

    private fun initLoginSignUpFragments() {
        makeTransaction(LoginFragment(this))
    }

    private fun makeTransaction(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.fg_login_signup, fragment).commit()
    }

    override fun changeToSignUp() {
        makeTransaction(SignUpFragment(this))
    }

    override fun changeToSignIn() {
        makeTransaction(LoginFragment(this))
    }
}