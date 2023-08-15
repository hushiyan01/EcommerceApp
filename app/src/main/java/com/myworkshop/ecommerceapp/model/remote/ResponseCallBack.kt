package com.myworkshop.ecommerceapp.model.remote

import com.myworkshop.ecommerceapp.model.remote.dto.login_signup.LoginResult
import com.myworkshop.ecommerceapp.model.remote.dto.login_signup.RegisterResult
import com.myworkshop.ecommerceapp.model.remote.dto.category.CategoryResult
import com.myworkshop.ecommerceapp.model.remote.dto.category.SubCategoryResult
import com.myworkshop.ecommerceapp.model.remote.dto.product.ProductResult

interface ResponseCallBack {
    interface LoginResponseCallBack{
        fun loginSuccess(result: LoginResult)
        fun loginFailed(errorMsg:String)
    }

    interface RegisterResponseCallBack{
        fun registerSuccess(registerResult: RegisterResult)
        fun registerFailed(errorMsg: String)
    }

    interface FetchCategoryCallBack{
        fun fetchCategorySuccess(categoryResult: CategoryResult)
        fun fetchCategoryFailed(errorMsg: String)
    }

    interface FetchSubCategoryFromCategoryCallback{
        fun fetchSubCategorySuccess(subCategoryResult: SubCategoryResult)
        fun fetchSubCategoryFailed(errorMsg: String)
    }

    interface FetchProductsFromSubCategoryIdCallBack{
        fun fetchProductsSuccess(productResult: ProductResult)
        fun fetchProductsFailed(errorMsg: String)
    }
}