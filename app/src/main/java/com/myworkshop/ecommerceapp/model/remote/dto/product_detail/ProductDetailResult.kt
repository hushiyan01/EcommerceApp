package com.myworkshop.ecommerceapp.model.remote.dto.product_detail

data class ProductDetailResult(
    val message: String,
    val product: Product,
    val status: Int
)