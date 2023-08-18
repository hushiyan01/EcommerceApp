package com.myworkshop.ecommerceapp.presenter

import com.myworkshop.ecommerceapp.model.remote.ResponseCallBack
import com.myworkshop.ecommerceapp.model.remote.dto.address.AddAddressResult
import com.myworkshop.ecommerceapp.model.remote.util.VolleyHandler

class AddAddressPresenter(
    private val volleyHandler: VolleyHandler,
    private val view: MVPInterfaces.AddAddress.View
) : MVPInterfaces.AddAddress.Presenter {
    override fun addAddresses(userId: String, title: String, address: String) {
        volleyHandler.addAddress(
            userId = userId,
            title= title,
            address=address,
            object : ResponseCallBack.AddAddressCallback{
                override fun getAddressesSuccess(addAddressResult: AddAddressResult) {
                    view.addSuccess(addAddressResult)
                }

                override fun getAddressesFailed(errorMsg: String) {
                    view.addFailed(errorMsg)
                }

            }
        )
    }

}