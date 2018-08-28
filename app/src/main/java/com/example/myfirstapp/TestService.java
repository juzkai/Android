package com.example.myfirstapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class TestService extends Service{
    private final String TAG = "TestService";

    /**
     * 需要实现的方法
     * @param intent
     * @return
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind方法被调用");
        return null;
    }

    /**
     * service被创建时调用
     */
    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate方法被调用");
        super.onCreate();
    }

    /**
     * service被启动时调用
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand方法被调用");
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * service被关闭时调用
     */
    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy方法被调用");
        super.onDestroy();
    }
}
