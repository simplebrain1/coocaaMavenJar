/**
 * Copyright (C) 2012 The SkyTvOS Project
 *
 * Version     Date           Author
 * ─────────────────────────────────────
 *           2013-12-5         wei li
 *
 */

package com.tianci.system.define;

/**
 * <p>
 * Description:
 * </p>
 * <p>
 * write something
 * </p>
 * 
 * @ClassName TCEnvKey
 * @author wei li
 * @date 2013-12-5
 * @version V1.0.0
 */
public class TCEnvKey
{
    public static final String SKY_SYSTEM_ENV_MAC = "MAC";// MAC地址
    public static final String SKY_SYSTEM_ENV_SOFT_VER = "VER";// 系统版本号
    public static final String SKY_SYSTEM_ENV_TIANCI_VER = "TIANCIVER";// 酷开版本号
    public static final String SKY_SYSTEM_ENV_SOFT_DATE = "DATE";// 系统编译时间
    public static final String SKY_SYSTEM_ENV_MODEL = "MODEL";// 机芯
    public static final String SKY_SYSTEM_ENV_TYPE = "TYPE";// 机型
    public static final String SKY_SYSTEM_ENV_CHIPID = "CHIPID";// 芯片ID
    public static final String SKY_SYSTEM_ENV_SERIAL_NUMBER = "SKY_SYSTEM_ENV_SERIAL_NUMBER";// 序列号

    public static final String SKY_SYSTEM_ENV_TVNAME = "TVNAME";// 电视名称
    public static final String SKY_SYSTEM_ENV_SYSTEM_TIME = "SYSTEM_TIME";// 系统时间
    public static final String SKY_SYSTEM_ENV_BARCODE = "Bar_Code"; // 机型机芯版本号
    public static final String SKY_SYSTEM_ENV_LOCATION = "SKY_CFG_TV_SET_LOCATION"; // 设置区域位置
    @Deprecated
    public static final String SKY_SYSTEM_ENV_WEATHER = "SKY_SYSTEM_ENV_WEATHER"; // 天气&温度
    public static final String SKY_SYSTEM_ENV_WEATHER2 = "SKY_SYSTEM_ENV_WEATHER2"; // 天气&温度
    public static final String SKY_SYSTEM_ENV_NEED_SHOW_BOOT_GUIDE = "SKY_SYSTEM_ENV_NEED_SHOW_BOOT_GUIDE"; // 开机引导
    public static final String SKY_SYSTEM_ENV_EXTERNAL_DEVICE = "SKY_SYSTEM_ENV_EXTERNAL_DEVICE"; // 外接设备
    public static final String SKY_SYSTEM_ENV_EXTERNAL_DEVICE_PATH = "SKY_SYSTEM_ENV_EXTERNAL_DEVICE_PATH"; // 外接设备
    public static final String SKY_SYSTEM_ENV_PANEL_SIZE = "SKY_SYSTEM_ENV_PANEL_SIZE"; // Tv屏幕尺寸
    public static final String SKY_SYSTEM_ENV_SAFE_PROTECTION_STATE = "SKY_SYSTEM_ENV_SAFE_PROTECTION_STATE"; // 系统安全防护状态
    public static final String SKY_SYSTEM_ENV_SETTING_DISABLE = "SKY_SYSTEM_ENV_SETTING_DISABLE"; //  设置快捷按键禁止用开关
    
    // add by yuzhan 设置里面新增的一些设置项，功能实现需要挪到SystemService中
    public static final String SKY_SYSTEM_ENV_SLEEP_TIMER = "SKY_SYSTEM_ENV_SLEEP_TIMER"; // 睡眠时间
    public static final String SKY_SYSTEM_ENV_BOOT_APP = "SKY_SYSTEM_ENV_BOOT_APP"; // 开机启动应用
    public static final String SKY_SYSTEM_ENV_RECOVERY = "SKY_SYSTEM_ENV_RECOVERY"; // 恢复出厂设置
//    public static final String SKY_SYSTEM_ENV_AP_STANDBY_MODE = "SKY_SYSTEM_ENV_AP_STANDBY_MODE"; // AP模式开关（热点打开时）
    public static final String SKY_SYSTEM_ENV_INTERACTION_SWITCH = "SKY_SYSTEM_ENV_INTERACTION_SWITCH"; // 互动消息开关
    public static final String SKY_SYSTEM_ENV_QUICK_STANDBY_MODE = "SKY_SYSTEM_ENV_QUICK_STANDBY_MODE"; //快速待机开关
    public static final String SKY_SYSTEM_ENV_QUICK_DEMO_MODE = "SKY_SYSTEM_ENV_QUICK_DEMO_MODE"; //快速演示模式开关
    public static final String SKY_SYSTEM_ENV_START_SCREEN_SAVER_MODE = "SKY_SYSTEM_ENV_START_SCREEN_SAVER_MODE"; // 屏保启动间隔
    public static final String SKY_SYSTEM_ENV_BOOT_START_HOME_PAGE = "SKY_SYSTEM_ENV_BOOT_START_HOME_PAGE"; // 开机启动首页
    public static final String SKY_SYSTEM_ENV_ACTIVE_ID = "SKY_SYSTEM_ENV_ACTIVE_ID"; // 激活ID（服务ID）
    public static final String SKY_SYSTEM_ENV_GAIN_COINS_ANIMATION_SWITCH = "SKY_SYSTEM_ENV_GAIN_COINS_ANIMATION_SWITCH";
    public static final String SKY_SYSTEM_ENV_MACHINE_CODE = "SKY_SYSTEM_ENV_MACHINE_CODE"; // 电视ID（设备Code）
    
    public static final String SKY_SYSTEM_ENV_CHILD_MODE_TIME_LIMIT_CONTROL = "SKY_SYSTEM_ENV_CHILD_MODE_TIME_LIMIT_CONTROL"; // 儿童模式观看时长控制
    public static final String SKY_SYSTEM_ENV_CHILD_MODE_STATE = "SKY_SYSTEM_ENV_CHILD_MODE_STATE"; // 儿童模式状态获取
    public static final String SKY_SYSTEM_ENV_CHILD_MODE_SWITCH = "SKY_SYSTEM_ENV_CHILD_MODE_SWITCH"; // 儿童模式开关
    public static final String SKY_SYSTEM_ENV_HOMEPAGE_SWITCH = "SKY_SYSTEM_ENV_HOMEPAGE_SWITCH"; // 首页切换
    
    public static final String SKY_SYSTEM_ENV_AUTO_POWEROFF = "SKY_SYSTEM_ENV_AUTO_POWEROFF"; //自动关机，海外产品功能
    public static final String SKY_SYSTEM_ENV_WATCH_TIME_REMINDER = "SKY_SYSTEM_ENV_WATCH_TIME_REMINDER"; //连续观看电视提醒，海外产品功能

    //add by tianjisheng 开放一些状态：设备类型 所属  是否正式/测试  软件id  设备号
    public static final String SKY_SYSTEM_ENV_SYSTEM_STATUS = "SKY_SYSTEM_ENV_SYSTEM_STATUS";//系统正式或者测试  String 1-正式；2-测试
    public static final String SKY_SYSTEM_ENV_SWID = "SKY_SYSTEM_ENV_SWID";//5.8新做的软件id也是系统配置索引，注意几个id的区别，不要用混
    public static final String SKY_SYSTEM_ENV_DEVICE = "SKY_SYSTEM_ENV_DEVICE";//5.8新做设备号
    public static final String SKY_SYSTEM_ENV_SYSTEM_OWNER = "SKY_SYSTEM_ENV_SYSTEM_OWNER";//所属者

    //智能摄像机
    public static final String SKY_SYSTEM_ENV_SMART_CAMERA_SWITCH = "SKY_SYSTEM_ENV_SMART_CAMERA_SWITCH";//智能摄像头开关
    public static final String SKY_SYSTEM_ENV_CHILD_VISION_PRO = "SKY_SYSTEM_ENV_CHILD_VISION_PRO";//儿童视力保护
    public static final String SKY_SYSTEM_ENV_ELDERLY_CARE_MODEL = "SKY_SYSTEM_ENV_ELDERLY_CARE_MODEL";//老人关爱模式
    public static final String SKY_SYSTEM_ENV_SMART_BRIGHTNESS_CONTROL = "SKY_SYSTEM_ENV_SMART_BRIGHTNESS_CONTROL";//智能场景识别
    public static final String SKY_SYSTEM_ENV_GESTURE_RECOGNITION = "SKY_SYSTEM_ENV_GESTURE_RECOGNITION";//手势识别开关

    // DataUsage
    public static final String SKY_SYSTEM_ENV_DATA_USAGE_LIMIT_VALUE = "SKY_SYSTEM_ENV_DATA_USAGE_LIMIT_VALUE";// DataUsage LimitBytes ,value only

    public static final String SKY_SYSTEM_ENV_DATA_USAGE_LIMIT = "SKY_SYSTEM_ENV_DATA_USAGE_LIMIT";// DataUsage LimitBytes String with GB

    public static final String SKY_SYSTEM_ENV_DATA_USAGE_ALERT = "SKY_SYSTEM_ENV_DATA_USAGE_ALERT";//

    public static final String SKY_SYSTEM_ENV_BOOT_START_NEW = "SKY_SYSTEM_ENV_BOOT_START_NEW"; // 9.2开机启动

    public static final String  SKY_SYSTEM_ENV_SOURCE_SWITCH = "SKY_SYSTEM_ENV_SOURCE_SWITCH"; // 信号源切换


    // ************** old key delete by mikan *********************

    // public static final String SKY_SYSTEM_ENV_STATUS_PATH = "STATUS_PATH";// 应用路径
    // public static final String SKY_SYSTEM_ENV_STATUS_USERNAME = "STATUS_USERNAME";// 登录用户昵称
    // public static final String SKY_SYSTEM_ENV_STATUS_USB = "STATUS_USB";// USB插入状态
    // public static final String SKY_SYSTEM_ENV_STATUS_SD = "STATUS_SD";// SD卡插入状态
    // public static final String SKY_SYSTEM_ENV_STATUS_WIFI = "STATUS_WIFI";// 无线IP获取状态
    // public static final String SKY_SYSTEM_ENV_STATUS_ETH = "STATUS_ETH";// 有线IP获取状态
    // public static final String SKY_SYSTEM_ENV_PHYSICAL_ETH = "PHYSICAL_ETH";// 有线物理状态
    // public static final String SKY_SYSTEM_ENV_PHYSICAL_WIFI = "PHYSICAL_WIFI";// 无线物理状态
    // public static final String SKY_SYSTEM_ENV_NET_AVAILIBLE = "NET_AVAILIBLE";// 网络连通状态
    // public static final String SKY_SYSTEM_ENV_SERVER_AVAILIBLE = "SERVER_AVAILIBLE";// 云平台服务器连通状态
    // public static final String SKY_SYSTEM_ENV_CURRENT_SOURCE = "CURRENT_SOURCE";// 当前通道
    // public static final String SKY_SYSTEM_ENV_BOOT_SOURCE = "BOOT_SOURCE";// 开机通道
    // public static final String SKY_SYSTEM_ENV_STB_SOURCE = "STB_SOURCE";// 机顶盒通道 //add for coocaa
    // public static final String SKY_SYSTEM_ENV_FILESCANNER_STATUS = "FILE_SCANNER_STATUS";//
    // // 文件扫描状态
    // public static final String SKY_SYSTEM_ENV_CURRENT_LANGUAGE = "CURRENT_LANGUAGE";// 系统当前语言
    // public static final String SKY_SYSTEM_ENV_STATUS_AUDIO_PLAY = "STATUS_AUDIO_PLAY";// 是否有音乐在播放
    // public static final String SKY_SYSTEM_ENV_STATUS_VOICE_READER = "STATUS_VOICE_READER"; //
    // // 是否语音阅读
    // public static final String SKY_SYSTEM_ENV_STATUS_RECORD_USER_HISTORY =
    // "STATUS_RECORD_USER_HISTORY"; // 是否记录历史
    // public static final String SKY_SYSTEM_ENV_STATUS_DOWNLOAD = "STATUS_DOWNLOAD";// 下载状态
    // public static final String SKY_SYSTEM_ENV_STATUS_UPDATE = "STATUS_UPDATE";// 系统/应用更新状态
    // public static final String SKY_SYSTEM_ENV_SIDE_KEY = "SIDE_KEY";// 侧边键控条
    // public static final String SKY_SYSTEM_ENV_RING_DISAPPEAR_TIME = "RING_DISAPPEAR_TIME";//
    // 圆环消失时间设置
    // public static final String SKY_SYSTEM_ENV_OPEN_OUTER_NET = "OPEN_OUTER_NET"; // 外网服务

    // ************** old key delete by mikan *********************
}
