package com.myworkshop.ecommerceapp.model.remote.dto.product

data class Review(
    val full_name: String,
    val rating: String,
    val review: String,
    val review_date: String,
    val review_id: String,
    val review_title: String,
    val user_id: String
)