package com.myworkshop.ecommerceapp.view.fragment.intros

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.myworkshop.ecommerceapp.databinding.FragmentIntro3Binding
import com.myworkshop.ecommerceapp.model.preferences.SharedPref
import com.myworkshop.ecommerceapp.presenter.MVPInterfaces
import com.myworkshop.ecommerceapp.view.activity.LoginSignUpActivity
import com.myworkshop.ecommerceapp.view.activity.MainActivity

class IntroFragment3(private val callback:OnFragmentFinishCallBack) : Fragment(), MVPInterfaces.Intro.View{
    private lateinit var presenter: MVPInterfaces.Intro.Presenter
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
            presenter.updatePref()
            goToSignUpLogin()
        }
    }

    override fun goToSignUpLogin() {
        val intent = Intent(requireContext(),LoginSignUpActivity::class.java)
        startActivity(intent)
        callback.finishActivity()
    }

}