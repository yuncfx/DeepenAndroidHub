package com.deepen.android.hub.codelib.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

/**
 * http://blog.csdn.net/harvic880925/article/details/51010839
 * Created by Shane on 2017-07-22.
 */
class PaintMethodDemoView2 @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {
    private var mPaint: Paint? = null
    private var mPath: Path? = null
    private var mPathBevel: Path? = null
    private var mPathRound: Path? = null
    private fun init() {
        mPaint = Paint()
        mPaint!!.style = Paint.Style.STROKE
        mPaint!!.isAntiAlias = true
        mPaint!!.color = Color.GREEN
        mPaint!!.strokeWidth = 20f
        mPath = Path()
        mPathBevel = Path()
        mPathRound = Path()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        mPath!!.moveTo(100f, 100f)
        mPath!!.lineTo(450f, 100f)
        mPath!!.lineTo(100f, 300f)
        mPaint!!.strokeJoin = Paint.Join.MITER
        canvas.drawPath(mPath!!, mPaint!!)
        mPathBevel!!.moveTo(100f, 400f)
        mPathBevel!!.lineTo(450f, 400f)
        mPathBevel!!.lineTo(100f, 600f)
        mPaint!!.strokeJoin = Paint.Join.BEVEL
        canvas.drawPath(mPathBevel!!, mPaint!!)
        mPathRound!!.moveTo(100f, 700f)
        mPathRound!!.lineTo(450f, 700f)
        mPathRound!!.lineTo(100f, 900f)
        mPaint!!.strokeJoin = Paint.Join.ROUND
        canvas.drawPath(mPathRound!!, mPaint!!)
    }

    init {
        init()
    }
}