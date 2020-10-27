package com.deepen.android.hub.codelib.service

import android.media.MediaMetadata
import android.media.MediaPlayer
import android.media.browse.MediaBrowser
import android.media.session.MediaSession
import android.media.session.PlaybackState
import android.net.Uri
import android.os.Bundle
import android.service.media.MediaBrowserService
import android.util.Log
import com.deepen.android.hub.R
import java.io.IOException
import java.util.*

/**
 * Created by anlia on 2018/3/8.
 */
class MusicService constructor() : MediaBrowserService() {
    private var mSession: MediaSession? = null
    private var mMediaPlayer: MediaPlayer? = null
    private var mPlaybackState: PlaybackState? = null
    override fun onCreate() {
        super.onCreate()
        Log.e(TAG, "onCreate-----------")
        mPlaybackState = PlaybackState.Builder()
                .setState(PlaybackState.STATE_NONE, 0, 1.0f)
                .build()
        mSession = MediaSession(this, "MusicService")
        mSession!!.setCallback(sessionCallback)
        mSession!!.setFlags(MediaSession.FLAG_HANDLES_TRANSPORT_CONTROLS)
        mSession!!.setPlaybackState(mPlaybackState)

        //设置token后会触发MediaBrowser.ConnectionCallback的回调方法
        //表示MediaBrowser与MediaBrowserService连接成功
        sessionToken = mSession!!.sessionToken
        mMediaPlayer = MediaPlayer()
        mMediaPlayer!!.setOnPreparedListener(preparedListener)
        mMediaPlayer!!.setOnCompletionListener(completionListener)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mMediaPlayer != null) {
            mMediaPlayer!!.release()
            mMediaPlayer = null
        }
        if (mSession != null) {
            mSession!!.release()
            mSession = null
        }
    }

    override fun onGetRoot(clientPackageName: String, clientUid: Int, rootHints: Bundle?): BrowserRoot? {
        Log.e(TAG, "onGetRoot-----------")
        return BrowserRoot(MEDIA_ID_ROOT, null)
    }

    override fun onLoadChildren(parentId: String, result: Result<List<MediaBrowser.MediaItem>>) {
        Log.e(TAG, "onLoadChildren--------")
        //将信息从当前线程中移除，允许后续调用sendResult方法
        result.detach()

        //我们模拟获取数据的过程，真实情况应该是异步从网络或本地读取数据
        val metadata: MediaMetadata = MediaMetadata.Builder()
                .putString(MediaMetadata.METADATA_KEY_MEDIA_ID, "" + R.raw.jinglebells)
                .putString(MediaMetadata.METADATA_KEY_TITLE, "圣诞歌11111111111111111111")
                .build()
        val mediaItems: ArrayList<MediaBrowser.MediaItem> = ArrayList()
        mediaItems.add(createMediaItem(metadata))

        //向Browser发送数据
        result.sendResult(mediaItems)
    }

    private fun createMediaItem(metadata: MediaMetadata): MediaBrowser.MediaItem {
        return MediaBrowser.MediaItem(metadata.description,
                MediaBrowser.MediaItem.FLAG_PLAYABLE)
    }

    /**
     * 响应控制器指令的回调
     */
    private val sessionCallback: MediaSession.Callback = object : MediaSession.Callback() {
        /**
         * 响应MediaController.getTransportControls().play
         */
        public override fun onPlay() {
            Log.e(TAG, "onPlay")
            if (mPlaybackState!!.state == PlaybackState.STATE_PAUSED) {
                mMediaPlayer!!.start()
                mPlaybackState = PlaybackState.Builder()
                        .setState(PlaybackState.STATE_PLAYING, 0, 1.0f)
                        .build()
                mSession!!.setPlaybackState(mPlaybackState)
            }
        }

        /**
         * 响应MediaController.getTransportControls().onPause
         */
        override fun onPause() {
            Log.e(TAG, "onPause")
            if (mPlaybackState!!.state == PlaybackState.STATE_PLAYING) {
                mMediaPlayer!!.pause()
                mPlaybackState = PlaybackState.Builder()
                        .setState(PlaybackState.STATE_PAUSED, 0, 1.0f)
                        .build()
                mSession!!.setPlaybackState(mPlaybackState)
            }
        }

        /**
         * 响应MediaController.getTransportControls().playFromUri
         * @param uri
         * @param extras
         */
        override fun onPlayFromUri(uri: Uri, extras: Bundle) {
            Log.e(TAG, "onPlayFromUri")
            try {
                when (mPlaybackState!!.state) {
                    PlaybackState.STATE_PLAYING, PlaybackState.STATE_PAUSED, PlaybackState.STATE_NONE -> {
                        mMediaPlayer!!.reset()
                        mMediaPlayer!!.setDataSource(this@MusicService, uri)
                        mMediaPlayer!!.prepare() //准备同步
                        mPlaybackState = PlaybackState.Builder()
                                .setState(PlaybackState.STATE_CONNECTING, 0, 1.0f)
                                .build()
                        mSession!!.setPlaybackState(mPlaybackState)
                        //我们可以保存当前播放音乐的信息，以便客户端刷新UI
                        mSession!!.setMetadata(MediaMetadata.Builder()
                                .putString(MediaMetadata.METADATA_KEY_TITLE, extras.getString("title"))
                                .build()
                        )
                    }
                    else -> {
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        override fun onPlayFromSearch(query: String, extras: Bundle) {}
    }

    /**
     * 监听MediaPlayer.prepare()
     */
    private val preparedListener: MediaPlayer.OnPreparedListener = MediaPlayer.OnPreparedListener {
        mMediaPlayer!!.start()
        mPlaybackState = PlaybackState.Builder()
            .setState(PlaybackState.STATE_PLAYING, 0, 1.0f)
            .build()
        mSession!!.setPlaybackState(mPlaybackState)
    }

    /**
     * 监听播放结束的事件
     */
    private val completionListener: MediaPlayer.OnCompletionListener = MediaPlayer.OnCompletionListener {
        mPlaybackState = PlaybackState.Builder()
            .setState(PlaybackState.STATE_NONE, 0, 1.0f)
            .build()
        mSession!!.setPlaybackState(mPlaybackState)
        mMediaPlayer!!.reset()
    }

    companion object {
        private const val TAG: String = "MusicService"
        const val MEDIA_ID_ROOT: String = "__ROOT__"
    }
}