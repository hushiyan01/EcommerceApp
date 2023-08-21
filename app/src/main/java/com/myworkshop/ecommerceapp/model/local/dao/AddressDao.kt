package com.myworkshop.ecommerceapp.model.local.dao

import android.content.ContentValues
import android.database.Cursor
import com.myworkshop.ecommerceapp.model.local.entity.db.ShoppingDBHelper
import com.myworkshop.ecommerceapp.model.local.entity.po.AddressView
import com.myworkshop.ecommerceapp.model.local.util.DBConstants

class AddressDao(private val dbHelper: ShoppingDBHelper) {

    fun getAllAddress(): List<AddressView> {
        val res = ArrayList<AddressView>()
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

    fun save(addressView: AddressView): Long {
        val contentValues = ContentValues()
        contentValues.put("id", addressView.id)
        contentValues.put("user_id", addressView.userId)
        contentValues.put("type", addressView.type)
        contentValues.put("address", addressView.address)
        return dbHelper.writableDatabase.insert(DBConstants.TABLE_NAME_ADDRESS, null, contentValues)
    }

    private fun getAddressFromCursor(cursor: Cursor): AddressView {
        cursor.apply {
            val id = getLong(getColumnIndexOrThrow("id"))
            val userId = getString(getColumnIndexOrThrow("user_id"))
            val type = getString(getColumnIndexOrThrow("type"))
            val address = getString(getColumnIndexOrThrow("address"))
            return AddressView(
                id = id,
                userId = userId,
                type = type,
                address = address
            )
        }
    }
}
