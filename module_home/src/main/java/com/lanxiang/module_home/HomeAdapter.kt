package com.lanxiang.module_home

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.lanxiang.module_home.dto.Data
import com.lanxiang.module_home.dto.Item

/**
 * @auhthor: wangdong
 * @e-mail: 690931210@qq.com
 * @date: 2021/4/29 8:53 PM
 * @desc:
 *
 */
class HomeAdapter:BaseQuickAdapter<Item,BaseViewHolder>(R.layout.item_home) {
    override fun convert(holder: BaseViewHolder, item: Item) {
        holder.setText(R.id.textView,item.data.author?.name)
        holder.setText(R.id.textView2,item.data.title)
//        Glide.with(context).load(item.data.header?.actionUrl?:"").into(holder.getView(R.id.imageView))
    }
}