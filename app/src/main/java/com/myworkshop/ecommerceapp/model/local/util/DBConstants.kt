package com.myworkshop.ecommerceapp.model.local.util

object DBConstants {
    const val TABLE_NAME_CART = "cart"
    const val TABLE_NAME_ADDRESS = "address"
    const val DB_NAME = "shopping"
    const val DB_VERSION = 1

    val CREATE_TABLE_CART = """create table $TABLE_NAME_CART(
        id integer primary key autoincrement,
        user_id text,
        item_title text,
        img text,
        price float,
        description text,
        num integer
    )
    """.trimMargin()

    val CREATE_TABLE_ADDRESS = """create table $TABLE_NAME_ADDRESS(
        id integer primary key autoincrement,
        user_id text,
        type text,
        address text
    )
    """.trimMargin()
}