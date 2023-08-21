package com.myworkshop.ecommerceapp.model.remote.dto.order

data class OrderDetailResult(
    val message: String,
    val order: OrderX,
    val status: Int
)