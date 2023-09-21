package com.tianci.system.listener;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IHookFaceCallback extends IInterface {
    abstract class Stub extends Binder implements IHookFaceCallback {
        public static final String DESCRIPTOR = "com.tianci.system.listener.IHookFaceCallback";

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IHookFaceCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iIn = obj.queryLocalInterface(DESCRIPTOR);

            if (iIn != null && iIn instanceof IHookFaceCallback) {
                return (IHookFaceCallback) iIn;
            }
            return new Stub.Proxy(obj);
        }

        @Override
        public IBinder asBinder() {
            return this;
        }

        @Override
        protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == TRANSACTION_onBarrierException) {
                data.enforceInterface(DESCRIPTOR);
                int _type = data.readInt();
                onBarrierException(_type);
                return true;
            } else if (code == TRANSACTION_onMotorException) {
                data.enforceInterface(DESCRIPTOR);
                int _type = data.readInt();
                onMotorException(_type);
                return true;
            } else if (code == TRANSACTION_onSystemBusy) {
                data.enforceInterface(DESCRIPTOR);
                boolean _isBusy = data.readInt() == 1 ? true : false;
                onSystemBusy(_isBusy);
                return true;
            } else if (code == TRANSACTION_onScreenLocationMax){
                data.enforceInterface(DESCRIPTOR);
                int _type = data.readInt();
                onScreenLocationMax(_type);
                return true;
            }else if (code == TRANSACTION_onPedestalLocationMax){
                data.enforceInterface(DESCRIPTOR);
                int _type = data.readInt();
                onPedestalLocationMax(_type);
                return true;
            }
            return super.onTransact(code, data, reply, flags);
        }

        public static final int TRANSACTION_onBarrierException = IBinder.FIRST_CALL_TRANSACTION;
        public static final int TRANSACTION_onMotorException = IBinder.FIRST_CALL_TRANSACTION + 1;
        public static final int TRANSACTION_onSystemBusy = IBinder.FIRST_CALL_TRANSACTION + 2;
        public static final int TRANSACTION_onScreenLocationMax = IBinder.FIRST_CALL_TRANSACTION + 3;
        public static final int TRANSACTION_onPedestalLocationMax = IBinder.FIRST_CALL_TRANSACTION + 4;

        public static class Proxy implements IHookFaceCallback{

            IBinder mRemote;

            public Proxy(IBinder obj) {
                this.mRemote = obj;
            }

            @Override
            public IBinder asBinder() {
                return mRemote;
            }

            @Override
            public void onBarrierException(int type) throws RemoteException {
                Parcel _data=Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(type);
                    mRemote.transact(TRANSACTION_onBarrierException, _data, null, 0);
                }finally {
                    _data.recycle();
                }
            }

            @Override
            public void onMotorException(int type) throws RemoteException {
                Parcel _data=Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(type);
                    mRemote.transact(TRANSACTION_onMotorException, _data, null, 0);
                }finally {
                    _data.recycle();
                }
            }

            @Override
            public void onSystemBusy(boolean isBusy) throws RemoteException {
                Parcel _data=Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(isBusy?1:0);
                    mRemote.transact(TRANSACTION_onSystemBusy, _data, null, 0);
                }finally {
                    _data.recycle();
                }
            }

            @Override
            public void onScreenLocationMax(int type) throws RemoteException {
                Parcel _data=Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(type);
                    mRemote.transact(TRANSACTION_onScreenLocationMax, _data, null, 0);
                }finally {
                    _data.recycle();
                }
            }

            @Override
            public void onPedestalLocationMax(int type) throws RemoteException {
                Parcel _data=Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(type);
                    mRemote.transact(TRANSACTION_onPedestalLocationMax, _data, null, 0);
                }finally {
                    _data.recycle();
                }
            }
        }
    }

    public abstract void onBarrierException(int type) throws RemoteException;

    public abstract void onMotorException(int type) throws RemoteException;

    public abstract void onSystemBusy(boolean isBusy) throws RemoteException;

    public abstract void onScreenLocationMax(int type) throws RemoteException;

    public abstract void onPedestalLocationMax(int type) throws RemoteException;

}
