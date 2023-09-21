/*
 * This file is auto-generated.  DO NOT MODIFY.
 */
package com.skyworth.smc;
// Declare any non-default types here with import statements

public interface IEffectDetectInterface extends android.os.IInterface
{
  /** Default implementation for IEffectDetectInterface. */
  public static class Default implements IEffectDetectInterface
  {
    /**
         * Demonstrates some basic types that you can use as parameters
         * and return values in AIDL.
         *///void startByteDanceEffect(int mode, IStartByteDanceEffectListener listener);
    //void stopByteDanceEffect(IStopByteDanceEffectListener listener);

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
  public static abstract class Stub extends android.os.Binder implements IEffectDetectInterface
  {
    private static final String DESCRIPTOR = "com.skyworth.smc.IEffectDetectInterface";
    /** Construct the stub at attach it to the interface. */
    public Stub()
    {
      this.attachInterface(this, DESCRIPTOR);
    }
    /**
     * Cast an IBinder object into an com.skyworth.smc.IEffectDetectInterface interface,
     * generating a proxy if needed.
     */
    public static IEffectDetectInterface asInterface(android.os.IBinder obj)
    {
      if ((obj==null)) {
        return null;
      }
      android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
      if (((iin!=null)&&(iin instanceof IEffectDetectInterface))) {
        return ((IEffectDetectInterface)iin);
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
    private static class Proxy implements IEffectDetectInterface
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
           *///void startByteDanceEffect(int mode, IStartByteDanceEffectListener listener);
      //void stopByteDanceEffect(IStopByteDanceEffectListener listener);

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
      public static IEffectDetectInterface sDefaultImpl;
    }
    static final int TRANSACTION_registerHandInfoListener = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
    static final int TRANSACTION_registerFaceInfoListener = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
    public static boolean setDefaultImpl(IEffectDetectInterface impl) {
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
    public static IEffectDetectInterface getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
  }
  /**
       * Demonstrates some basic types that you can use as parameters
       * and return values in AIDL.
       *///void startByteDanceEffect(int mode, IStartByteDanceEffectListener listener);
  //void stopByteDanceEffect(IStopByteDanceEffectListener listener);

  public void registerHandInfoListener(com.skyworth.smc.listener.IHandInfoListener handInfoListener) throws android.os.RemoteException;
  public void registerFaceInfoListener(com.skyworth.smc.listener.IFaceInfoListener faceInfoListener) throws android.os.RemoteException;
}
