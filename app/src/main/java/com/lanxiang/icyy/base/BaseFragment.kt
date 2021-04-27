package com.lanxiang.icyy.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 * @auhthor: wangdong
 * @e-mail: wangdong@happygo.com
 * @date: 2021/4/27 4:24 PM
 * @desc:
 *
 */
abstract class BaseFragment<T:ViewDataBinding>:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<T>(inflater, getLayoutId(), container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewModel()
        //初始化事件
        initEven()
        doDataChange()
    }

    open fun initViewModel() {

    }

    open fun initEven() {

    }

    open fun doDataChange() {

    }

    abstract fun getLayoutId(): Int
}