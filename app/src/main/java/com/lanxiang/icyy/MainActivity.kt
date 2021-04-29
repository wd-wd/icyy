package com.lanxiang.icyy

import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.launcher.ARouter
import com.lanxiang.comlib.Const
import com.lanxiang.icyy.base.BaseActivity
import com.lanxiang.icyy.databinding.TestDataBinding
import com.lanxiang.icyy.dto.TestDTO
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<TestDataBinding>() {
    private val mainModel: MainModel by viewModels()
    override fun getLayoutId(): Int = R.layout.activity_main
    override fun initEven() {

        tv.setOnClickListener {
            ARouter.getInstance().build(Const.Home.HOME_HOME).navigation()
        }
        button.setOnClickListener {
            ARouter.getInstance().build("/path/test").navigation()

        }
        button1.setOnClickListener {
            ARouter.getInstance().build(Const.Home.HOME_HOME)
                .withString("home","我是app")
                .navigation()
        }
    }

    override fun doDataChange() {
        mainModel.getList()
        mainModel.homeRecommendDTO.observe(this, Observer {
            binding.test = TestDTO(it.toString())
        })

    }

}