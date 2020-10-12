package com.deepen.android.hub.performance.tasks;

import com.deepen.android.hub.performance.launchstarter.task.Task;
import com.deepen.android.hub.performance.net.FrescoTraceListener;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.listener.RequestListener;

import java.util.HashSet;
import java.util.Set;

public class InitFrescoTask extends Task {

    @Override
    public void run() {
        Set<RequestListener> listenerset = new HashSet<>();
        listenerset.add(new FrescoTraceListener());
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(mContext).setRequestListeners(listenerset)
                .build();
        Fresco.initialize(mContext,config);
    }
}
