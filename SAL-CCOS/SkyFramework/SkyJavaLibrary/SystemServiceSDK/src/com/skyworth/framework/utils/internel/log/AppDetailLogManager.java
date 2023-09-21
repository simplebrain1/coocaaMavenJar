package com.skyworth.framework.utils.internel.log;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import com.skyworth.framework.skysdk.properties.SkySystemProperties;

public class AppDetailLogManager {
    private final static String TAG = "AppDetailLog";
    public final static String APP_DETAIL_LOG_EN_PROP = "persist.appdetail_log.en";
    public static final String APP_DETAIL_LOG_EN_NOTIFY = "APP_DETAIL_LOG_EN_NOTIFY";
    private static AppDetailLogEnReceiver mAppDetailLogEnReceiver = null;

    public static void regAppDetailReceiver(Context mContext) {
        Log.d(TAG, "regAppDetailReceiver");
        if (mContext == null) {
            return;
        }
        Context ctx = mContext.getApplicationContext();
        if (ctx == null) {
            ctx = mContext;
        }
        if (mAppDetailLogEnReceiver == null) {
            mAppDetailLogEnReceiver = new AppDetailLogEnReceiver();
        }
        ctx.registerReceiver(
                mAppDetailLogEnReceiver, new IntentFilter(APP_DETAIL_LOG_EN_NOTIFY));
    }

    public static void unRegAppDetailReceiver(Context mContext) {
        Log.d(TAG, "unRegAppDetailReceiver");
        if (mAppDetailLogEnReceiver != null && mContext != null) {
            Context ctx = mContext.getApplicationContext();
            if (ctx == null) {
                ctx = mContext;
            }
            try {
                ctx.unregisterReceiver(mAppDetailLogEnReceiver);
                mAppDetailLogEnReceiver = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class AppDetailLogEnReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String appDetailLogEnProp = SkySystemProperties.getProperty(APP_DETAIL_LOG_EN_PROP);
            appDetailLogEn = "1".equals(appDetailLogEnProp) ? true : false;
        }
    }

    /**
     * 通知App detail log 开关状态变化
     */
    public static void notifyAppDetailLogEnChange(Context mContext) {
        Context ctx = mContext.getApplicationContext();
        if (ctx == null) {
            ctx = mContext;
        }
        Intent intent = new Intent(APP_DETAIL_LOG_EN_NOTIFY);
        ctx.sendBroadcast(intent);
    }


    private static volatile Boolean appDetailLogEn = null;

    public static boolean isAppDetailLogEnable() {
        if (appDetailLogEn == null) {
            String appDetailLogEnProp = SkySystemProperties.getProperty(APP_DETAIL_LOG_EN_PROP);
            if ("1".equals(appDetailLogEnProp)) {
                appDetailLogEn = true;
            } else {
                appDetailLogEn = false;
            }
        }
        return appDetailLogEn;
    }
}
