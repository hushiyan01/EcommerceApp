package com.myworkshop.ecommerceapp.view.fragment.sign_in_register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.myworkshop.ecommerceapp.R
import com.myworkshop.ecommerceapp.databinding.FragmentSignUpBinding
import com.myworkshop.ecommerceapp.view.activity.OnSignInNRegisterChanged


class SignUpFragment(private val onSignInNRegisterChanged: OnSignInNRegisterChanged) : Fragment() {

    private lateinit var binding:FragmentSignUpBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnJumpToSignIn.setOnClickListener {
            onSignInNRegisterChanged.changeToSignIn()
        }
    }

}