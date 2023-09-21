package com.tianci.system.api;

import android.content.Context;
import android.os.Bundle;

import com.tianci.system.define.SkyConfigDefs;
import com.tianci.system.utils.ApiUtil;
import com.tianci.utils.SkySSSLog;

import static com.tianci.system.utils.ApiUtil.KEY_IS_PLUGIN;
import static com.tianci.system.utils.ApiUtil.KEY_RESULT;
import static com.tianci.system.utils.ApiUtil.KEY_RET_TYPE;
import static com.tianci.system.utils.ApiUtil.KEY_TC_TYPE;
import static com.tianci.system.utils.ApiUtil.PARAM_RET_BOOL;
import static com.tianci.system.utils.ApiUtil.PARAM_RET_STRING;
import static com.tianci.system.utils.ApiUtil.TC_DATA_INFO;
import static com.tianci.system.utils.ApiUtil.TC_DATA_SWITCH;

/**
 * api接口不通用的放这里(定制机、客制化)
 */
public class TCCustomApi {
    private static final String TAG = "TCCustomApi";

    public TCCustomApi(Context context) {
        SkySSSLog.d(TAG, "created");
        Context ctx = context.getApplicationContext();
        if (ctx == null) {
            ctx = context;
        }
        ApiUtil.setContext(ctx);
    }

    private Bundle executeCmd(String cmd, Bundle body, boolean isSet) {
        return ApiUtil.executeSystemCmd(cmd, body, isSet);
    }

    /**
     * 获取移动机器IMEI号
     *
     * @return IMEI号
     */
    public String getCMCCImeiCode() {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_TC_TYPE, TC_DATA_INFO);
        bundle.putInt(KEY_RET_TYPE, PARAM_RET_STRING);
        bundle.putBoolean(KEY_IS_PLUGIN, true);
        Bundle ret = executeCmd(SkyConfigDefs.SKY_CFG_TV_GET_CMCC_IMEI_CODE, bundle, false);
        String result = ret.getString(KEY_RESULT);
        SkySSSLog.d(TAG, "getCMCCImeiCode result=" + result);
        return result;
    }

    /**
     * 判断是否是移动机器
     *
     * @return true：是
     */
    public boolean isCMCCMachine() {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_TC_TYPE, TC_DATA_SWITCH);
        bundle.putInt(KEY_RET_TYPE, PARAM_RET_BOOL);
        bundle.putBoolean(KEY_IS_PLUGIN, true);
        Bundle ret = executeCmd(SkyConfigDefs.SKY_CFG_TV_IS_CMCC_MACHINE, bundle, false);
        boolean result = ret.getBoolean(KEY_RESULT);
        SkySSSLog.d(TAG, "isCMCCMachine result=" + result);
        return result;
    }
}