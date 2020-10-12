package com.deepen.android.hub.codelib.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * http://blog.csdn.net/harvic880925/article/details/51010839
 * Created by Shane on 2017-07-22.
 */
/**
 * paint中基本设置的函数都有哪些：
 * reset()
 * 重置画笔
 * setColor(int color)
 * 给画笔设置颜色值
 * setARGB(int a, int r, int g, int b)
 * 同样是设置颜色，但是利用ARGB分开设置
 * setAlpha(int a)
 * 设置画笔透明度
 * setStyle(Paint.Style style)
 * 设置画笔样式，取值有
 *
 *
 * Paint.Style.FILL :填充内部
 * Paint.Style.FILL_AND_STROKE ：填充内部和描边
 * Paint.Style.STROKE ：仅描边
 *
 *
 * setStrokeWidth(float width)
 * 设置画笔宽度
 * setAntiAlias(boolean aa)
 * 设置画笔是否抗锯齿
 */
class PaintMethodDemoView @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {
    private var mPaint: Paint? = null
    private fun init() {
        mPaint = Paint()
        mPaint!!.style = Paint.Style.STROKE
        mPaint!!.isAntiAlias = true
        mPaint!!.color = Color.GREEN
        mPaint!!.strokeWidth = 20f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        mPaint!!.strokeCap = Paint.Cap.BUTT
        canvas.drawLine(100f, 100f, 400f, 100f, mPaint!!)
        mPaint!!.strokeCap = Paint.Cap.SQUARE
        canvas.drawLine(100f, 200f, 400f, 200f, mPaint!!)
        mPaint!!.strokeCap = Paint.Cap.ROUND
        canvas.drawLine(100f, 300f, 400f, 300f, mPaint!!)
        mPaint!!.reset()
        mPaint!!.strokeWidth = 2f
        mPaint!!.color = Color.RED
        canvas.drawLine(100f, 50f, 100f, 750f, mPaint!!)
    }

    init {
        init()
    }
}