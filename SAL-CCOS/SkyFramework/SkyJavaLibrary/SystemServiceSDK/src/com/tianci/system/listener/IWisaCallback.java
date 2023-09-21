package com.tianci.system.listener;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IWisaCallback extends IInterface {


    abstract class Stub extends Binder implements IWisaCallback {

        public static final String DESCRIPTOR = "com.tianci.system.listener.IWisaCallback";

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IWisaCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iIn = obj.queryLocalInterface(DESCRIPTOR);

            if (iIn != null && iIn instanceof IWisaCallback) {
                return (IWisaCallback) iIn;
            }
            return new Stub.Proxy(obj);
        }

        @Override
        public IBinder asBinder() {
            return this;
        }

        @Override
        protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == TRANSACTION_onConnect) {
                data.enforceInterface(DESCRIPTOR);
                int _connType = data.readInt();
                onConnect(_connType);
                return true;
            } else if (code == TRANSACTION_onException) {
                data.enforceInterface(DESCRIPTOR);
                int _exceptionType = data.readInt();
                onException(_exceptionType);
                return true;
            } else if (code == TRANSACTION_onDisConnect) {
                data.enforceInterface(DESCRIPTOR);
                int _disType = data.readInt();
                onDisConnect(_disType);
                return true;
            }
            return super.onTransact(code, data, reply, flags);
        }


        public static final int TRANSACTION_onConnect = IBinder.FIRST_CALL_TRANSACTION + 0;
        public static final int TRANSACTION_onException = IBinder.FIRST_CALL_TRANSACTION + 1;
        public static final int TRANSACTION_onDisConnect = IBinder.FIRST_CALL_TRANSACTION + 2;


        public static class Proxy implements IWisaCallback {
            IBinder mRemote;

            public Proxy(IBinder obj) {
                this.mRemote = obj;
            }

            @Override
            public IBinder asBinder() {
                return mRemote;
            }


            @Override
            public void onConnect(int type) throws RemoteException {
                Parcel _data = null;
                try {
                    _data = Parcel.obtain();
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(type);
                    mRemote.transact(TRANSACTION_onConnect, _data, null, 0);
                } finally {
                    _data.recycle();
                }
            }

            @Override
            public void onException(int exceptionType) throws RemoteException {
                Parcel _data = null;
                try {
                    _data = Parcel.obtain();
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(exceptionType);
                    mRemote.transact(TRANSACTION_onException, _data, null, 0);
                } finally {
                    _data.recycle();
                }
            }

            @Override
            public void onDisConnect(int type) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(type);
                    mRemote.transact(TRANSACTION_onDisConnect, _data, null, 0);
                } finally {
                    _data.recycle();
                }
            }
        }
    }


    public abstract void onConnect(int type) throws RemoteException;

    public abstract void onException(int exceptionType) throws RemoteException;

    public abstract void onDisConnect(int type) throws RemoteException;


}
