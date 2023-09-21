/*
 * This file is auto-generated.  DO NOT MODIFY.
 */
package com.skyworth.smc.listener;
// Declare any non-default types here with import statements

public interface IFaceInfoListener extends android.os.IInterface
{
  /** Default implementation for IFaceInfoListener. */
  public static class Default implements IFaceInfoListener
  {
    /**
         * Demonstrates some basic types that you can use as parameters
         * and return values in AIDL.
         */
    @Override public void onResult(com.skyworth.smc.bean.FocusFaceInfo faceInfo) throws android.os.RemoteException
    {
    }
    @Override
    public android.os.IBinder asBinder() {
      return null;
    }
  }
  /** Local-side IPC implementation stub class. */
  public static abstract class Stub extends android.os.Binder implements IFaceInfoListener
  {
    private static final String DESCRIPTOR = "com.skyworth.smc.listener.IFaceInfoListener";
    /** Construct the stub at attach it to the interface. */
    public Stub()
    {
      this.attachInterface(this, DESCRIPTOR);
    }
    /**
     * Cast an IBinder object into an com.skyworth.smc.listener.IFaceInfoListener interface,
     * generating a proxy if needed.
     */
    public static IFaceInfoListener asInterface(android.os.IBinder obj)
    {
      if ((obj==null)) {
        return null;
      }
      android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
      if (((iin!=null)&&(iin instanceof IFaceInfoListener))) {
        return ((IFaceInfoListener)iin);
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
        case TRANSACTION_onResult:
        {
          data.enforceInterface(descriptor);
          com.skyworth.smc.bean.FocusFaceInfo _arg0;
          if ((0!=data.readInt())) {
            _arg0 = com.skyworth.smc.bean.FocusFaceInfo.CREATOR.createFromParcel(data);
          }
          else {
            _arg0 = null;
          }
          this.onResult(_arg0);
          reply.writeNoException();
          return true;
        }
        default:
        {
          return super.onTransact(code, data, reply, flags);
        }
      }
    }
    private static class Proxy implements IFaceInfoListener
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
      @Override public void onResult(com.skyworth.smc.bean.FocusFaceInfo faceInfo) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          if ((faceInfo!=null)) {
            _data.writeInt(1);
            faceInfo.writeToParcel(_data, 0);
          }
          else {
            _data.writeInt(0);
          }
          boolean _status = mRemote.transact(Stub.TRANSACTION_onResult, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().onResult(faceInfo);
            return;
          }
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      public static IFaceInfoListener sDefaultImpl;
    }
    static final int TRANSACTION_onResult = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
    public static boolean setDefaultImpl(IFaceInfoListener impl) {
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
    public static IFaceInfoListener getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
  }
  /**
       * Demonstrates some basic types that you can use as parameters
       * and return values in AIDL.
       */
  public void onResult(com.skyworth.smc.bean.FocusFaceInfo faceInfo) throws android.os.RemoteException;
}
