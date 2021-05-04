package com.lanxiang.module_detail

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.lanxiang.comlib.TilTokDTO
import com.lanxiang.module_detail.player.CyyPlayer
import com.shuyu.gsyvideoplayer.utils.GSYVideoHelper

/**
 * @auhthor: wangdong
 * @e-mail: 690931210@qq.com
 * @date: 2021/5/3 8:50 AM
 * @desc:
 *
 */
class PlayerAdapter : BaseQuickAdapter<TilTokDTO, BaseViewHolder>(R.layout.item_player) {
    override fun convert(holder: BaseViewHolder, item: TilTokDTO) {
        val player = holder.getView<CyyPlayer>(R.id.player)
        Glide.with(context).load(item.videoDownloadUrl)
            .into(player.coverImage)
        GSYVideoHelper.GSYVideoHelperBuilder()
            .setIsTouchWiget(false)
            .setRotateViewAuto(false)
            .setLockLand(true)
            .setLooping(true)
            .setCacheWithPlay(true)
            .setSeekRatio(1.0f)
            .setAutoFullWithSize(true)
            .setPlayTag(PlayerActivity.PLAYER_TAG)
            .setPlayPosition(holder.adapterPosition)
            .build(player)
        player.setUp(item.videoDownloadUrl, true, "")
        holder.setText(R.id.videoDes,item.title)
    }
}