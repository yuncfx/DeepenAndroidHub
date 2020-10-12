package com.deepen.android.hub.codelib.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.deepen.android.hub.R

class MainActivity constructor() : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<View>(R.id.btn_start_activity).setOnClickListener(object : View.OnClickListener {
            public override fun onClick(v: View) {
                startActivity(Intent(this@MainActivity, BlurDemoActivity::class.java))
            }
        })
        findViewById<View>(R.id.iv_exit_btn).setOnClickListener(object : View.OnClickListener {
            public override fun onClick(v: View) {
                finish()
            }
        })
        findViewById<View>(R.id.btn_to_text_view).setOnClickListener(object : View.OnClickListener {
            public override fun onClick(v: View) {
                startActivity(Intent(this@MainActivity, TextDemoActivity::class.java))
            }
        })
        findViewById<View>(R.id.btn_to_bezier).setOnClickListener(object : View.OnClickListener {
            public override fun onClick(v: View) {
                startActivity(Intent(this@MainActivity, BezierActivity::class.java))
            }
        })
        findViewById<View>(R.id.btn_to_paint_method_demo).setOnClickListener(object : View.OnClickListener {
            public override fun onClick(v: View) {
                startActivity(Intent(this@MainActivity, PaintMethodDemoActivity::class.java))
            }
        })
        findViewById<View>(R.id.btn_to_start_activity).setOnClickListener(object : View.OnClickListener {
            public override fun onClick(v: View) {
                val intent: Intent = Intent()
                intent.setClassName("com.android.contacts", "com.gionee.vip.ContactVipActivity")
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                try {
                    startActivity(intent)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        })
        findViewById<View>(R.id.btn_to_start_webview).setOnClickListener(object : View.OnClickListener {
            public override fun onClick(view: View) {
                val intent: Intent = Intent(this@MainActivity, WebViewActivity::class.java)
                startActivity(intent)
            }
        })
        findViewById<View>(R.id.btn_to_test_bar_code).setOnClickListener(object : View.OnClickListener {
            public override fun onClick(view: View) {
                val intent: Intent = Intent(this@MainActivity, BarcodeActivity::class.java)
                startActivity(intent)
            }
        })
    }
}