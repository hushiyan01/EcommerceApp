package com.myworkshop.ecommerceapp.view.fragment.sign_in_register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.myworkshop.ecommerceapp.databinding.FragmentSignUpBinding
import com.myworkshop.ecommerceapp.model.local.util.UIUtils
import com.myworkshop.ecommerceapp.model.remote.dto.login_signup.LoginResult
import com.myworkshop.ecommerceapp.model.remote.dto.login_signup.RegisterResult
import com.myworkshop.ecommerceapp.model.remote.util.VolleyHandler
import com.myworkshop.ecommerceapp.presenter.MVPInterfaces
import com.myworkshop.ecommerceapp.presenter.RegisterPresenter
import com.myworkshop.ecommerceapp.view.activity.OnSignInNRegisterChanged


class RegisterFragment(private val onSignInNRegisterChanged: OnSignInNRegisterChanged) : Fragment(),MVPInterfaces.Register.View {
    private lateinit var presenter: RegisterPresenter
    private lateinit var binding:FragmentSignUpBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        presenter = RegisterPresenter(volleyHandler = VolleyHandler(requireContext()), view = this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            btnJumpToSignIn.setOnClickListener {
                onSignInNRegisterChanged.changeToSignIn()
            }

            btnRegister.setOnClickListener {
                val fullName = etFullName.text.toString()
                val mobileNo = etMobileNo.text.toString()
                val emailId = etEmailId.text.toString()
                val password = etPassword.text.toString()
                if(fullName.isEmpty()||mobileNo.isEmpty()||emailId.isEmpty()||password.isEmpty()){
                    UIUtils.showSnackBar(requireView(), "please complete your registering info")
                    return@setOnClickListener
                }
                presenter.register(
                    fullName = fullName,
                    emailId = emailId,
                    mobileNo = mobileNo,
                    password = password
                )
            }
        }
    }

    override fun registerSuccess(registerResult: RegisterResult) {
        UIUtils.showSnackBar(requireView(), registerResult.message)
        onSignInNRegisterChanged.changeToSignIn()
    }

    override fun registerFailed(errorMsg: String) {
        UIUtils.showSnackBar(requireView(), errorMsg)
    }

}