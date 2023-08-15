package com.myworkshop.ecommerceapp.presenter

import com.myworkshop.ecommerceapp.model.remote.ResponseCallBack
import com.myworkshop.ecommerceapp.model.remote.dto.product.ProductResult
import com.myworkshop.ecommerceapp.model.remote.util.VolleyHandler

class ProductPresenter(
    private val volleyHandler: VolleyHandler,
    private val view: MVPInterfaces.Product.View
) : MVPInterfaces.Product.Presenter{
    override fun fetchProductsBySubCategoryId(subCategoryId: String) {
        volleyHandler.fetchProductBySubCategoryId(subCategoryId, object :ResponseCallBack.FetchProductsFromSubCategoryIdCallBack{
            override fun fetchProductsSuccess(productResult: ProductResult) {
                view.fetchSuccess(productResult)
            }

            override fun fetchProductsFailed(errorMsg: String) {
                view.fetchFailed(errorMsg)
            }

        })
    }

}