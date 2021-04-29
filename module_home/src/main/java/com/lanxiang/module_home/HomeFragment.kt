package com.lanxiang.module_home

import com.alibaba.android.arouter.facade.annotation.Route
import com.lanxiang.comlib.Const.Home.HOME_HOME_FRAG
import com.lanxiang.comlib.base.BaseFragment
import com.lanxiang.module_home.databinding.FragmentHomeBinding

/**
 * @auhthor: wangdong
 * @e-mail: 690931210@qq.com
 * @date: 2021/4/29 2:09 PM
 * @desc:
 *
 */
@Route(path = HOME_HOME_FRAG)
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }
}