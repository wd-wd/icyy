package com.lanxiang.comlib.player;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ShareVideoState implements Parcelable {
    public String mPlayTag = "";
    public int mPlayPosition = -22;
    public boolean mLooping = false;
    //当前的播放状态
    public int mCurrentState = -1;
    //当前的播放位置
    public long mCurrentPosition;
    //原来的url
    public String mOriginUrl;

    //转化后的URL
    public String mUrl;
    //是否播边边缓冲
    public boolean mCache = false;
    //缓存路径，可不设置
    public File mCachePath;
    //http request header
    public Map<String, String> mMapHeadData = new HashMap<>();
    public int progress;
    public int secondaryProgress;
    public String totalTimeText;
    public String currentTimeText;
    public boolean forceStop = false;

    @Override
    public String toString() {
        return "ShareVideoState{" +
                "mLooping=" + mLooping +
                ", mCurrentState=" + mCurrentState +
                ", mOriginUrl='" + mOriginUrl + '\'' +
                ", mUrl='" + mUrl + '\'' +
                ", mCache=" + mCache +
                ", mCachePath=" + mCachePath +
                ", mMapHeadData=" + mMapHeadData +
                ", progress=" + progress +
                ", secondaryProgress=" + secondaryProgress +
                ", totalTimeText='" + totalTimeText + '\'' +
                ", currentTimeText='" + currentTimeText + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mPlayTag);
        dest.writeInt(this.mPlayPosition);
        dest.writeByte(this.mLooping ? (byte) 1 : (byte) 0);
        dest.writeInt(this.mCurrentState);
        dest.writeLong(this.mCurrentPosition);
        dest.writeString(this.mOriginUrl);
        dest.writeString(this.mUrl);
        dest.writeByte(this.mCache ? (byte) 1 : (byte) 0);
        dest.writeSerializable(this.mCachePath);
        dest.writeInt(this.mMapHeadData.size());
        for (Map.Entry<String, String> entry : this.mMapHeadData.entrySet()) {
            dest.writeString(entry.getKey());
            dest.writeString(entry.getValue());
        }
        dest.writeInt(this.progress);
        dest.writeInt(this.secondaryProgress);
        dest.writeString(this.totalTimeText);
        dest.writeString(this.currentTimeText);
        dest.writeByte(this.forceStop ? (byte) 1 : (byte) 0);
    }

    public ShareVideoState() {
    }

    protected ShareVideoState(Parcel in) {
        this.mPlayTag = in.readString();
        this.mPlayPosition = in.readInt();
        this.mLooping = in.readByte() != 0;
        this.mCurrentState = in.readInt();
        this.mCurrentPosition = in.readLong();
        this.mOriginUrl = in.readString();
        this.mUrl = in.readString();
        this.mCache = in.readByte() != 0;
        this.mCachePath = (File) in.readSerializable();
        int mMapHeadDataSize = in.readInt();
        this.mMapHeadData = new HashMap<String, String>(mMapHeadDataSize);
        for (int i = 0; i < mMapHeadDataSize; i++) {
            String key = in.readString();
            String value = in.readString();
            this.mMapHeadData.put(key, value);
        }
        this.progress = in.readInt();
        this.secondaryProgress = in.readInt();
        this.totalTimeText = in.readString();
        this.currentTimeText = in.readString();
        this.forceStop = in.readByte() != 0;
    }

    public static final Parcelable.Creator<ShareVideoState> CREATOR = new Parcelable.Creator<ShareVideoState>() {
        @Override
        public ShareVideoState createFromParcel(Parcel source) {
            return new ShareVideoState(source);
        }

        @Override
        public ShareVideoState[] newArray(int size) {
            return new ShareVideoState[size];
        }
    };
}
