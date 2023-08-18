package com.myworkshop.ecommerceapp.model.remote.dto.product

import android.os.Parcel
import android.os.Parcelable

data class Product(
    val average_rating: String,
    val category_id: String,
    val category_name: String,
    val description: String,
    val price: String,
    val product_id: String,
    val product_image_url: String,
    val product_name: String,
    val sub_category_id: String,
    val subcategory_name: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel, p1: Int) {
        p0.writeString(average_rating)
        p0.writeString(category_id)
        p0.writeString(category_name)
        p0.writeString(description)
        p0.writeString(price)
        p0.writeString(product_id)
        p0.writeString(product_image_url)
        p0.writeString(product_name)
        p0.writeString(sub_category_id)
        p0.writeString(subcategory_name)
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }
}