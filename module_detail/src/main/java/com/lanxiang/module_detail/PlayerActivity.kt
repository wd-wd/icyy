package com.lanxiang.module_detail

import com.alibaba.android.arouter.facade.annotation.Route
import com.bumptech.glide.Glide
import com.lanxiang.comlib.base.BaseActivity
import com.lanxiang.module_detail.databinding.PlayerDataBinding
import com.shuyu.gsyvideoplayer.utils.GSYVideoHelper
import kotlinx.android.synthetic.main.activity_player.*

/**
 * @auhthor: wangdong
 * @e-mail: 690931210@qq.com
 * @date: 2021/5/2 10:22 AM
 * @desc:
 *
 */
@Route(path = "/player/player")
class PlayerActivity:BaseActivity<PlayerDataBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.activity_player
    }

    override fun initView() {
        val string = "https://aweme.snssdk.com/aweme/v1/play/?video_id=v0200fc40000bnjp049evctvb2f04l90&line=0&ratio=480p&watermark=1&media_type=4&vr_type=0&improve_bitrate=0&logo_name=aweme"
        Glide.with(this).load(string)
            .into(player.coverImage)
//        videoView.loadCoverImage(item?.url)
        GSYVideoHelper.GSYVideoHelperBuilder()
            .setIsTouchWiget(false)
            .setRotateViewAuto(false)
            .setLockLand(true)
            .setLooping(true)
            .setCacheWithPlay(true)
            .setSeekRatio(1.0f)
            .setAutoFullWithSize(true)

            .build(player)
        player.setUp(string, true, "")
        player.startPlayLogic()

        videoDes.text = "卢卡斯打了卡多拉斯柯达"
    }

    override fun onPause() {
        super.onPause()
        player.onVideoPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }
}