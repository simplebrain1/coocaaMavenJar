package com.tianci.system.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.skyworth.framework.skysdk.ipc.SkyApplication;
import com.skyworth.framework.skysdk.ipc.SkyCmd;
import com.skyworth.framework.skysdk.ipc.SkyCmdTransporterIPC;
import com.skyworth.framework.skysdk.ipc.SkyCmdURI;
import com.skyworth.framework.skysdk.schema.ParcelableUtil;
import com.skyworth.framework.skysdk.schema.SkyCmdHeader;
import com.tianci.system.callback.OldIPCCallback;
import com.tianci.system.command.TCSystemCmd;
import com.tianci.system.data.TCRetData;
import com.tianci.system.data.TCSetData;
import com.tianci.system.data.TCSetDataFactory;
import com.tianci.utils.SkySSSLog;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ServiceUtil {
    private final static String TAG = "ServiceUtil";
    private static final String SERVICE_BROADCAST_ACTION = "com.skyworth.tianci.servicebroadcast";
    public final static String MENU_SELECT_INDEX_KEY = "MENU_SELECT_INDEX_KEY";
    private final static String cmdHeader = "tianci://com.tianci.system/com.tianci.system.SystemService?cmd=";
    private final static String cmdHeader2 = "tianci://com.tianci.system/com.tianci.uiservice.SimplyUIService?cmd=";
    private static HashMap<String, List<OldIPCCallback>> mIPCCallbacks = new HashMap<String, List<OldIPCCallback>>();
    private static HashMap<String, List<OldIPCCallback>> mBroadCallbacks;
    private static volatile boolean isAddListener;
    private static volatile boolean isAddReceiver;
    private static BroadcastReceiver receiver;
    private final static SkyApplication.SkyCmdConnectorListener mListener = new SkyApplication.SkyCmdConnectorListener() {
        @Override
        public void onCmdConnectorInit() {
            SkySSSLog.d(TAG, "onCmdConnectorInit");
        }

        @Override
        public byte[] onHandler(String fromtarget, String cmd, byte[] body) {
            SkySSSLog.d(TAG, "onHandler fromtarget=" + fromtarget + ",cmd=" + cmd + ",body=" + body);
            List<OldIPCCallback> list = mIPCCallbacks.get(cmd);
            if (list != null) {
                for (OldIPCCallback callBack : list) {
                    callBack.handle(body);
                    if (callBack.isNeedRemove()) {
                        removeIPCCallback(cmd, callBack);
                    }
                }
            }
            return new byte[0];
        }

        @Override
        public void onResult(String fromtarget, String cmd, byte[] body) {
            SkySSSLog.d(TAG, "onResult fromtarget=" + fromtarget + ",cmd=" + cmd + ",body=" + body);
        }

        @Override
        public String getCmdClassName() {
            return TAG;
        }
    };

    public static void addIPCCallback(String key, OldIPCCallback callback) {
        List<OldIPCCallback> list = mIPCCallbacks.get(key);
        if (list == null) {
            list = new CopyOnWriteArrayList<OldIPCCallback>();
        }
        list.add(callback);
        mIPCCallbacks.put(key, list);
    }

    private static void removeIPCCallback(String key, OldIPCCallback callback) {
        List<OldIPCCallback> list = mIPCCallbacks.get(key);
        if (list != null) {
            list.remove(callback);
        }
    }

    public static void removeCallback(String key, Object callback) {
        List<OldIPCCallback> list = mIPCCallbacks.get(key);
        if (list != null) {
            for (OldIPCCallback cb : list) {
                if (cb.getObject() == callback) {
                    list.remove(cb);
                    return;
                }
            }
        }
    }

    private static void registerReceiver(Context context) {
        SkySSSLog.d(TAG, "registerReceiver isAddReceiver=" + isAddReceiver);
        if (isAddReceiver) {
            return;
        }
        if (receiver == null) {
            receiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    String action = intent.getAction();
                    SkySSSLog.d(TAG, "onReceive action=" + action);
                    if (!SERVICE_BROADCAST_ACTION.equals(action)) {
                        return;
                    }
                    byte[] header = intent.getByteArrayExtra("cmdheader");
                    SkyCmdHeader cmdheader = ParcelableUtil.unmarshall(header, SkyCmdHeader.CREATOR);
                    byte[] body = intent.getByteArrayExtra("cmdbody");
                    SkyCmd cmd;
                    if (body != null && body.length > 0) {
                        cmd = new SkyCmd(cmdheader, body);
                    } else {
                        cmd = new SkyCmd(cmdheader, null);
                    }
                    String cmdStr = cmd.getCmdHeader().getCmd();
                    byte[] inBody = cmd.getCmdBody();
                    SkySSSLog.d(TAG, "onReceive cmdStr=" + cmdStr);
                    if (mBroadCallbacks != null) {
                        List<OldIPCCallback> list = mBroadCallbacks.get(cmdStr);
                        if (list != null) {
                            for (OldIPCCallback callback : list) {
                                callback.handle(inBody);
                            }
                        }
                    }
                }
            };
        }
        Intent intent = new Intent();
        intent.setAction("");
        context.registerReceiver(receiver, new IntentFilter(SERVICE_BROADCAST_ACTION));
        isAddReceiver = true;
    }

    private static void unregisterReceiver(Context context) {
        SkySSSLog.d(TAG, "unregisterReceiver isAddReceiver=" + isAddReceiver);
        if (!isAddReceiver) {
            return;
        }
        if (receiver != null) {
            context.unregisterReceiver(receiver);
        }
        isAddReceiver = false;
    }

    public static synchronized void addBroadCallback(Context context, String key,
                                                     OldIPCCallback callback) {
        registerReceiver(context);
        if (mBroadCallbacks == null) {
            mBroadCallbacks = new HashMap<String, List<OldIPCCallback>>();
        }
        List<OldIPCCallback> list = mBroadCallbacks.get(key);
        if (list == null) {
            list = new CopyOnWriteArrayList<OldIPCCallback>();
        }
        list.add(callback);
        mBroadCallbacks.put(key, list);
    }

    public static synchronized void removeBroadCallback(Context context, String key, Object callback) {
        if (mBroadCallbacks == null) {
            unregisterReceiver(context);
            return;
        }
        List<OldIPCCallback> list = mBroadCallbacks.get(key);
        if (list != null) {
            for (OldIPCCallback cb : list) {
                if (cb.getObject() == callback) {
                    list.remove(cb);
                    break;
                }
            }
            if (list.isEmpty()) {
                mBroadCallbacks.remove(key);
            }
        }
        if (mBroadCallbacks.isEmpty()) {
            unregisterReceiver(context);
        }
    }

    private static synchronized void addListener() {
        if (isAddListener) {
            return;
        }
        isAddListener = true;
        SkyApplication.addCmdListener(mListener);
    }

    public static TCSetData getSetData(String key) {
        SkyCmdURI cmdUrl;
        try {
            cmdUrl = new SkyCmdURI(cmdHeader + TCSystemCmd.TC_SYSTEM_CMD_GET_CONFIG.toString());
            byte[] ackData = SkyApplication.execCommand(mListener, cmdUrl, key.getBytes());
            if (ackData != null) {
                return TCSetDataFactory.createSetData(ackData);
            }
        } catch (URISyntaxException e) {
            SkySSSLog.e(TAG, "getSetData e=" + e.getMessage());
        } catch (SkyCmdURI.SkyCmdPathErrorException e) {
            SkySSSLog.e(TAG, "getSetData e=" + e.getMessage());
        }
        return null;
    }

    public static TCRetData setData(TCSetData data) {
        SkyCmdURI cmdUrl;
        try {
            cmdUrl = new SkyCmdURI(cmdHeader + TCSystemCmd.TC_SYSTEM_CMD_SET_CONFIG.toString());
            SkyApplication.execCommand(mListener, cmdUrl, data.toBytes());
            return new TCRetData(true);
        } catch (URISyntaxException e) {
            SkySSSLog.e(TAG, "setData e=" + e.getMessage());
        } catch (SkyCmdURI.SkyCmdPathErrorException e) {
            SkySSSLog.e(TAG, "setData e=" + e.getMessage());
        }
        return null;
    }

    public static byte[] executeSysCmd(String key, byte[] body) {
        addListener();
        try {
            SkyCmdURI cmdUrl = new SkyCmdURI(cmdHeader + key);
            return SkyApplication.execCommand(mListener, cmdUrl, body);
        } catch (URISyntaxException e) {
            SkySSSLog.e(TAG, "executeSysCmd e=" + e.getMessage());
        } catch (SkyCmdURI.SkyCmdPathErrorException e) {
            SkySSSLog.e(TAG, "executeSysCmd e=" + e.getMessage());
        }
        return null;
    }

    public static byte[] executeUICmd(String key, byte[] body) {
        addListener();
        try {
            SkyCmdURI cmdUrl = new SkyCmdURI(cmdHeader2 + key);
            return SkyApplication.execCommand(mListener, cmdUrl, body);
        } catch (URISyntaxException e) {
            SkySSSLog.e(TAG, "executeUICmd e=" + e.getMessage());
        } catch (SkyCmdURI.SkyCmdPathErrorException e) {
            SkySSSLog.e(TAG, "executeUICmd e=" + e.getMessage());
        }
        return null;
    }
}
