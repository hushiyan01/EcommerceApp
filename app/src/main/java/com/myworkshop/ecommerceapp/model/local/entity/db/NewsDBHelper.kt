package com.myworkshop.ecommerceapp.model.local.entity.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.myworkshop.ecommerceapp.model.local.util.DBConstants
import com.myworkshop.ecommerceapp.model.local.util.DBConstants.CREATE_TABLE_ADDRESS
import com.myworkshop.ecommerceapp.model.local.util.DBConstants.CREATE_TABLE_CART

class ShoppingDBHelper(private val context: Context) : SQLiteOpenHelper(context,
    DBConstants.DB_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE_CART)
        db?.execSQL(CREATE_TABLE_ADDRESS)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }


}