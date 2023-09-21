package com.tianci.system.callback;

import com.tianci.utils.SkySSSLog;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class OldIPCCallback {
    private static final String TAG = "OldIPCCallback";
    private Object object;
    private Method[] methods;
    private Class[][] args;
    private boolean needRemove;//回调后，是否移除

    public Object getObject() {
        return object;
    }

    protected OldIPCCallback(Object object, String[] methods, Class[][] args, boolean needRemove) {
        this.needRemove = needRemove;
        this.object = object;
        this.args = args;
        this.methods = new Method[methods.length];
        try {
            for (int i = 0; i < methods.length; i++) {
                this.methods[i] = object.getClass().getMethod(methods[i], args[i]);
            }
        } catch (NoSuchMethodException e) {
            SkySSSLog.e(TAG, "OldIPCCallback e=" + e.getMessage());
        }
    }

    public boolean isNeedRemove() {
        return needRemove;
    }

    public void handle(Object data) {
        Method method = methods[0];
        if (method == null) {
            SkySSSLog.e(TAG, "handle method is null");
            return;
        }
        try {
            method.invoke(object, data);
        } catch (IllegalAccessException e) {
            SkySSSLog.e(TAG, "handle e=" + e.getMessage());
        } catch (InvocationTargetException e) {
            SkySSSLog.e(TAG, "handle e=" + e.getMessage());
        }
    }

    public void handle(byte[] data) {
    }
}
