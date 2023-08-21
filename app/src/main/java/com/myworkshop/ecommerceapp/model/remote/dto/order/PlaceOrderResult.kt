package com.myworkshop.ecommerceapp.model.remote.dto.order

data class PlaceOrderResult(
    val message: String,
    val order_id: Int,
    val status: Int
)