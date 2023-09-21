/*
 * This file is auto-generated.  DO NOT MODIFY.
 */
package com.skyworth.smc;
// Declare any non-default types here with import statements

public interface ISystemEffectDetectInterface extends android.os.IInterface
{
  /** Default implementation for ISystemEffectDetectInterface. */
  public static class Default implements ISystemEffectDetectInterface
  {
    /**
         * Demonstrates some basic types that you can use as parameters
         * and return values in AIDL.
         */
    @Override public void startByteDanceEffect(int mode, com.skyworth.smc.listener.IStartByteDanceEffectListener listener) throws android.os.RemoteException
    {
    }
    @Override public void stopByteDanceEffect(com.skyworth.smc.listener.IStopByteDanceEffectListener listener) throws android.os.RemoteException
    {
    }
    @Override public void registerHandInfoListener(com.skyworth.smc.listener.IHandInfoListener handInfoListener) throws android.os.RemoteException
    {
    }
    @Override public void registerFaceInfoListener(com.skyworth.smc.listener.IFaceInfoListener faceInfoListener) throws android.os.RemoteException
    {
    }
    @Override
    public android.os.IBinder asBinder() {
      return null;
    }
  }
  /** Local-side IPC implementation stub class. */
  public static abstract class Stub extends android.os.Binder implements ISystemEffectDetectInterface
  {
    private static final String DESCRIPTOR = "com.skyworth.smc.ISystemEffectDetectInterface";
    /** Construct the stub at attach it to the interface. */
    public Stub()
    {
      this.attachInterface(this, DESCRIPTOR);
    }
    /**
     * Cast an IBinder object into an com.skyworth.smc.ISystemEffectDetectInterface interface,
     * generating a proxy if needed.
     */
    public static ISystemEffectDetectInterface asInterface(android.os.IBinder obj)
    {
      if ((obj==null)) {
        return null;
      }
      android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
      if (((iin!=null)&&(iin instanceof ISystemEffectDetectInterface))) {
        return ((ISystemEffectDetectInterface)iin);
      }
      return new Proxy(obj);
    }
    @Override public android.os.IBinder asBinder()
    {
      return this;
    }
    @Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
    {
      String descriptor = DESCRIPTOR;
      switch (code)
      {
        case INTERFACE_TRANSACTION:
        {
          reply.writeString(descriptor);
          return true;
        }
        case TRANSACTION_startByteDanceEffect:
        {
          data.enforceInterface(descriptor);
          int _arg0;
          _arg0 = data.readInt();
          com.skyworth.smc.listener.IStartByteDanceEffectListener _arg1;
          _arg1 = com.skyworth.smc.listener.IStartByteDanceEffectListener.Stub.asInterface(data.readStrongBinder());
          this.startByteDanceEffect(_arg0, _arg1);
          reply.writeNoException();
          return true;
        }
        case TRANSACTION_stopByteDanceEffect:
        {
          data.enforceInterface(descriptor);
          com.skyworth.smc.listener.IStopByteDanceEffectListener _arg0;
          _arg0 = com.skyworth.smc.listener.IStopByteDanceEffectListener.Stub.asInterface(data.readStrongBinder());
          this.stopByteDanceEffect(_arg0);
          reply.writeNoException();
          return true;
        }
        case TRANSACTION_registerHandInfoListener:
        {
          data.enforceInterface(descriptor);
          com.skyworth.smc.listener.IHandInfoListener _arg0;
          _arg0 = com.skyworth.smc.listener.IHandInfoListener.Stub.asInterface(data.readStrongBinder());
          this.registerHandInfoListener(_arg0);
          reply.writeNoException();
          return true;
        }
        case TRANSACTION_registerFaceInfoListener:
        {
          data.enforceInterface(descriptor);
          com.skyworth.smc.listener.IFaceInfoListener _arg0;
          _arg0 = com.skyworth.smc.listener.IFaceInfoListener.Stub.asInterface(data.readStrongBinder());
          this.registerFaceInfoListener(_arg0);
          reply.writeNoException();
          return true;
        }
        default:
        {
          return super.onTransact(code, data, reply, flags);
        }
      }
    }
    private static class Proxy implements ISystemEffectDetectInterface
    {
      private android.os.IBinder mRemote;
      Proxy(android.os.IBinder remote)
      {
        mRemote = remote;
      }
      @Override public android.os.IBinder asBinder()
      {
        return mRemote;
      }
      public String getInterfaceDescriptor()
      {
        return DESCRIPTOR;
      }
      /**
           * Demonstrates some basic types that you can use as parameters
           * and return values in AIDL.
           */
      @Override public void startByteDanceEffect(int mode, com.skyworth.smc.listener.IStartByteDanceEffectListener listener) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeInt(mode);
          _data.writeStrongBinder((((listener!=null))?(listener.asBinder()):(null)));
          boolean _status = mRemote.transact(Stub.TRANSACTION_startByteDanceEffect, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().startByteDanceEffect(mode, listener);
            return;
          }
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      @Override public void stopByteDanceEffect(com.skyworth.smc.listener.IStopByteDanceEffectListener listener) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeStrongBinder((((listener!=null))?(listener.asBinder()):(null)));
          boolean _status = mRemote.transact(Stub.TRANSACTION_stopByteDanceEffect, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().stopByteDanceEffect(listener);
            return;
          }
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      @Override public void registerHandInfoListener(com.skyworth.smc.listener.IHandInfoListener handInfoListener) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeStrongBinder((((handInfoListener!=null))?(handInfoListener.asBinder()):(null)));
          boolean _status = mRemote.transact(Stub.TRANSACTION_registerHandInfoListener, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().registerHandInfoListener(handInfoListener);
            return;
          }
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      @Override public void registerFaceInfoListener(com.skyworth.smc.listener.IFaceInfoListener faceInfoListener) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeStrongBinder((((faceInfoListener!=null))?(faceInfoListener.asBinder()):(null)));
          boolean _status = mRemote.transact(Stub.TRANSACTION_registerFaceInfoListener, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().registerFaceInfoListener(faceInfoListener);
            return;
          }
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      public static ISystemEffectDetectInterface sDefaultImpl;
    }
    static final int TRANSACTION_startByteDanceEffect = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
    static final int TRANSACTION_stopByteDanceEffect = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
    static final int TRANSACTION_registerHandInfoListener = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
    static final int TRANSACTION_registerFaceInfoListener = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
    public static boolean setDefaultImpl(ISystemEffectDetectInterface impl) {
      // Only one user of this interface can use this function
      // at a time. This is a heuristic to detect if two different
      // users in the same process use this function.
      if (Proxy.sDefaultImpl != null) {
        throw new IllegalStateException("setDefaultImpl() called twice");
      }
      if (impl != null) {
        Proxy.sDefaultImpl = impl;
        return true;
      }
      return false;
    }
    public static ISystemEffectDetectInterface getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
  }
  /**
       * Demonstrates some basic types that you can use as parameters
       * and return values in AIDL.
       */
  public void startByteDanceEffect(int mode, com.skyworth.smc.listener.IStartByteDanceEffectListener listener) throws android.os.RemoteException;
  public void stopByteDanceEffect(com.skyworth.smc.listener.IStopByteDanceEffectListener listener) throws android.os.RemoteException;
  public void registerHandInfoListener(com.skyworth.smc.listener.IHandInfoListener handInfoListener) throws android.os.RemoteException;
  public void registerFaceInfoListener(com.skyworth.smc.listener.IFaceInfoListener faceInfoListener) throws android.os.RemoteException;
}
