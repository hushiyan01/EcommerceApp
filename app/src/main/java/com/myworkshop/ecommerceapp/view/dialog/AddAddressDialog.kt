package com.myworkshop.ecommerceapp.view.dialog

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import com.myworkshop.ecommerceapp.databinding.AddAddressDialogBinding
import com.myworkshop.ecommerceapp.model.local.dao.AddressDao
import com.myworkshop.ecommerceapp.model.local.entity.db.ShoppingDBHelper
import com.myworkshop.ecommerceapp.model.local.entity.po.Address
import com.myworkshop.ecommerceapp.model.preferences.SharedPref

class AddAddressDialog(private val context: Context) : AlertDialog(context) {
    private lateinit var binding: AddAddressDialogBinding
    private lateinit var addressDao: AddressDao
    private lateinit var userId: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddAddressDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addressDao = AddressDao(ShoppingDBHelper(context))
        userId = SharedPref.getSecuredSharedPreferences(context)
            .getString("logged_in_email_id", "unknown_user") ?: "unknown_user"
        binding.apply {
            btnSaveAddress.setOnClickListener {
                val type = binding.etAddressType.text.toString()
                val address = binding.etAddressContent.text.toString()
                addressDao.save(
                    Address(
                        id = null,
                        type = type,
                        address = address,
                        userId,
                    )
                )
                this@AddAddressDialog.dismiss()
            }
            btnCancel.setOnClickListener {
                this@AddAddressDialog.dismiss()
            }
        }

    }

}