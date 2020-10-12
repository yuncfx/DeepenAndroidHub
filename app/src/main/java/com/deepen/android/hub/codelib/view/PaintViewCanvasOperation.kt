package com.deepen.android.hub.codelib.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View

/**
 * File Name:
 * Description:
 * Author: Shane
 * History: 17-6-23 created
 */
class PaintViewCanvasOperation @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {
    private var mPaint: Paint? = null
    private var rect1: Rect? = null
    private var rect2: Rect? = null
    private var mPaintGreen: Paint? = null
    private var mPaintRed: Paint? = null
    private var rect3: Rect? = null
    private var rect4: Rect? = null
    private var rect5: Rect? = null
    private var rect6: Rect? = null
    private fun initPaint() {
        mPaint = Paint()
        mPaint!!.color = Color.GREEN
        mPaint!!.style = Paint.Style.FILL
        rect1 = Rect(0, 120, 400, 200)
        rect2 = Rect(0, 300, 200, 500)
        rect3 = Rect(100, 100, 800, 800)
        rect4 = Rect(200, 200, 700, 700)
        rect5 = Rect(300, 300, 600, 600)
        rect6 = Rect(400, 400, 500, 500)
        mPaintGreen = generatePaint(Color.GREEN, Paint.Style.STROKE, 3)
        mPaintRed = generatePaint(Color.RED, Paint.Style.STROKE, 3)
    }

    private fun generatePaint(color: Int, style: Paint.Style, width: Int): Paint {
        val paint = Paint()
        paint.color = color
        paint.style = style
        paint.strokeWidth = width.toFloat()
        return paint
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

//        canvas.translate(100, 100);
//        canvas.drawRect(rect1, mPaint);
//
//        canvas.translate(100, 100);
//        canvas.drawRect(rect1, mPaintRed);
//

//        canvas.drawRect(rect1, mPaintGreen);
//
//        canvas.skew(1.732f, 0);
//        canvas.drawRect(rect1, mPaintRed);

//        canvas.drawColor(Color.YELLOW);
//        canvas.clipRect(rect1);
//        canvas.drawColor(Color.GREEN);
        canvas.drawColor(Color.RED)
        canvas.save()
        canvas.clipRect(rect3!!)
        canvas.drawColor(Color.GREEN)
        canvas.save()
        canvas.clipRect(rect4!!)
        canvas.drawColor(Color.BLUE)
        canvas.save()
        canvas.clipRect(rect5!!)
        canvas.drawColor(Color.BLACK)
        //保存画布大小为Rect(300, 300, 600, 600)
        canvas.save()
        canvas.clipRect(rect6!!)
        canvas.drawColor(Color.WHITE)
        canvas.restore()
        canvas.drawColor(Color.YELLOW)

//        canvas.drawRect(rect1, mPaintRed);
//
//        canvas.scale(0.5f, 1);
//        canvas.drawRect(rect1, mPaintGreen);

//        canvas.rotate(30);
//        canvas.drawRect(rect2, mPaintRed);
    }

    init {
        initPaint()
    }
}