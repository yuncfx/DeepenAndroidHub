package com.deepen.android.hub.performance.tasks;

import com.deepen.android.hub.performance.launchstarter.task.Task;
import com.tencent.bugly.crashreport.CrashReport;

public class InitBuglyTask extends Task {

    @Override
    public void run() {
        CrashReport.initCrashReport(mContext, "注册时申请的APPID", false);
    }
}
