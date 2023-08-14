package com.myworkshop.ecommerceapp.model.remote.dto.category

data class SubCategoryResult(
    val message: String,
    val status: Int,
    val subcategories: List<Subcategory>
)