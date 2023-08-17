package com.myworkshop.ecommerceapp.view.fragment.checkout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.myworkshop.ecommerceapp.R
import com.myworkshop.ecommerceapp.databinding.CustomTabBinding
import com.myworkshop.ecommerceapp.databinding.FragmentCheckoutBinding
import com.myworkshop.ecommerceapp.model.remote.util.VolleyImageCaching
import com.myworkshop.ecommerceapp.view.adapter.FragmentViewpagerAdapter
import com.myworkshop.ecommerceapp.view.fragment.cart.CartItemsFragment
import com.myworkshop.ecommerceapp.view.fragment.cart.DeliveryFragment
import com.myworkshop.ecommerceapp.view.fragment.cart.PaymentFragment
import com.myworkshop.ecommerceapp.view.fragment.cart.SummaryFragment

class CheckOutFragment : Fragment() {
    private lateinit var binding: FragmentCheckoutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCheckoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cartItemsFragment = CartItemsFragment()


        val subcategoryFrags =
            listOf(CartItemsFragment(), DeliveryFragment(), PaymentFragment(), SummaryFragment())
        binding.apply {
            vpCheckOut.adapter = FragmentViewpagerAdapter(subcategoryFrags, requireActivity())
            TabLayoutMediator(checkoutTabLayout, vpCheckOut) {
                    tab, position ->
                when(position){
                    0-> tab.text = "Cart Items"
                    1-> tab.text = "Delivery"
                    2-> tab.text = "Payment"
                    3-> tab.text = "Summary"
                }
            }.attach()
        }
    }

}