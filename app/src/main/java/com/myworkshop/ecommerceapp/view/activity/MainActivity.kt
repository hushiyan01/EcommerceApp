package com.myworkshop.ecommerceapp.view.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.android.volley.toolbox.ImageLoader
import com.myworkshop.ecommerceapp.R
import com.myworkshop.ecommerceapp.databinding.ActivityMainBinding
import com.myworkshop.ecommerceapp.model.local.util.UIUtils
import com.myworkshop.ecommerceapp.model.preferences.SharedPref
import com.myworkshop.ecommerceapp.model.remote.util.VolleyImageCaching
import com.myworkshop.ecommerceapp.view.fragment.main.CategoryFragment
import com.myworkshop.ecommerceapp.view.fragment.main.OnChangeToolbarCallback
import com.myworkshop.ecommerceapp.view.fragment.main.OnGoToSubCategoryViewPagerCallBack
import com.myworkshop.ecommerceapp.view.fragment.main.SubCategoryFragment

class MainActivity : AppCompatActivity(),
    OnGoToSubCategoryViewPagerCallBack,
    OnChangeToolbarCallback {
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
        initImageLoader()
    }

    private fun initImageLoader() {
        VolleyImageCaching.initialize(this)
    }


    @SuppressLint("SetTextI18n")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {

            val fragment = supportFragmentManager.findFragmentById(R.id.fg_home_container)
            when(fragment){
                is CategoryFragment ->{
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
                is SubCategoryFragment ->{
                    supportFragmentManager.popBackStack()
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun initViews() {
        setSupportActionBar(binding.tbToolbar1)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

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
        makeFragTransaction("category_fragment", CategoryFragment(this,this))
    }

    private fun goToHome() {
        binding.drawer.closeDrawer(GravityCompat.START)
        makeFragTransaction("category_fragment", CategoryFragment(this,this))
    }

    private fun logout() {
        binding.drawer.closeDrawer(GravityCompat.START)
        pref.edit().putBoolean("is_login", false).apply()
        val intent = Intent(this, LoginSignUpActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun makeFragTransaction(flag:String, fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fg_home_container, fragment)
            .addToBackStack(flag)
            .commit()
    }

    override fun go(subCategoryId:String, categoryTitle:String) {
        makeFragTransaction("sub_category_fragment", SubCategoryFragment(subCategoryId, categoryTitle, this))
    }

    override fun changeToolbar(fragment: Fragment, toolBarTitle:String) {
        when(fragment){
            is SubCategoryFragment -> {
                binding.tvToolbarTitle.text= toolBarTitle
                supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24)
            }
            is CategoryFragment ->{
                binding.tvToolbarTitle.text= toolBarTitle
                supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_density_medium_24)
            }
        }
    }
}