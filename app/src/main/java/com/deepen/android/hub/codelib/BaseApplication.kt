package com.deepen.android.hub.codelib

import android.app.Application
import android.content.Context
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.Utils
import com.bumptech.glide.util.Util
import com.tencent.smtt.sdk.QbSdk
import com.tencent.smtt.sdk.QbSdk.PreInitCallback

/**
 * @author shane
 */
class BaseApplication constructor() : Application() {
    public override fun onCreate() {
        super.onCreate()
        sMainInstance = this
        initX5()
        Utils.init(this)
    }

    private fun initX5() {
        val cb: PreInitCallback = object : PreInitCallback {
            public override fun onViewInitFinished(arg0: Boolean) {
                // TODO Auto-generated method stub
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                LogUtils.d(TAG, " onViewInitFinished is " + arg0)
            }

            public override fun onCoreInitFinished() {
                // TODO Auto-generated method stub
            }
        }
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(), cb)
    }

    companion object {
        val TAG: String = BaseApplication::class.java.getSimpleName()
        private var sMainInstance: BaseApplication? = null
        val context: Context
            get() {
                return sMainInstance!!.getApplicationContext()
            }
    }
}