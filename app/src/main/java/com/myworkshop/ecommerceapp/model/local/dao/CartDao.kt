package com.myworkshop.ecommerceapp.model.local.dao

import android.content.ContentValues
import android.database.Cursor
import com.myworkshop.ecommerceapp.model.local.entity.db.ShoppingDBHelper
import com.myworkshop.ecommerceapp.model.local.entity.po.CartItem
import com.myworkshop.ecommerceapp.model.local.util.DBConstants

class CartDao(private val dbHelper: ShoppingDBHelper) {

    fun getAllItems(): List<CartItem> {
        val res = ArrayList<CartItem>()
        val cursor = dbHelper.readableDatabase.query(
            DBConstants.TABLE_NAME_CART,
            null,
            null,
            null,
            null,
            null,
            null,
            null
        )
        while (cursor.moveToNext()) {
            val item = getCartFromCursor(cursor)
            res.add(item)
        }
        cursor.close()
        return res
    }

    fun delete(id: String): Int {
        val selection = "id = ?"
        val selectionArgs = arrayOf(id)

        return dbHelper.writableDatabase.delete(
            DBConstants.TABLE_NAME_CART,
            selection,
            selectionArgs
        )
    }

    fun updateById(id:String, change:Int){
        val contentValues = ContentValues()
        contentValues.put("num", "num + $change")
        if(change>0){
            dbHelper.writableDatabase.execSQL("UPDATE cart SET num=num+1 WHERE id = $id")
        }else{
            dbHelper.writableDatabase.execSQL("UPDATE cart SET num=num-1 WHERE id = $id")
        }

        dbHelper.writableDatabase.close()
    }

    fun save(cartItem: CartItem): Long {
        val contentValues = ContentValues()
        contentValues.put("id", cartItem.id)
        contentValues.put("user_id", cartItem.userId)
        contentValues.put("item_title", cartItem.itemTitle)
        contentValues.put("img", cartItem.img)
        contentValues.put("price", cartItem.price)
        contentValues.put("description", cartItem.description)
        contentValues.put("num", cartItem.num)
        return dbHelper.writableDatabase.insert(DBConstants.TABLE_NAME_CART, null, contentValues)
    }

    fun isInCart(id:String):Boolean{
        val selection = "id = ?"
        val selectionArguments = arrayOf(id)

        val cursor = dbHelper.readableDatabase.query(
            DBConstants.TABLE_NAME_CART,
            null,
            selection,
            selectionArguments,
            null,
            null,
            null,
            null
        )
        val res = cursor.moveToNext()
        cursor.close()
        return res
    }

    private fun getCartFromCursor(cursor: Cursor): CartItem {
        cursor.apply {
            val id = getLong(getColumnIndexOrThrow("id"))
            val userId = getString(getColumnIndexOrThrow("user_id"))
            val title = getString(getColumnIndexOrThrow("item_title"))
            val price = getFloat(getColumnIndexOrThrow("price"))
            val img = getString(getColumnIndexOrThrow("img"))
            val description = getString(getColumnIndexOrThrow("description"))
            val num = getInt(getColumnIndexOrThrow("num"))
            return CartItem(
                id = id,
                userId = userId,
                itemTitle = title,
                price = price,
                img = img,
                description = description,
                num = num
            )
        }
    }
}

fun List<String>.toStringWithComma(): String {
    val res = StringBuffer()
    return this.map { it.trim() }.joinToString(separator = ",")
}