package com.myworkshop.ecommerceapp.presenter

import com.myworkshop.ecommerceapp.model.remote.ResponseCallBack
import com.myworkshop.ecommerceapp.model.remote.dto.address.GetAddressesResult
import com.myworkshop.ecommerceapp.model.remote.util.VolleyHandler

class GetAddressPresenter(
    private val volleyHandler: VolleyHandler,
    private val view: MVPInterfaces.GetAddresses.View
) : MVPInterfaces.GetAddresses.Presenter {
    override fun getAddresses(userId: String) {
        volleyHandler.fetchAddressesByUserId(
            userId,
            object : ResponseCallBack.GetAddressByUserIdCallback {
                override fun getAddressesSuccess(getAddressesResult: GetAddressesResult) {
                    view.fetchSuccess(getAddressesResult)
                }

                override fun getAddressesFailed(errorMsg: String) {
                    view.fetchFailed(errorMsg)
                }
            })
    }
}