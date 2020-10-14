package com.deepen.android.hub.codelib.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.PixelFormat
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.LogUtils
import com.deepen.android.hub.R
import com.deepen.android.hub.codelib.utils.SPUtil
import com.tencent.smtt.export.external.extension.interfaces.IX5WebViewExtension
import com.tencent.smtt.sdk.WebChromeClient
import com.tencent.smtt.sdk.WebSettings
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient

/**
 * @author shane
 */
class WebViewActivity constructor() : AppCompatActivity() {
    private var mWebView: WebView? = null
    private var mWebContainer: ViewGroup? = null
    private var loadingFinished: Boolean = true
    private var redirect: Boolean = false
    private var mUrl: String? = null

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        getWindow().setFormat(PixelFormat.TRANSLUCENT)
        getWindow().setSoftInputMode(
            (WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
                    or WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        )
        setContentView(R.layout.activity_webview)
        val intent: Intent? = getIntent()
        if (intent == null) {
            finish()
            return
        }
        mWebContainer = findViewById(R.id.web_view_container)
        mWebView = findViewById(R.id.wvb_merch)
        mWebView?.setWebViewClient(HelloWebViewClient())
        mWebView?.getView()?.setClickable(true)
        mWebView?.setWebChromeClient(WebChromeClient())
        val webSettings: WebSettings? = mWebView?.getSettings()
        // 开启 DOM storage API 功能
        webSettings?.setDomStorageEnabled(true)
        //开启 database storage API 功能
        webSettings?.setDatabaseEnabled(true)
        //开启 Application Caches 功能
        webSettings?.setAppCacheEnabled(true)
        val cacheDirPath: String = getFilesDir().getAbsolutePath() + APP_CACHE_DIRNAME
        //设置  Application Caches 缓存目录
        webSettings?.setAppCachePath(cacheDirPath)
        webSettings?.setJavaScriptEnabled(true)
        val urlWithModifyTime: String? = intent.getStringExtra("merchantUrl")
        LogUtils.d(TAG, " urlWithModifyTime = " + (urlWithModifyTime ?: ""))
        LogUtils.d(TAG, " mUrl: " + (mUrl ?: ""))
        mUrl = "www.qq.com"
        if (!TextUtils.isEmpty(mUrl)) {
            mWebView?.loadUrl(mUrl)
        }
        // set cache
        webSettings?.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK)
        webSettings?.setRenderPriority(WebSettings.RenderPriority.HIGH)
        //        // multiple windows
        webSettings?.setSupportMultipleWindows(true)
        val ix5: IX5WebViewExtension? = mWebView?.getX5WebViewExtension()
        if (null != ix5) {
            ix5.setScrollBarFadingEnabled(true)
        }
        val ivExit: ImageView = findViewById(R.id.iv_exit)
        ivExit.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(v: View) {
                finish()
            }
        })
    }

    public override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView!!.canGoBack()) {
            mWebView!!.goBack()
            return true
        }
        return false
    }

    private inner class HelloWebViewClient constructor() : WebViewClient() {
        public override fun shouldOverrideUrlLoading(
            view: WebView, url: String
        ): Boolean {
            if (!loadingFinished) {
                redirect = true
            }
            loadingFinished = false
            view.loadUrl(url)
            return false
        }

        public override fun onPageStarted(
            view: WebView?, url: String?, favicon: Bitmap?
        ) {
            super.onPageStarted(view, url, favicon)
            loadingFinished = false
            //SHOW LOADING IF IT IS NOT ALREADY VISIBLE
        }

        public override fun onPageFinished(view: WebView, url: String) {
            if (!redirect) {
                loadingFinished = true
            }
            if (loadingFinished && !redirect) {
                //HIDE LOADING IT HAS FINISHED
                SPUtil.putLong(this@WebViewActivity, mUrl, -1)
            } else {
                redirect = false
            }
        }
    }

    public override fun onDestroy() {
        destroyWebView()
        super.onDestroy()
    }

    private fun destroyWebView() {

        // Make sure you remove the WebView from its parent view before doing anything.
        mWebContainer!!.removeAllViews()
        mWebView!!.clearHistory()

        // Loading a blank page is optional, but will ensure that the WebView isn't doing anything when you destroy it.
        mWebView!!.loadUrl("about:blank")
        mWebView!!.onPause()
        mWebView!!.removeAllViews()
        mWebView!!.destroyDrawingCache()

        // NOTE: This can occasionally cause a segfault below API 17 (4.2)
        mWebView!!.destroy()

        // Null out the reference so that you don't end up re-using it.
        mWebView = null
    }

    public override fun onResume() {
        super.onResume()
        mWebView!!.onResume()
    }

    public override fun onPause() {
        super.onPause()
        mWebView!!.onPause()
    }

    companion object {
        private val TAG: String = WebViewActivity::class.java.getSimpleName()
        private val APP_CACHE_DIRNAME: String = "webview_cache"
    }
}