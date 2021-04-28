package com.lanxiang.netlibrary.coroutines

/**
 * @auhthor: wangdong
 * @e-mail: wangdong@happygo.com
 * @date: 2021/4/28 9:38 AM
 * @desc:错误提示
 *
 */

/**
 * 未知错误
 */
const val UNKNOWN = "-1000"

/**
 * 解析错误
 */
const val PARSE_ERROR = "-1001"

/**
 * 网络错误
 */
const val NETWORK_ERROR = "-1002"

const val ERROR_MSG = "网络异常，请稍后再试"
const val ERROR_MSG_TIMEOUT = "网络请求超时，请重试"
const val ERROR_MSG_DISCON = "网络中断，请检查您的网络状态"
const val ERROR_MSG_PARSE = "网络异常(1001)，请稍后再试"
const val ERROR_MSG_UNKNOWN = "网络异常(1002)，请稍后再试"