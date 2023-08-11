package com.myworkshop.ecommerceapp.view.fragment.sign_in_register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.BounceInterpolator
import com.myworkshop.ecommerceapp.R
import com.myworkshop.ecommerceapp.databinding.FragmentLoginBinding
import com.myworkshop.ecommerceapp.view.activity.OnSignInNRegisterChanged

class LoginFragment(private val onSignInNRegisterChanged: OnSignInNRegisterChanged) : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val bounceAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.bounce_anim)
        bounceAnimation.interpolator = BounceInterpolator()
        super.onViewCreated(view, savedInstanceState)
        binding.btnSignUp.setOnClickListener {
            onSignInNRegisterChanged.changeToSignUp()
        }
        binding.ivShoppingBasket.startAnimation(bounceAnimation)
    }

}