package com.myworkshop.ecommerceapp.model.remote.util

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.myworkshop.ecommerceapp.model.remote.ResponseCallBack
import com.myworkshop.ecommerceapp.model.remote.dto.login_signup.LoginResult
import org.json.JSONObject

class VolleyHandler(private val context: Context) {

    fun login(userName:String, password:String, responseCallback: ResponseCallBack.LoginResponseCallBack) {
        val requestQueue = Volley.newRequestQueue(context)

        val jsonObject = JSONObject()
        jsonObject.put("email_id", userName)
        jsonObject.put("password", password)

        val request = JsonObjectRequest(Request.Method.POST, LOGIN_URL, jsonObject,
            { response ->
                val loginResult:LoginResult = Gson().fromJson(response.toString(), LoginResult::class.java)
                if(loginResult.status == 0){
                    responseCallback.loginSuccess(loginResult)
                }else{
                    responseCallback.loginFailed(loginResult.message)
                }

            },
            { error ->
                responseCallback.loginFailed(error.toString())
            })

        requestQueue.add(request)
    }


    companion object{
        private const val BASE_URL = "http://192.168.0.12/myshop/index.php/"

        const val LOGIN_URL = BASE_URL + "user/auth"
//        fun getHeader():MutableMap<String, String>{
//            val header = HashMap<String, String>()
//            header["Content-Type"] = "application/json"
//            return header
//        }
    }

}