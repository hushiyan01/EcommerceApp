package com.myworkshop.ecommerceapp.presenter

import com.myworkshop.ecommerceapp.model.remote.ResponseCallBack
import com.myworkshop.ecommerceapp.model.remote.dto.order.OrderDetailResult
import com.myworkshop.ecommerceapp.model.remote.util.VolleyHandler

class OrderDetailPresenter(val volleyHandler: VolleyHandler, val view:MVPInterfaces.OrderDetail.View):MVPInterfaces.OrderDetail.Presenter {
    override fun getOrderDetail(orderId: String) {
        volleyHandler.getOrderDetail(orderId, object :ResponseCallBack.GetOrderDetailCallback{
            override fun getOrdersSuccess(orderDetailResult: OrderDetailResult) {
                view.getOrderDetailSuccess(orderDetailResult)
            }

            override fun getOrdersFailed(errorMsg: String) {
                view.getOrderDetailFailed(errorMsg)
            }
        })
    }
}