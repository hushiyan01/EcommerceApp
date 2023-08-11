package com.myworkshop.ecommerceapp.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.myworkshop.ecommerceapp.databinding.FragmentIntro3Binding
import com.myworkshop.ecommerceapp.model.preferences.SharedPref
import com.myworkshop.ecommerceapp.view.activity.MainActivity

class IntroFragment3 : Fragment() {
    private lateinit var binding: FragmentIntro3Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIntro3Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnGoToLogin.setOnClickListener {
            val loginIntent = Intent(requireContext(), MainActivity::class.java)
            val sharedPref = SharedPref.getSecuredSharedPreferences(requireContext())
            sharedPref.edit().putBoolean("is_first_launch", false).apply()
            startActivity(loginIntent)
        }
    }

}