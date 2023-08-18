package com.myworkshop.ecommerceapp.view.fragment.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.myworkshop.ecommerceapp.databinding.FragmentPaymentBinding
import com.myworkshop.ecommerceapp.view.adapter.PaymentAdapter

class PaymentFragment : Fragment() {
    private lateinit var binding: FragmentPaymentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPaymentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvPayments.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = PaymentAdapter(
                arrayListOf(
                    "Cash On Delivery",
                    "Internet Banking",
                    "Debit Card/ Credit Card",
                    "Paypal"
                ),
                requireContext()
            )
        }
    }
}