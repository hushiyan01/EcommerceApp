package com.myworkshop.ecommerceapp.view.fragment.cart

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.myworkshop.ecommerceapp.R
import com.myworkshop.ecommerceapp.databinding.FragmentSummaryBinding
import com.myworkshop.ecommerceapp.model.local.dao.CartDao
import com.myworkshop.ecommerceapp.model.local.entity.db.ShoppingDBHelper
import com.myworkshop.ecommerceapp.model.local.entity.po.CartItem
import com.myworkshop.ecommerceapp.model.preferences.SharedPref
import com.myworkshop.ecommerceapp.model.remote.dto.address.Address
import com.myworkshop.ecommerceapp.model.remote.dto.order.PlaceOrderResult
import com.myworkshop.ecommerceapp.model.remote.util.VolleyHandler
import com.myworkshop.ecommerceapp.presenter.MVPInterfaces
import com.myworkshop.ecommerceapp.presenter.PlaceOrderPresenter
import com.myworkshop.ecommerceapp.view.adapter.CartCheckoutAdapter
import com.myworkshop.ecommerceapp.view.fragment.checkout.PlaceOrderFragment
import com.myworkshop.ecommerceapp.view.fragment.checkout.UpdateCheckoutInfo

class SummaryFragment(private val updateCheckoutInfo: UpdateCheckoutInfo) : Fragment(),
    MVPInterfaces.PlaceOrder.View {
    private lateinit var binding: FragmentSummaryBinding
    private var cartItems: List<CartItem>? = null
    private lateinit var fragmentManager: FragmentManager
    private lateinit var placeOrderPresenter: PlaceOrderPresenter
    private lateinit var cartDao: CartDao
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSummaryBinding.inflate(inflater, container, false)
        fragmentManager = requireActivity().supportFragmentManager
        placeOrderPresenter = PlaceOrderPresenter(VolleyHandler(requireContext()), this)
        cartDao = CartDao(ShoppingDBHelper(requireContext()))
        return binding.root
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
                if (cartItems != null) {
                    adapter = CartCheckoutAdapter(cartItems!!)
                }
            }
            if (cartItems != null) {
                val totalPrice = cartItems!!.map { it.price*it.num }.sum()
                tvTotalPrice.text = "$ $totalPrice"
            } else {
                tvTotalPrice.text = "$ 0"
            }

            btnPlaceOrder.setOnClickListener {
                val userId = SharedPref.getSecuredSharedPreferences(requireContext())
                    .getString("user_id", "unknown_user")
                if (checkEligible()) {
                    val addressView = updateCheckoutInfo.getDeliverAddress()!!
                    val address = Address(
                        addressView.address,
                        (addressView.id ?: 0L).toString(),
                        addressView.type
                    )
                    val items = updateCheckoutInfo.getCartItems()!!
                    val payment = updateCheckoutInfo.getPaymentMethod()!!
                    placeOrderPresenter.placeOrder(userId!!, address, items, payment)
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Order info is not complete! please check before place your order!",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }
            }
        }
    }

    private fun checkEligible(): Boolean {
        return cartItems != null &&
                updateCheckoutInfo.getPaymentMethod() != null &&
                updateCheckoutInfo.getDeliverAddress() != null
    }

    override fun placeSuccess(placeOrderResult: PlaceOrderResult) {
        val addressView = updateCheckoutInfo.getDeliverAddress()!!
        val address =
            Address(addressView.address, (addressView.id ?: 0L).toString(), addressView.type)
        val items = updateCheckoutInfo.getCartItems()!!
        val payment = updateCheckoutInfo.getPaymentMethod()!!
        val orderId = placeOrderResult.order_id
        items.forEach { cartDao.delete(it.id!!.toString()) }

        val bundle = Bundle()
        bundle.putString("order_id", orderId.toString())
        bundle.putParcelable("address", address)
        bundle.putParcelableArrayList("items", ArrayList(items))
        bundle.putString("payment", payment)
        val placeOrderFragment = PlaceOrderFragment()
        placeOrderFragment.arguments = bundle
        fragmentManager.beginTransaction().replace(R.id.fg_home_container, placeOrderFragment)
            .addToBackStack("place_order_fragment").commit()
    }

    override fun placeFailed(errorMsg: String) {
        Toast.makeText(requireContext(), errorMsg, Toast.LENGTH_SHORT).show()
    }
}