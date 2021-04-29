package com.lanxiang.module_home

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.lanxiang.comlib.Const.Home.HOME_HOME_FRAG
import com.lanxiang.comlib.base.BaseFragment
import com.lanxiang.module_home.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * @auhthor: wangdong
 * @e-mail: 690931210@qq.com
 * @date: 2021/4/29 2:09 PM
 * @desc:
 *
 */
@Route(path = HOME_HOME_FRAG)
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val homeAdapter:HomeAdapter= HomeAdapter()
    private val homeVM:HomeVM by viewModels()
    override fun initView() {
        homeRv.layoutManager = LinearLayoutManager(context)
        homeRv.adapter =homeAdapter
    }
    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun doDataChange() {
        homeVM.getList()
        homeVM.homeRecommendDTO.observe(this, Observer {
            homeAdapter.setNewInstance(it.itemList)
        })
    }

}