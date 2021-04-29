package com.lanxiang.module_home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.lanxiang.comlib.Const
import kotlinx.android.synthetic.main.activity_home.*

@Route(path = Const.Home.HOME_HOME)
class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val intentStr = intent.getStringExtra("home") ?: ""
        fas.text = "我是home组件得了app的通讯值：$intentStr"
        fas.setOnClickListener {
            ARouter.getInstance().build(Const.Home.HOME_TEST).navigation()
        }
    }
}
