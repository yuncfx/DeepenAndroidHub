package com.deepen.android.hub.codelib.activity

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.RemoteException
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.deepen.android.hub.R
import deepen.android.hub.IService


class MainActivity constructor() : AppCompatActivity() {

    //定义aidl接口变量
    var iService: IService? = null

    //创建ServiceConnection的匿名类
    val connection: ServiceConnection = object : ServiceConnection {
        //重写onServiceConnected()方法和onServiceDisconnected()方法
        //在Activity与Service建立关联和解除关联的时候调用
        override fun onServiceDisconnected(name: ComponentName) {}

        //在Activity与Service建立关联时调用
        override fun onServiceConnected(name: ComponentName, service: IBinder) {

            //使用AIDLService1.Stub.asInterface()方法将传入的IBinder对象传换成了mAIDL_Service对象
            iService = IService.Stub.asInterface(service)
            try {

                //通过该对象调用在MyAIDLService.aidl文件中定义的接口方法,从而实现跨进程通信
                iService?.test()
                // 非oneway的方法， 会卡住
                iService?.test1(arrayListOf("1", "2", "3"))
                iService?.test2()
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<View>(R.id.btn_start_activity).setOnClickListener(object :
            View.OnClickListener {
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
        findViewById<View>(R.id.btn_to_paint_method_demo).setOnClickListener(object :
            View.OnClickListener {
            public override fun onClick(v: View) {
                startActivity(Intent(this@MainActivity, PaintMethodDemoActivity::class.java))
            }
        })
        findViewById<View>(R.id.btn_to_start_activity).setOnClickListener(object :
            View.OnClickListener {
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
        findViewById<View>(R.id.btn_to_start_webview).setOnClickListener(object :
            View.OnClickListener {
            public override fun onClick(view: View) {
                val intent: Intent = Intent(this@MainActivity, WebViewActivity::class.java)
                startActivity(intent)
            }
        })
        findViewById<View>(R.id.btn_to_test_bar_code).setOnClickListener(object :
            View.OnClickListener {
            public override fun onClick(view: View) {
                val intent: Intent = Intent(this@MainActivity, BarcodeActivity::class.java)
                startActivity(intent)
            }
        })

        findViewById<View>(R.id.bind_aidl).setOnClickListener {
            Log.d("MainActivity", "bind service clicked")
            Toast.makeText(this@MainActivity, "111111", Toast.LENGTH_SHORT).show()

            //通过Intent指定服务端的服务名称和所在包，与远程Service进行绑定,参数与服务器端的action要一致
            val intent = Intent("com.deepen.android.serviceserver.ddd")

            //Android5.0后无法只通过隐式Intent绑定远程Service
            //需要通过setPackage()方法指定包名
            intent.setPackage("com.deepen.android.serviceserver")

            //绑定服务,传入intent和ServiceConnection对象
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
            println(iService)
        }
    }
}