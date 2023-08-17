package com.myworkshop.ecommerceapp.presenter

import com.myworkshop.ecommerceapp.model.remote.ResponseCallBack
import com.myworkshop.ecommerceapp.model.remote.dto.product_detail.ProductDetailResult
import com.myworkshop.ecommerceapp.model.remote.util.VolleyHandler

class ProductDetailPresenter(
    private val id: String,
    private val volleyHandler: VolleyHandler,
    private val view: MVPInterfaces.ProductDetail.View
) : MVPInterfaces.ProductDetail.Presenter {
    override fun fetchProductDetailById(productId: String) {
        volleyHandler.fetchProductDetailById(
            id, object : ResponseCallBack.FetchProductsDetailCallBack {
                override fun fetchProductDetailSuccess(productDetailResult: ProductDetailResult) {
                    view.fetchSuccess(productDetailResult)
                }

                override fun fetchProductDetailFailed(errorMsg: String) {
                    view.fetchFailed(errorMsg)
                }
            })
    }
}