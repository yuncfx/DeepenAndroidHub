package com.deepen.android.hub.codelib.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * File Name:
 * Description:
 * Author: Shane
 * History: 17-5-26 created
 */
class PaintViewPathText @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {
    private var mLinePaint: Paint? = null
    private var mLinePath: Path? = null
    private var mCcwRectPath: Path? = null
    private var mCwRectPath: Path? = null
    private var mRectPathText: String? = null
    private var mTextPaint: Paint? = null
    private var mRoundRectPath: Path? = null
    private var mCirclePath: Path? = null
    private var mArcPath: Path? = null
    private var mQuadPath: Path? = null
    private fun init() {
        initLinePaint()
        initRectPaint()
        initRoundRectPaint()
        initCirclePaint()
        initArcPaint()
        initQuadPaint()
        initTextPaint()
    }

    private fun initLinePaint() {
        mLinePaint = Paint()
        mLinePaint!!.color = Color.RED
        mLinePaint!!.flags = Paint.ANTI_ALIAS_FLAG
        mLinePaint!!.style = Paint.Style.STROKE
        mLinePaint!!.strokeWidth = 5f
        mLinePath = Path()
        mLinePath!!.moveTo(10f, 10f)
        mLinePath!!.lineTo(10f, 100f)
        mLinePath!!.lineTo(300f, 100f)
        //        mLinePath.lineTo(500, 100);
        mLinePath!!.close()
    }

    private fun initRectPaint() {
        mCcwRectPath = Path()
        val rectF1 = RectF(50f, 50f, 240f, 200f)
        mCcwRectPath!!.addRect(rectF1, Path.Direction.CCW)
        mCwRectPath = Path()
        val rectF2 = RectF(290f, 50f, 480f, 200f)
        mCwRectPath!!.addRect(rectF2, Path.Direction.CW)
        mRectPathText = "风萧萧兮易水寒，壮士一去兮不复返"
        mTextPaint = Paint()
        mTextPaint!!.strokeWidth = 3f
        mTextPaint!!.color = Color.GRAY
        mTextPaint!!.flags = Paint.ANTI_ALIAS_FLAG
    }

    private fun initRoundRectPaint() {
        mRoundRectPath = Path()
        val rectF1 = RectF(50f, 50f, 240f, 200f)
        mRoundRectPath!!.addRoundRect(rectF1, 10f, 15f, Path.Direction.CCW)
        val rectF2 = RectF(290f, 50f, 480f, 200f)
        val radii = floatArrayOf(10f, 15f, 20f, 25f, 30f, 35f, 40f, 45f)
        mRoundRectPath!!.addRoundRect(rectF2, radii, Path.Direction.CCW)
    }

    private fun initCirclePaint() {
        mCirclePath = Path()
        mCirclePath!!.addCircle(200f, 200f, 100f, Path.Direction.CCW)
    }

    private fun initArcPaint() {
        mArcPath = Path()
        val rectF = RectF(100f, 200f, 300f, 400f)
        mArcPath!!.addArc(rectF, 0f, 100f)
    }

    private fun initQuadPaint() {
        mQuadPath = Path()
        mQuadPath!!.moveTo(10f, 10f)
        mQuadPath!!.quadTo(200f, 200f, 320f, 320f)
    }

    private fun initTextPaint() {
        mTextPaint!!.strokeWidth = 5f
        mTextPaint!!.isAntiAlias = true
        mTextPaint!!.style = Paint.Style.FILL
        mTextPaint!!.textAlign = Paint.Align.LEFT // some chars would be hidden
        mTextPaint!!.textSize = 40f
        mTextPaint!!.isFakeBoldText = true //设置是否为粗体文字
        mTextPaint!!.isUnderlineText = true //设置下划线
        mTextPaint!!.textSkewX = (-0.25).toFloat() //设置字体水平倾斜度，普通斜体字是-0.25
        //        mTextPaint.setTextSkewX((float) 0.25); //设置字体水平倾斜度，往左斜
        mTextPaint!!.isStrikeThruText = true //设置带有删除线效果
        mTextPaint!!.textScaleX = 2f //只会将水平方向拉伸，高度不会变
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(mLinePath!!, mLinePaint!!)

//        canvas.drawPath(mCcwRectPath, mLinePaint);
//        canvas.drawPath(mCwRectPath, mLinePaint);

//        canvas.drawTextOnPath(mRectPathText, mCcwRectPath, 0, 18, mTextPaint);
//        canvas.drawTextOnPath(mRectPathText, mCwRectPath, 0, 18, mTextPaint);
        canvas.drawPath(mRoundRectPath!!, mLinePaint!!)
        canvas.drawPath(mCirclePath!!, mLinePaint!!)
        canvas.drawPath(mArcPath!!, mLinePaint!!)
        canvas.drawPath(mQuadPath!!, mLinePaint!!)
        canvas.drawText(" Hello world Shane", 50f, 100f, mTextPaint!!)
        canvas.drawText(" Hello world Shane", 50f, 200f, mTextPaint!!)
        canvas.drawText(" Hello world Shane", 50f, 300f, mTextPaint!!)
    }

    init {
        init()
    }
}