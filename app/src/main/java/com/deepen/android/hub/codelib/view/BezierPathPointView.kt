package com.deepen.android.hub.codelib.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * File Name:
 * Description:
 * Author: Shane
 * History: 17-7-21 created
 */
class BezierPathPointView @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {
    private val mPaint: Paint
    private val mPath = Path()
    private var mPreX = 0f
    private var mPreY = 0f
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                mPath.moveTo(event.x, event.y)
                mPreX = event.x
                mPreY = event.y
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                val endX = (mPreX + event.x) / 2
                val endY = (mPreY + event.y) / 2
                mPath.quadTo(mPreX, mPreY, endX, endY)
                mPreX = endX
                mPreY = endY
                invalidate()
            }
            MotionEvent.ACTION_UP -> {
            }
            else -> {
            }
        }
        return super.onTouchEvent(event)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(mPath, mPaint)
    }

    fun reset() {
        mPath.reset()
        invalidate()
    }

    init {
        mPaint = Paint()
        mPaint.style = Paint.Style.STROKE
        mPaint.color = Color.GREEN
        mPaint.isAntiAlias = true
    }
}