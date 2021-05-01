package com.lanxiang.module_find

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.lanxiang.comlib.Const
import com.lanxiang.comlib.Const.Home.FIND_FRAG
import com.lanxiang.comlib.base.BaseFragment
import com.lanxiang.module_find.databinding.FindFragmentBinding
import kotlinx.android.synthetic.main.fragment_find.*

/**
 * @auhthor: wangdong
 * @e-mail: 690931210@qq.com
 * @date: 2021/5/1 11:38 AM
 * @desc:
 *
 */
@Route(path = FIND_FRAG)
class FindFragment : BaseFragment<FindFragmentBinding>() {
    private val findAdapter: FindAdapter = FindAdapter()
    private val findVM:FindVM by viewModels()
    override fun getLayoutId(): Int {
        return R.layout.fragment_find
    }

    override fun initView() {
        findRv.layoutManager = GridLayoutManager(context,2)
        findRv.adapter =findAdapter
    }

    override fun doDataChange() {
        findVM.getList()
        findVM.tikTokDTO.observe(this, Observer {
            findAdapter.setNewInstance(it)
        })
    }
}