package com.lanxiang.module_find

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.lanxiang.comlib.TilTokDTO

/**
 * @auhthor: wangdong
 * @e-mail: 690931210@qq.com
 * @date: 2021/5/1 11:46 AM
 * @desc:
 *
 */
class FindAdapter : BaseQuickAdapter<TilTokDTO, BaseViewHolder>(R.layout.item_find) {
    override fun convert(holder: BaseViewHolder, item: TilTokDTO) {
        Glide.with(context).load(item.coverImgUrl).into(holder.getView(R.id.findIv))
        holder.setText(R.id.findTv,item.filterTitleStr)
    }
}