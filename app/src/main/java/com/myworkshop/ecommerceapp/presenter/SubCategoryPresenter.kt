package com.myworkshop.ecommerceapp.presenter

import com.myworkshop.ecommerceapp.model.remote.ResponseCallBack
import com.myworkshop.ecommerceapp.model.remote.dto.category.CategoryResult
import com.myworkshop.ecommerceapp.model.remote.dto.category.SubCategoryResult
import com.myworkshop.ecommerceapp.model.remote.util.VolleyHandler

class SubCategoryPresenter (private val volleyHandler: VolleyHandler, private val view: MVPInterfaces.SubCategory.View):MVPInterfaces.SubCategory.Presenter {

    override fun fetchSubCategoriesById(categoryId: String) {
        volleyHandler.fetchSubCategory(categoryId, object : ResponseCallBack.FetchSubCategoryFromCategoryCallback{

            override fun fetchSubCategorySuccess(subCategoryResult: SubCategoryResult) {
                view.fetchSuccess(subCategoryResult)
            }

            override fun fetchSubCategoryFailed(errorMsg: String) {
                view.fetchFailed(errorMsg)
            }

        })
    }
}