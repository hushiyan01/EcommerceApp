package com.myworkshop.ecommerceapp.view.fragment.cart

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.myworkshop.ecommerceapp.databinding.FragmentSummaryBinding
import com.myworkshop.ecommerceapp.model.local.entity.po.CartItem
import com.myworkshop.ecommerceapp.view.adapter.CartCheckoutAdapter
import com.myworkshop.ecommerceapp.view.fragment.checkout.UpdateCheckoutInfo

class SummaryFragment(val updateCheckoutInfo: UpdateCheckoutInfo) : Fragment() {
    private lateinit var binding:FragmentSummaryBinding
    private var cartItems: List<CartItem>?=null
    private lateinit var fragmentManager: FragmentManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSummaryBinding.inflate(inflater, container, false)
        fragmentManager = requireActivity().supportFragmentManager
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    @SuppressLint("SetTextI18n")
    override fun onResume() {
        super.onResume()
        binding.apply {
            tvPaymentOption.text = updateCheckoutInfo.getPaymentMethod()
            tvAddressTitle.text = updateCheckoutInfo.getDeliverAddress()?.type
            tvAddressContent.text = updateCheckoutInfo.getDeliverAddress()?.address
            rvCartItemsSummary.apply {
                layoutManager = LinearLayoutManager(requireContext())
                cartItems = updateCheckoutInfo.getCartItems()
                if(cartItems!=null){
                    adapter = CartCheckoutAdapter(cartItems!!)
                }
            }
            if(cartItems!=null){
                val totalPrice = cartItems!!.map { it.price }.sum()
                tvTotalPrice.text = "$ $totalPrice"
            }else{
                tvTotalPrice.text = "$ 0"
            }
        }
    }
}