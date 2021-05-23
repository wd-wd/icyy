package com.lanxiang.comlib

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewConfiguration
import android.widget.ScrollView
import kotlin.math.abs


/**
 * @auhthor: wangdong
 * @e-mail: 690931210@qq.com
 * @date: 2021/5/23 10:17 AM
 * @desc:
 *
 */
class MyS:ScrollView {
    private var downX = 0
    private var downY:Int = 0
    private var mTouchSlop = 0
    constructor(context: Context) : this(context,null)
    constructor(
        context: Context,
        attrs: AttributeSet?
    ) : this(context, attrs,0)

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr){
        mTouchSlop = ViewConfiguration.get(context).scaledTouchSlop
    }
    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        when(ev?.action){
            MotionEvent.ACTION_DOWN->{
                downX = ev.rawX.toInt()
                downY = ev.rawY.toInt()
            }
            MotionEvent.ACTION_MOVE->{
                val moveY = ev.rawY.toInt()
                // 判断是否滑动，若滑动就拦截事件
                if (abs(moveY - downY) > mTouchSlop) {
                    return true
                }
            }
        }
        return super.onInterceptTouchEvent(ev)
    }
}