package com.lanxiang.module_detail

import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.facade.annotation.Route
import com.lanxiang.comlib.base.BaseActivity
import com.lanxiang.comlib.widget.OnViewPagerListener
import com.lanxiang.comlib.widget.ViewPagerLayoutManager
import com.lanxiang.module_detail.databinding.PlayerDataBinding
import com.lanxiang.module_detail.player.CyyPlayer
import com.shuyu.gsyvideoplayer.utils.GSYVideoType
import kotlinx.android.synthetic.main.activity_player.*

/**
 * @auhthor: wangdong
 * @e-mail: 690931210@qq.com
 * @date: 2021/5/2 10:22 AM
 * @desc:
 *
 */
@Route(path = "/player/player")
class PlayerActivity : BaseActivity<PlayerDataBinding>() {
    private var layoutManager: ViewPagerLayoutManager = ViewPagerLayoutManager(this)
    private val playerAdapter: PlayerAdapter = PlayerAdapter()
    private var mCurPos = 0
    private var index = 0
    private val playerVM: PlayerVM by viewModels()

    companion object {
        const val PLAYER_TAG = "VIDEO_LIST"
    }


    override fun getLayoutId(): Int {
        return R.layout.activity_player
    }

    override fun initView() {
        GSYVideoType.setShowType(GSYVideoType.SCREEN_TYPE_DEFAULT)
        index = intent.getIntExtra("currentPosition", 0)
        playerRv.layoutManager = layoutManager
        playerRv.adapter = playerAdapter
        playerRv.post {
            playerRv.scrollToPosition(index)
        }
//        playerRv.smoothScrollToPosition(index)
    }

    override fun initEven() {
        layoutManager.setOnViewPagerListener(object : OnViewPagerListener {
            override fun onInitComplete() {
                startPlay(index)
            }

            override fun onPageRelease(isNext: Boolean, position: Int) {
                if (mCurPos == position) {
                    getPlayer()?.release()
                }
            }

            override fun onPageSelected(position: Int, isBottom: Boolean) {
                Log.e("layoutManager", "position:$position")
                if (mCurPos == position) return
                startPlay(position)
            }
        })
    }

    override fun doDataChange() {
        playerVM.getList()
        playerVM.tikTokDTO.observe(this, Observer {
            playerAdapter.setNewInstance(it)
            playerAdapter.notifyDataSetChanged()
        })
    }

    private fun startPlay(position: Int) {
        mCurPos = position
        getPlayer()?.playTag = PLAYER_TAG
        getPlayer()?.playPosition = position
        val item = playerAdapter.data[position]
        getPlayer()?.setUp(item.videoDownloadUrl, true, "")
        getPlayer()?.startPlayLogic()
    }

    private fun getPlayer(): CyyPlayer? {
        val childAt = layoutManager.getChildAt(0)
        return childAt?.findViewById(R.id.player)
    }

    override fun onPause() {
        super.onPause()
        getPlayer()?.onVideoPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        getPlayer()?.release()
    }
}