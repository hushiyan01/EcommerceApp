package com.myworkshop.ecommerceapp.model.remote.util

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.myworkshop.ecommerceapp.model.local.entity.po.CartItem
import com.myworkshop.ecommerceapp.model.remote.ResponseCallBack
import com.myworkshop.ecommerceapp.model.remote.dto.address.AddAddressResult
import com.myworkshop.ecommerceapp.model.remote.dto.address.Address
import com.myworkshop.ecommerceapp.model.remote.dto.address.GetAddressesResult
import com.myworkshop.ecommerceapp.model.remote.dto.category.CategoryResult
import com.myworkshop.ecommerceapp.model.remote.dto.category.SubCategoryResult
import com.myworkshop.ecommerceapp.model.remote.dto.login_signup.LoginResult
import com.myworkshop.ecommerceapp.model.remote.dto.login_signup.RegisterResult
import com.myworkshop.ecommerceapp.model.remote.dto.order.GetOrdersResult
import com.myworkshop.ecommerceapp.model.remote.dto.order.OrderDetailResult
import com.myworkshop.ecommerceapp.model.remote.dto.order.PlaceOrderResult
import com.myworkshop.ecommerceapp.model.remote.dto.product.ProductResult
import com.myworkshop.ecommerceapp.model.remote.dto.product_detail.ProductDetailResult
import org.json.JSONArray
import org.json.JSONObject

class VolleyHandler(private val context: Context) {

    fun userLogin(
        emailId: String,
        password: String,
        loginResponseCallback: ResponseCallBack.LoginResponseCallBack
    ) {
        val requestQueue = Volley.newRequestQueue(context)
        val jsonObject = JSONObject()
        jsonObject.put("email_id", emailId)
        jsonObject.put("password", password)
        val request = JsonObjectRequest(Request.Method.POST, USER_LOGIN_URL, jsonObject,
            { response ->
                val loginResult: LoginResult =
                    Gson().fromJson(response.toString(), LoginResult::class.java)
                if (loginResult.status == 0) {
                    loginResponseCallback.loginSuccess(loginResult)
                } else {
                    loginResponseCallback.loginFailed(loginResult.message)
                }

            },
            { error ->
                loginResponseCallback.loginFailed(error.toString())
            })

        requestQueue.add(request)
    }

    fun userRegister(
        fullName: String,
        mobileNo: String,
        emailId: String,
        password: String,
        registerResponseCallBack: ResponseCallBack.RegisterResponseCallBack
    ) {
        val requestQueue = Volley.newRequestQueue(context)
        val jsonObject = JSONObject()
        jsonObject.put("full_name", fullName)
        jsonObject.put("mobile_no", mobileNo)
        jsonObject.put("email_id", emailId)
        jsonObject.put("password", password)
        val request = JsonObjectRequest(Request.Method.POST, USER_REGISTER_URL, jsonObject,
            { response ->
                val registerResult: RegisterResult =
                    Gson().fromJson(response.toString(), RegisterResult::class.java)
                if (registerResult.status == 0) {
                    registerResponseCallBack.registerSuccess(registerResult)
                } else {
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
                val categoryResult: CategoryResult =
                    Gson().fromJson(response.toString(), CategoryResult::class.java)
                if (categoryResult.status == 0) {
                    fetchCategoryCallBack.fetchCategorySuccess(categoryResult)
                } else {
                    fetchCategoryCallBack.fetchCategoryFailed(categoryResult.message)
                }
            },
            { error ->
                fetchCategoryCallBack.fetchCategoryFailed(error.toString())
            })
        requestQueue.add(request)
    }

    fun fetchSubCategory(
        id: String,
        fetchSubCategoryCallBack: ResponseCallBack.FetchSubCategoryFromCategoryCallback
    ) {
        val requestQueue = Volley.newRequestQueue(context)
        val jsonObject = JSONObject()
        val params = "?category_id=$id"
        val request =
            JsonObjectRequest(Request.Method.GET, FETCH_SUB_CATEGORY_URL + params, jsonObject,
                { response ->
                    val categoryResult: SubCategoryResult =
                        Gson().fromJson(response.toString(), SubCategoryResult::class.java)
                    if (categoryResult.status == 0) {
                        fetchSubCategoryCallBack.fetchSubCategorySuccess(categoryResult)
                    } else {
                        fetchSubCategoryCallBack.fetchSubCategoryFailed(categoryResult.message)
                    }
                },
                { error ->
                    fetchSubCategoryCallBack.fetchSubCategoryFailed(error.toString())
                })
        requestQueue.add(request)
    }

    fun fetchProductBySubCategoryId(
        id: String,
        fetchProductCallBack: ResponseCallBack.FetchProductsFromSubCategoryIdCallBack
    ) {
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

    fun fetchProductDetailById(
        id: String,
        fetchProductsDetailCallBack: ResponseCallBack.FetchProductsDetailCallBack
    ) {
        val requestQueue = Volley.newRequestQueue(context)
        val jsonObject = JSONObject()
        val request =
            JsonObjectRequest(Request.Method.GET, FETCH_PRODUCTS_DETAIL_URL + id, jsonObject,
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

    fun fetchAddressesByUserId(
        id: String,
        getAddressByUserIdCallback: ResponseCallBack.GetAddressByUserIdCallback
    ) {
        val requestQueue = Volley.newRequestQueue(context)
        val jsonObject = JSONObject()
        val request =
            JsonObjectRequest(Request.Method.GET, FETCH_ADDRESSES_URL + id, jsonObject,
                { response ->
                    val getAddressesResult: GetAddressesResult =
                        Gson().fromJson(response.toString(), GetAddressesResult::class.java)
                    if (getAddressesResult.status == 0) {
                        getAddressByUserIdCallback.getAddressesSuccess(getAddressesResult)
                    } else {
                        getAddressByUserIdCallback.getAddressesFailed(getAddressesResult.message)
                    }
                },
                { error ->
                    getAddressByUserIdCallback.getAddressesFailed(error.toString())
                })
        requestQueue.add(request)
    }

    fun addAddress(
        userId:String,
        title:String,
        address:String,
        getAddressCallback: ResponseCallBack.AddAddressCallback
    ) {
        val requestQueue = Volley.newRequestQueue(context)
        val jsonObject = JSONObject()
        jsonObject.put("user_id",userId)
        jsonObject.put("title",title)
        jsonObject.put("address",address)
        val request =
            JsonObjectRequest(Request.Method.POST, ADD_ADDRESS_URL, jsonObject,
                { response ->
                    val addressesResult: AddAddressResult =
                        Gson().fromJson(response.toString(), AddAddressResult::class.java)
                    if (addressesResult.status == 0) {
                        getAddressCallback.getAddressesSuccess(addressesResult)
                    } else {
                        getAddressCallback.getAddressesFailed(addressesResult.message)
                    }
                },
                { error ->
                    getAddressCallback.getAddressesFailed(error.toString())
                })
        requestQueue.add(request)
    }

    fun placeOrder(
        userId: String,
        address: Address,
        items:List<CartItem>,
        payment:String,
        placeOrderCallback: ResponseCallBack.PlaceOrderCallback
    ){
        val requestQueue = Volley.newRequestQueue(context)
        val jsonObject = JSONObject()
        val billAmount = items.map { it.price*it.num }.sum()
        jsonObject.put("user_id",userId)
        jsonObject.put("payment_method",payment)
        jsonObject.put("bill_amount",billAmount)

        val addressObject = JSONObject()
        addressObject.put("title", address.title)
        addressObject.put("address", address.address)

        val itemArray = JSONArray()
        for (item in items) {
            val itemObject = JSONObject()
            itemObject.put("product_id", item.id)
            itemObject.put("quantity", item.num)
            itemObject.put("unit_price", item.price)
            itemArray.put(itemObject)
        }
        jsonObject.put("delivery_address",addressObject)
        jsonObject.put("items", itemArray)
        val request =
            JsonObjectRequest(Request.Method.POST, PLACE_ORDER_URL, jsonObject,
                { response ->
                    val placeOrderResult: PlaceOrderResult =
                        Gson().fromJson(response.toString(), PlaceOrderResult::class.java)
                    if (placeOrderResult.status == 0) {
                        placeOrderCallback.placeSuccess(placeOrderResult)
                    } else {
                        placeOrderCallback.placeFailed(placeOrderResult.message)
                    }
                },
                { error ->
                    placeOrderCallback.placeFailed(error.toString())
                })
        requestQueue.add(request)
    }

    fun getOrdersByUserId(
        id: String,
        getOrdersCallback: ResponseCallBack.GetOrdersCallback
    ) {
        val requestQueue = Volley.newRequestQueue(context)
        val jsonObject = JSONObject()
        val request =
            JsonObjectRequest(Request.Method.GET, GET_ORDERS_URL + id, jsonObject,
                { response ->
                    val getAddressesResult: GetOrdersResult =
                        Gson().fromJson(response.toString(), GetOrdersResult::class.java)
                    if (getAddressesResult.status == 0) {
                        getOrdersCallback.getOrdersSuccess(getAddressesResult)
                    } else {
                        getOrdersCallback.getOrdersFailed(getAddressesResult.message)
                    }
                },
                { error ->
                    getOrdersCallback.getOrdersFailed(error.toString())
                })
        requestQueue.add(request)
    }

    fun getOrderDetail(
        id: String,
        getOrderDetailCallback: ResponseCallBack.GetOrderDetailCallback
    ) {
        val requestQueue = Volley.newRequestQueue(context)
        val jsonObject = JSONObject()
        val request =
            JsonObjectRequest(Request.Method.GET, GET_ORDER_DETAIL_URL + id, jsonObject,
                { response ->
                    val orderDetailResult: OrderDetailResult =
                        Gson().fromJson(response.toString(), OrderDetailResult::class.java)
                    if (orderDetailResult.status == 0) {
                        getOrderDetailCallback.getOrdersSuccess(orderDetailResult)
                    } else {
                        getOrderDetailCallback.getOrdersFailed(orderDetailResult.message)
                    }
                },
                { error ->
                    getOrderDetailCallback.getOrdersFailed(error.toString())
                })
        requestQueue.add(request)
    }

    companion object {
        private const val BASE_URL = "http://192.168.0.12/myshop/index.php/"
        const val USER_LOGIN_URL = BASE_URL + "user/auth"
        const val USER_REGISTER_URL = BASE_URL + "user/register"
        const val FETCH_CATEGORY_URL = BASE_URL + "category"
        const val FETCH_SUB_CATEGORY_URL = BASE_URL + "subcategory"
        const val FETCH_PRODUCTS_URL = BASE_URL + "SubCategory/products/"
        const val FETCH_PRODUCTS_DETAIL_URL = BASE_URL + "product/details/"
        const val FETCH_ADDRESSES_URL = BASE_URL + "user/addresses/"
        const val ADD_ADDRESS_URL = BASE_URL + "user/address"
        const val PLACE_ORDER_URL = BASE_URL + "order"
        const val GET_ORDERS_URL = BASE_URL + "order/userOrders/"
        const val GET_ORDER_DETAIL_URL = BASE_URL + "order?order_id="
        const val FETCH_IMAGE_URL = "http://192.168.0.12/myshop/images/"
    }

}