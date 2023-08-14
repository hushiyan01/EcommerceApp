package com.myworkshop.ecommerceapp.model.remote.util

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.myworkshop.ecommerceapp.model.remote.ResponseCallBack
import com.myworkshop.ecommerceapp.model.remote.dto.login_signup.LoginResult
import com.myworkshop.ecommerceapp.model.remote.dto.login_signup.RegisterResult
import com.myworkshop.ecommerceapp.model.remote.dto.search.CategoryResult
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


    companion object{
        private const val BASE_URL = "http://192.168.0.12/myshop/index.php/"

        const val USER_LOGIN_URL = BASE_URL + "user/auth"
        const val USER_REGISTER_URL = BASE_URL + "user/register"
        const val FETCH_CATEGORY_URL = BASE_URL + "category"

//        fun getHeader():MutableMap<String, String>{
//            val header = HashMap<String, String>()
//            header["Content-Type"] = "application/json"
//            return header
//        }
    }

}