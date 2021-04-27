package com.lanxiang.netlibrary

import com.lanxiang.comlib.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

/**
 * @auhthor: wangdong
 * @e-mail: wangdong@happygo.com
 * @date: 2021/4/27 5:37 PM
 * @desc:
 *
 */
class OkHttpClientManager private constructor(interceptor: Interceptor) {
    private lateinit var okHttpClient: OkHttpClient

    companion object {


        @Volatile
        var instance: OkHttpClientManager? = null
        fun getInstance(interceptor: Interceptor): OkHttpClientManager? {
            if (instance == null) {
                synchronized(OkHttpClientManager::class) {
                    if (instance == null) {
                        instance = OkHttpClientManager(interceptor)
                    }
                }
            }
            return instance
        }

    }

    init {



    }

    fun getOkHttpClient(): OkHttpClient = okHttpClient
}