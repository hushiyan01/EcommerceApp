package com.myworkshop.ecommerceapp.view.fragment.checkout

import com.myworkshop.ecommerceapp.model.local.entity.po.AddressView
import com.myworkshop.ecommerceapp.model.local.entity.po.CartItem

interface UpdateCheckoutInfo {
    fun updateDeliverAddress(deliverAddresses: AddressView)
    fun updatePaymentMethod(paymentMethod:String)
    fun updateCartItems(cartItems:List<CartItem>)

    fun getDeliverAddress(): AddressView?
    fun getPaymentMethod():String?

    fun getCartItems(): List<CartItem>?
}