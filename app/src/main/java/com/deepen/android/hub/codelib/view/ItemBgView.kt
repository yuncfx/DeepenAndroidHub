package com.deepen.android.hub.codelib.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import com.blankj.utilcode.util.LogUtils
import com.deepen.android.hub.R

/**
 * @author shane
 */
class ItemBgView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {
    private val xfermode: PorterDuffXfermode
    private var rectF: RectF? = null
    var mPaint: Paint? = null
    var mArcPaint: Paint? = null
    var mLinePaint: Paint? = null
    private var mGroupNumber: Int
    private var mWidth = 0
    private var mHeight = 0
    private var mLinePath: Path? = null
    private var mMainPath: Path? = null
    private var mContext: Context? = null
    private var mStyle = 0
    private var mHead = false

    constructor(context: Context, groupNumber: Int, style: Int, head: Boolean) : this(context) {
        require(groupNumber >= 1) { "group number should be larger than 0" }
        mGroupNumber = groupNumber
        mStyle = style
        mHead = head
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //        int layerID = canvas.saveLayer(0, 0, mWidth * 2, mHeight * 2, mPaint, Canvas.ALL_SAVE_FLAG);
        canvas.drawRoundRect(rectF!!, 12f, 12f, mPaint!!)
        LogUtils.d("ItemBgView", "mWidth = $mWidth, mHeight = $mHeight, mStyle = $mStyle")
        when (mStyle) {
            TOP_STYLE -> {
                mPaint!!.xfermode = xfermode
                canvas.drawCircle(0f, mHeight.toFloat(), 12f, mArcPaint!!)
                canvas.drawCircle(mWidth.toFloat(), mHeight.toFloat(), 12f, mArcPaint!!)
                mArcPaint!!.xfermode = null
            }
            MIDDLE_STYLE -> {
                mPaint!!.xfermode = xfermode
                canvas.drawCircle(0f, 0f, 12f, mArcPaint!!)
                canvas.drawCircle(mWidth.toFloat(), 0f, 12f, mArcPaint!!)
                canvas.drawCircle(0f, mHeight.toFloat(), 12f, mArcPaint!!)
                canvas.drawCircle(mWidth.toFloat(), mHeight.toFloat(), 12f, mArcPaint!!)
                mArcPaint!!.xfermode = null
            }
            BOTTOM_STYLE -> {
                mPaint!!.xfermode = xfermode
                canvas.drawCircle(0f, 0f, 12f, mArcPaint!!)
                canvas.drawCircle(mWidth.toFloat(), 0f, 12f, mArcPaint!!)
                mArcPaint!!.xfermode = null
            }
            SINGLE_STYLE -> {
            }
            else -> {
            }
        }
        canvas.drawPath(mLinePath!!, mLinePaint!!)

//        canvas.restoreToCount(layerID);
    }

    @SuppressLint("DrawAllocation")
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        val width: Int
        val height: Int
        if (widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY) {
            width = widthSize
            height = heightSize
            val gradient = LinearGradient(0f, 0f, width.toFloat(), height.toFloat(),
                    mContext!!.resources.getColor(R.color.white), mContext!!.resources.getColor(
                    R.color.sub_gray), Shader.TileMode.CLAMP)
            mPaint!!.shader = gradient
            mWidth = width
            mHeight = height
            rectF = RectF(0f, 0f, width.toFloat(), height.toFloat())
            if (mGroupNumber == 1) {
                if (mHead) {
                    mLinePath!!.moveTo(0f, 116f)
                    mLinePath!!.lineTo(width.toFloat(), 116f)
                } else {
                    mLinePath!!.moveTo(0f, height - 120.toFloat())
                    mLinePath!!.lineTo(width.toFloat(), height - 120.toFloat())
                }
            } else {
                for (i in 0 until mGroupNumber) {
                    mLinePath!!.moveTo(0f, 116 + 408 * i.toFloat())
                    mLinePath!!.lineTo(width.toFloat(), 116 + 408 * i.toFloat())
                }
            }
        } else {
            throw IllegalArgumentException("Exact width & height not set!")
        }
        setMeasuredDimension(width, height)
    }

    companion object {
        const val TOP_STYLE = 0x000a
        const val MIDDLE_STYLE = 0x000b
        const val BOTTOM_STYLE = 0x000c
        const val SINGLE_STYLE = 0x000d
    }

    init {
        mContext = context
        val array = context.obtainStyledAttributes(attrs, R.styleable.ItemBgView)
        mGroupNumber = array.getInteger(R.styleable.ItemBgView_groups, 0)
        mWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                array.getInteger(R.styleable.ItemBgView_bg_width, -1).toFloat(), resources.displayMetrics).toInt()
        mHeight = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                array.getInteger(R.styleable.ItemBgView_bg_height, -1).toFloat(), resources.displayMetrics).toInt()
        array.recycle()
        require(!(mWidth == -1 || mHeight == -1)) { "ItemBgView height || width was not set!" }
        mPaint = Paint()
        mPaint!!.style = Paint.Style.FILL
        mPaint!!.isAntiAlias = true
        mArcPaint = Paint()
        mArcPaint!!.style = Paint.Style.FILL
        mArcPaint!!.isAntiAlias = true
        mArcPaint!!.color = Color.BLACK
        mLinePaint = Paint()
        mLinePaint!!.style = Paint.Style.STROKE
        mLinePaint!!.color = context.resources.getColor(R.color.dash_gray)
        mLinePaint!!.pathEffect = DashPathEffect(floatArrayOf(10f, 10f), 0f)
        mLinePath = Path()
        xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_OUT)
        mMainPath = Path()
    }
}