package com.lanxiang.module_detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import kotlinx.android.synthetic.main.activity_detail.*

@Route(path = "/detail/detail")
class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val stringExtra = intent.getStringExtra("position")
        deTv.text = "详情:$stringExtra"
    }
}
