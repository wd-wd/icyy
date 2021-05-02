package com.lanxiang.module_find

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.lanxiang.comlib.Const

@Route(path = "/find/find")
class FindActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find)
        val findFragment = FindFragment()
        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.add(R.id.findContent, findFragment).commit()
    }
}
