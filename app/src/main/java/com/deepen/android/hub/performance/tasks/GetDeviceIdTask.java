package com.deepen.android.hub.performance.tasks;

import android.content.Context;
import android.telephony.TelephonyManager;

import com.deepen.android.hub.performance.PerformanceApp;
import com.deepen.android.hub.performance.launchstarter.task.Task;

public class GetDeviceIdTask extends Task {
    private String mDeviceId;

    @Override
    public void run() {
        // 真正自己的代码
        TelephonyManager tManager = (TelephonyManager) mContext.getSystemService(
                Context.TELEPHONY_SERVICE);
        mDeviceId = tManager.getDeviceId();
        PerformanceApp app = (PerformanceApp) mContext;
        app.setDeviceId(mDeviceId);
    }
}
