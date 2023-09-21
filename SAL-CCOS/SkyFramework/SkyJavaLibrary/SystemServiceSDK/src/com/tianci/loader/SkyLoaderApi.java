/**
 * Copyright (C) 2012 The SkyTvOS Project
 * <p>
 * Version     Date           Author
 * ─────────────────────────────────────
 * 2015年3月26日         wen
 */

package com.tianci.loader;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemProperties;

import com.tianci.loader.LoaderParams.UpdateHandlerType;
import com.tianci.system.utils.ApiUtil;
import com.tianci.utils.SkySSSLog;

import java.io.File;

import static com.tianci.system.utils.ApiUtil.KEY_RESULT;
import static com.tianci.system.utils.ApiUtil.KEY_RET_TYPE;
import static com.tianci.system.utils.ApiUtil.PARAM_RET_BYTES;

public class SkyLoaderApi
{
    private static final String TAG = "SkyLoaderApi";

    public SkyLoaderApi(Context context) {
        ApiUtil.setContext(context);
    }

    /**
     * 获取当前系统是否强制升级包 （已下载、校验完全，准备安装）<br/> 查找指定配置文件是否存在 2015年12月1日
     *
     * @return boolean
     */
    public static boolean hasForceUpgrade()
    {
        boolean hasForceOta = false;
        try
        {
            hasForceOta = new File(ForceFlagUtils.getFlagFilePath()).exists();
        } catch (Exception e)
        {
            SkySSSLog.e(TAG, "hasForceUpgrade(), exception = " + e.getMessage());
            e.printStackTrace();
        }
        return hasForceOta;
    }


    /**
     * 启动强制升级倒计时界面<br/> 2015年12月1日
     *
     * @param context
     *         void
     */
    public static void startForceDialog(Context context)
    {

        // FIXME 移除判断cache分区是否挂载上, 当前判断方法有误, 后续寻找新方法再添加上 BY关皓文 170523
//        if (!ForceFlagUtils.checkCacheDisk())
//        {
//            SkySSSLog.e(TAG, "startForceDialog(), /cache not mount, return");
//            return;
//        }

        LoaderParams params = new LoaderParams();
        params.handlerType = UpdateHandlerType.FORCE;
        params.version = ForceFlagUtils.readVersion();
        Intent intent = new Intent();
        if (ForceVersionUtils.isTcVersionUpper6_10())
        {
            SkySSSLog.d(TAG, "send action = android.settings.SYSTEM_UPGRADE.force");
            intent.setAction("android.settings.SYSTEM_UPGRADE.force");
        }
        else
        {
            SkySSSLog.d(TAG, "send action = android.settings.SYSTEM_UPGRADE.force.onSetting");
            intent.setAction("android.settings.SYSTEM_UPGRADE.force.onSetting");
        }
        intent.putExtra("params", params.toString().getBytes());
        intent.putExtra("new_version", params.version);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        // startUpdateDialog(context, params);
    }

    /**
     * 显示升级主界面<br/>
     * 2015年4月11日
     *
     * @param context
     * @return boolean
     */
    @Deprecated
    public static boolean showLoaderView(Context context)
    {
        if (context != null)
        {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            ComponentName cn = new ComponentName("com.tianci.system",
                    "com.tianci.loader.TvosLoaderServiceActivity");
            intent.setComponent(cn);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("from", "TCSetting");
            // intent.putExtra("status", "CHECK");
            context.startActivity(intent);
            return true;
        } else
        {
            return false;
        }
    }

    /**
     * 获取升级包下载进度（百分比%）<br/> 2015年4月13日
     *
     * @return int 0到100,-1代表未下载
     */
    public int getDownloadPercent() {
        Bundle param = new Bundle();
        param.putInt(KEY_RET_TYPE, PARAM_RET_BYTES);
        Bundle ret = ApiUtil.executeSystemCmd(SkyLoaderServiceDefs.SkyLoaderServiceCmdEnum.
                LOADERSERVICE_CMD_GET_DOWNLOAD_PERCENT.toString(), param, false);
        int result = ApiUtil.parseInt(ret.getByteArray(KEY_RESULT));
        SkySSSLog.i(TAG, "getDownloadPercent result=" + result);
        return result;
    }

    /**
     * 是否检测到在线升级<br/> 2015年12月24日
     *
     * @return boolean true 检测到在线升级，false 未检测到升级
     */
    public boolean hasUpgrade() {
        Bundle param = new Bundle();
        param.putInt(KEY_RET_TYPE, PARAM_RET_BYTES);
        Bundle ret = ApiUtil.executeSystemCmd(SkyLoaderServiceDefs.SkyLoaderServiceCmdEnum.
                LOADERSERVICE_CMD_HAS_UPGRADE.toString(), param, false);
        boolean result = ApiUtil.parseBool(ret.getByteArray(KEY_RESULT));
        SkySSSLog.i(TAG, "getDownloadPercent result=" + result);
        return result;
    }

    /**
     * 是否AB升级<br/> 2020年01月21日
     *
     * @return boolean true AB升级，false 非AB升级
     */
    public static boolean isABUpdate(){
        int android_version = android.os.Build.VERSION.SDK_INT;
        boolean isABUpdate = false;
        // Android has introduced A/B System Updates since 7.0
        if(android_version >=24  && "true".equals(SystemProperties.get("ro.build.ab_update"))){
            isABUpdate = true;
        }
        SkySSSLog.d(TAG, "isABUpdate:"+isABUpdate);
        return  isABUpdate;
    }

}
