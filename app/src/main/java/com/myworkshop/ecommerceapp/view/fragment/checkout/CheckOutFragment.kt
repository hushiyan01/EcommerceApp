package com.myworkshop.ecommerceapp.view.fragment.checkout

import android.os.Bundle
import android.provider.Telephony.Mms.Addr
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.myworkshop.ecommerceapp.databinding.FragmentCheckoutBinding
import com.myworkshop.ecommerceapp.model.local.entity.po.AddressView
import com.myworkshop.ecommerceapp.model.local.entity.po.CartItem
import com.myworkshop.ecommerceapp.view.activity.MainActivity
import com.myworkshop.ecommerceapp.view.adapter.FragmentViewpagerAdapter
import com.myworkshop.ecommerceapp.view.fragment.cart.CartItemsFragment
import com.myworkshop.ecommerceapp.view.fragment.cart.DeliveryFragment
import com.myworkshop.ecommerceapp.view.fragment.cart.PaymentFragment
import com.myworkshop.ecommerceapp.view.fragment.cart.SummaryFragment

class CheckOutFragment : Fragment(),UpdateCheckoutInfo {
    private lateinit var binding: FragmentCheckoutBinding
    private lateinit var deliveryAddress: AddressView
    private lateinit var paymentOption: String
    private lateinit var cartItems:List<CartItem>

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
        val subcategoryFrags =
            listOf(
                CartItemsFragment(this),
                DeliveryFragment(this),
                PaymentFragment(this),
                SummaryFragment(this))
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

    override fun updateDeliverAddress(deliverAddresses: AddressView) {
        this.deliveryAddress = deliverAddresses
    }

    override fun updatePaymentMethod(paymentMethod: String) {
        this.paymentOption = paymentMethod
    }

    override fun updateCartItems(cartItems: List<CartItem>) {
        this.cartItems = cartItems
    }

    override fun getPaymentMethod(): String? {
        return if(this::paymentOption.isInitialized){
            paymentOption
        }else{
            null
        }
    }

    override fun getCartItems(): List<CartItem>? {
        return if(this::cartItems.isInitialized){
            cartItems
        }else{
            null
        }
    }

    override fun getDeliverAddress(): AddressView? {
        return if(this::deliveryAddress.isInitialized){
            deliveryAddress
        }else{
            null
        }
    }

}