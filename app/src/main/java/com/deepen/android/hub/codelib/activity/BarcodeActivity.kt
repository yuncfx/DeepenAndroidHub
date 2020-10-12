package com.deepen.android.hub.codelib.activity

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.LogUtils
import com.deepen.android.hub.R
import com.deepen.android.hub.codelib.utils.BitmapUtils

class BarcodeActivity constructor() : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_barcode)
        val bitmap: Bitmap? = BitmapUtils.createBarcode(this, "9876544678889", 544, 90, false)
        val imageView: ImageView = findViewById(R.id.barcode_image)
        val layoutParams: FrameLayout.LayoutParams = FrameLayout.LayoutParams(544, 90)
        imageView.setLayoutParams(layoutParams)
        imageView.setImageBitmap(bitmap)
        imageView.setBackgroundColor(Color.RED)
        val displayMetrics: DisplayMetrics = DisplayMetrics()
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics)
        val height: Int = displayMetrics.heightPixels
        val width: Int = displayMetrics.widthPixels
        LogUtils.d("BarcodeActivity", "onCreate: " + width + " :" + height)
    }
}