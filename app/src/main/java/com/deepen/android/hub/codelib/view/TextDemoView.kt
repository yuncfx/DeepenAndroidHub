package com.deepen.android.hub.codelib.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import com.blankj.utilcode.util.LogUtils

/**
 * File Name:
 * Description:
 * Author: Shane
 * History: 17-7-6 created
 */
class TextDemoView @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {
    private lateinit var mLinePaint: Paint
    private lateinit var mTextPaint: Paint
    private var baseY = 0
    private var baseX = 0
    private var ascentY = 0f
    private var descentY = 0f
    private var topY = 0f
    private var bottomY = 0f
    private lateinit var metrics: Paint.FontMetrics
    private fun init() {
        mLinePaint = Paint()
        mLinePaint!!.strokeWidth = 2f
        mLinePaint!!.style = Paint.Style.FILL
        mLinePaint!!.isAntiAlias = true
        mLinePaint!!.color = Color.RED
        mTextPaint = Paint()
        mTextPaint!!.strokeWidth = 2f
        mTextPaint!!.style = Paint.Style.FILL
        mTextPaint!!.isAntiAlias = true
        mTextPaint!!.color = Color.GREEN
        mTextPaint!!.textSize = 20f
        mTextPaint!!.textAlign = Paint.Align.LEFT
        //        mTextPaint.setTextAlign(Paint.Align.CENTER);
//        mTextPaint.setTextAlign(Paint.Align.RIGHT);
        metrics = mTextPaint!!.fontMetrics
        baseY = 200
        baseX = 0
        ascentY = baseY + metrics.ascent
        topY = baseY + metrics.top
        descentY = baseY + metrics.descent
        bottomY = baseY + metrics.bottom
        val height = (bottomY - topY).toInt()
        val width = mTextPaint.measureText("hello world's width").toInt()
        val text = "hello world's width"
        mTextPaint.textSize = 120f //px
        val minRect = Rect()
        mTextPaint.getTextBounds(text, 0, text.length, minRect)
        LogUtils.d(TAG, "init: minRect= " + minRect.toShortString()) //[8,-90][975,2]
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        mLinePaint!!.color = Color.RED
        canvas.drawLine(baseX.toFloat(), baseY.toFloat(), 3000f, baseY.toFloat(), mLinePaint)
        canvas.drawText("hello world", baseX.toFloat(), baseY.toFloat(), mTextPaint)
        mLinePaint!!.color = Color.GREEN
        canvas.drawLine(baseX.toFloat(), ascentY, 3000f, ascentY, mLinePaint)
        mLinePaint!!.color = Color.GRAY
        canvas.drawLine(baseX.toFloat(), topY, 3000f, topY, mLinePaint)
        mLinePaint!!.color = Color.GREEN
        canvas.drawLine(baseX.toFloat(), bottomY, 3000f, bottomY, mLinePaint)
        mLinePaint!!.color = Color.GRAY
        canvas.drawLine(baseX.toFloat(), descentY, 3000f, descentY, mLinePaint)
        val text = "hello world , this is my house"
        val baseLineY = 800
        val baseLineX = 0
        val top = (baseLineY + metrics!!.top).toInt()
        val bottom = (baseLineY + metrics!!.bottom).toInt()
        val width = mTextPaint!!.measureText(text).toInt()
        val rect = Rect(baseLineX, top, baseLineX + width, bottom)
        mTextPaint.color = Color.GREEN
        canvas.drawRect(rect, mTextPaint)
        val minRect = Rect()
        mTextPaint.getTextBounds(text, 0, text.length, minRect)
        minRect.top = baseLineY + minRect.top
        minRect.bottom = baseLineY + minRect.bottom
        mTextPaint!!.color = Color.RED
        canvas.drawRect(minRect, mTextPaint)
        mTextPaint!!.color = Color.BLACK
        canvas.drawText(text, baseLineX.toFloat(), baseLineY.toFloat(), mTextPaint)
    }

    companion object {
        private val TAG = TextDemoView::class.java.simpleName
    }

    init {
        init()
    }
}