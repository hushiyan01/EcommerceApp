package com.myworkshop.ecommerceapp.presenter

import com.myworkshop.ecommerceapp.model.local.entity.po.CartItem
import com.myworkshop.ecommerceapp.model.remote.ResponseCallBack
import com.myworkshop.ecommerceapp.model.remote.dto.address.Address
import com.myworkshop.ecommerceapp.model.remote.dto.order.PlaceOrderResult
import com.myworkshop.ecommerceapp.model.remote.util.VolleyHandler

class PlaceOrderPresenter(
    private val volleyHandler: VolleyHandler,
    private val view: MVPInterfaces.PlaceOrder.View
) : MVPInterfaces.PlaceOrder.Presenter {
    override fun placeOrder(
        userId: String,
        address: Address,
        items: List<CartItem>,
        payment: String
    ) {
        volleyHandler.placeOrder(
            userId,
            address,
            items,
            payment,
            object : ResponseCallBack.PlaceOrderCallback {
                override fun placeSuccess(placeOrderResult: PlaceOrderResult) {
                    view.placeSuccess(placeOrderResult)
                }

                override fun placeFailed(errorMsg: String) {
                    view.placeFailed(errorMsg)
                }
            })
    }
}