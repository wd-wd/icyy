package com.lanxiang.comlib.player;

import android.content.Context;
import android.media.AudioManager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.shuyu.gsyvideoplayer.utils.Debuger;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

/**
 * Created by xxh on 2020/10/15.
 * 文件描述：
 * 修改记录：
 */
public class IPlayer extends StandardGSYVideoPlayer {
    private final static String TAG = "CloneableVideoPlayer";

    public IPlayer(Context context, Boolean fullFlag) {
        super(context, fullFlag);
    }

    public IPlayer(Context context) {
        super(context);
    }

    public IPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean forceStop =  false;

    public long recordPosition  = 0;
    @Override
    protected void init(Context context) {
        super.init(context);
        setViewShowState(mBottomContainer, VISIBLE);
        post(new Runnable() {
            @Override
            public void run() {
                gestureDetector = new GestureDetector(getContext().getApplicationContext(), new GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public boolean onDoubleTap(MotionEvent e) {
                        return super.onDoubleTap(e);
                    }

                    @Override
                    public boolean onSingleTapConfirmed(MotionEvent e) {
                        if (getGSYVideoManager().isPlaying()){
                            onVideoPause();
                        }else {
                            onVideoResume(false);
                        }
                        return super.onSingleTapConfirmed(e);
                    }

                    @Override
                    public void onLongPress(MotionEvent e) {
                        super.onLongPress(e);
                    }
                });
            }
        });
        onAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
            @Override
            public void onAudioFocusChange(int focusChange) {
                switch (focusChange) {
                    case AudioManager.AUDIOFOCUS_GAIN:
                        break;
                    case AudioManager.AUDIOFOCUS_LOSS:
                        //todo 判断如果不是外界造成的就不处理
                        break;
                    case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                        //todo 判断如果不是外界造成的就不处理
                        break;
                    case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                        break;
                }
            }
        };
    }

    public void setSurfaceToPlay() {
        addTextureView();
        getGSYVideoManager().setListener(this);
        setStateAndUi(mCurrentState);
    }

    public void recordCurrentPosition(){
        Log.e("tag","record Pos:"+mCurrentPosition);
        recordPosition =  mCurrentPosition;
    }

    @Override
    protected int getFullId() {
        return CustomManager.FULLSCREEN_ID;
    }

    @Override
    protected int getSmallId() {
        return CustomManager.SMALL_ID;
    }

    @Override
    public void addTextureView() {
        super.addTextureView();
    }

    public ShareVideoState saveState() {
        ShareVideoState shareVideoState = new ShareVideoState();
        shareVideoState.mPlayTag = mPlayTag;
        shareVideoState.mPlayPosition = mPlayPosition;
        shareVideoState.mLooping = mLooping;
        shareVideoState.mCurrentState = mCurrentState;
        if( recordPosition!=0) {
            shareVideoState.mCurrentPosition = recordPosition;
        }else {
            shareVideoState.mCurrentPosition = mCurrentPosition;
        }

        shareVideoState.mOriginUrl = mOriginUrl;
        shareVideoState.mUrl = mUrl;
        shareVideoState.mCache = mCache;
        shareVideoState.mCachePath = mCachePath;
        shareVideoState.mMapHeadData = mMapHeadData;
        if (mProgressBar != null) {
            shareVideoState.progress = mProgressBar.getProgress();
            shareVideoState.secondaryProgress = mProgressBar.getSecondaryProgress();
        }
        if (mTotalTimeTextView != null) {
            shareVideoState.totalTimeText = mTotalTimeTextView.getText().toString();
        }
        if (mCurrentTimeTextView != null) {
            shareVideoState.currentTimeText = mCurrentTimeTextView.getText().toString();
        }
        shareVideoState.forceStop = forceStop;
        return shareVideoState;
    }

    public void cloneState(ShareVideoState shareVideoState) {
        this.mPlayTag = shareVideoState.mPlayTag;
        this.mPlayPosition = shareVideoState.mPlayPosition;
        this.mLooping = shareVideoState.mLooping;
        this.mCurrentState = shareVideoState.mCurrentState;
        this.mOriginUrl = shareVideoState.mOriginUrl;
        this.mUrl = shareVideoState.mUrl;
        this.mCache = shareVideoState.mCache;
        this.mCachePath = shareVideoState.mCachePath;
        this.mMapHeadData = shareVideoState.mMapHeadData;
        this.mCurrentPosition = shareVideoState.mCurrentPosition;
        if (mProgressBar != null && shareVideoState.progress != 0) {
            mProgressBar.setProgress(shareVideoState.progress);
            mProgressBar.setSecondaryProgress(shareVideoState.secondaryProgress);
        }
        if (mTotalTimeTextView != null && shareVideoState.totalTimeText != null) {
            mTotalTimeTextView.setText(shareVideoState.totalTimeText);
        }
        if (mCurrentTimeTextView != null && shareVideoState.currentTimeText != null) {
            mCurrentTimeTextView.setText(shareVideoState.currentTimeText);
        }
    }

    public String getKey() {
        if (mPlayPosition == -22) {
            Debuger.printfError(getClass().getSimpleName() + " used getKey() " + "******* PlayPosition never set. ********");
        }
        if (TextUtils.isEmpty(mPlayTag)) {
            Debuger.printfError(getClass().getSimpleName() + " used getKey() " + "******* PlayTag never set. ********");
        }
        return TAG + mPlayTag;
    }
    /**
     * 显示比例
     * 注意，GSYVideoType.setShowType是全局静态生效，除非重启APP。
     */
    public void resolveTypeUI() {
        if (!mHadPlay) {
            return;
        }
        changeTextureViewShowType();
        if (mTextureView != null)
            mTextureView.requestLayout();
    }

    protected void resolveUIState(int state) {
        switch (state) {
            case CURRENT_STATE_NORMAL:
                changeUiToNormal();
                setViewShowState(mBottomContainer, VISIBLE);
                cancelDismissControlViewTimer();
                break;
            case CURRENT_STATE_PREPAREING:
                changeUiToPreparingShow();
                setViewShowState(mLoadingProgressBar, INVISIBLE);
                startDismissControlViewTimer();
                break;
            case CURRENT_STATE_PLAYING:
                cancelDismissControlViewTimer();
                hideAllWidget();
                setViewShowState(mBottomContainer, VISIBLE);
                break;
            case CURRENT_STATE_PAUSE:
                changeUiToPauseShow();
                cancelDismissControlViewTimer();
                break;
            case CURRENT_STATE_ERROR:
                changeUiToError();
                setViewShowState(mBottomContainer, VISIBLE);
                break;
            case CURRENT_STATE_AUTO_COMPLETE:
                changeUiToCompleteShow();
                cancelDismissControlViewTimer();
                break;
            case CURRENT_STATE_PLAYING_BUFFERING_START:
                changeUiToPlayingBufferingShow();
                setViewShowState(mLoadingProgressBar, INVISIBLE);
                break;
        }
    }
}
