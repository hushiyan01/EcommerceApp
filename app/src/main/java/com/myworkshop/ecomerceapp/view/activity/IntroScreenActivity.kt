package com.myworkshop.ecomerceapp.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2

import com.apolisb42.view_pager.fragments.intro.IntroFragment1
import com.apolisb42.view_pager.fragments.intro.IntroFragment2
import com.apolisb42.view_pager.fragments.intro.IntroFragment3
import com.google.android.material.tabs.TabLayoutMediator
import com.myworkshop.ecomerceapp.databinding.ActivityIntroScreenBinding
import com.myworkshop.ecomerceapp.databinding.IndicatorDotsBinding
import com.myworkshop.ecomerceapp.view.adapter.ViewpagerAdapter

class IntroScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIntroScreenBinding
    private lateinit var viewpagerAdapter: ViewpagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityIntroScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        val introFrags = listOf(IntroFragment1(), IntroFragment2(), IntroFragment3())
        viewpagerAdapter = ViewpagerAdapter(
            introFrags,
            this@IntroScreenActivity
        )
        binding.apply {
            viewPager2.adapter = viewpagerAdapter
            TabLayoutMediator(tabLayout,viewPager2){tab,_->
                tab.customView = createTabView()
            }.attach()

            viewPager2.registerOnPageChangeCallback(object :ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    updateTabDots(position)
                }
            })

        }
    }

    private fun updateTabDots(position: Int) {
        for(i in 0 until binding.tabLayout.tabCount){
            val tabView = binding.tabLayout.getTabAt(i)?.customView
            val tabBinding = IndicatorDotsBinding.bind(tabView!!)

            if(i == position){
                tabBinding.imgDotSelected.visibility = View.VISIBLE
                tabBinding.imgDotUnselected.visibility = View.GONE
            } else{
                tabBinding.imgDotSelected.visibility = View.GONE
                tabBinding.imgDotUnselected.visibility = View.VISIBLE
            }
        }
    }

    private fun createTabView(): View {
        val tabBinding = IndicatorDotsBinding.inflate(layoutInflater)
        return tabBinding.root
    }
}