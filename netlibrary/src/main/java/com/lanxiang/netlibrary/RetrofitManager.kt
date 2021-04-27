package com.lanxiang.netlibrary

import com.lanxiang.comlib.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @auhthor: wangdong
 * @e-mail: wangdong@happygo.com
 * @date: 2021/4/27 4:51 PM
 * @desc:
 *
 */
class RetrofitManager private constructor(private val interceptor: Interceptor) {
    companion object {
        private const val CONNECT_TIMEOUT = 20L
        private const val READ_TIMEOUT = 20L
        private const val WRITE_TIMEOUT = 20L
        const val BASE_URL = ""

        @Volatile
        var instance: RetrofitManager? = null
        fun getInstance(interceptor: Interceptor): RetrofitManager? {
            if (instance == null) {
                synchronized(RetrofitManager::class) {
                    if (instance == null) {
                        instance = RetrofitManager(interceptor)
                    }
                }
            }
            return instance
        }
    }

    private fun getOkHttpClient(interceptor: Interceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder //                .cache(cache)
            .connectTimeout(
                CONNECT_TIMEOUT,
                TimeUnit.SECONDS
            )
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(
                WRITE_TIMEOUT,
                TimeUnit.SECONDS
            ) //                .cookieJar(cookieJar)
            .retryOnConnectionFailure(true)
//            .dns(MyDNS())
            .addNetworkInterceptor(interceptor)
        //设置http 日志拦截
        if (BuildConfig.DEBUG) {
            //使用统一日志管理

            //使用统一日志管理
//            val httpLogging = HttpLoggingInterceptor { message ->
//                try {
//
//                } catch (e: IllegalArgumentException) {
//                } catch (e: Exception) {
//                }
//            }
//            httpLogging.setLevel(HttpLoggingInterceptor.Level.BODY)
//            builder.addNetworkInterceptor(httpLogging)
//            builder.addNetworkInterceptor(StethoInterceptor())
        }
        return builder.build()
    }

    fun <T> create(clazz: Class<T>): T {
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .client(getOkHttpClient(interceptor))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        return retrofit.create(clazz)
    }
}