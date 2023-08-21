package com.myworkshop.ecommerceapp.model.remote.dto.order

data class GetOrdersResult(
    val message: String,
    val orders: List<Order>,
    val status: Int
)