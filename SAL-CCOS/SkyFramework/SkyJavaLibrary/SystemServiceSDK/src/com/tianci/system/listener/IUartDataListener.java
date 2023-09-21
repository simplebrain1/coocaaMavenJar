package com.tianci.system.listener;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/**
 * Created by hq on 2018/10/24.
 */

public interface IUartDataListener extends IInterface {
    public static abstract class Stub extends BaseListener<OnUartDataListener> implements IUartDataListener {
        private static final String DESCRIPTOR = "com.tianci.system.listener.IUartDataListener";

        public Stub() {
            this.attachInterface(this, DESCRIPTOR);
        }

        public static IUartDataListener asInterface(IBinder binder) {
            if (binder == null) {
                return null;
            }
            IInterface iin = binder.queryLocalInterface(DESCRIPTOR);
            if (iin != null && iin instanceof IUartDataListener) {
                return (IUartDataListener) iin;
            }
            return new Stub.Proxy(binder);
        }

        @Override
        public IBinder asBinder() {
            return this;
        }

        @Override
        public boolean onTransact(int code, Parcel _data, Parcel _reply, int flags) throws RemoteException {
            String descriptor = DESCRIPTOR;
            switch (code) {
                case INTERFACE_TRANSACTION: {
                    _reply.writeString(descriptor);
                    return true;
                }
                case TRANSACTION_onUartDataReceived: {
                    _data.enforceInterface(descriptor);
                    byte[] data = null;
                    int dataLength = _data.readInt();
                    if (dataLength >= 0) {
                        data = new byte[dataLength];
                    }
                    _data.readByteArray(data);
                    onUartDataReceived(data);
                    _reply.writeNoException();
                    return true;
                }
                default: {
                    return super.onTransact(code, _data, _reply, flags);
                }
            }
        }

        private static final class Proxy implements IUartDataListener {
            private IBinder mRemote;

            private Proxy(IBinder remote) {
                mRemote = remote;
            }

            @Override
            public IBinder asBinder() {
                return mRemote;
            }

            @Override
            public void onUartDataReceived(byte[] data) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(data == null ? -1 : data.length);
                    _data.writeByteArray(data);
                    mRemote.transact(TRANSACTION_onUartDataReceived, _data, _reply, 0);
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        static final int TRANSACTION_onUartDataReceived = (FIRST_CALL_TRANSACTION + 0);
    }

    void onUartDataReceived(byte[] data) throws RemoteException;
}
