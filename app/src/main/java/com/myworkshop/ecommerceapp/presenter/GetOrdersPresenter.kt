package com.myworkshop.ecommerceapp.presenter

import com.myworkshop.ecommerceapp.model.remote.ResponseCallBack
import com.myworkshop.ecommerceapp.model.remote.dto.order.GetOrdersResult
import com.myworkshop.ecommerceapp.model.remote.util.VolleyHandler

class GetOrdersPresenter(private val volleyHandler: VolleyHandler, private val view:MVPInterfaces.GetOrders.View):MVPInterfaces.GetOrders.Presenter {
    override fun getOrders(userId: String) {
        volleyHandler.getOrdersByUserId(userId, object :ResponseCallBack.GetOrdersCallback{
            override fun getOrdersSuccess(getOrdersResult: GetOrdersResult) {
                view.getOrdersSuccess(getOrdersResult)
            }

            override fun getOrdersFailed(errorMsg: String) {
                view.getOrdersFailed(errorMsg)
            }

        })
    }
}