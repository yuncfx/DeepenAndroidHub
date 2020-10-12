package com.deepen.android.hub.performance.tasks.delayinittask;

import com.deepen.android.hub.performance.launchstarter.task.MainTask;
import com.deepen.android.hub.performance.utils.LogUtils;

public class DelayInitTaskB extends MainTask {

    @Override
    public void run() {
        // 模拟一些操作

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LogUtils.i("DelayInitTaskB finished");
    }
}
