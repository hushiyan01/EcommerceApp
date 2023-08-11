package com.myworkshop.ecommerceapp.view.fragment.intros

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.myworkshop.ecommerceapp.databinding.FragmentIntro1Binding
import com.myworkshop.ecommerceapp.model.preferences.SharedPref
import com.myworkshop.ecommerceapp.presenter.IntroPresenter
import com.myworkshop.ecommerceapp.presenter.MVPInterfaces
import com.myworkshop.ecommerceapp.presenter.SplashPresenter
import com.myworkshop.ecommerceapp.view.activity.LoginSignUpActivity
import com.myworkshop.ecommerceapp.view.activity.MainActivity

class IntroFragment1(private val callBack: OnFragmentFinishCallBack) : Fragment(), MVPInterfaces.Intro.View {
    private lateinit var binding: FragmentIntro1Binding
    private lateinit var presenter: MVPInterfaces.Intro.Presenter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        presenter = IntroPresenter(requireContext(),this)
        binding = FragmentIntro1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSkip.setOnClickListener {
            presenter.updatePref()
            goToSignUpLogin()
        }
    }

    override fun goToSignUpLogin() {
        val intent = Intent(requireContext(), LoginSignUpActivity::class.java)
        startActivity(intent)
        callBack.finishActivity()
    }

}