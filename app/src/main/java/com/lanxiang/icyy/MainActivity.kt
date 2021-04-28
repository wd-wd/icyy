package com.lanxiang.icyy

import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.lanxiang.icyy.base.BaseActivity
import com.lanxiang.icyy.databinding.TestDataBinding
import com.lanxiang.icyy.dto.TestDTO

class MainActivity : BaseActivity<TestDataBinding>() {
    private val mainModel: MainModel by viewModels()
    override fun getLayoutId(): Int = R.layout.activity_main

    override fun doDataChange() {
        mainModel.getList()
        mainModel.homeRecommendDTO.observe(this, Observer {
            binding.test = TestDTO(it.toString())
        })

    }

}