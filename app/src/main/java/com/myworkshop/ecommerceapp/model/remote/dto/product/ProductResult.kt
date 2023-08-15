package com.myworkshop.ecommerceapp.model.remote.dto.product

data class ProductResult(
    val message: String,
    val products: List<Product>,
    val status: Int
)