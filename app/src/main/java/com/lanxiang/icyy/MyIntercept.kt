package com.lanxiang.icyy

import okhttp3.Interceptor
import okhttp3.Response
import org.json.JSONObject
import java.net.URLEncoder

/**
 * @auhthor: wangdong
 * @e-mail: wangdong@happygo.com
 * @date: 2021/4/27 6:29 PM
 * @desc:
 *
 */
class MyIntercept : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        // get token
        val originalRequest = chain.request()
        var dInfo: String? = ""
        try {
            val jsonObject = JSONObject()
            jsonObject.put("clientType", 1)
            //            jsonObject.put("bizChannel", "1");
            jsonObject.put("version", BuildConfig.VERSION_NAME)
            dInfo = URLEncoder.encode(jsonObject.toString(), "utf-8")
        } catch (e: Exception) {
        }
        val token: String = ""

        // get new request, add request header

        // get new request, add request header
        val builder = originalRequest.newBuilder()
            .header("dInfo", dInfo)
        //取消长链接
//              builder.header("Connection", "close")
        //取消长链接
//              builder.header("Connection", "close")
        if (null != token && !token.isEmpty()) {
            builder.header("token", token)
        }
        val updateRequest = builder.build()
        return chain.proceed(updateRequest)
    }
}