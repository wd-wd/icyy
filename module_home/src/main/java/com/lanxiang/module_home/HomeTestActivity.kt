package com.lanxiang.module_home

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.lanxiang.comlib.Const
import kotlinx.android.synthetic.main.activity_home_test.*

/**
 * @auhthor: wangdong
 * @e-mail: 690931210@qq.com
 * @date: 2021/4/28 8:21 PM
 * @desc:
 *
 */
@Route(path = Const.Home.HOME_TEST)
class HomeTestActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_test)
        textTv.setOnClickListener {

        }
        textTv.setOnTouchListener { v, event ->
            textTv.performClick()
            true }
    }
}