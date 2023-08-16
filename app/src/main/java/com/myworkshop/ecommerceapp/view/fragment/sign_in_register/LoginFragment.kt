package com.myworkshop.ecommerceapp.view.fragment.sign_in_register

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.BounceInterpolator
import androidx.fragment.app.Fragment
import com.myworkshop.ecommerceapp.R
import com.myworkshop.ecommerceapp.databinding.FragmentLoginBinding
import com.myworkshop.ecommerceapp.model.local.util.UIUtils
import com.myworkshop.ecommerceapp.model.remote.dto.login_signup.LoginResult
import com.myworkshop.ecommerceapp.model.remote.util.VolleyHandler
import com.myworkshop.ecommerceapp.presenter.LoginPresenter
import com.myworkshop.ecommerceapp.presenter.MVPInterfaces
import com.myworkshop.ecommerceapp.view.activity.MainActivity
import com.myworkshop.ecommerceapp.view.activity.OnSignInNRegisterChanged
import com.myworkshop.ecommerceapp.view.fragment.intros.OnFragmentFinishCallBack

class LoginFragment(
    private val onSignInNRegisterChanged: OnSignInNRegisterChanged,
    private val onFragmentFinishCallBack: OnFragmentFinishCallBack
) : Fragment(), MVPInterfaces.SignIn.View {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var presenter: LoginPresenter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        presenter = LoginPresenter(VolleyHandler(requireContext()), this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val bounceAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.bounce_anim)
        bounceAnimation.interpolator = BounceInterpolator()
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            btnJumpToSignUp.setOnClickListener {
                onSignInNRegisterChanged.changeToSignUp()
            }
            ivShoppingBasket.startAnimation(bounceAnimation)

            btnSignIn.setOnClickListener {
                val username = etEmailId.text.toString()
                val password = etPassword.text.toString()
                if (username.isEmpty() || password.isEmpty()) {
                    UIUtils.showSnackBar(requireView(), "email id or password should not be empty!")
                    return@setOnClickListener
                }
                presenter.login(username, password)
            }
        }
    }

    override fun loginSuccess(loginResult: LoginResult) {
        val intent = Intent(requireContext(), MainActivity::class.java)
        intent.putExtra("full_name", loginResult.user.full_name)
        intent.putExtra("email_id", loginResult.user.email_id)
        intent.putExtra("mobile_no", loginResult.user.mobile_no)
        presenter.updatePref(
            requireContext(),
            fullName = loginResult.user.full_name,
            emailId = loginResult.user.email_id,
            mobileNo = loginResult.user.mobile_no
        )
        startActivity(intent)
        onFragmentFinishCallBack.finishActivity()
    }

    override fun loginFailed(errorMsg: String) {
        UIUtils.showSnackBar(requireView(), errorMsg)
    }


}