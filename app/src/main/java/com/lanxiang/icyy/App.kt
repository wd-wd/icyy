package com.lanxiang.icyy

import android.app.Application
import com.lanxiang.netlibrary.RetrofitManager

/**
 * @auhthor: wangdong
 * @e-mail: wangdong@happygo.com
 * @date: 2021/4/27 6:27 PM
 * @desc:
 *
 */
class App : Application() {
    val instance: Application by lazy {
        this
    }

    override fun onCreate() {
        super.onCreate()
        RetrofitManager.retrofitManager.init(MyIntercept())
    }
}