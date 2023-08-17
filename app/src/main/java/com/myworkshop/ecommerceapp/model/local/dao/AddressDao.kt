package com.myworkshop.ecommerceapp.model.local.dao

import android.content.ContentValues
import android.database.Cursor
import com.myworkshop.ecommerceapp.model.local.entity.db.ShoppingDBHelper
import com.myworkshop.ecommerceapp.model.local.entity.po.Address
import com.myworkshop.ecommerceapp.model.local.util.DBConstants

class AddressDao(private val dbHelper: ShoppingDBHelper) {

    fun getAllAddress(): List<Address> {
        val res = ArrayList<Address>()
        val cursor = dbHelper.readableDatabase.query(
            DBConstants.TABLE_NAME_ADDRESS,
            null,
            null,
            null,
            null,
            null,
            null,
            null
        )
        while (cursor.moveToNext()) {
            val item = getAddressFromCursor(cursor)
            res.add(item)
        }
        cursor.close()
        return res
    }

    fun delete(id: String): Int {
        val selection = "id = ?"
        val selectionArgs = arrayOf(id)

        return dbHelper.writableDatabase.delete(
            DBConstants.TABLE_NAME_ADDRESS,
            selection,
            selectionArgs
        )
    }

    fun updateById(id:String, change:Int){
//        val contentValues = ContentValues()
//        contentValues.put("num", "num + $change")
//        if(change>0){
//            dbHelper.writableDatabase.execSQL("UPDATE Address SET num=num+1 WHERE id = $id")
//        }else{
//            dbHelper.writableDatabase.execSQL("UPDATE Address SET num=num-1 WHERE id = $id")
//        }
        dbHelper.writableDatabase.close()
    }

    fun save(address: Address): Long {
        val contentValues = ContentValues()
        contentValues.put("id", address.id)
        contentValues.put("user_id", address.userId)
        contentValues.put("type", address.type)
        contentValues.put("address", address.address)
        return dbHelper.writableDatabase.insert(DBConstants.TABLE_NAME_ADDRESS, null, contentValues)
    }

    private fun getAddressFromCursor(cursor: Cursor): Address {
        cursor.apply {
            val id = getLong(getColumnIndexOrThrow("id"))
            val userId = getString(getColumnIndexOrThrow("user_id"))
            val type = getString(getColumnIndexOrThrow("type"))
            val address = getString(getColumnIndexOrThrow("address"))
            return Address(
                id = id,
                userId = userId,
                type = type,
                address = address
            )
        }
    }
}
