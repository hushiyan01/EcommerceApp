package com.myworkshop.ecommerceapp.model.local.entity.po

data class AddressView(
    val id: Long?,
    val type: String,
    val address: String,
    val userId: String,
    var isSelected: Boolean = false
)
