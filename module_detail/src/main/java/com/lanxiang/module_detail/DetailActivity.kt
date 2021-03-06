package com.lanxiang.module_detail

import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.google.android.material.snackbar.Snackbar
import com.lanxiang.comlib.MMKVUtils
import com.lanxiang.comlib.base.BaseActivity
import com.lanxiang.module_detail.databinding.DetailBinding
import kotlinx.android.synthetic.main.activity_detail.*

@Route(path = "/detail/detail")
class DetailActivity : BaseActivity<DetailBinding>() {
    override fun getLayoutId(): Int = R.layout.activity_detail
    override fun initView() {
        //测试读取
        val testMMKV = MMKVUtils.decodeString("mmkvTest") ?: ""
        Snackbar.make(deTv,testMMKV,Snackbar.LENGTH_LONG).show()
        val stringExtra = intent.getStringExtra("position")
        deTv.text = "详情:$stringExtra"
    }

    override fun initEven() {
        deTv.setOnClickListener {
            ARouter.getInstance().build("/player/player").navigation()
        }
    }
}
