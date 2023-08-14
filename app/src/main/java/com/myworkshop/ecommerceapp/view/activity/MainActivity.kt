package com.myworkshop.ecommerceapp.view.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.MenuItem
import android.widget.TextView
import androidx.core.view.GravityCompat
import com.myworkshop.ecommerceapp.R
import com.myworkshop.ecommerceapp.databinding.ActivityMainBinding
import com.myworkshop.ecommerceapp.model.local.util.UIUtils

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var fullName: String
    private lateinit var email: String
    private lateinit var mobileNo: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fullName = intent.extras!!.getString("full_name", "")
        email = intent.extras!!.getString("email_id", "")
        mobileNo = intent.extras!!.getString("mobile_no", "")
        if (fullName.isNotEmpty()) {
            UIUtils.showSnackBar(binding.root, "$fullName login successfully")
        }
        initViews()
    }


    @SuppressLint("SetTextI18n")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            if (binding.drawer.isDrawerOpen(GravityCompat.START)) {
                binding.drawer.closeDrawer(GravityCompat.START)
            } else {
                binding.drawer.openDrawer(GravityCompat.START)
                findViewById<TextView>(R.id.tv_header_name).text = "Welcome  $fullName"
                findViewById<TextView>(R.id.tv_header_email).text = email
                findViewById<TextView>(R.id.tv_header_mobile_no).text = mobileNo
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun initViews() {
        setSupportActionBar(binding.tbToolbar1)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.baseline_density_medium_24)
        }

        binding.nvNavView.setNavigationItemSelectedListener { menuItems ->
            menuItems.isChecked = true

//            when (menuItems.itemId) {
//                R.id.profile -> showToast("profile")
//                R.id.offer -> showToast("offer")
//                R.id.settings -> showToast("setting")
//                R.id.logout -> showToast("logout")
//                R.id.moments -> showToast("moments")
//                R.id.wallet -> showToast("wallet")

//            }

            true

        }
    }
}