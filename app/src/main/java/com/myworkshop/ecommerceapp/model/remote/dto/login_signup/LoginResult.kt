package com.myworkshop.ecommerceapp.model.remote.dto.login_signup

data class LoginResult(
    val message: String,
    val status: Int,
    val user: User
)