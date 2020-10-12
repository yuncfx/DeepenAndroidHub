package com.deepen.android.hub.performance.tasks.delayinittask;

import com.deepen.android.hub.performance.launchstarter.task.MainTask;
import com.deepen.android.hub.performance.utils.LogUtils;

public class DelayInitTaskA extends MainTask {

    @Override
    public void run() {
        // 模拟一些操作
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LogUtils.i("DelayInitTaskA finished");
    }
}
