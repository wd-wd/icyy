package com.lanxiang.icyy

import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.launcher.ARouter
import com.lanxiang.icyy.base.BaseActivity
import com.lanxiang.icyy.databinding.TestDataBinding
import com.lanxiang.icyy.dto.TestDTO
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : BaseActivity<TestDataBinding>() {
    private val mainModel: MainModel by viewModels()
    override fun getLayoutId(): Int = R.layout.activity_main
    override fun initEven() {

        tv.setOnClickListener {
            try {
                ARouter.getInstance().build("/path/test").navigation()
            }catch (e:Exception){
                Log.e("MainActivity",e.message.toString())
            }

        }
    }
    override fun doDataChange() {
        mainModel.getList()
        mainModel.homeRecommendDTO.observe(this, Observer {
            binding.test = TestDTO(it.toString())
        })

    }

}