package com.lanxiang.icyy

import com.alibaba.android.arouter.facade.annotation.Route
import com.lanxiang.comlib.base.BaseActivity
import com.lanxiang.icyy.databinding.TestA

/**
 * @auhthor: wangdong
 * @e-mail: 690931210@qq.com
 * @date: 2021/4/28 6:26 PM
 * @desc:
 *
 */
@Route(path = "/path/test")
class TestActivity : BaseActivity<TestA>() {
    override fun getLayoutId(): Int {
        return R.layout.activity_test
    }
}