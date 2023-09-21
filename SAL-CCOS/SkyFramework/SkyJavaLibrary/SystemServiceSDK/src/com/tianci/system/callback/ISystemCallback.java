package com.tianci.system.callback;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

import com.tianci.utils.SkySSSLog;

public interface ISystemCallback extends IInterface
{
    public static abstract class Stub extends CallbackList<OnSystemCallback>
            implements ISystemCallback
    {
        private static final String DESCRIPTOR = "com.tianci.system.sdk.adapter.manager.callback.ISystemCallback";

        public Stub()
        {
            this.attachInterface(this, DESCRIPTOR);
        }

        public static ISystemCallback asInterface(IBinder binder)
        {
            if (binder == null)
            {
                return null;
            }
            IInterface iin = binder.queryLocalInterface(DESCRIPTOR);
            if (iin != null && iin instanceof ISystemCallback)
            {
                return (ISystemCallback) iin;
            }
            return new Stub.Proxy(binder);
        }

        @Override
        public IBinder asBinder()
        {
            return this;
        }

        @Override
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags)
                throws RemoteException
        {
            String descriptor = DESCRIPTOR;
            switch (code)
            {
                case INTERFACE_TRANSACTION:
                {
                    reply.writeString(descriptor);
                    return true;
                }
                case TRANSACTION_onPictureModeChange:
                {
                    data.enforceInterface(descriptor);
                    boolean isAIMode = (data.readInt() == 1);
                    Log.d("WJ", "TRANSACTION_onPictureModeChange:isAIMode =   "+isAIMode);
                    onPictureModeChange(isAIMode);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_onSoundModeChange:
                {
                    data.enforceInterface(descriptor);
                    boolean isAIMode = (data.readInt() == 1);
                    Log.d("WJ", "TRANSACTION_onSoundModeChange:isAIMode =   "+isAIMode);
                    onSoundModeChange(isAIMode);
                    reply.writeNoException();
                    return true;
                }
                default:
                {
                    return super.onTransact(code, data, reply, flags);
                }
            }
        }

        private static final class Proxy implements ISystemCallback
        {
            private IBinder mRemote;

            private Proxy(IBinder remote)
            {
                mRemote = remote;
            }

            @Override
            public IBinder asBinder()
            {
                return mRemote;
            }

            @Override
            public void onPictureModeChange(boolean isAIMode) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try
                {
                    SkySSSLog.i("WJ", "onPictureModeChange: _data "+isAIMode);
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(isAIMode?1:0);
                    mRemote.transact(TRANSACTION_onPictureModeChange, _data, _reply, 0);
                    _reply.readException();
                } finally
                {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override
            public void onSoundModeChange(boolean isAIMode) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try
                {
                    SkySSSLog.i("WJ", "onSoundModeChange: _data "+isAIMode);
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(isAIMode?1:0);
                    mRemote.transact(TRANSACTION_onSoundModeChange, _data, _reply, 0);
                    _reply.readException();
                } finally
                {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        static final int TRANSACTION_onPictureModeChange = (FIRST_CALL_TRANSACTION + 0);
        static final int TRANSACTION_onSoundModeChange = (FIRST_CALL_TRANSACTION + 1);
    }

    void onPictureModeChange(boolean isAIMode) throws RemoteException;

    void onSoundModeChange(boolean isAIMode) throws RemoteException;
}
