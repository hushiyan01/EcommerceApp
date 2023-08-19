package com.myworkshop.ecommerceapp.view.fragment.checkout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.myworkshop.ecommerceapp.databinding.FragmentCheckoutBinding
import com.myworkshop.ecommerceapp.view.activity.MainActivity
import com.myworkshop.ecommerceapp.view.adapter.FragmentViewpagerAdapter
import com.myworkshop.ecommerceapp.view.fragment.cart.CartItemsFragment
import com.myworkshop.ecommerceapp.view.fragment.cart.DeliveryFragment
import com.myworkshop.ecommerceapp.view.fragment.cart.PaymentFragment
import com.myworkshop.ecommerceapp.view.fragment.cart.SummaryFragment
import com.myworkshop.ecommerceapp.view.fragment.main.OnChangeToolbarCallback

class CheckOutFragment(
//    private val onChangeToolbarCallback: OnChangeToolbarCallback
) : Fragment() {
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
        (activity as? MainActivity)?.changeToolbar(this, "Cart")
//        onChangeToolbarCallback.changeToolbar(this@CheckOutFragment, "CHECKOUT")
        val subcategoryFrags =
            listOf(CartItemsFragment(), DeliveryFragment(), PaymentFragment(), SummaryFragment())
        binding.apply {
            vpCheckOut.adapter = FragmentViewpagerAdapter(subcategoryFrags, requireActivity())
            TabLayoutMediator(checkoutTabLayout, vpCheckOut) { tab, position ->
                when (position) {
                    0 -> tab.text = "Cart Items"
                    1 -> tab.text = "Delivery"
                    2 -> tab.text = "Payment"
                    3 -> tab.text = "Summary"
                }
            }.attach()
        }
    }

}