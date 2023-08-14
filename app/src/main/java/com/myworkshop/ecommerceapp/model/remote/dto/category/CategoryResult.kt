package com.myworkshop.ecommerceapp.model.remote.dto.category

data class CategoryResult(
    val categories: List<Category>,
    val message: String,
    val status: Int
)