/**
 * Copyright (C) 2012 The SkyTvOS Project
 *
 * Version     Date           Author
 * ─────────────────────────────────────
 *           2013-11-23         xingkong207
 *
 */

package com.tianci.system.command;

/**
 * <p>Description:SystemService处理的广播命令</p> 
 * <p>write something</p>
 * @ClassName TCSystemDefs
 * @author wei li
 * @date 2013-11-23
 * @version V1.0.0
 */
public class TCSystemDefs
{
    public enum TCSystemBroadcast{
        /**
         * Description:同步缓冲数据广播(其他地方改变数据，向SystemService通知数据改变）
         */
        TC_SYSTEM_BROADCAST_SYNC_DATA,
        /**
         * Description:同步缓冲数据广播完成
         */
        TC_SYSTEM_BROADCAST_SYNC_DATA_FINISH,
        /**
         * Description:每次被调用setConfig，SystemService都会发送广播数据改变
         */
        TC_SYSTEM_BROADCAST_NOTIFY_DATA_CHANCHED,
        /**
         * Description:每次更新env数据时都会发送广播
         */
        TC_SYSTEM_BROADCAST_NOTIFY_ENV_CHANCHED,
        /**
         * Description:每次连接外置设备时发送的广播
         */
        TC_SYSTEM_BROADCAST_MEDIA_MOUNTED,
        /**
         * Description:每次移出外置设备时发送的广播
         */
        TC_SYSTEM_BROADCAST_MEDIA_REMOVED,
        /**
         * Description:每次更新后门时发送的广播
         */
        TC_SYSTEM_BROADCAST_NOTIFY_BACKDOOR_CHANCHED,
        /**
         * Description:system plugin load finish
         */
        TC_SYSTEM_BROADCAST_NOTIFY_SYSTEM_PLUGIN_FINISH,

        /**
         * Description:system open screen
         */
        TC_SYSTEM_BROADCAST_NOTIFY_OPEN_SCREEN,
    }
}
