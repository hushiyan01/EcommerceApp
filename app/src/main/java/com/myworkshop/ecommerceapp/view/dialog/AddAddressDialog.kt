package com.myworkshop.ecommerceapp.view.dialog

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import com.myworkshop.ecommerceapp.databinding.AddAddressDialogBinding
import com.myworkshop.ecommerceapp.model.local.dao.AddressDao
import com.myworkshop.ecommerceapp.model.local.entity.db.ShoppingDBHelper
import com.myworkshop.ecommerceapp.model.preferences.SharedPref
import com.myworkshop.ecommerceapp.model.remote.dto.address.AddAddressResult
import com.myworkshop.ecommerceapp.model.remote.util.VolleyHandler
import com.myworkshop.ecommerceapp.presenter.AddAddressPresenter
import com.myworkshop.ecommerceapp.presenter.MVPInterfaces

class AddAddressDialog(
    private val context: Context,
    private val refreshCallback:RefreshDeliveryListCallback
) : AlertDialog(context),MVPInterfaces.AddAddress.View {
    private lateinit var binding: AddAddressDialogBinding
    private lateinit var addressDao: AddressDao
    private lateinit var userId: String
    private lateinit var addAddressPresenter: AddAddressPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddAddressDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addressDao = AddressDao(ShoppingDBHelper(context))
        addAddressPresenter = AddAddressPresenter(VolleyHandler(context), this)
        userId = SharedPref.getSecuredSharedPreferences(context)
            .getString("user_id", "") ?: ""
        binding.apply {
            btnSaveAddress.setOnClickListener {
                val title = binding.etAddressType.text.toString()
                val address = binding.etAddressContent.text.toString()
                if(userId.isNotEmpty()){
                    addAddressPresenter.addAddresses(
                        userId = userId,
                        title = title,
                        address = address
                    )
                }else{
                    Toast.makeText(context, "unknown user! please re-login!", Toast.LENGTH_SHORT).show()
                }
                this@AddAddressDialog.dismiss()
            }
            btnCancel.setOnClickListener {
                this@AddAddressDialog.dismiss()
            }
        }

    }

    override fun addSuccess(addAddressResult: AddAddressResult) {
        refreshCallback.refresh(addAddressResult.message)
    }

    override fun addFailed(errorMsg: String) {
        Toast.makeText(context,"add new address remote call failed",Toast.LENGTH_SHORT).show()
    }

}