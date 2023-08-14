package com.myworkshop.ecommerceapp.model.remote.dto.search

data class CategoryResult(
    val categories: List<Category>,
    val message: String,
    val status: Int
)