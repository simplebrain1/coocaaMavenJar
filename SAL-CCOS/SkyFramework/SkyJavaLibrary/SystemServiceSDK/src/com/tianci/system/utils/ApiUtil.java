package com.tianci.system.utils;

import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import com.skyworth.os.BuildInfo;
import com.tianci.system.callback.CommonObserver;
import com.tianci.system.data.TCEnumSetData;
import com.tianci.system.data.TCInfoSetData;
import com.tianci.system.data.TCRangeSetData;
import com.tianci.system.data.TCRetData;
import com.tianci.system.data.TCSetData;
import com.tianci.system.data.TCSwitchSetData;
import com.tianci.system.define.SystemProviderDefines;
import com.tianci.utils.SkySSSLog;

import java.io.Serializable;

public class ApiUtil {
    private static final String TAG = "ApiUtil";
    public static final String ACTION_IPC_CALLBACK = "sky.action.receive.ipc.callback";
    public final static String KEY_RESULT = "ret";
    private final static String KEY_CLASS = "class";
    public final static String KEY_METHOD = "method";
    public static final String KEY_PARAM = "param";
    public static final String KEY_METHOD_SET = "method_set";
    public static final String KEY_RET_TYPE = "ret_type";
    public static final String KEY_TC_TYPE = "tc_type";
    public static final String KEY_PARAM_TYPE = "param_type";
    public static final String KEY_RET_OBJ_CLASS = "ret_class";
    public static final String KEY_IS_PLUGIN = "is_plugin";
    public static final String KEY_IS_SERIALIZE = "serialize";
    public static final String KEY_RET_SERIALIZE = "ret_serialize";

    //以下定义接口参数及返回数据类型
    public static final int PARAM_RET_INT = 0;
    public static final int PARAM_RET_STRING = 1;
    public static final int PARAM_RET_BOOL = 2;
    public static final int PARAM_RET_OBJ = 3;
    public static final int PARAM_RET_BYTES = 4;
    public static final int PARAM_RET_LONG = 5;
    public static final int PARAM_RET_SERIAL = 6;
    public static final int PARAM_RET_NULL = 7;
    public static final int PARAM_RET_SERIAL_LIST = 8;

    //以下为TCSetData类型
    public static final int TC_DATA_INFO = 0;
    public static final int TC_DATA_ENUM = 1;
    public static final int TC_DATA_SWITCH = 2;
    public static final int TC_DATA_RANGE = 3;
    public static final int TC_DATA_RET = 4;
    public static final int TC_DATA_NULL = 5;

    private static ContentResolver mContentResolver;

    public synchronized static void setContext(Context context) {
        SkySSSLog.w(TAG, "setContext");
        if (mContentResolver != null) {
            return;
        }
        Context ctx = context.getApplicationContext();
        if (ctx == null) {
            ctx = context;
        }
        mContentResolver = ctx.getContentResolver();
    }

    /**
     * 设置调用方法数据
     */
    public static void setParams(Bundle bundle, String cls, String method,
                                 Bundle value, boolean set) {
        if (bundle == null) {
            return;
        }
        bundle.putString(KEY_CLASS, cls);
        bundle.putString(KEY_METHOD, method);
        Bundle param = new Bundle();
        if (value != null) {
            param.putParcelable(KEY_PARAM, value);
        }
        param.putBoolean(KEY_METHOD_SET, set);
        bundle.putBundle(KEY_PARAM, param);
    }

    /**
     * 获取数据通用方法
     */
    @SuppressWarnings("unchecked")
    public static <T> T getData(Bundle params, Class<T> retCls) {
        if (retCls == null || params == null) {
            SkySSSLog.e(TAG, "getData param is null");
            return null;
        }
        ContentProviderClient client = getClient();
        if (client == null) {
            SkySSSLog.e(TAG, "getData client is null");
            return null;
        }
        Bundle ret = null;
        try {
            ret = client.call(params.getString(KEY_CLASS), params.getString(KEY_METHOD),
                    params.getBundle(KEY_PARAM));
        } catch (Exception e) {
            SkySSSLog.e(TAG, "getData err=" + e.getMessage());
        }
        if (ret == null) {
            return null;
        }
        T result = null;
        if (ret.isEmpty()) {
            SkySSSLog.d(TAG, "ret isEmpty");
            return null;
        }
        if (retCls.getSimpleName().equals("Integer")) {
            result = (T) (Integer) ret.getInt(KEY_RESULT);
        } else if (retCls.getSimpleName().equals("String")) {
            result = (T) ret.getString(KEY_RESULT);
        } else if (retCls.getSimpleName().equals("Boolean")) {
            result = (T) (Boolean) ret.getBoolean(KEY_RESULT);
        } else if (retCls.getSimpleName().equals("byte[]")) {
            result = (T) ret.getByteArray(KEY_RESULT);
        } else if (retCls.getSimpleName().equals("int[]")) {
            result = (T) ret.getIntArray(KEY_RESULT);
        }else if (retCls.getSimpleName().equals("String[]")) {
            result = (T) ret.getStringArray(KEY_RESULT);
        }
        SkySSSLog.d(TAG, "getData result=" + result);
        return result;
    }

    /**
     * 设置数据通用方法
     */
    public static boolean setData(Bundle params) {
        if (params == null) {
            SkySSSLog.e(TAG, "setData param is null");
            return false;
        }
        ContentProviderClient client = getClient();
        if (client == null) {
            SkySSSLog.e(TAG, "setData client is null");
            return false;
        }
        Bundle ret = null;
        try {
            ret = client.call(params.getString(KEY_CLASS), params.getString(KEY_METHOD),
                    params.getBundle(KEY_PARAM));
        } catch (Exception e) {
            SkySSSLog.e(TAG, "setData err=" + e.getMessage());
        }
        boolean result = false;
        if (ret != null) {
            result = ret.getBoolean(KEY_RESULT, false);
        }
        SkySSSLog.d(TAG, "setData ret=" + ret);
        return result;
    }

    public static Bundle setTCData(Bundle bundle) {
        ContentProviderClient client = getClient();
        if (client == null) {
            SkySSSLog.e(TAG, "setTCData client is null");
            return new Bundle();
        }
        try {
            return client.call(SystemProviderDefines.METHOD_SYSTEM_CMD_SET_CONFIG, null, bundle);
        } catch (Exception e) {
            SkySSSLog.e(TAG, "setTCData e=" + e.getMessage());
        }
        return new Bundle();
    }

    public static Bundle getTCData(Bundle bundle) {
        ContentProviderClient client = getClient();
        if (client == null) {
            SkySSSLog.e(TAG, "getTCData client is null");
            return new Bundle();
        }
        try {
            return client.call(SystemProviderDefines.METHOD_SYSTEM_CMD_GET_CONFIG, null, bundle);
        } catch (Exception e) {
            SkySSSLog.e(TAG, "getTCData e=" + e.getMessage());
        }
        return new Bundle();
    }

    public static void setTCSetDataType(Bundle bundle, TCSetData ret) {
        int type = -1;
        if (ret instanceof TCRangeSetData) {
            type = TC_DATA_RANGE;
        } else if (ret instanceof TCSwitchSetData) {
            type = TC_DATA_SWITCH;
        } else if (ret instanceof TCEnumSetData) {
            TCEnumSetData enumObj = ((TCEnumSetData) ret);
            boolean isSerialize = enumObj.isSerialize();
            bundle.putBoolean(KEY_IS_SERIALIZE, isSerialize);
            if (isSerialize) {
                bundle.putSerializable("current", enumObj.getCurSerializable());
                bundle.putSerializable(KEY_RET_SERIALIZE, (Serializable) enumObj.getEnumSerialList());
            }
            type = TC_DATA_ENUM;
        } else if (ret instanceof TCInfoSetData) {
            type = TC_DATA_INFO;
        } else if (ret instanceof TCRetData) {
            type = TC_DATA_RET;
        }
        bundle.putInt(KEY_TC_TYPE, type);
    }

    public static TCSetData getTCSetDataSerialize(Bundle bundle, byte[] bytes, int type, boolean serialize) {
        TCSetData tcSetData = null;
        switch (type) {
            case TC_DATA_INFO:
                tcSetData = new TCInfoSetData(bytes);
                break;
            case TC_DATA_RET:
                tcSetData = new TCRetData(bytes);
                break;
            case TC_DATA_RANGE:
                tcSetData = new TCRangeSetData(bytes);
                break;
            case TC_DATA_SWITCH:
                tcSetData = new TCSwitchSetData(bytes);
                break;
            case TC_DATA_ENUM:
                if (serialize) {
                    tcSetData = new TCEnumSetData(bundle, bytes);
                } else {
                    tcSetData = new TCEnumSetData(bytes);
                }
                break;
        }
        return tcSetData;
    }

    public static TCSetData getTCSetDataType(byte[] bytes, int type) {
        TCSetData tcSetData = null;
        switch (type) {
            case TC_DATA_INFO:
                tcSetData = new TCInfoSetData(bytes);
                break;
            case TC_DATA_RET:
                tcSetData = new TCRetData(bytes);
                break;
            case TC_DATA_RANGE:
                tcSetData = new TCRangeSetData(bytes);
                break;
            case TC_DATA_SWITCH:
                tcSetData = new TCSwitchSetData(bytes);
                break;
            case TC_DATA_ENUM:
                tcSetData = new TCEnumSetData(bytes);
                break;
        }
        return tcSetData;
    }

    /**
     * 判断api是否走provider
     */
    public static boolean isNewPlatform() {
        boolean isBootPathImpl = false;
        try {
            isBootPathImpl = (getSystemVersionMain() >= 8 ? true : false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isBootPathImpl;
    }

    public static int getSystemVersionMain() {
        int ret = 0;
        String tc_version = BuildInfo.getSystemVersion();
        SkySSSLog.w(TAG, "tc_version=" + tc_version);
        String[] versions = tc_version.split("\\.");
        if (versions != null && versions.length > 1) {
            try {
                ret = Integer.parseInt(versions[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    public static Bundle executeSystemCmd(String cmd, Bundle body, boolean isSet) {
        ContentProviderClient client = getClient();
        if (client == null) {
            SkySSSLog.e(TAG, "executeSystemCmd client is null");
            return new Bundle();
        }
        Bundle bundle = new Bundle();
        if (body != null) {
            body.putBoolean(ApiUtil.KEY_METHOD_SET, isSet);
        }
        bundle.putBundle(KEY_PARAM, body);
        Bundle ret = null;
        try {
            ret = client.call(SystemProviderDefines.METHOD_SYSTEM_IPC_SET_GET, cmd, bundle);
        } catch (Exception e) {
            SkySSSLog.e(TAG, "executeSystemCmd e=" + e.getMessage());
        }
        SkySSSLog.d(TAG, "executeSystemCmd cmd=" + cmd + ",ret=" + ret);
        if (ret == null) {
            return new Bundle();
        }
        return ret;
    }

    public static Bundle executeCommonCmd(String cmd, Bundle body) {
        if (mContentResolver == null) {
            return new Bundle();
        }
        ContentProviderClient client = getClient();
        if (client == null) {
            SkySSSLog.e(TAG, "executeCommonCmd client is null");
            return new Bundle();
        }
        Bundle bundle = new Bundle();
        bundle.putBundle(KEY_PARAM, body);
        Bundle ret = null;
        try {
            ret = client.call(SystemProviderDefines.METHOD_COMMON, cmd, bundle);
        } catch (Exception e) {
            SkySSSLog.e(TAG, "executeSystemCmd e=" + e.getMessage());
        }
        SkySSSLog.d(TAG, "executeSystemCmd cmd=" + cmd + ",ret=" + ret);
        if (ret == null) {
            return new Bundle();
        }
        return ret;
    }

    public static boolean parseBool(byte[] bytes) {
        boolean result = false;
        try {
            result = Boolean.parseBoolean(new String(bytes));
        } catch (Exception e) {
            SkySSSLog.e(TAG, "parseBool e=" + e.getMessage());
        }
        return result;
    }

    public static int parseInt(byte[] bytes) {
        int result = -1;
        try {
            result = Integer.parseInt(new String(bytes));
        } catch (Exception e) {
            SkySSSLog.e(TAG, "parseInt e=" + e.getMessage());
        }
        return result;
    }

    public static void registerCallback(String define, CommonObserver callback) {
        callback.setContentResolver(mContentResolver);
        mContentResolver.registerContentObserver(Uri.parse(SystemProviderDefines.URI_PATH_METHOD + "/" + define),
                false, callback);
    }

    public static void unregisterCallback(CommonObserver callback) {
        callback.unregister();
        mContentResolver.unregisterContentObserver(callback);
    }

    public static ContentProviderClient getClient() {
        if (mContentResolver == null) {
            SkySSSLog.e(TAG, "getClient contentResolver is null");
            return null;
        }
        return mContentResolver.acquireUnstableContentProviderClient(
                Uri.parse(SystemProviderDefines.URI_PATH_METHOD));
    }
}
