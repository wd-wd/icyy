package com.lanxiang.netlibrary

/**
 * @auhthor: wangdong
 * @e-mail: wangdong@happygo.com
 * @date: 2021/4/28 9:28 AM
 * @desc:
 *
 */
class ApiException(val errCode: String?, val errMessage: String?) : Exception() {
    constructor(errCode: String, errMessage: String, data: Any) : this(errCode, errMessage)

}