package com.lanxiang.netlibrary

/**
 * @auhthor: wangdong
 * @e-mail: wangdong@happygo.com
 * @date: 2021/4/28 8:43 AM
 * @desc:基本数据基类，属性名可根据后台定义来命名
 *
 */
data class BaseDTO<T>(
    val isSuccess: Boolean,
    val code: String,
    val message: String, val data: T
)