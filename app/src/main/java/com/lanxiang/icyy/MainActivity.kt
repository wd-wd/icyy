package com.lanxiang.icyy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.lanxiang.comlib.StringUtils
import com.lanxiang.icyy.databinding.TestDataBinding
import com.lanxiang.icyy.dto.TestDTO

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<TestDataBinding>(this, R.layout.activity_main)
        binding.test = TestDTO(StringUtils.getString())
    }
}