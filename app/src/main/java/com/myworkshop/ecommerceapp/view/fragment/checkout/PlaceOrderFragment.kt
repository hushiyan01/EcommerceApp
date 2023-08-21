package com.myworkshop.ecommerceapp.view.fragment.checkout

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.myworkshop.ecommerceapp.databinding.FragmentOrderConfirmedBinding
import com.myworkshop.ecommerceapp.model.local.entity.po.CartItem
import com.myworkshop.ecommerceapp.model.preferences.SharedPref
import com.myworkshop.ecommerceapp.model.remote.dto.address.Address
import com.myworkshop.ecommerceapp.model.remote.dto.order.PlaceOrderResult
import com.myworkshop.ecommerceapp.model.remote.util.VolleyHandler
import com.myworkshop.ecommerceapp.presenter.MVPInterfaces
import com.myworkshop.ecommerceapp.presenter.PlaceOrderPresenter
import com.myworkshop.ecommerceapp.view.adapter.CartCheckoutAdapter

class PlaceOrderFragment : Fragment() {
    private lateinit var binding:FragmentOrderConfirmedBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrderConfirmedBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = requireArguments()
        val orderId = bundle.getString("order_id")?:""
        val address = bundle.getParcelable<Address>("address")?:Address("","","")
        val payment = bundle.getString("payment")?:""
        val items = bundle.getParcelableArrayList("items")?:ArrayList<CartItem>()

        binding.apply {
            tvPaymentOption.text = payment
            tvOrderId.text = "# $orderId"
            tvAddressTitle.text = address.title
            tvAddressContent.text = address.address
            tvTotalPrice.text = items.map { it.price*it.num }.sum().toString()
            rvCartItemsConfirmed.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = CartCheckoutAdapter(items.toList())
            }
        }
    }
}