package com.lanxiang.module_home

import com.bumptech.glide.Glide
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.lanxiang.module_home.dto.Data
import com.lanxiang.module_home.dto.Item
import com.lanxiang.module_home.vo.FeedVO

/**
 * @auhthor: wangdong
 * @e-mail: 690931210@qq.com
 * @date: 2021/4/29 8:53 PM
 * @desc:
 *
 */
class HomeAdapter : BaseQuickAdapter<FeedVO, BaseViewHolder>(R.layout.item_home) {
    override fun convert(holder: BaseViewHolder, item: FeedVO) {
        holder.setText(R.id.title, item.title)
        holder.setText(R.id.description, item.description)
        Glide.with(context).load(item.coverUrl).apply(RequestOptions.bitmapTransform(RoundedCorners(12)))
            .into(holder.getView(R.id.imageView))
        Glide.with(context).load(item.authorUrl)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .into(holder.getView(R.id.author))
    }
}