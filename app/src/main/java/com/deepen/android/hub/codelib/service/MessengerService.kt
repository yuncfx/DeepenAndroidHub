package com.deepen.android.hub.codelib.service

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import android.widget.Toast

/**
 *
 * 服务实现一个Handler，由其接收来自客户端的每个调用的回调。
 * Handler用于创建Messenger对象（对Handler的引用）。
 * Messenger创建一个IBinder，服务通过onBind()使其返回客户端。
 * 客户端使用IBinder将Messenger（引用服务的Handler）实例化，然后使用后者将Message对象发送给服务。
 * 服务在其Handler中（具体地讲，是在handleMessage()方法中）接收每个Message。
 */
class MessengerService : Service() {

    private var mMessenger = Messenger(IncomingHandler())

    @SuppressLint("HandlerLeak")
    inner class IncomingHandler : Handler() {

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                0 -> Toast.makeText(this@MessengerService, "dingdingding", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onBind(intent: Intent): IBinder {
        return mMessenger.binder
    }
}
