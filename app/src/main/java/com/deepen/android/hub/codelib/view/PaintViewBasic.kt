package com.deepen.android.hub.codelib.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * File Name:
 * Description:
 * Author: Shane
 * History: 17-5-2 created
 */
class PaintViewBasic @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {
    var mPaint: Paint? = null
    private var mLinePaint: Paint? = null
    private lateinit var pts: FloatArray
    private lateinit var mPoints: FloatArray
    private var mPointPaint: Paint? = null
    private var mRectPaint: Paint? = null
    private var rectf: RectF? = null
    private var rect: Rect? = null
    private var mCirclePaint: Paint? = null
    private var mOvalPaint: Paint? = null
    private var mArcPaint: Paint? = null
    private fun initPaint() {
        mPaint = Paint()
        mPaint!!.isAntiAlias = true
        mPaint!!.color = Color.RED
        mPaint!!.style = Paint.Style.FILL //设置填充样式
        mPaint!!.strokeWidth = 30f
        mPaint!!.setShadowLayer(10f, 15f, 15f, Color.GREEN) //设置阴影
    }

    private fun initLinePaint() {
        mLinePaint = Paint()
        mLinePaint!!.color = Color.BLUE
        mLinePaint!!.isAntiAlias = true
        mLinePaint!!.strokeWidth = 5f
        pts = floatArrayOf(10f, 10f, 100f, 100f, 200f, 200f, 400f, 400f)
    }

    private fun initPointPaint() {
        mPointPaint = Paint()
        mPointPaint!!.color = Color.BLACK
        mPointPaint!!.style = Paint.Style.FILL
        mPointPaint!!.strokeWidth = 15f
        mPoints = floatArrayOf(10f, 10f, 100f, 100f, 400f, 400f, 600f, 600f)
    }

    private fun initRectPaint() {
        mRectPaint = Paint()
        mRectPaint!!.color = Color.RED
        mRectPaint!!.style = Paint.Style.FILL
        mRectPaint!!.strokeWidth = 15f
        rectf = RectF(220f, 500f, 320f, 700f)
        rect = Rect(340, 500, 440, 700)
    }

    private fun initCirclePaint() {
        mCirclePaint = Paint()
        mCirclePaint!!.color = Color.BLUE
        mCirclePaint!!.style = Paint.Style.FILL
        mCirclePaint!!.strokeWidth = 15f
    }

    private fun initOvalPaint() {
        mOvalPaint = Paint()
        mOvalPaint!!.color = Color.GREEN
        mOvalPaint!!.style = Paint.Style.STROKE
        mOvalPaint!!.strokeWidth = 5f
    }

    private fun initArcPaint() {
        mArcPaint = Paint()
        mArcPaint!!.flags = Paint.ANTI_ALIAS_FLAG
        mArcPaint!!.color = Color.BLACK
        mArcPaint!!.style = Paint.Style.STROKE
        mArcPaint!!.strokeWidth = 5f
    }

    public override fun onDraw(canvas: Canvas) {
        canvas.drawRGB(255, 255, 255)
        canvas.drawCircle(190f, 200f, 150f, mPaint!!)
        canvas.drawLine(100f, 100f, 200f, 200f, mLinePaint!!)
        canvas.drawLines(pts, mLinePaint!!)
        canvas.drawLines(pts, 2, 4, mLinePaint!!)
        canvas.drawPoint(400f, 400f, mPointPaint!!)
        canvas.drawPoints(mPoints, 2, 4, mPointPaint!!)
        canvas.drawRect(100f, 500f, 200f, 700f, mRectPaint!!)
        canvas.drawRect(rectf!!, mRectPaint!!)
        canvas.drawRect(rect!!, mRectPaint!!)
        canvas.drawCircle(150f, 800f, 100f, mCirclePaint!!)
        canvas.drawOval(rectf!!, mOvalPaint!!)
        canvas.drawArc(rectf!!, 0f, 90f, true, mArcPaint!!)
        canvas.drawArc(rectf!!, 120f, 220f, false, mArcPaint!!)
    }

    init {
        initPaint()
        initLinePaint()
        initPointPaint()
        initRectPaint()
        initCirclePaint()
        initOvalPaint()
        initArcPaint()
    }
}