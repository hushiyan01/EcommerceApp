package com.myworkshop.ecommerceapp.view.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.MenuItem
import android.widget.TextView
import androidx.core.view.GravityCompat
import com.myworkshop.ecommerceapp.R
import com.myworkshop.ecommerceapp.databinding.ActivityMainBinding
import com.myworkshop.ecommerceapp.model.local.util.UIUtils
import com.myworkshop.ecommerceapp.model.preferences.SharedPref
import com.myworkshop.ecommerceapp.view.fragment.main.CategoryFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var pref: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pref = SharedPref.getSecuredSharedPreferences(this)

        val fullName = pref.getString("logged_in_full_name", "")
        if (fullName!!.isNotEmpty()) {
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
                findViewById<TextView>(R.id.tv_header_name).text =
                    "Welcome  ${pref.getString("logged_in_full_name", "")}"
                findViewById<TextView>(R.id.tv_header_email).text =
                    pref.getString("logged_in_email_id", "")
                findViewById<TextView>(R.id.tv_header_mobile_no).text =
                    pref.getString("logged_in_mobile_no", "")
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

            when (menuItems.itemId) {
                R.id.app_home -> goToHome()
//                R.id.offer -> showToast("offer")
//                R.id.settings -> showToast("setting")
//                R.id.logout -> showToast("logout")
//                R.id.moments -> showToast("moments")
                R.id.logout -> logout()

            }

            true

        }
        supportFragmentManager.beginTransaction().add(R.id.fg_home_container, CategoryFragment()).commit()
    }

    private fun goToHome() {
        binding.drawer.closeDrawer(GravityCompat.START)
    }

    private fun logout() {
        binding.drawer.closeDrawer(GravityCompat.START)
        pref.edit().putBoolean("is_login", false).apply()
        val intent = Intent(this, LoginSignUpActivity::class.java)
        startActivity(intent)
        finish()
    }
}