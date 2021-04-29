package com.lanxiang.icyy

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.lanxiang.comlib.BaseApplication
import com.lanxiang.netlibrary.RetrofitManager

/**
 * @auhthor: wangdong
 * @e-mail: wangdong@happygo.com
 * @date: 2021/4/27 6:27 PM
 * @desc:
 *
 */
class App : BaseApplication() {

    override fun onCreate() {
        super.onCreate()

        RetrofitManager.retrofitManager.init(MyIntercept())
    }
}