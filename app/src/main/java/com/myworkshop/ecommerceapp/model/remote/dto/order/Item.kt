package com.myworkshop.ecommerceapp.model.remote.dto.order

data class Item(
    val amount: String,
    val description: String,
    val product_id: String,
    val product_image_url: String,
    val product_name: String,
    val quantity: String,
    val unit_price: String
)