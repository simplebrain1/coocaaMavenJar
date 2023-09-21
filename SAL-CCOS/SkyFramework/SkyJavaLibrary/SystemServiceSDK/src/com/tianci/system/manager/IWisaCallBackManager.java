package com.tianci.system.manager;

import android.content.ContentProviderClient;
import android.os.Bundle;
import android.os.RemoteException;

import com.tianci.system.define.SystemProviderDefines;
import com.tianci.system.listener.IWisaCallback;
import com.tianci.system.listener.WisaCallback;
import com.tianci.system.utils.ApiUtil;

import java.util.LinkedList;
import java.util.List;

public class IWisaCallBackManager {
    public class WisaCallBackBinder extends IWisaCallback.Stub { //咱们才是server
        List<WisaCallback> mCallbackList = new LinkedList<WisaCallback>();

        public synchronized void addCallback(WisaCallback callback) {
            if (!this.mCallbackList.contains(callback)) {
                this.mCallbackList.add(callback);
            }
        }

        public synchronized void removeCallback(WisaCallback callback) {
            if (this.mCallbackList.contains(callback)) {
                this.mCallbackList.remove(callback);
            }
        }

        @Override
        public void onConnect(int type) throws RemoteException {
            if (null != mCallbackList && !mCallbackList.isEmpty()) {
                for (WisaCallback callback : mCallbackList) {
                    callback.onConnect(type);
                }
            }
        }

        @Override
        public void onException(int _exceptionType) throws RemoteException {
            if (null != mCallbackList && !mCallbackList.isEmpty()) {
                for (WisaCallback callback : mCallbackList) {
                    callback.onException(_exceptionType);
                }
            }
        }

        @Override
        public void onDisConnect(int type) throws RemoteException {
            if (null != mCallbackList && !mCallbackList.isEmpty()) {
                for (WisaCallback callback : mCallbackList) {
                    callback.onDisConnect(type);
                }
            }
        }
    }

    private WisaCallBackBinder mBinder;

    public void addWisaCallback(WisaCallback callback) {
        if (callback == null) {
            return;
        }
        if (mBinder == null) { // 注册
            mBinder = new WisaCallBackBinder();
            registerBinder(true);
        }
        mBinder.addCallback(callback);
    }

    public void removeWisaCallback(WisaCallback callback) {
        if (callback == null) {
            return;
        }
        mBinder.removeCallback(callback);
        if (mBinder.mCallbackList.size() == 0) { //木有任何监听了
            registerBinder(false);
            mBinder = null;
        }
    }

    private void registerBinder(boolean isReg) {
        ContentProviderClient client = ApiUtil.getClient();
        if (client == null) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putBinder("binder", getBinder());
        bundle.putBoolean("isReg", isReg);
        try {
            client.call(SystemProviderDefines.METHOD_SYSTEM_SET_WISA_LISTENER, null, bundle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public WisaCallBackBinder getBinder() {
        return mBinder;
    }
}
