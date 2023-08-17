package com.myworkshop.ecommerceapp.model.local.entity.po

data class CartItem (
    val id:Long?,
    val userId:String,
    val itemTitle:String,
    val price:Float,
    val img:String,
    val description: String,
    var num:Int = 1
)