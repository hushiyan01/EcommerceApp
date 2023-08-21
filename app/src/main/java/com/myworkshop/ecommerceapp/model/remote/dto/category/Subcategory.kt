package com.myworkshop.ecommerceapp.model.remote.dto.category

import android.os.Parcel
import android.os.Parcelable

data class Subcategory(
    val category_id: String,
    val is_active: String,
    val subcategory_id: String,
    val subcategory_image_url: String,
    val subcategory_name: String
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readString()?:""
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(category_id)
        parcel.writeString(is_active)
        parcel.writeString(subcategory_id)
        parcel.writeString(subcategory_image_url)
        parcel.writeString(subcategory_name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Subcategory> {
        override fun createFromParcel(parcel: Parcel): Subcategory {
            return Subcategory(parcel)
        }

        override fun newArray(size: Int): Array<Subcategory?> {
            return arrayOfNulls(size)
        }
    }
}