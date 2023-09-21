package com.tianci.system.listener;

import com.tianci.system.define.SysConst;

public class OnAodConfigListener {

    /**
     * 设置“全时AI”开关更改
     *
     * @param onOff true:AI待机开启，false：AI待机关闭
     */
    public void onAIStandbySwitched(boolean onOff) {

    }

    /**
     * AOD显示显示方式
     *
     * @param type 1：主屏AOD；2：副屏AOD
     * @param mode type==1:{{@link SysConst#SKY_CFG_TV_AOD_WAKE_STANDBY_MODE},
     *             {@link SysConst#SKY_CFG_TV_AOD_AI_STANDBY_MODE}};
     *             <p>
     *             type==2:{{@link SysConst#SKY_CFG_TV_AOD_WAKE_ALWAY_MODE_ASSISTANT},
     *             {@link SysConst#SKY_CFG_TV_AOD_WAKE_STANDBY_MODE_ASSISTANT},
     *             {@link SysConst#SKY_CFG_TV_AOD_WAKE_SYSTEM_MODE_ASSISTANT}}
     */
    public void onAodShowModeSwitched(int type, int mode) {

    }
}
