package com.lanxiang.icyy

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
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
        //ARoute 初始化
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
        RetrofitManager.retrofitManager.init(MyIntercept())
    }
}