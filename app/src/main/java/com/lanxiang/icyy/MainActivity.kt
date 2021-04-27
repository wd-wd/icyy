package com.lanxiang.icyy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.lanxiang.comlib.StringUtils
import com.lanxiang.icyy.base.BaseActivity
import com.lanxiang.icyy.databinding.TestDataBinding
import com.lanxiang.icyy.dto.TestDTO

class MainActivity : BaseActivity<TestDataBinding>() {
    override fun getLayoutId(): Int = R.layout.activity_main
    override fun doDataChange() {
        binding.test = TestDTO(StringUtils.getString())
    }

}