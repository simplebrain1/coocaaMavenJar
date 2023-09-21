/**
 * Copyright (C) 2012 The SkyTvOS Project
 * <p>
 * Version     Date           Author
 * ─────────────────────────────────────
 * 2013-12-3         xingkong207
 */

package com.tianci.system.define;

/**
 * <p>Description:</p>
 * <p>write something</p>
 *
 * @author wei li
 * @version V1.0.0
 * @ClassName SysConst
 * @date 2013-12-3
 */
public class SysConst {
    public static final String TAG = "tcsys";

    // tv名字
    public static final String SKY_CFG_TV_TVNAME_PARLOUR = "SKY_CFG_TV_TVNAME_PARLOUR";

    public static final String SKY_SHARE_DATA_KEY_WEATHER = "env_weather";
    public static final String SKY_SHARE_DATA_KEY_LOCATION = "env_location";

    /**
     * Description: property key---mac
     */
    public static final String MAC_PROPERTY_KEY = "third.get.mac";
    /**
     * Description:property key---barcode
     */
    public static final String BARCODE_PROPERTY_KEY = "third.get.barcode";

    public static final String SKY_SHARE_DATA_KEY_TCVERSION = "tcsys_version";

    public final static String SKY_SHARE_DATA_KEY_SKYWORTH_ROUTE_MAC = "skyworth_route_mac";

    public static final String LOAD_CONFIG_PLUGIN_FINISH = "third.get.loadCfgPluginFinish";

    public static final String LOAD_NET_PLUGIN_FINISH = "third.get.loadNetPluginFinish";

    public static final String SKY_SKIP_FULL_BT_PAIR_CHECK = "persist.sys.skip.fullbt.check";

    //desperate
    public static final String CMCC_SERIAL_NUMBER = "third.get.cmei";
    //desperate
    public static final String CMCC_DEVICE_TYPE = "third.get.cmcc.deviceType";
    //desperate
    public static final String CMCC_PRODUCT_TOKEN = "third.get.cmcc.productToken";
    //desperate
    public static final String CMCC_ALINK_TOKEN = "third.get.cmcc.andlinkToken";

    public static final String PERSIST_BARCODE_PROPERTY_KEY = "persist.third.get.barcode";
    public static final String PERSIST_MAC_PROPERTY_KEY = "persist.third.get.mac";
    public static final String PERSIST_CMCC_SERIAL_NUMBER = "persist.third.cmcc.cmei";
    public static final String PERSIST_CMCC_DEVICE_TYPE = "persist.third.cmcc.deviceType";
    public static final String PERSIST_CMCC_PRODUCT_TOKEN = "persist.third.cmcc.productToken";
    public static final String PERSIST_CMCC_ALINK_TOKEN = "persist.third.cmcc.andlinkToken";

    public static int RC_RSSI_USER_THRESHOLD = 60;

    public static int RC_RSSI_MALL_THRESHOLD = 50;

    public static final String BT_RSSI_TAG = "BT_RSSI";

    // Uart Channel
    public static final int UART_CHANNEL_UNKNOWN = -1;
    public static final int UART_CHANNEL_INFRARED = 0;
    public static final int UART_CHANNEL_FRIDGE = 1;
    public static final int UART_CHANNEL_ZIGBEE = 2;
    public static final int UART_CHANNEL_DEVICEWELL = 3;
    public static final int E_UART_CHANNEL_USB_GENERAL = 4;

    /*屏保时间*/
    public static final int ONE_MIN = 0;
    public static final int TWO_MIN = 1;
    public static final int FIVE_MIN = 2;
    public static final int TEN_MIN = 3;
    public static final int THIRTY_MIN = 4;
    public static final int NONE = 5;

    /*屏幕显示方向*/
    public static final int SKY_CFG_TV_DISPLAY_MODE_ROTATION_LEVEL = 0;//横屏
    public static final int SKY_CFG_TV_DISPLAY_MODE_ROTATION_CLOCKWISE = 1;//顺时针90度
    public static final int SKY_CFG_TV_DISPLAY_MODE_ROTATION_ANTI_CLOCKWISE = 2;//逆时针90度

    /*AOD待机模式*/
    public static final int SKY_CFG_TV_AOD_WAKE_STANDBY_MODE = 0; // 唤醒显示
    public static final int SKY_CFG_TV_AOD_AI_STANDBY_MODE = 1; // 关闭
    public static final int SKY_CFG_TV_AOD_WAKE_ALWAY_MODE_ASSISTANT = 2; // 始终显示
    public static final int SKY_CFG_TV_AOD_WAKE_STANDBY_MODE_ASSISTANT = 3; // 唤醒显示
    public static final int SKY_CFG_TV_AOD_WAKE_SYSTEM_MODE_ASSISTANT = 4; // 跟随系统
    /*AOD显示模式*/
    public static final int SKY_CFG_TV_SET_AOD_SCREEN_MAIN = 0; // 息屏
    public static final int SKY_CFG_TV_SET_AOD_SCREEN_ASSISTANT = 1;// 副屏

    //以下为虚拟按键类型
    public static final int VIRTUALINPUT_clickButton = 0;
    public static final int VIRTUALINPUT_buttonDown = 1;
    public static final int VIRTUALINPUT_buttonUp = 2;
    public static final int VIRTUALINPUT_rollMdlBtnUp = 3;
    public static final int VIRTUALINPUT_rollMdlBtnDown = 4;
    public static final int VIRTUALINPUT_moveMouse = 5;
    public static final int VIRTUALINPUT_moveMouseTo = 6;
    public static final int VIRTUALINPUT_touch = 7;
    public static final int VIRTUALINPUT_pressKey = 8;
    public static final int VIRTUALINPUT_releaseKey = 9;
    public static final int VIRTUALINPUT_clickKey = 10;
    public static final int VIRTUALINPUT_sensorChangedInput = 11;
    public static final int VIRTUALINPUT_touch_down = 12;
    public static final int VIRTUALINPUT_touch_up = 13;


    // HDR
    public static int YOUKU_FRAME_ENJOY_HDR = 0;
    public static int IQIYI_FRAME_QI_HDR = 1;
}
