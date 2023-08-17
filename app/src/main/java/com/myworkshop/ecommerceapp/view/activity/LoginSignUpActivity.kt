package com.myworkshop.ecommerceapp.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.myworkshop.ecommerceapp.R
import com.myworkshop.ecommerceapp.databinding.ActivityLoginSignUpBinding
import com.myworkshop.ecommerceapp.view.fragment.intros.OnFragmentFinishCallBack
import com.myworkshop.ecommerceapp.view.fragment.sign_in_register.LoginFragment
import com.myworkshop.ecommerceapp.view.fragment.sign_in_register.RegisterFragment

class LoginSignUpActivity : AppCompatActivity(), OnSignInNRegisterChanged,
    OnFragmentFinishCallBack {
    private lateinit var binding: ActivityLoginSignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginSignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initLoginSignUpFragments()
    }

    private fun initLoginSignUpFragments() {
        makeTransaction(LoginFragment(this, this))
    }

    private fun makeTransaction(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fg_login_signup, fragment).commit()
    }

    override fun changeToSignUp() {
        makeTransaction(RegisterFragment(this))
    }

    override fun changeToSignIn() {
        makeTransaction(LoginFragment(this, this))
    }

    override fun finishActivity() {
        finish()
    }
}