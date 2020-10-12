package com.deepen.android.hub.codelib.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View

/**
 * File Name:
 * Description:
 * Author: Shane
 * History: 17-6-16 created
 */
class PaintViewCanvas @JvmOverloads constructor(private val mContext: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(mContext, attrs, defStyleAttr) {
    private var mPaint: Paint? = null
    private var mTypefacePaint: Paint? = null
    private lateinit var pos: FloatArray
    private fun initPaint() {
        mPaint = Paint()
        mPaint!!.color = Color.RED
        mPaint!!.isAntiAlias = true
        mPaint!!.strokeWidth = 5f
        mPaint!!.textSize = 80f
        mPaint!!.style = Paint.Style.FILL
        pos = floatArrayOf(80f, 100f, 80f, 200f, 80f, 300f, 80f, 400f)
        val familyName = "宋体"
        val font = Typeface.create(familyName, Typeface.NORMAL)
        mPaint!!.typeface = font
    }

    private fun initTypefacePaint() {
        mTypefacePaint = Paint()
        mTypefacePaint!!.color = Color.RED
        mTypefacePaint!!.isAntiAlias = true
        mTypefacePaint!!.textSize = 40f
        mTypefacePaint!!.strokeWidth = 5f
        mTypefacePaint!!.style = Paint.Style.FILL
        val assetManager = mContext.assets
        val typeface = Typeface.createFromAsset(assetManager, "fonts/jian_luobo.ttf")
        mTypefacePaint!!.typeface = typeface
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPosText("画图示例", pos, mPaint!!)
        canvas.drawText("welcome to gayhub", 80f, 500f, mPaint!!)
        canvas.drawText("I can use typeface", 80f, 600f, mTypefacePaint!!)
    }

    init {
        initPaint()
        initTypefacePaint()
    }
}