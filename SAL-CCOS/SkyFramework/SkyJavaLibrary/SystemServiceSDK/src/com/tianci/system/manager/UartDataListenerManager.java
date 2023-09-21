package com.tianci.system.manager;

import com.tianci.system.callback.SerializableBinder;
import com.tianci.system.listener.IUartDataListener;
import com.tianci.system.listener.OnUartDataListener;

public class UartDataListenerManager {

    public static class UartDataBinder extends IUartDataListener.Stub {

        private static final long serialVersionUID = 1140058363367903851L;

        @Override
        public synchronized void onUartDataReceived(byte[] data) {
            if (mListener != null) {
                mListener.onUartDataReceived(data);
            }
        }
    }

    private UartDataBinder mBinder;

    public synchronized SerializableBinder setOnUartDataListener(OnUartDataListener listener) {
        SerializableBinder binder = null;
        if (mBinder == null && listener != null) {
            mBinder = new UartDataBinder();
            mBinder.setListener(listener);
            binder = mBinder;
        } else if (mBinder != null && listener == null) {
            binder = mBinder;
            mBinder.setListener(null);
            mBinder = null;
        }
        return binder;
    }
}
