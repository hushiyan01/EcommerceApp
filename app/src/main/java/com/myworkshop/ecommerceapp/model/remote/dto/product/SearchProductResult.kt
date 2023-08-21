package com.myworkshop.ecommerceapp.model.remote.dto.product

data class SearchProductResult(
    val message: String,
    val product: ProductX,
    val status: Int
)