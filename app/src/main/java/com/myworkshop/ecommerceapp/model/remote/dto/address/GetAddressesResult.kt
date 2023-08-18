package com.myworkshop.ecommerceapp.model.remote.dto.address

data class GetAddressesResult(
    val addresses: List<Address>,
    val message: String,
    val status: Int
)