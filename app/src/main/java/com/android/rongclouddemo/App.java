package com.android.rongclouddemo;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import io.rong.imkit.RongIM;
import io.rong.push.RongPushClient;

/**
 * Created by Administrator on 2016/12/8.
 */

public class App extends Application {

    private String miAppId = "2882303761517533061";
    private String miAppKey = "5741753333061";

    @Override
    public void onCreate() {
        super.onCreate();
        RongPushClient.registerMiPush(this, miAppId, miAppKey);
        RongIM.init(this);
    }

    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

}
