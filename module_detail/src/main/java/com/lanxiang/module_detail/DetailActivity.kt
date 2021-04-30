package com.lanxiang.module_detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.lanxiang.comlib.base.BaseActivity
import com.lanxiang.module_detail.databinding.DetailBinding
import kotlinx.android.synthetic.main.activity_detail.*

@Route(path = "/detail/detail")
class DetailActivity : BaseActivity<DetailBinding>() {
    override fun getLayoutId(): Int = R.layout.activity_detail
    override fun initView() {
        val stringExtra = intent.getStringExtra("position")
        deTv.text = "详情:$stringExtra"
    }
}
