package com.myworkshop.ecommerceapp.presenter

import android.content.Context
import com.myworkshop.ecommerceapp.model.local.entity.po.CartItem
import com.myworkshop.ecommerceapp.model.remote.dto.address.AddAddressResult
import com.myworkshop.ecommerceapp.model.remote.dto.address.Address
import com.myworkshop.ecommerceapp.model.remote.dto.address.GetAddressesResult
import com.myworkshop.ecommerceapp.model.remote.dto.category.CategoryResult
import com.myworkshop.ecommerceapp.model.remote.dto.category.SubCategoryResult
import com.myworkshop.ecommerceapp.model.remote.dto.login_signup.LoginResult
import com.myworkshop.ecommerceapp.model.remote.dto.login_signup.RegisterResult
import com.myworkshop.ecommerceapp.model.remote.dto.order.PlaceOrderResult
import com.myworkshop.ecommerceapp.model.remote.dto.product.ProductResult
import com.myworkshop.ecommerceapp.model.remote.dto.product_detail.ProductDetailResult

interface MVPInterfaces {
    interface Splash {
        interface SplashPresenter {
            fun fetchPref(context: Context)
        }

        interface View {
            fun goToIntroActivity()
            fun goToLoginActivity()
            fun goToMainActivity()
        }
    }

    interface Intro {
        interface Presenter {
            fun updatePref()
        }

        interface View {
            fun goToSignUpLogin()
        }
    }

    interface SignIn {
        interface Presenter {
            fun login(userName: String, password: String)
            fun updatePref(
                context: Context,
                fullName: String,
                emailId: String,
                mobileNo: String,
                userId: String
            )
        }

        interface View {
            fun loginSuccess(loginResult: LoginResult)
            fun loginFailed(errorMsg: String)
        }
    }

    interface Register {
        interface Presenter {
            fun register(fullName: String, mobileNo: String, emailId: String, password: String)
        }

        interface View {
            fun registerSuccess(registerResult: RegisterResult)
            fun registerFailed(errorMsg: String)
        }
    }

    interface Category {
        interface Presenter {
            fun fetchAllCategories()
        }

        interface View {
            fun fetchSuccess(categoryResult: CategoryResult)
            fun fetchFailed(errorMsg: String)
        }
    }

    interface SubCategory {
        interface Presenter {
            fun fetchSubCategoriesById(categoryId: String)
        }

        interface View {
            fun fetchSuccess(subCategoryResult: SubCategoryResult)
            fun fetchFailed(errorMsg: String)
        }
    }

    interface Product {
        interface Presenter {
            fun fetchProductsBySubCategoryId(subCategoryId: String)
        }

        interface View {
            fun fetchSuccess(productResult: ProductResult)
            fun fetchFailed(errorMsg: String)
        }
    }

    interface ProductDetail {
        interface Presenter {
            fun fetchProductDetailById(productId: String)
        }

        interface View {
            fun fetchSuccess(productDetailResult: ProductDetailResult)
            fun fetchFailed(errorMsg: String)
        }
    }

    interface ProductCart {
        interface Presenter {
            fun fetchProductsInCart()
            fun productMinus1(id: String)
            fun productPlus1(id: String)
            fun insertNewItem(cartItem: CartItem)
            fun isInCart(id: String): Boolean
            fun remove(id: String): Int
        }

        interface View {
            fun loadCart(products: List<CartItem>)
        }
    }

    interface GetAddresses{
        interface Presenter{
            fun getAddresses(userId: String)
        }

        interface View{
            fun fetchSuccess(getAddressesResult: GetAddressesResult)
            fun fetchFailed(errorMsg: String)
        }
    }

    interface AddAddress{
        interface Presenter{
            fun addAddresses(userId: String, title:String, address:String)
        }

        interface View{
            fun addSuccess(addAddressResult: AddAddressResult)
            fun addFailed(errorMsg: String)
        }
    }

    interface PlaceOrder{
        interface Presenter{
            fun placeOrder(userId: String, address: Address, items:List<CartItem>, payment:String)
        }

        interface View{
            fun placeSuccess(placeOrderResult: PlaceOrderResult)
            fun placeFailed(errorMsg: String)
        }
    }

}