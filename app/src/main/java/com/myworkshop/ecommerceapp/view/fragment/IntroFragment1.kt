package com.myworkshop.ecommerceapp.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.myworkshop.ecommerceapp.databinding.FragmentIntro1Binding
import com.myworkshop.ecommerceapp.databinding.FragmentIntro3Binding
import com.myworkshop.ecommerceapp.model.preferences.SharedPref
import com.myworkshop.ecommerceapp.view.activity.MainActivity

class IntroFragment1 : Fragment() {
    private lateinit var binding: FragmentIntro1Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIntro1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSkip.setOnClickListener {
            val loginIntent = Intent(requireContext(), MainActivity::class.java)
            val sharedPref = SharedPref.getSecuredSharedPreferences(requireContext())
            sharedPref.edit().putBoolean("is_first_launch", false).apply()
            startActivity(loginIntent)
        }
    }

}