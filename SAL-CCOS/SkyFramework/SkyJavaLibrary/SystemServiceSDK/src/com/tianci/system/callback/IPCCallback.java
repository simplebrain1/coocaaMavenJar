package com.tianci.system.callback;

import android.os.Bundle;

import com.tianci.system.utils.ApiUtil;
import com.tianci.utils.SkySSSLog;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class IPCCallback {
    private static final String TAG = "IPCCallback";
    private Object object;
    private Method[] methods;
    private Class[][] args;
    private boolean needRemove;//回调后，是否移除

    public IPCCallback(Object object, String[] methods, Class[][] args, boolean needRemove) {
        this.needRemove = needRemove;
        this.object = object;
        this.args = args;
        this.methods = new Method[methods.length];
        try {
            for (int i = 0; i < methods.length; i++) {
                this.methods[i] = object.getClass().getMethod(methods[i], args[i]);
            }
        } catch (NoSuchMethodException e) {
            SkySSSLog.e(TAG, "IPCCallback e=" + e.getMessage());
        }
    }

    public Object getCallBack() {
        return object;
    }

    public boolean isNeedRemove() {
        return needRemove;
    }

    public void onIPCNotified(int index, Bundle data) {
        SkySSSLog.d(TAG, "onIPCNotified index=" + index + ",data=" + data);
        if (index >= methods.length || index < 0) {
            SkySSSLog.e(TAG, "onIPCNotified index=" + index + ",length=" + methods.length);
            return;
        }
        Method method = methods[index];
        if (method == null) {
            SkySSSLog.e(TAG, "onIPCNotified method is null index=" + index + ",length=" + methods.length);
            return;
        }
        Class arg = args[index][0];//目前只支持一个参数回调接口
        if (arg == null) {
            SkySSSLog.e(TAG, "onIPCNotified arg is null index=" + index + ",length=" + methods.length);
            return;
        }
        String clsName = arg.getSimpleName();
        SkySSSLog.d(TAG, "onIPCNotified clsName=" + clsName);
        try {
            if ("Boolean".equals(clsName)) {
                method.invoke(object, data.getBoolean(ApiUtil.KEY_RESULT));
            } else if ("Integer".equals(clsName)) {
                method.invoke(object, data.getInt(ApiUtil.KEY_RESULT));
            } else if ("String".equals(clsName)) {
                method.invoke(object, data.getString(ApiUtil.KEY_RESULT));
            } else if ("List".equals(clsName)) {
                Object obj = data.getSerializable(ApiUtil.KEY_RESULT);
                SkySSSLog.d(TAG, "onIPCNotified list cls=" + obj.getClass().getSimpleName());
                method.invoke(object, obj);
            } else {
                Object obj = data.getSerializable(ApiUtil.KEY_RESULT);
                SkySSSLog.d(TAG, "onIPCNotified cls=" + obj.getClass().getSimpleName());
                method.invoke(object, obj);
            }
        } catch (IllegalAccessException e) {
            SkySSSLog.e(TAG, "onIPCNotified e=" + e.getMessage());
        } catch (InvocationTargetException e) {
            SkySSSLog.e(TAG, "onIPCNotified e=" + e.getMessage());
        } catch (Exception e) {
            SkySSSLog.e(TAG, "onIPCNotified e=" + e.getMessage());
        }
    }

}
