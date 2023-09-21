package com.tianci.system.helper;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;

import com.tianci.system.callback.IPCCallback;
import com.tianci.system.callback.OnBackLigthChange;
import com.tianci.system.callback.OnSpecularBoostChange;
import com.tianci.system.callback.OnTVColorChange;
import com.tianci.system.callback.RotateHangerCallback;
import com.tianci.system.define.SystemProviderDefines;
import com.tianci.system.listener.OnAodConfigListener;
import com.tianci.system.utils.ApiUtil;
import com.tianci.utils.SkySSSLog;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class TCSystemApiHelper {
    private final static String TAG = "TCSystemApiHelper";
    private ContentResolver mContentResolver;
    private SoftReference<OnAodConfigListener> mOnAodConfigCallbackRef;
    private ConfigObserver mConfigObserver;
    private RotateHangerCallback mRotateHangerCallback;
    private RotateHangerObserver mRotateHangerObserver;
    private HashMap<String, HashSet<IPCCallback>> mBroadcastCallbacks;
    private Context mContext;
    private IPCReceiver mIPCReceiver;
    private volatile boolean isBroadcastRegistered;
    private OnBackLigthChange onBackLigthChange;
    private OnSpecularBoostChange onSpecularBoostChange;
    private BackLightObserver backLightObserver;
    private SpecularBoostObserver specularBoostObserver;
    private OnTVColorChange onTVColorChange;
    private TVColorObserver tvColorObserver;

    public TCSystemApiHelper(ContentResolver contentResolver, Context context) {
        this.mContext = context;
        this.mContentResolver = contentResolver;
    }

    public void addIPCCallback(String cmd, IPCCallback ipcCallback) {
        SkySSSLog.d(TAG, "addIPCCallback cmd=" + cmd + ",ipcCallback=" + ipcCallback);
        addBroadcastCallback(cmd, ipcCallback);
    }

    public synchronized void addBroadcastCallback(String cmd, IPCCallback ipcCallback) {
        SkySSSLog.d(TAG, "addBroadcastCallback cmd=" + cmd + ",ipcCallback=" +
                ipcCallback + ",isBroadcastRegistered=" + isBroadcastRegistered);
        if (mBroadcastCallbacks == null) {
            mBroadcastCallbacks = new HashMap<String, HashSet<IPCCallback>>();
        }
        HashSet<IPCCallback> list = mBroadcastCallbacks.get(cmd);
        if (list == null) {
            list = new HashSet<IPCCallback>();
            mBroadcastCallbacks.put(cmd, list);
        }
        list.add(ipcCallback);
        if (isBroadcastRegistered) {
            return;
        }
        if (mIPCReceiver == null) {
            mIPCReceiver = new IPCReceiver();
            isBroadcastRegistered = true;
        }
        mContext.registerReceiver(mIPCReceiver, new IntentFilter(ApiUtil.ACTION_IPC_CALLBACK));
    }

    public synchronized void removeIPCCallback(String cmd, Object callback) {
        SkySSSLog.d(TAG, "removeIPCCallback cmd=" + cmd + ",callback=" + callback);
        if (mBroadcastCallbacks != null) {
            HashSet<IPCCallback> callbacks = mBroadcastCallbacks.get(cmd);
            if (callbacks == null || callbacks.isEmpty()) {
                mBroadcastCallbacks.remove(cmd);
            } else {
                IPCCallback deleteRef = null;
                for (IPCCallback oldCallback : callbacks) {
                    if (callback == oldCallback.getCallBack()) {
                        deleteRef = oldCallback;
                        break;
                    }
                }
                if (deleteRef != null) {
                    callbacks.remove(deleteRef);
                }
                if (callbacks.isEmpty()) {
                    mBroadcastCallbacks.remove(cmd);
                }
            }
            if (mBroadcastCallbacks.isEmpty()) {
                SkySSSLog.d(TAG, "removeIPCCallback isEmpty isBroadcastRegistered="
                        + isBroadcastRegistered);
                if (isBroadcastRegistered) {
                    isBroadcastRegistered = false;
                    mContext.unregisterReceiver(mIPCReceiver);
                }
            }
        }
    }

    private synchronized void removeIPCCallbacks(String cmd, List<IPCCallback> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        SkySSSLog.d(TAG, "removeIPCCallbacks cmd=" + cmd + ",list.size=" + list.size());
        if (mBroadcastCallbacks != null) {
            HashSet<IPCCallback> callbacks = mBroadcastCallbacks.get(cmd);
            if (callbacks == null || callbacks.isEmpty()) {
                mBroadcastCallbacks.remove(cmd);
            } else {
                SkySSSLog.d(TAG, "removeIPCCallbacks callbacks.size=" + callbacks.size());
                for (IPCCallback ref : list) {
                    callbacks.remove(ref);
                }
                if (callbacks.isEmpty()) {
                    SkySSSLog.d(TAG, "removeIPCCallbacks callbacks.isEmpty()");
                    mBroadcastCallbacks.remove(cmd);
                }
            }
        }
    }

    /**
     * 注册AOD相关设置项变更监听
     */
    public void registerAodConfigListener(OnAodConfigListener configCallback) {
        SkySSSLog.d(TAG, "registerAodConfigListener configCallback=" + configCallback);
        if (mOnAodConfigCallbackRef != null) {
            mOnAodConfigCallbackRef.clear();
            mOnAodConfigCallbackRef = null;
        } else {
            registerAodConfigListener();
        }
        mOnAodConfigCallbackRef = new SoftReference<OnAodConfigListener>(configCallback);
    }

    /**
     * 解注册AOD相关设置项变更监听
     */
    public void unRegisterAodConfigListener() {
        SkySSSLog.d(TAG, "unRegisterAodConfigListener");
        if (mOnAodConfigCallbackRef != null) {
            mOnAodConfigCallbackRef.clear();
            mOnAodConfigCallbackRef = null;
        }
        if (mConfigObserver != null) {
            mContentResolver.unregisterContentObserver(mConfigObserver);
            mConfigObserver = null;
        }
    }

    private void registerAodConfigListener() {
        SkySSSLog.d(TAG, "unRegisterAodConfigListener mConfigObserver=" + mConfigObserver);
        if (mConfigObserver != null) {
            return;
        }
        mConfigObserver = new ConfigObserver();
        mContentResolver.registerContentObserver(Uri.parse(SystemProviderDefines.URI_PATH_AOD),
                true, mConfigObserver);
    }

    class ConfigObserver extends ContentObserver {

        private ConfigObserver() {
            super(null);
        }

        @Override
        public void onChange(boolean selfChange, Uri uri) {
            String query = uri.getQuery();
            String str = uri.getLastPathSegment();
            SkySSSLog.d(TAG, "onChange selfChange=" + selfChange + ",query=" + query + ",str=" + str);
            if (TextUtils.isEmpty(query) || !query.contains("=")) {
                return;
            }
            OnAodConfigListener listener = mOnAodConfigCallbackRef.get();
            if (listener == null) {
                return;
            }
            String value = uri.getQueryParameter("arg");
            SkySSSLog.d(TAG, "onChange value=" + value);
            if (SystemProviderDefines.METHOD_AOD_AI_SWITCH.equals(str)) {
                boolean ret = "1".equals(value);
                SkySSSLog.d(TAG, "onChange ret=" + ret);
                listener.onAIStandbySwitched(ret);
            } else if (SystemProviderDefines.METHOD_AOD_SWITCH.equals(str)) {
                try {
                    int type = Integer.parseInt(value);
                    int mode = Integer.parseInt(uri.getQueryParameter("arg2"));
                    SkySSSLog.d(TAG, "onChange type=" + type + ",mode=" + mode);
                    listener.onAodShowModeSwitched(type, mode);
                } catch (Exception e) {
                    SkySSSLog.e(TAG, "onChange METHOD_AOD_SWITCH e=" + e.getMessage());
                }
            }
        }
    }

    public void setRotateHangerCallback(RotateHangerCallback callback) {
        SkySSSLog.d(TAG, "setRotateHangerCallback callback=" + callback);
        mRotateHangerCallback = callback;
        if (callback == null) {
            mContentResolver.unregisterContentObserver(mRotateHangerObserver);
            mRotateHangerObserver = null;
        } else {
            if (mRotateHangerObserver != null) {
                return;
            }
            mRotateHangerObserver = new RotateHangerObserver();
            mContentResolver.registerContentObserver(Uri.parse(SystemProviderDefines.URI_PATH_ROTATE_HANGER),
                    false, mRotateHangerObserver);
        }
    }

    public void setBackLightCallback(OnBackLigthChange callback) {
        onBackLigthChange = callback;
        if (callback == null) {
            if (backLightObserver != null) {
                mContentResolver.unregisterContentObserver(backLightObserver);
                backLightObserver = null;
            }
        } else {
            if (backLightObserver != null) {
                return;
            }
            backLightObserver = new BackLightObserver();
            mContentResolver.registerContentObserver(Uri.parse(SystemProviderDefines.URI_PATH_BACK_LIGHT),
                    false, backLightObserver);
        }
    }

    public void setSpecularBoostCallback(OnSpecularBoostChange callback) {
        onSpecularBoostChange = callback;
        if (callback == null) {
            if (specularBoostObserver != null) {
                mContentResolver.unregisterContentObserver(specularBoostObserver);
                specularBoostObserver = null;
            }
        } else {
            if (specularBoostObserver != null) {
                return;
            }
            specularBoostObserver = new SpecularBoostObserver();
            mContentResolver.registerContentObserver(Uri.parse(SystemProviderDefines.URI_PATH_SPECULAR_BOOST),
                    false, specularBoostObserver);
        }
    }

    /**
     * 设置饱和度监听
     *
     * @param callback
     */
    public void setTVColorCallback(OnTVColorChange callback) {
        onTVColorChange = callback;
        if (callback == null) {
            if (tvColorObserver != null) {
                mContentResolver.unregisterContentObserver(tvColorObserver);
                tvColorObserver = null;
            }
        } else {
            if (tvColorObserver != null) {
                return;
            }
            tvColorObserver = new TVColorObserver();
            mContentResolver.registerContentObserver(Uri.parse(SystemProviderDefines.URI_PATH_TV_COLOR),
                    false, tvColorObserver);
        }
    }

    class RotateHangerObserver extends ContentObserver {

        private RotateHangerObserver() {
            super(null);
        }

        @Override
        public void onChange(boolean selfChange, Uri uri) {
            if (mRotateHangerCallback == null) {
                return;
            }
            String valueStr = uri.getQueryParameter("arg");
            SkySSSLog.d(TAG, "onChange selfChange=" + selfChange + ",valueStr=" + valueStr);
            int value = Integer.parseInt(valueStr);
            switch (value) {
                case 0:
                    mRotateHangerCallback.onScreenSwitchStart();
                    break;
                case 1:
                    mRotateHangerCallback.onScreenSwitchEnd();
                    break;
                case -1:
                    mRotateHangerCallback.onScreenSwitchError();
                    break;
                case 2:
                    mRotateHangerCallback.onConnectChanged(true);
                    break;
                case 3:
                    mRotateHangerCallback.onConnectChanged(false);
                    break;
            }
        }
    }

    /**
     * 用于大数据结果回调，比如List
     */
    class IPCReceiver extends BroadcastReceiver {
        private int count = 0;

        @Override
        public void onReceive(Context context, Intent intent) {
            if (ApiUtil.ACTION_IPC_CALLBACK.equals(intent.getAction())) {
                String cmd = intent.getStringExtra(ApiUtil.KEY_METHOD);
                Bundle value = intent.getBundleExtra(ApiUtil.KEY_RESULT);
                int methodIndex = 0;
                if (value != null) {
                    methodIndex = value.getInt(ApiUtil.KEY_PARAM, 0);
                }
                SkySSSLog.d(TAG, "onReceive cmd=" + cmd + ",value=" + value + ",methodIndex="
                        + methodIndex);
                HashSet<IPCCallback> callbacks = mBroadcastCallbacks.get(cmd);
                if (callbacks != null && !callbacks.isEmpty()) {
                    List<IPCCallback> removeRefs = null;
                    synchronized (TCSystemApiHelper.this) {
                        for (IPCCallback callback : callbacks) {
                            if (callback != null) {
                                count = 0;
                                callback.onIPCNotified(methodIndex, value);
                                if (callback.isNeedRemove()) {
                                    if (removeRefs == null) {
                                        removeRefs = new ArrayList<IPCCallback>();
                                    }
                                    removeRefs.add(callback);
                                }
                            }
                        }
                    }
                    if (removeRefs != null) {
                        removeIPCCallbacks(cmd, removeRefs);
                    }
                }
                if (mBroadcastCallbacks.isEmpty()) {
                    count++;
                    SkySSSLog.d(TAG, "onReceive count=" + count);
                    if (count == 5) {
                        synchronized (TCSystemApiHelper.this) {
                            SkySSSLog.d(TAG, "onReceive isBroadcastRegistered="
                                    + isBroadcastRegistered);
                            if (isBroadcastRegistered) {
                                isBroadcastRegistered = false;
                                context.unregisterReceiver(this);
                            }
                        }
                        count = 0;
                    }
                }
            }
        }
    }

    class BackLightObserver extends ContentObserver {

        private BackLightObserver() {
            super(null);
        }

        @Override
        public void onChange(boolean selfChange, Uri uri) {
            if (onBackLigthChange == null) {
                return;
            }
            String valueStr = uri.getQueryParameter("arg");
            SkySSSLog.d(TAG, "onChange selfChange=" + selfChange + ",valueStr=" + valueStr);
            int value = Integer.parseInt(valueStr);
            onBackLigthChange.onBackLightChange(value);
        }
    }

    class TVColorObserver extends ContentObserver {

        private TVColorObserver() {
            super(null);
        }

        @Override
        public void onChange(boolean selfChange, Uri uri) {
            if (onTVColorChange == null) {
                return;
            }
            String valueStr = uri.getQueryParameter("arg");
            SkySSSLog.d(TAG, "onChange selfChange=" + selfChange + ",valueStr=" + valueStr);
            int value = Integer.parseInt(valueStr);
            onTVColorChange.onTVColorChange(value);
        }
    }

    class SpecularBoostObserver extends ContentObserver {

        private SpecularBoostObserver() {
            super(null);
        }

        @Override
        public void onChange(boolean selfChange, Uri uri) {
            if (onSpecularBoostChange == null) {
                return;
            }
            String valueStr = uri.getQueryParameter("arg");
            SkySSSLog.d(TAG, "onChange selfChange=" + selfChange + ",valueStr=" + valueStr);
            onSpecularBoostChange.onSpecularBoostChange(valueStr);
        }
    }
}
