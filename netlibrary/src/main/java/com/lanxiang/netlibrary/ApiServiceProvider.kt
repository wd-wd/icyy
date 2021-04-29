package com.lanxiang.netlibrary

import com.lanxiang.netlibrary.RetrofitManager

/**
 * @auhthor: wangdong
 * @e-mail: 690931210@qq.com
 * @date: 2021/4/28 1:53 PM
 * @desc:
 *
 */
object ApiServiceProvider {
    private val serviceCache: HashMap<String, Any> by lazy { HashMap<String,Any>() }

    fun <T> provideApiService(clazz: Class<T>): T {
        var cache = serviceCache[clazz.name]
        if (cache == null) {
            cache = RetrofitManager.retrofitManager.create(clazz)
            serviceCache[clazz.name] = cache!!
        }
        return cache as T
    }

    fun clearCache() {
        serviceCache.clear()
    }
}

inline fun <reified T> ApiServiceProvider.getApiService(): T {
    return provideApiService(T::class.java)
}