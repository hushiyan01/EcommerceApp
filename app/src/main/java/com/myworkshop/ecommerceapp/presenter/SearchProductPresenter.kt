package com.myworkshop.ecommerceapp.presenter

import com.myworkshop.ecommerceapp.model.remote.ResponseCallBack
import com.myworkshop.ecommerceapp.model.remote.dto.product.SearchProductResult
import com.myworkshop.ecommerceapp.model.remote.util.VolleyHandler

class SearchProductPresenter(val volleyHandler: VolleyHandler, val view:MVPInterfaces.SearchProduct.View):MVPInterfaces.SearchProduct.Presenter {
    override fun searchProduct(keyword: String) {
        volleyHandler.searchProduct(keyword, object :ResponseCallBack.SearchProductCallback{
            override fun getOrdersSuccess(searchProductResult: SearchProductResult) {
                view.searchSuccess(searchProductResult)
            }

            override fun getOrdersFailed(errorMsg: String) {
                view.searchFailed(errorMsg)
            }

        })
    }
}