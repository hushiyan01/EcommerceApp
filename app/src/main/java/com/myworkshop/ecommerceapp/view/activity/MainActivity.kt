package com.myworkshop.ecommerceapp.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.myworkshop.ecommerceapp.R
import com.myworkshop.ecommerceapp.databinding.ActivityMainBinding
import com.myworkshop.ecommerceapp.model.local.util.UIUtils

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.extras!!.getString("username","")
        if(username.isNotEmpty()){
            UIUtils.showSnackBar(binding.root, "$username login successfully")
        }
    }
}