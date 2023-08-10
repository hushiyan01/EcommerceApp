package com.apolisb42.view_pager.fragments.intro

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.myworkshop.ecomerceapp.databinding.FragmentIntro3Binding
import com.myworkshop.ecomerceapp.model.preferences.SharedPref
import com.myworkshop.ecomerceapp.view.activity.MainActivity

/**
 * A simple [Fragment] subclass.
 * Use the [IntroFragment3.newInstance] factory method to
 * create an instance of this fragment.
 */
class IntroFragment3 : Fragment() {
    lateinit var binding: FragmentIntro3Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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