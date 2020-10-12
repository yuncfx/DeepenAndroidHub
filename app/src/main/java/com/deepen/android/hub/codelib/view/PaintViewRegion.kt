package com.deepen.android.hub.codelib.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * File Name:
 * Description:
 * Author: Shane
 * History: 17-6-16 created
 */
class PaintViewRegion @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {
    private var mPaint: Paint? = null
    private var region: Region? = null
    private var mPathPaint: Paint? = null
    private var ovalPath: Path? = null
    private var rgn: Region? = null
    private fun initPathPaint() {
        mPathPaint = Paint()
        mPathPaint!!.color = Color.RED
        mPathPaint!!.strokeWidth = 2f
        mPathPaint!!.style = Paint.Style.STROKE
        ovalPath = Path()
        val rectF = RectF(250f, 250f, 400f, 800f)
        ovalPath!!.addOval(rectF, Path.Direction.CCW)
        rgn = Region()
        rgn!!.setPath(ovalPath!!, Region(250, 250, 400, 500))
    }

    private fun init() {
        mPaint = Paint()
        mPaint!!.color = Color.RED
        mPaint!!.style = Paint.Style.FILL
        mPaint!!.strokeWidth = 2f
        region = Region(10, 10, 100, 100)
        region!![100, 100, 200] = 200
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawRegion(canvas, region, mPaint)
        drawRegion(canvas, rgn, mPathPaint)
    }

    private fun drawRegion(canvas: Canvas, region: Region?, paint: Paint?) {
        val iterator = RegionIterator(region)
        val r = Rect()
        while (iterator.next(r)) {
            canvas.drawRect(r, paint!!)
        }
    }

    init {
        init()
        initPathPaint()
    }
}