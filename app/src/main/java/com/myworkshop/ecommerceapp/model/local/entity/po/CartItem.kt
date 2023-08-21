package com.myworkshop.ecommerceapp.model.local.entity.po

import android.os.Parcel
import android.os.Parcelable

data class CartItem(
    val id: Long?,
    val userId: String,
    val itemTitle: String,
    val price: Float,
    val img: String,
    val description: String,
    var num: Int = 1
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readFloat(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(userId)
        parcel.writeString(itemTitle)
        parcel.writeFloat(price)
        parcel.writeString(img)
        parcel.writeString(description)
        parcel.writeInt(num)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CartItem> {
        override fun createFromParcel(parcel: Parcel): CartItem {
            return CartItem(parcel)
        }

        override fun newArray(size: Int): Array<CartItem?> {
            return arrayOfNulls(size)
        }
    }

}