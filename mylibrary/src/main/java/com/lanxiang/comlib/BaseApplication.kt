package com.lanxiang.comlib

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter

/**
 * @auhthor: wangdong
 * @e-mail: 690931210@qq.com
 * @date: 2021/4/28 7:26 PM
 * @desc:
 *
 */
open class BaseApplication:Application() {
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
    }
    override fun onTerminate() {
        super.onTerminate()
        ARouter.getInstance().destroy()
    }
}