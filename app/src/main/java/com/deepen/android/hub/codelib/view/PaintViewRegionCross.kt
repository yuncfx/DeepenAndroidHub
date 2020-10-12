package com.deepen.android.hub.codelib.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * File Name:
 * Description:
 * Author: Shane
 * History: 17-6-23 created
 */
class PaintViewRegionCross @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {
    private var rect1: Rect? = null
    private var rect2: Rect? = null
    private var mPaint: Paint? = null
    private var mFillPaint: Paint? = null
    private var region1: Region? = null
    private var region2: Region? = null
    private fun initPaint() {
        rect1 = Rect(100, 100, 400, 200)
        rect2 = Rect(200, 0, 300, 300)
        mPaint = Paint()
        mPaint!!.color = Color.RED
        mPaint!!.style = Paint.Style.STROKE
        mPaint!!.strokeWidth = 2f
        region1 = Region(rect1!!)
        region2 = Region(rect2!!)
        region1!!.op(region2!!, Region.Op.XOR)
        mFillPaint = Paint()
        mFillPaint!!.color = Color.GREEN
        mFillPaint!!.style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawRect(rect1!!, mPaint!!)
        canvas.drawRect(rect2!!, mPaint!!)
        drawRegion(canvas, region1, mFillPaint)
    }

    private fun drawRegion(canvas: Canvas, region: Region?, fillPaint: Paint?) {
        val iter = RegionIterator(region)
        val r = Rect()
        while (iter.next(r)) {
            canvas.drawRect(r, fillPaint!!)
        }
    }

    init {
        initPaint()
    }
}