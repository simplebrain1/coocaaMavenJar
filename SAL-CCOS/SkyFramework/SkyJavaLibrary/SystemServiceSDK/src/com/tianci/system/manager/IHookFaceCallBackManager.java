package com.tianci.system.manager;

import android.content.ContentProviderClient;
import android.os.Bundle;
import android.os.RemoteException;

import com.tianci.system.define.SystemProviderDefines;
import com.tianci.system.listener.HookFaceCallback;
import com.tianci.system.listener.IHookFaceCallback;
import com.tianci.system.utils.ApiUtil;

import java.util.LinkedList;
import java.util.List;

public class IHookFaceCallBackManager {

    public class HookFaceCallBackBinder extends IHookFaceCallback.Stub{ //咱们才是server
        List<HookFaceCallback> mCallbackList = new LinkedList<HookFaceCallback>();

        public synchronized void addCallback(HookFaceCallback callback) {
            if (!this.mCallbackList.contains(callback)) {
                this.mCallbackList.add(callback);
            }
        }

        public synchronized void removeCallback(HookFaceCallback callback) {
            if (this.mCallbackList.contains(callback)) {
                this.mCallbackList.remove(callback);
            }
        }

        @Override
        public void onBarrierException(int type) throws RemoteException {
            if (null != mCallbackList && !mCallbackList.isEmpty()) {
                for (HookFaceCallback callback : mCallbackList) {
                    callback.onBarrierException(type);
                }
            }
        }

        @Override
        public void onMotorException(int type) throws RemoteException {
            if (null != mCallbackList && !mCallbackList.isEmpty()) {
                for (HookFaceCallback callback : mCallbackList) {
                    callback.onMotorException(type);
                }
            }
        }

        @Override
        public void onSystemBusy(boolean isBusy) throws RemoteException {
            if (null != mCallbackList && !mCallbackList.isEmpty()) {
                for (HookFaceCallback callback : mCallbackList) {
                    callback.onSystemBusy(isBusy);
                }
            }
        }

        @Override
        public void onScreenLocationMax(int type) throws RemoteException {
            if (null != mCallbackList && !mCallbackList.isEmpty()) {
                for (HookFaceCallback callback : mCallbackList) {
                    callback.onScreenLocationMax(type);
                }
            }
        }

        @Override
        public void onPedestalLocationMax(int type) throws RemoteException {
            if (null != mCallbackList && !mCallbackList.isEmpty()) {
                for (HookFaceCallback callback : mCallbackList) {
                    callback.onPedestalLocationMax(type);
                }
            }
        }
    }

    private HookFaceCallBackBinder mBinder;

    public void addHookFaceCallback(HookFaceCallback callback) {
        if (callback == null) {
            return;
        }
        if (mBinder == null) { // 注册
            mBinder = new HookFaceCallBackBinder();
            registerBinder(true);
        }
        mBinder.addCallback(callback);
    }

    public void removeHookFaceCallback(HookFaceCallback callback) {
        if (callback == null||mBinder==null) {
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
            client.call(SystemProviderDefines.METHOD_SYSTEM_SET_HOOK_FACE_LISTENER, null, bundle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HookFaceCallBackBinder getBinder() {
        return mBinder;
    }
}
