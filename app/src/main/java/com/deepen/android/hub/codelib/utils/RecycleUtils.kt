package com.deepen.android.hub.codelib.utils

import android.graphics.drawable.AnimationDrawable
import com.blankj.utilcode.util.LogUtils

object RecycleUtils {
    private const val TAG = "RecycleUtils"
    fun recycleAnimationDrawable(animationDrawables: AnimationDrawable?) {
        LogUtils.d(TAG, "time start : " + System.currentTimeMillis())
        if (animationDrawables != null) {
            animationDrawables.stop()
            for (i in 0 until animationDrawables.numberOfFrames) {
                var drawable = animationDrawables.getFrame(i)
                drawable!!.callback = null
                drawable = null
            }
            animationDrawables.callback = null
        }
        LogUtils.d(TAG, "time end : " + System.currentTimeMillis())
    }
}