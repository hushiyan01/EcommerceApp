package com.myworkshop.ecommerceapp.presenter

import com.myworkshop.ecommerceapp.model.remote.ResponseCallBack
import com.myworkshop.ecommerceapp.model.remote.dto.category.CategoryResult
import com.myworkshop.ecommerceapp.model.remote.util.VolleyHandler

class CategoryPresenter(private val volleyHandler: VolleyHandler, private val view: MVPInterfaces.Category.View):MVPInterfaces.Category.Presenter {
    override fun fetchAllCategories() {
        volleyHandler.fetchCategory(object : ResponseCallBack.FetchCategoryCallBack{
            override fun fetchCategorySuccess(categoryResult: CategoryResult) {
                view.fetchSuccess(categoryResult)
            }

            override fun fetchCategoryFailed(errorMsg: String) {
                view.fetchFailed(errorMsg)
            }

        })
    }
}