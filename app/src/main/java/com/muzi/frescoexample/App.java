package com.muzi.frescoexample;

import android.app.Application;

import com.facebook.common.logging.FLog;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.listener.RequestLoggingListener;

import java.util.HashSet;
import java.util.Set;

/**
 * 作者: lipeng
 * 时间: 2018/5/25
 * 邮箱: lipeng@moyi365.com
 * 功能:
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FLog.setMinimumLoggingLevel(FLog.VERBOSE);
        Set<RequestListener> listeners = new HashSet<>();
        listeners.add(new RequestLoggingListener());
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .setRequestListeners(listeners)
                .build();
        Fresco.initialize(this, config);
    }
}
