package com.deepen.android.hub.codelib.view

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator

/**
 * File Name:
 * Description:
 * Author: Shane
 * History: 17-7-21 created
 */
class BezierPathAnimatorView @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {
    private val mPaint: Paint
    private val mPath: Path
    private val ITEM_WAVE_LENGTH = 400
    private var dx = 0
    private var valueAnimator: ValueAnimator? = null
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        mPath.reset()
        val originY = 300
        val halfWaveLen = ITEM_WAVE_LENGTH / 2
        mPath.moveTo(-ITEM_WAVE_LENGTH + dx.toFloat(), originY.toFloat())
        var i = -ITEM_WAVE_LENGTH
        while (i <= width + ITEM_WAVE_LENGTH) {
            mPath.rQuadTo(halfWaveLen / 2.toFloat(), -100f, halfWaveLen.toFloat(), 0f)
            mPath.rQuadTo(halfWaveLen / 2.toFloat(), 100f, halfWaveLen.toFloat(), 0f)
            i += ITEM_WAVE_LENGTH
        }
        mPath.lineTo(width.toFloat(), height.toFloat())
        mPath.lineTo(0f, height.toFloat())
        mPath.close()
        canvas.drawPath(mPath, mPaint)
    }

    fun startAnim() {
        valueAnimator = ValueAnimator.ofInt(0, ITEM_WAVE_LENGTH)
        valueAnimator?.setDuration(2000)
        valueAnimator?.setRepeatCount(ValueAnimator.INFINITE)
        valueAnimator?.setInterpolator(LinearInterpolator())
        valueAnimator?.addUpdateListener(ValueAnimator.AnimatorUpdateListener { animation ->
            dx = animation.animatedValue as Int
            postInvalidate()
        })
        valueAnimator?.start()
    }

    fun cancelAnim() {
        if (valueAnimator != null && valueAnimator!!.isRunning) {
            valueAnimator!!.cancel()
        }
    }

    init {
        mPath = Path()
        mPaint = Paint()
        mPaint.color = Color.GREEN
        mPaint.style = Paint.Style.FILL_AND_STROKE
    }
}