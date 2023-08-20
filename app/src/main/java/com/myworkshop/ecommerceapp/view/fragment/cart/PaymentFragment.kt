package com.myworkshop.ecommerceapp.view.fragment.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.myworkshop.ecommerceapp.databinding.FragmentPaymentBinding
import com.myworkshop.ecommerceapp.model.local.entity.po.PaymentView
import com.myworkshop.ecommerceapp.view.adapter.PaymentAdapter
import com.myworkshop.ecommerceapp.view.fragment.checkout.CheckOutFragment
import com.myworkshop.ecommerceapp.view.fragment.checkout.UpdateCheckoutInfo

class PaymentFragment(val updateCheckoutInfo: UpdateCheckoutInfo) : Fragment() {
    private lateinit var binding: FragmentPaymentBinding
    private val paymentMethods = arrayListOf(
        PaymentView("Cash On Delivery",false),
        PaymentView("Internet Banking",false),
        PaymentView("Debit Card/ Credit Card",false),
        PaymentView("Paypal",false)
    )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPaymentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            rvPayments.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = PaymentAdapter(paymentMethods, requireContext(),updateCheckoutInfo)
            }
            btnNext.setOnClickListener {
                val checkoutViewPager2 = findViewPagerParent(view)
                if (checkoutViewPager2 != null) {
                    val currentItem = checkoutViewPager2.currentItem
                    val nextPage = currentItem + 1

                    if (checkoutViewPager2.adapter != null && nextPage < checkoutViewPager2.adapter!!.itemCount) {
                        checkoutViewPager2.setCurrentItem(nextPage, true)
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if(paymentMethods.any{it.isSelected}){
            updateCheckoutInfo.updatePaymentMethod(paymentMethods.filter { it.isSelected }[0].payment)
        }
    }
}