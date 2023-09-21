package com.tianci.system.callback;

import java.util.ArrayList;
import java.util.List;

public abstract class CallbackList<T> extends SerializableBinder {
    protected List<T> mCallbackList;

    public synchronized int register(T callback) {
        if (mCallbackList == null) {
            mCallbackList = new ArrayList<T>(1);
        }
        for (T c : mCallbackList) {
            if (c == callback) {
                return mCallbackList.size();
            }
        }
        mCallbackList.add(callback);
        return mCallbackList.size();
    }

    public synchronized int unregister(T callback) {
        if (mCallbackList == null || callback == null) {
            return -1;
        }
        mCallbackList.remove(callback);
        return mCallbackList.size();
    }
}
