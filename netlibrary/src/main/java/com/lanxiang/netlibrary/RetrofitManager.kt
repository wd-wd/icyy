package com.lanxiang.netlibrary

import com.lanxiang.comlib.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URLEncoder
import java.util.concurrent.TimeUnit

/**
 * @auhthor: wangdong
 * @e-mail: wangdong@happygo.com
 * @date: 2021/4/27 4:51 PM
 * @desc:
 *
 */
class RetrofitManager private constructor() {
    private lateinit var interceptor: Interceptor

    companion object {
        private const val CONNECT_TIMEOUT = 20L
        private const val READ_TIMEOUT = 20L
        private const val WRITE_TIMEOUT = 20L
        const val BASE_URL = "http://baobab.kaiyanapp.com"

        val retrofitManager: RetrofitManager by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            RetrofitManager()
        }
    }

    /**
     * @param interceptor
     * @des 不使用到主项目的内容可以不用
     */
    fun init(interceptor: Interceptor) {
        this.interceptor = interceptor
    }

    private fun getOkHttpClient(): OkHttpClient {
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
            .addNetworkInterceptor(Interceptor {
                // get token
                val originalRequest = it.request()
                try {
                    val jsonObject = JSONObject()
                    jsonObject.put("clientType", 1)
                    //            jsonObject.put("bizChannel", "1");
                    jsonObject.put("version", BuildConfig.VERSION_NAME)
                } catch (e: Exception) {
                }
                val token: String = ""

                // get new request, add request header
                val builder = originalRequest.newBuilder()
                //取消长链接
//              builder.header("Connection", "close")
                if (null != token && !token.isEmpty()) {
                    builder.header("token", token)
                }
                val updateRequest = builder.build()
                it.proceed(updateRequest)
            })
        //设置http 日志拦截
        if (BuildConfig.DEBUG) {
            //使用统一日志管理
            val httpLogging = HttpLoggingInterceptor { message ->
                try {

                } catch (e: IllegalArgumentException) {

                } catch (e: Exception) {
                    
                }
            }
            httpLogging.setLevel(HttpLoggingInterceptor.Level.BODY)
            builder.addNetworkInterceptor(httpLogging)
//            builder.addNetworkInterceptor(StethoInterceptor())
        }
        return builder.build()
    }

    fun <T> create(clazz: Class<T>): T {
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        return retrofit.create(clazz)
    }
}