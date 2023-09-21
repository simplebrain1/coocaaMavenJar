package com.tianci.system.listener;

import com.tianci.system.callback.SerializableBinder;

public abstract class BaseListener<T> extends SerializableBinder {
    protected T mListener;

    public synchronized void setListener(T listener) {
        mListener = listener;
    }
}
