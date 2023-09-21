package com.tianci.system.api;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.skyworth.smc.IEffectDetectInterface;
import com.skyworth.smc.ISystemEffectDetectInterface;
import com.skyworth.smc.bean.FocusFaceInfo;
import com.skyworth.smc.bean.FocusHandInfo;
import com.skyworth.smc.listener.IFaceInfoListener;
import com.skyworth.smc.listener.IHandInfoListener;
import com.skyworth.smc.listener.IStartByteDanceEffectListener;
import com.tianci.system.define.SystemProviderDefines;
import com.tianci.system.utils.ApiUtil;
import com.tianci.utils.SkySSSLog;


/**
 * Created on 2021/6/28
 *
 * @author whw
 */
public class TCSystemCameraApi {
    private static final String TAG = "TCSystemCameraApi";
    private static TCSystemCameraApi tcSystemCameraApi;
    private IEffectDetectInterface mEffectDetectInterface;
    private ServiceConnection serviceConnection;
    private boolean isBindSuccess = false;
    private Context context;
    private ISystemEffectDetectInterface systemEffectDetectInterface;

    /**
     * 旧版smc需要先发广播再绑定服务，新版的直接绑定服务即可
     */
    private boolean isNewSmc;

    /**
     * 旧版apk 1代表人脸跟踪，2代表人形跟踪
     */
    private int followType = 1;

    /**
     * call用于回调给调用者服务的连接状态
     */
    private ConnectCallBack connectCallBack;

    /**
     * 服务是否已经连接，用于重复绑定service的情况的连接状态判断
     */
    private boolean isServiceConnect = false;

    public static TCSystemCameraApi getInstance(Context context) {
        if (tcSystemCameraApi == null) {
            synchronized (TCSystemCameraApi.class) {
                if (tcSystemCameraApi == null) {
                    tcSystemCameraApi = new TCSystemCameraApi(context);
                }
            }
        }
        return tcSystemCameraApi;
    }

    public TCSystemCameraApi(Context context) {
        this.context = context;
        isNewSmc = false;
        SkySSSLog.d(TAG, "isNewSmc:" + isNewSmc);
        Context ctx = context.getApplicationContext();
        if (ctx == null) {
            ctx = context;
        }
        ApiUtil.setContext(ctx);
    }


    /**
     * @param connectCallBack 服务连接成功的回调,必须回调成功后才能注册监听,
     */
    private void connectService(ConnectCallBack connectCallBack) {
        bindSmcService(context);
        this.connectCallBack = connectCallBack;
    }


    /**
     * 退出时必须要断开连接,解绑服务,置空变量
     */
    private void disconnect() {
        unBindSmcService(context);
        if (!isNewSmc) {
            sendBroadCast(0);
        }
        if (connectCallBack != null) {
            connectCallBack = null;
        }
    }


    /**
     * 绑定字节的服务
     *
     * @param context
     * @return
     */
    private boolean bindSmcService(Context context) {
        initServiceConnection();
        boolean bindSuccess = false;
        Intent intent = new Intent();
        intent.setPackage("com.skyworth.smc");
        intent.setAction(!isNewSmc ? "com.skyworth.action.EffectDetect" : "com.skyworth.action.SystemEffectDetect");
        try {
            bindSuccess = context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE | Context.BIND_IMPORTANT);
        } catch (Exception e) {
            e.printStackTrace();
            bindSuccess = false;
        }
        isBindSuccess = bindSuccess;
        SkySSSLog.d(TAG, "service bindSuccess:" + bindSuccess);
        return bindSuccess;
    }

    /**
     * 初始化服务的connection
     */
    private void initServiceConnection() {
        if (serviceConnection == null) {
            serviceConnection = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    SkySSSLog.d(TAG, "onServiceConnected");
                    if (isNewSmc) {
                        systemEffectDetectInterface = ISystemEffectDetectInterface.Stub.asInterface(service);
                    } else {
                        mEffectDetectInterface = IEffectDetectInterface.Stub.asInterface(service);
                    }
                    //连接成功后回调
                    connectCallBack.isConnect(true);
                    isServiceConnect = true;
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {
                    connectCallBack.isConnect(false);
                    isServiceConnect = false;
                }
            };
        }else {
            if(isServiceConnect){
                connectCallBack.isConnect(true);
            }
        }
    }

    /**
     * 解绑字节的服务
     *
     * @param context
     */
    private void unBindSmcService(Context context) {
        isBindSuccess = false;
        if (serviceConnection != null) {
            try {
                context.unbindService(serviceConnection);
            } catch (Exception e) {
                e.printStackTrace();
            }
            serviceConnection = null;
        }
        if (systemEffectDetectInterface != null) {
            systemEffectDetectInterface = null;
        }
        if (mEffectDetectInterface != null) {
            mEffectDetectInterface = null;
        }
    }


    /**
     * 注册手势监听
     *
     * @param iHandInfoListener 手势监听器
     */

    /**
     * 注册手势监听
     *
     * @param handInfoCallBack 数据回调
     */
    public void registerHandInfoListener(final HandInfoCallBack handInfoCallBack) {
        if (isNewSmc) {
            if (systemEffectDetectInterface == null) {
                SkySSSLog.d(TAG, "systemEffectDetectInterface is null return");
                return;
            }
            try {
                systemEffectDetectInterface.startByteDanceEffect(followType, new IStartByteDanceEffectListener.Stub() {
                    @Override
                    public void onResult(String msg) throws RemoteException {
                        SkySSSLog.d(TAG, "onResult: " + msg);
                        systemEffectDetectInterface.registerHandInfoListener(new IHandInfoListener() {
                            @Override
                            public void onResult(FocusHandInfo handInfo) throws RemoteException {
                                handInfoCallBack.callback(handInfo);
                            }

                            @Override
                            public IBinder asBinder() {
                                return null;
                            }
                        });
                    }
                });
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            if (mEffectDetectInterface == null) {
                SkySSSLog.d(TAG, "mEffectDetectInterface is null return");
                return;
            }
            sendBroadCast(followType);
            if (mEffectDetectInterface != null) {
                try {
                    mEffectDetectInterface.registerHandInfoListener(new IHandInfoListener.Stub() {
                        @Override
                        public void onResult(FocusHandInfo handInfo) throws RemoteException {
                            handInfoCallBack.callback(handInfo);
                        }
                    });
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 注册人脸跟踪监听
     *
     * @param callBack
     */
    public void registerFaceInfoListener(final FaceInfoCallBack callBack) {
        if (isNewSmc) {
            if (systemEffectDetectInterface == null) {
                SkySSSLog.d(TAG, "systemEffectDetectInterface is null return");
                return;
            }
            try {
                systemEffectDetectInterface.startByteDanceEffect(followType, new IStartByteDanceEffectListener.Stub() {
                    @Override
                    public void onResult(String msg) throws RemoteException {
                        Log.d(TAG, "onResult: " + msg);
                        systemEffectDetectInterface.registerFaceInfoListener(new IFaceInfoListener.Stub() {
                            @Override
                            public void onResult(FocusFaceInfo faceInfo) throws RemoteException {
                                callBack.callback(faceInfo);
                            }
                        });
                    }
                });
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            if (mEffectDetectInterface == null) {
                SkySSSLog.d(TAG, "mEffectDetectInterface is null return");
                return;
            }
            sendBroadCast(followType);
            if (mEffectDetectInterface != null) {
                try {
                    mEffectDetectInterface.registerFaceInfoListener(new IFaceInfoListener.Stub() {
                        @Override
                        public void onResult(FocusFaceInfo faceInfo) throws RemoteException {
                            callBack.callback(faceInfo);
                        }
                    });
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void sendBroadCast(int mode) {
        try {
            SkySSSLog.d(TAG, "sendBroadCast mode: " + mode);
            Intent intent = new Intent("skyworth.ptzcamera.feature.bytedance");
            intent.setPackage("com.skyworth.smc");
            intent.setClassName("com.skyworth.smc", "com.skyworth.smc.receiver.CameraFocusReceiver");
            intent.putExtra("mode", mode);
            context.sendBroadcast(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param followType 1：人脸跟踪
     *                   2：动体（人形）跟踪
     * @param connectCallBack 二代云台连接上字节服务连接的回调，需要在回调后开始调用监听的方法
     * @return
     */
    public boolean startFollowing(int followType, ConnectCallBack connectCallBack) {
        TCSystemApi tcSystemApi = TCSystemApi.getInstance(context);
        if (tcSystemApi.isSupportGestureFocusSwitch(context)) {
            SkySSSLog.i(TAG, "camera is CM401N0");
            this.followType = followType;
            bindSmcService(context);
            this.connectCallBack = connectCallBack;
            if(!isNewSmc){
                sendBroadCast(this.followType);
            }
            return true;
        } else {
            Integer ret = 0;
            if (followType == 1) {
                tcSystemApi.ptzCameraInterface(9, 1, 0, 0);
                ret = tcSystemApi.ptzCameraInterface(6, 1, 1, 0);
            } else if (followType == 2 || followType == 3) {
                tcSystemApi.ptzCameraInterface(9, 2, 0, 0);
                ret = tcSystemApi.ptzCameraInterface(8, 1, 1, 0);
            }
            SkySSSLog.i(TAG, "ptzCameraInterface ret=" + ret + ",followType=" + followType);
            if (ret != null) {
                return ret == 0;
            }
        }
        return false;
    }


    /**
     * 关闭智能跟随，listener置空，跟随模式改为普通
     *
     * 退出请调用此接口
     * @return
     */
    public boolean closeFollowing(Context context){
        if (TCSystemApi.getInstance(context).isSupportGestureFocusSwitch(context)) {
            unBindSmcService(context);
            if (!isNewSmc) {
                sendBroadCast(0);
            }
            if (connectCallBack != null) {
                connectCallBack = null;
            }
            isServiceConnect = false;
            return true;
        } else {
            Bundle bundle = new Bundle();
            Bundle param = new Bundle();
            param.putInt("iOptionCmd", 9);
            param.putInt("iFuncDesc", 4);
            param.putInt("iExtra1", 0);
            param.putInt("iExtra2", 0);
            ApiUtil.setParams(bundle, SystemProviderDefines.METHOD_COMMON,
                    SystemProviderDefines.COMMON_CONTROL_PTZ_CAMERA, param, true);
            Integer ret = ApiUtil.getData(bundle, Integer.class);
            SkySSSLog.i(TAG, "ptzCameraInterface ret=" + ret);
            if (ret != null) {
                return ret == 0;
            }
        }
        return false;
    }

    public interface ConnectCallBack {
        /**
         * 连接状态回调
         *
         * @param isConnect
         */
        void isConnect(boolean isConnect);
    }


    public interface FaceInfoCallBack {
        /**
         * 人脸信息回调
         *
         * @param info
         */
        void callback(FocusFaceInfo info);
    }

    public interface HandInfoCallBack {
        /**
         * 手势信息回调
         *
         * @param info
         */
        void callback(FocusHandInfo info);
    }

}
