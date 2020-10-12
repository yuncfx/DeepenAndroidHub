package com.deepen.android.hub.performance.tasks;

import android.app.Application;

import com.deepen.android.hub.performance.launchstarter.task.MainTask;
import com.taobao.weex.InitConfig;
import com.taobao.weex.WXSDKEngine;

/**
 * 主线程执行的task
 */
public class InitWeexTask extends MainTask {

    @Override
    public boolean needWait() {
        return true;
    }

    @Override
    public void run() {

        InitConfig config = new InitConfig.Builder().build();
        WXSDKEngine.initialize((Application) mContext, config);
    }
}
