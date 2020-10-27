package com.deepen.android.hub.codelib.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.RemoteException
import android.util.Log
import deepen.android.hub.IService

/**
 * this class should be defined in server.
 *
 * 服务端包名:com.deepen.android.hub
 * 服务端Service action :"com.deepen.android.hub.ddd"
 */
class MyService : Service() {
    // 实例化AIDL的Stub类(Binder的子类)
    private var mBinder: IService.Stub = object : IService.Stub() {
        //重写接口里定义的方法
        @Throws(RemoteException::class)
        override fun test() {
            Log.d("MyService", "test works")
        }

        @Throws(RemoteException::class)
        override fun test1(list: List<*>) {
            Log.d("MyService", "test1 works")
            try {
                Thread.sleep(5000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }

        @Throws(RemoteException::class)
        override fun test2() {
            Log.d("MyService", "test2 works")
        }
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("MyService", "执行了onCreate()")
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.d("MyService", "执行了onStartCommand()")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyService", "onDestroy()")
    }

    //在onBind()返回Stub类实例
    override fun onBind(intent: Intent): IBinder? {
        Log.d("MyService", "onBind()")
        return mBinder
    }

    override fun onUnbind(intent: Intent): Boolean {
        Log.d("MyService", "执行了onUnbind()")
        return super.onUnbind(intent)
    }
}