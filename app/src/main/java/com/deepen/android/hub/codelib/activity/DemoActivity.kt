package com.deepen.android.hub.codelib.activity

import android.content.ComponentName
import android.media.MediaMetadata
import android.media.browse.MediaBrowser
import android.media.session.MediaController
import android.media.session.PlaybackState
import android.net.Uri
import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.os.RemoteException
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.deepen.android.hub.R
import com.deepen.android.hub.codelib.adapter.DemoAdapter
import com.deepen.android.hub.codelib.service.MusicService
import java.util.*
import kotlin.collections.ArrayList

class DemoActivity : AppCompatActivity() {
    private var mBtnPlay: Button? = null
    private var mTextTitle: TextView? = null
    private var mItemList: ArrayList<MediaBrowser.MediaItem>? = null
    private var demoAdapter: DemoAdapter? = null
    private var mBrowser: MediaBrowser? = null
    private var mController: MediaController? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo)
        mBrowser = MediaBrowser(
                this,
                ComponentName(this, MusicService::class.java),  //绑定浏览器服务
                mBrowserConnectionCallback,  //设置连接回调
                null
        )
        mBtnPlay = findViewById<View>(R.id.btn_play) as Button
        mTextTitle = findViewById<View>(R.id.text_title) as TextView
        mItemList = ArrayList()
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        demoAdapter = DemoAdapter(this, mItemList)
        demoAdapter!!.setOnItemClickListener(object : DemoAdapter.OnItemClickListener {
            @RequiresApi(VERSION_CODES.M)
            override fun onItemClick(view: View?, position: Int) {
                val bundle = Bundle()
                bundle.putString("title", Objects.requireNonNull(mItemList?.get(position)?.description?.title).toString())
                mController!!.transportControls.playFromUri(
                        rawToUri(Objects.requireNonNull(mItemList?.get(position)?.mediaId)!!.toInt()),
                        bundle
                )
            }

            override fun onItemLongClick(view: View?, position: Int) {}
        })
        val recyclerView = findViewById<View>(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = demoAdapter
    }

    override fun onStart() {
        super.onStart()
        //Browser发送连接请求
        mBrowser!!.connect()
    }

    override fun onStop() {
        super.onStop()
        mBrowser!!.disconnect()
    }

    fun clickEvent(view: View) {
        if (view.id == R.id.btn_play) {
            if (mController != null) {
                handlerPlayEvent()
            }
        }
    }

    /**
     * 处理播放按钮事件
     */
    private fun handlerPlayEvent() {
        when (mController?.playbackState?.state) {
            PlaybackState.STATE_PLAYING -> mController!!.transportControls.pause()
            PlaybackState.STATE_PAUSED -> mController!!.transportControls.play()
            else -> mController!!.transportControls.playFromSearch("", null)
        }
    }

    /**
     * 连接状态的回调接口，连接成功时会调用onConnected()方法
     */
    private val mBrowserConnectionCallback: MediaBrowser.ConnectionCallback = object : MediaBrowser.ConnectionCallback() {
        override fun onConnected() {
            Log.e(TAG, "onConnected------")
            if (mBrowser!!.isConnected) {
                //mediaId即为MediaBrowserService.onGetRoot的返回值
                //若Service允许客户端连接，则返回结果不为null，其值为数据内容层次结构的根ID
                //若拒绝连接，则返回null
                val mediaId = mBrowser!!.root

                //Browser通过订阅的方式向Service请求数据，发起订阅请求需要两个参数，其一为mediaId
                //而如果该mediaId已经被其他Browser实例订阅，则需要在订阅之前取消mediaId的订阅者
                //虽然订阅一个 已被订阅的mediaId 时会取代原Browser的订阅回调，但却无法触发onChildrenLoaded回调

                //ps：虽然基本的概念是这样的，但是Google在官方demo中有这么一段注释...
                // This is temporary: A bug is being fixed that will make subscribe
                // consistently call onChildrenLoaded initially, no matter if it is replacing an existing
                // subscriber or not. Currently this only happens if the mediaID has no previous
                // subscriber or if the media content changes on the service side, so we need to
                // unsubscribe first.
                //大概的意思就是现在这里还有BUG，即只要发送订阅请求就会触发onChildrenLoaded回调
                //所以无论怎样我们发起订阅请求之前都需要先取消订阅
                mBrowser!!.unsubscribe(mediaId)
                //之前说到订阅的方法还需要一个参数，即设置订阅回调SubscriptionCallback
                //当Service获取数据后会将数据发送回来，此时会触发SubscriptionCallback.onChildrenLoaded回调
                mBrowser!!.subscribe(mediaId, BrowserSubscriptionCallback)
                try {
                    mController = MediaController(this@DemoActivity, mBrowser!!.sessionToken)
                    mController!!.registerCallback(ControllerCallback)
                } catch (e: RemoteException) {
                    e.printStackTrace()
                }
            }
        }

        override fun onConnectionFailed() {
            Log.e(TAG, "连接失败！")
        }
    }

    /**
     * 向媒体浏览器服务(MediaBrowserService)发起数据订阅请求的回调接口
     */
    private val BrowserSubscriptionCallback: MediaBrowser.SubscriptionCallback = object : MediaBrowser.SubscriptionCallback() {
        override fun onChildrenLoaded(parentId: String,
                                      children: List<MediaBrowser.MediaItem>) {
            Log.e(TAG, "onChildrenLoaded------")
            //children 即为Service发送回来的媒体数据集合
            for (item in children) {
                Log.e(TAG, Objects.requireNonNull(item.description.title).toString())
                mItemList!!.add(item)
            }
            demoAdapter!!.notifyDataSetChanged()
        }
    }

    /**
     * 媒体控制器控制播放过程中的回调接口，可以用来根据播放状态更新UI
     */
    private val ControllerCallback: MediaController.Callback = object : MediaController.Callback() {
        /***
         * 音乐播放状态改变的回调
         * @param state
         */
        override fun onPlaybackStateChanged(state: PlaybackState?) {
            when (state?.state) {
                PlaybackState.STATE_NONE -> {
                    mTextTitle!!.text = ""
                    mBtnPlay!!.text = "开始"
                }
                PlaybackState.STATE_PAUSED -> mBtnPlay!!.text = "开始"
                PlaybackState.STATE_PLAYING -> mBtnPlay!!.text = "暂停"
                else -> {
                }
            }
        }

        /**
         * 播放音乐改变的回调
         * @param metadata
         */
        override fun onMetadataChanged(metadata: MediaMetadata?) {
            mTextTitle!!.text = metadata?.description?.title
        }
    }

    private fun rawToUri(id: Int): Uri {
        val uriStr = "android.resource://$packageName/$id"
        return Uri.parse(uriStr)
    }

    companion object {
        private const val TAG = "DemoActivity"
    }
}