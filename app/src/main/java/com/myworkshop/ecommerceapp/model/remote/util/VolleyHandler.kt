package com.myworkshop.ecommerceapp.model.remote.util

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.myworkshop.ecommerceapp.model.remote.ResponseCallBack
import com.myworkshop.ecommerceapp.model.remote.dto.login_signup.LoginResult
import com.myworkshop.ecommerceapp.model.remote.dto.login_signup.RegisterResult
import com.myworkshop.ecommerceapp.model.remote.dto.category.CategoryResult
import com.myworkshop.ecommerceapp.model.remote.dto.category.SubCategoryResult
import com.myworkshop.ecommerceapp.model.remote.dto.product.ProductResult
import com.myworkshop.ecommerceapp.model.remote.dto.product_detail.ProductDetailResult
import org.json.JSONObject

class VolleyHandler(private val context: Context) {

    fun userLogin(emailId:String, password:String, loginResponseCallback: ResponseCallBack.LoginResponseCallBack) {
        val requestQueue = Volley.newRequestQueue(context)

        val jsonObject = JSONObject()
        jsonObject.put("email_id", emailId)
        jsonObject.put("password", password)

        val request = JsonObjectRequest(Request.Method.POST, USER_LOGIN_URL, jsonObject,
            { response ->
                val loginResult:LoginResult = Gson().fromJson(response.toString(), LoginResult::class.java)
                if(loginResult.status == 0){
                    loginResponseCallback.loginSuccess(loginResult)
                }else{
                    loginResponseCallback.loginFailed(loginResult.message)
                }

            },
            { error ->
                loginResponseCallback.loginFailed(error.toString())
            })

        requestQueue.add(request)
    }

    fun userRegister(fullName:String,mobileNo:String,emailId:String, password:String, registerResponseCallBack: ResponseCallBack.RegisterResponseCallBack) {
        val requestQueue = Volley.newRequestQueue(context)

        val jsonObject = JSONObject()
        jsonObject.put("full_name", fullName)
        jsonObject.put("mobile_no", mobileNo)
        jsonObject.put("email_id", emailId)
        jsonObject.put("password", password)

        val request = JsonObjectRequest(Request.Method.POST, USER_REGISTER_URL, jsonObject,
            { response ->
                val registerResult:RegisterResult = Gson().fromJson(response.toString(), RegisterResult::class.java)
                if(registerResult.status == 0){
                    registerResponseCallBack.registerSuccess(registerResult)
                }else{
                    registerResponseCallBack.registerFailed(errorMsg = registerResult.message)
                }
            },
            { error ->
                registerResponseCallBack.registerFailed(error.toString())
            })

        requestQueue.add(request)
    }

    fun fetchCategory(fetchCategoryCallBack: ResponseCallBack.FetchCategoryCallBack) {
        val requestQueue = Volley.newRequestQueue(context)

        val jsonObject = JSONObject()

        val request = JsonObjectRequest(Request.Method.GET, FETCH_CATEGORY_URL, jsonObject,
            { response ->
                val categoryResult:CategoryResult = Gson().fromJson(response.toString(), CategoryResult::class.java)
                if(categoryResult.status == 0){
                    fetchCategoryCallBack.fetchCategorySuccess(categoryResult)
                }else{
                    fetchCategoryCallBack.fetchCategoryFailed(categoryResult.message)
                }
            },
            { error ->
                fetchCategoryCallBack.fetchCategoryFailed(error.toString())
            })

        requestQueue.add(request)
    }

    fun fetchSubCategory(id:String, fetchSubCategoryCallBack: ResponseCallBack.FetchSubCategoryFromCategoryCallback) {
        val requestQueue = Volley.newRequestQueue(context)

        val jsonObject = JSONObject()
        val params = "?category_id=$id"

        val request = JsonObjectRequest(Request.Method.GET, FETCH_SUB_CATEGORY_URL+params, jsonObject,
            { response ->
                val categoryResult:SubCategoryResult = Gson().fromJson(response.toString(), SubCategoryResult::class.java)
                if(categoryResult.status == 0){
                    fetchSubCategoryCallBack.fetchSubCategorySuccess(categoryResult)
                }else{
                    fetchSubCategoryCallBack.fetchSubCategoryFailed(categoryResult.message)
                }
            },
            { error ->
                fetchSubCategoryCallBack.fetchSubCategoryFailed(error.toString())
            })
        requestQueue.add(request)
    }

    fun fetchProductBySubCategoryId(id:String, fetchProductCallBack: ResponseCallBack.FetchProductsFromSubCategoryIdCallBack) {
        val requestQueue = Volley.newRequestQueue(context)

        val jsonObject = JSONObject()

        val request = JsonObjectRequest(Request.Method.GET, FETCH_PRODUCTS_URL + id, jsonObject,
            { response ->
                val productResult: ProductResult =
                    Gson().fromJson(response.toString(), ProductResult::class.java)
                if (productResult.status == 0) {
                    fetchProductCallBack.fetchProductsSuccess(productResult)
                } else {
                    fetchProductCallBack.fetchProductsFailed(productResult.message)
                }
            },
            { error ->
                fetchProductCallBack.fetchProductsFailed(error.toString())
            })
        requestQueue.add(request)
    }

    fun fetchProductDetailById(id:String, fetchProductsDetailCallBack: ResponseCallBack.FetchProductsDetailCallBack) {
        val requestQueue = Volley.newRequestQueue(context)

        val jsonObject = JSONObject()

        val request = JsonObjectRequest(Request.Method.GET, FETCH_PRODUCTS_DETAIL_URL + id, jsonObject,
            { response ->
                val productDetailResult: ProductDetailResult =
                    Gson().fromJson(response.toString(), ProductDetailResult::class.java)
                if (productDetailResult.status == 0) {
                    fetchProductsDetailCallBack.fetchProductDetailSuccess(productDetailResult)
                } else {
                    fetchProductsDetailCallBack.fetchProductDetailFailed(productDetailResult.message)
                }
            },
            { error ->
                fetchProductsDetailCallBack.fetchProductDetailFailed(error.toString())
            })
        requestQueue.add(request)
    }

    companion object{
        private const val BASE_URL = "http://192.168.0.12/myshop/index.php/"

        const val USER_LOGIN_URL = BASE_URL + "user/auth"
        const val USER_REGISTER_URL = BASE_URL + "user/register"
        const val FETCH_CATEGORY_URL = BASE_URL + "category"
        const val FETCH_SUB_CATEGORY_URL = BASE_URL + "subcategory"
        const val FETCH_PRODUCTS_URL = BASE_URL + "SubCategory/products/"
        const val FETCH_PRODUCTS_DETAIL_URL = BASE_URL + "product/details/"
        const val FETCH_IMAGE_URL = "http://192.168.0.12/myshop/images/"

//        fun getHeader():MutableMap<String, String>{
//            val header = HashMap<String, String>()
//            header["Content-Type"] = "application/json"
//            return header
//        }
    }

}