package com.deepen.android.hub.codelib.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.PointF
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix

object BitmapUtils {
    fun createBarcode(context: Context, contents: String,
                      desiredWidth: Int, desiredHeight: Int, displayCode: Boolean): Bitmap? {
        var resultBitmap: Bitmap? = null
        val marginW = 20
        val barcodeFormat = BarcodeFormat.CODE_128
        resultBitmap = if (displayCode) {
            val barcodeBitmap = encodeAsBitmap(contents, barcodeFormat,
                    desiredWidth, desiredHeight)
            val codeBitmap = createCodeBitmap(context, contents, desiredWidth
                    + 2 * marginW, desiredHeight, false)
            mixtureBitmap(barcodeBitmap, codeBitmap, PointF(
                    0f, desiredHeight.toFloat()))
        } else {
            encodeAsBitmap(contents, barcodeFormat,
                    desiredWidth, desiredHeight)
        }
        return resultBitmap
    }

    private fun encodeAsBitmap(contents: String,
                               format: BarcodeFormat, desiredWidth: Int, desiredHeight: Int): Bitmap {
        val WHITE = -0x1
        val BLACK = -0x1000000
        val TRANSPARENT = 0x00000000
        val writer = MultiFormatWriter()
        var result: BitMatrix? = null
        try {
            result = writer.encode(contents, format, desiredWidth,
                    desiredHeight, null)
        } catch (e: WriterException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }
        val width = result!!.width
        val height = result.height
        val pixels = IntArray(width * height)
        // All are 0, or black, by default
        for (y in 0 until height) {
            val offset = y * width
            for (x in 0 until width) {
                pixels[offset + x] = if (result[x, y]) BLACK else TRANSPARENT
            }
        }
        val bitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888)
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height)
        return bitmap
    }

    private fun createCodeBitmap(context: Context, contents: String,
                                 width: Int, height: Int, isNull: Boolean): Bitmap {
        val tv = TextView(context)
        val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        tv.layoutParams = layoutParams
        tv.text = contents
        tv.height = height
        tv.gravity = Gravity.CENTER_HORIZONTAL
        tv.width = width
        tv.isDrawingCacheEnabled = true
        tv.setTextColor(Color.WHITE)
        tv.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED))
        tv.layout(0, 0, tv.measuredWidth, tv.measuredHeight)
        tv.buildDrawingCache()
        return tv.drawingCache
    }

    private fun mixtureBitmap(first: Bitmap?, second: Bitmap?,
                              fromPoint: PointF?): Bitmap? {
        if (first == null || second == null || fromPoint == null) {
            return null
        }
        val marginW = 20
        val newBitmap = Bitmap.createBitmap(
                first.width + second.width + marginW,
                first.height + second.height, Bitmap.Config.ARGB_4444)
        val cv = Canvas(newBitmap)
        cv.drawBitmap(first, marginW.toFloat(), 0f, null)
        cv.drawBitmap(second, fromPoint.x, fromPoint.y, null)
        cv.save()
        cv.restore()
        return newBitmap
    }
}