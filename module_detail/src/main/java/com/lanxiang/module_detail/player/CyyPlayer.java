package com.lanxiang.module_detail.player;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lanxiang.comlib.player.IPlayer;
import com.lanxiang.module_detail.R;
import com.shuyu.gsyvideoplayer.utils.GSYVideoType;
import com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer;

/**
 * @auhthor: wangdong
 * @e-mail: 690931210@qq.com
 * @date: 2021/5/2 10:43 AM
 * @desc:
 *
 */
public class CyyPlayer extends IPlayer {

    ImageView mCoverImage;
    String mCoverOriginUrl;

    public ImageView getCoverImage() {
        return mCoverImage;
    }

    /**
     * 1.5.0开始加入，如果需要不同布局区分功能，需要重载
     */
    public CyyPlayer(Context context, Boolean fullFlag) {
        super(context, fullFlag);
    }

    public CyyPlayer(Context context) {
        super(context);
    }

    public CyyPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);

    }

    @Override
    protected void touchDoubleUp(MotionEvent e) {
        super.touchDoubleUp(e);
    }

    @Override
    protected void init(Context context) {
        super.init(context);
        mCoverImage = (ImageView) findViewById(R.id.thumbImage);

        if (mThumbImageViewLayout != null &&
                (mCurrentState == -1 || mCurrentState == CURRENT_STATE_NORMAL || mCurrentState == CURRENT_STATE_ERROR)) {
            mThumbImageViewLayout.setVisibility(VISIBLE);
        }
    }

    public void loadCoverImage(String url) {
        mCoverOriginUrl = url;
        Glide.with(getContext().getApplicationContext())
                .load(getVideoSarDen())
                .into(mCoverImage);
    }

    @Override
    public GSYBaseVideoPlayer startWindowFullscreen(Context context, boolean actionBar, boolean statusBar) {
        GSYBaseVideoPlayer gsyBaseVideoPlayer = super.startWindowFullscreen(context, actionBar, statusBar);
        CyyPlayer cyyPlayer = (CyyPlayer) gsyBaseVideoPlayer;
//        hgVideoView.loadCoverImage(mCoverOriginUrl);
        return cyyPlayer;
    }

    @Override
    protected void touchSurfaceMoveFullLogic(float absDeltaX, float absDeltaY) {
        super.touchSurfaceMoveFullLogic(absDeltaX, absDeltaY);
        //不给触摸快进，如果需要，屏蔽下方代码即可
        mChangePosition = false;

        //不给触摸音量，如果需要，屏蔽下方代码即可
        mChangeVolume = false;

        //不给触摸亮度，如果需要，屏蔽下方代码即可
        mBrightness = false;
    }


    @Override
    public int getLayoutId() {
        return R.layout.cyy_player_view;
    }


    @Override
    protected void updateStartImage() {
        if (mStartButton instanceof ImageView) {
            ImageView imageView = (ImageView) mStartButton;
            if (mCurrentState == CURRENT_STATE_PLAYING) {
                imageView.setVisibility(GONE);
            } else if (mCurrentState == CURRENT_STATE_ERROR) {
                imageView.setVisibility(VISIBLE);
                imageView.setImageResource(R.drawable.icon_video_play);
            } else {
                imageView.setVisibility(VISIBLE);
                imageView.setImageResource(R.drawable.icon_video_play);
            }
        }

    }


    /******************* 下方两个重载方法，在播放开始前不屏蔽封面，不需要可屏蔽 ********************/
    @Override
    public void onSurfaceUpdated(Surface surface) {
        super.onSurfaceUpdated(surface);
        if (mThumbImageViewLayout != null && mThumbImageViewLayout.getVisibility() == VISIBLE) {
            mThumbImageViewLayout.setVisibility(INVISIBLE);
        }
    }

    @Override
    protected void setProgressAndTime(int progress, int secProgress, int currentTime, int totalTime, boolean forceChange) {
        if (progress != 0 || forceChange) mProgressBar.setProgress(progress);
    }

    @Override
    protected void resetProgressAndTime() {
        super.resetProgressAndTime();

    }

    @Override
    protected void setViewShowState(View view, int visibility) {
        if (view == mThumbImageViewLayout && visibility != VISIBLE) {
            return;
        }
        super.setViewShowState(view, visibility);
    }

    @Override
    public void onSurfaceAvailable(Surface surface) {
        super.onSurfaceAvailable(surface);
        if (GSYVideoType.getRenderType() != GSYVideoType.TEXTURE) {
            if (mThumbImageViewLayout != null && mThumbImageViewLayout.getVisibility() == VISIBLE) {
                mThumbImageViewLayout.setVisibility(INVISIBLE);
            }
        }
    }
}
