package com.lanxiang.comlib.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * @auhthor: wangdong
 * @e-mail: wangdong@happygo.com
 * @date: 2021/4/27 2:44 PM
 * @desc:
 *
 */
abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {
    lateinit var binding: T
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<T>(this, getLayoutId())
        initView()
        initViewModel()
        //初始化事件
        initEven()
        doDataChange()
    }

    open fun initView() {

    }

    abstract fun getLayoutId(): Int

    open fun initViewModel() {

    }

    open fun initEven() {

    }

    open fun doDataChange() {

    }
}
