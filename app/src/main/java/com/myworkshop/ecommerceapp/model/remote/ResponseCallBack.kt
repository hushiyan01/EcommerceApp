package com.myworkshop.ecommerceapp.model.remote

import com.myworkshop.ecommerceapp.model.remote.dto.address.AddAddressResult
import com.myworkshop.ecommerceapp.model.remote.dto.address.GetAddressesResult
import com.myworkshop.ecommerceapp.model.remote.dto.category.CategoryResult
import com.myworkshop.ecommerceapp.model.remote.dto.category.SubCategoryResult
import com.myworkshop.ecommerceapp.model.remote.dto.login_signup.LoginResult
import com.myworkshop.ecommerceapp.model.remote.dto.login_signup.RegisterResult
import com.myworkshop.ecommerceapp.model.remote.dto.order.GetOrdersResult
import com.myworkshop.ecommerceapp.model.remote.dto.order.OrderDetailResult
import com.myworkshop.ecommerceapp.model.remote.dto.order.PlaceOrderResult
import com.myworkshop.ecommerceapp.model.remote.dto.product.ProductResult
import com.myworkshop.ecommerceapp.model.remote.dto.product.SearchProductResult
import com.myworkshop.ecommerceapp.model.remote.dto.product_detail.ProductDetailResult

interface ResponseCallBack {
    interface LoginResponseCallBack {
        fun loginSuccess(result: LoginResult)
        fun loginFailed(errorMsg: String)
    }

    interface RegisterResponseCallBack {
        fun registerSuccess(registerResult: RegisterResult)
        fun registerFailed(errorMsg: String)
    }

    interface FetchCategoryCallBack {
        fun fetchCategorySuccess(categoryResult: CategoryResult)
        fun fetchCategoryFailed(errorMsg: String)
    }

    interface FetchSubCategoryFromCategoryCallback {
        fun fetchSubCategorySuccess(subCategoryResult: SubCategoryResult)
        fun fetchSubCategoryFailed(errorMsg: String)
    }

    interface FetchProductsFromSubCategoryIdCallBack {
        fun fetchProductsSuccess(productResult: ProductResult)
        fun fetchProductsFailed(errorMsg: String)
    }

    interface FetchProductsDetailCallBack {
        fun fetchProductDetailSuccess(productDetailResult: ProductDetailResult)
        fun fetchProductDetailFailed(errorMsg: String)
    }

    interface GetAddressByUserIdCallback{
        fun getAddressesSuccess(getAddressesResult: GetAddressesResult)
        fun getAddressesFailed(errorMsg: String)
    }

    interface AddAddressCallback{
        fun getAddressesSuccess(addAddressResult: AddAddressResult)
        fun getAddressesFailed(errorMsg: String)
    }

    interface PlaceOrderCallback{
        fun placeSuccess(placeOrderResult: PlaceOrderResult)
        fun placeFailed(errorMsg: String)
    }

    interface GetOrdersCallback{
        fun getOrdersSuccess(getOrdersResult: GetOrdersResult)
        fun getOrdersFailed(errorMsg: String)
    }

    interface GetOrderDetailCallback{
        fun getOrdersSuccess(orderDetailResult: OrderDetailResult)
        fun getOrdersFailed(errorMsg: String)
    }

    interface SearchProductCallback{
        fun getOrdersSuccess(searchProductResult: SearchProductResult)
        fun getOrdersFailed(errorMsg: String)
    }
}