package com.tianci.system.define;

public class SystemProviderDefines {
    public static final String AUTHORITY = "com.tianci.provider";

    public static final String URI_PATH_METHOD = "content://" + AUTHORITY;
    public static final String METHOD_COMMON_REG_SYSTEM_CB = "METHOD_COMMON_REG_SYSTEM_CB";
    public static final String METHOD_COMMON_UNREG_SYSTEM_CB = "METHOD_COMMON_UNREG_SYSTEM_CB";

    public static final String METHOD_SYSTEM_SEND_UART = "METHOD_SYSTEM_SEND_UART";
    public static final String METHOD_SYSTEM_SET_UART_LISTENER = "METHOD_SYSTEM_SET_UART_LISTENER";
    public static final String METHOD_SYSTEM_SEND_UART_SYNC = "METHOD_SYSTEM_SEND_UART_SYNC";

    public static final String METHOD_SYSTEM_CMD_GET_CONFIG = "METHOD_SYSTEM_CMD_GET_CONFIG";
    public static final String METHOD_SYSTEM_CMD_SET_CONFIG = "METHOD_SYSTEM_CMD_SET_CONFIG";

    public static final String METHOD_SYSTEM_CMD_GET_ENV_CONFIG = "METHOD_SYSTEM_CMD_GET_ENV_CONFIG";
    public static final String METHOD_SYSTEM_CMD_SET_ENV_CONFIG = "METHOD_SYSTEM_CMD_SET_ENV_CONFIG";

    public static final String METHOD_HOTEL = "METHOD_HOTEL";
    public static final String METHOD_HOTEL_POWER_AP_STANDBY = "POWER_AP_STANDBY";
    public static final String METHOD_HOTEL_SUPPORT_AP_STANDBY = "SUPPORT_AP_STANDBY";
    public static final String METHOD_HOTEL_SET_AP_STANDBY = "SET_AP_STANDBY";
    public static final String METHOD_HOTEL_GET_AP_STANDBY = "GET_AP_STANDBY";
    public static final String METHOD_HOTEL_GET_AP_STANDBY_STATE = "GET_AP_STANDBY_STATE";
    public static final String METHOD_HOTEL_POWERONOFF_TIMER = "powerOnOff_timer";

    public static final String METHOD_AOD_SCREEN_FIX = "aod_screen_fix";

    public static final String URI_PATH_AOD = URI_PATH_METHOD + "/aod/";
    public static final String METHOD_AOD = "METHOD_AOD";
    public static final String METHOD_AOD_AI_SWITCH = "ai_switch";//AI开关
    public static final String METHOD_AOD_SWITCH = "aod_switch";//AOD显示方式切换
    public static final String METHOD_INSTALL_APK = "install_apk";

    /**
     * 通用接口
     */
    public static final String METHOD_COMMON = "COMMON";
    public static final String COMMON_IS_TOUCH_SCREEN = "is_touch_screen";
    //设置EDID后是否需要重启
    public static final String COMMON_IS_REBOOT_EDID = "is_reboot_edid";
    public static final String COMMON_GETHARDWAREINFO = "getHardwareInfo";
    public static final String COMMON_GETCPUINFO = "getCPUInfo";
    public static final String COMMON_GETRAMINFO = "getRAMInfo";
    public static final String COMMON_GETROMINFO = "getROMInfo";
    public static final String COMMON_GETMACINFO = "getMACInfo";
    public static final String COMMON_GETOUTPUTRESOLUTION = "getOutputResolution";

    public static final String COMMON_IS_ENVIRONMENT_LIGHT_SUPPORT = "is_environment_light_support";

    //旋转挂架
    public static final String URI_PATH_ROTATE_HANGER = URI_PATH_METHOD + "/rh";
    public static final String COMMON_RH_IS_ENABLE_OR_SWITCH = "rh_is_enable_or_switch";

    //调节拍摄角度等级
    public static final String COMMON_ADJUST_CAMERA_ANGLE = "adjust_camera_angle";

    //调节摄像头变焦等级
    public static final String COMMON_ADJUST_CAMERA_ZOOM = "adjust_camera_zoom";

    //调节拍摄角度 摄像头升降状态 true：升，false ：降
    public static final String COMMON_CAMERA_STATE = "camera_state";

    //设置摄像头供电状态
    public static final String COMMON_CAMERA_POWER_STATE = "CameraPowerState";

    // 控制云台摄像头
    public static final String COMMON_CONTROL_PTZ_CAMERA = "ptzCamera";

    // 二代控制云台摄像头
    public static final String COMMON_CONTROL_SEND_CAMERA_CMD = "sendCameraCmd";

    //AI待机
    public static final String COMMON_STANDBY_AI = "standbyAI";

    //耳机默认音量
    public static final String COMMON_SET_GET_EARPHONE_DEFAULT_VOLUME = "setGetEarphoneDefaultVolume";

    //去ipc，改成contentProvider
    public static final String URI_PATH_IPC = URI_PATH_METHOD + "/ipc";
    public static final String METHOD_SYSTEM_IPC_SET_GET = "METHOD_SYSTEM_IPC_SET_GET";

    //系统升级——来自本地媒体（本地升级）
    public static final String COMMON_START_UPGRADE = "start_upgrade";

    //是否支持动态
    public static final String COMMON_IS_SUPPORT_CMO = "common_is_support_cmo";

    //激活flag
    public static final String COMMON_FORCE_ACTIVATE_FLAG = "forceActivateFlag";

    //饱和度
    public static final String URI_PATH_TV_COLOR = URI_PATH_METHOD + "/tv_color";

    //背光亮度
    public static final String URI_PATH_BACK_LIGHT = URI_PATH_METHOD + "/back_light";

    //动态范围扩展 监听
    public static final String URI_PATH_SPECULAR_BOOST = URI_PATH_METHOD + "/specular_boost";

    //获取TV name
    public static final String COMMON_GET_TV_NAME = "get_tv_name";

    //获取ip信息
    public static final String COMMON_GET_IP = "get_ip";

    //获取ip信息
    public static final String COMMON_GET_IPV6 = "get_ipv6";

    //静态配置有线网
    public static final String COMMON_CONNECTETHBYSTATIC = "connectEthByStatic";
    //使用动态配置方式连接以太网
    public static final String COMMON_CONNECTETHBYDHCP = "connectEthByDhcp";
    //获取网络类型静态or动态
    public static final String COMMON_GETCONNECTMODE = "getConnectMode";

    //CMO 提示状态
    public static final String COMMON_CMO_REMIND_STATE = "common_cmo_remind_state";

    //上电启动模式
    public static final String COMMON_ADDELECTRICPOWERMODE = "AddElectricPowerMode";
    //显示模式
    public static final String COMMON_DISPLAYMODE = "DisplayMode";
    //执行linux命令
    public static final String COMMON_EXEC_LINUX_CMD = "execLinuxCmd";

    //青少年模式开启
    public static final String COMMON_TEEN_MODE_OPEN_STATUS = "teenModeOpenStatus";

    //去coocaa主页     需要先finish掉自己apk的所有activity
    public static final String COMMON_GO_TO_HOME_PAGE = "gotoHomePage";

    //api版本
    public static final String COMMON_API_LEVEL = "apiLevel";

    //是否在Wifi界面flag
    public static final String COMMON_WIFI_VIEW_FLAG = "wifiViewFlag";

    //虚拟按键
    public static final String COMMON_VIRTUAL_INPUT = "VirtualInput";
    //Wisa模块重新连接音箱
    public static final String COMMON_WISA_RECONN_SPEAKER = "Wisa_Reconn";
    //Wisa模块断开音箱连接
    public static final String COMMON_WISA_DETACH_SPEAKER = "Wisa_Detach";

    //查询左音箱是否连接
    public static final String COMMON_WISA_CONN_L_SPEAKER = "common_wisa_conn_l_speaker";
    //查询右音箱是否连接
    public static final String COMMON_WISA_CONN_R_SPEAKER = "common_wisa_conn_r_speaker";
    //查询低音炮是否连接
    public static final String COMMON_WISA_CONN_S_SPEAKER = "common_wisa_conn_s_speaker";
    //设置Wisa音箱模式是否打开
    public static final String COMMON_WISA_MODE_SET = "common_wisa_conn_mode_set";
    //Wisa 设置listener
    public static final String METHOD_SYSTEM_SET_WISA_LISTENER = "method_system_set_wisa_listener";
    //曲面屏 设置listener
    public static final String METHOD_SYSTEM_SET_HOOK_FACE_LISTENER = "method_system_set_hook_face_listener";
    //获取5.8G 重低音环绕音音箱 环绕音音箱连接状态
    public static final String COMMON_G58_AROUND_STATE = "common_g58_around_state";
    //获取5.8G 重低音环绕音音箱 重低音音箱连接状态
    public static final String COMMON_G58_BASS_STATE = "COMMON_G58_BASS_STATE";

    //TV当天使用时间
    public static final String COMMON_TV_USE_TIME_DAY = "TVUseTimeDay";

    //TV7天使用时间
    public static final String COMMON_TV_USE_TIME_WEEK = "TVUseTimeWeek";

    //护眼模式
    public static final String COMMON_NEW_EYE_PROTECT_MODE = "NewEyeProtectMode";

    //保活应用注册解注册
    public static final String COMMON_KEEP_LIVE_APP = "KeepLiveApp";

    //SDR-3D LUT文件
    public static final String COMMON_SDR_LUT_INPUT = "sdrLutInput";

    //HDR-3D LUT文件导入
    public static final String COMMON_HDR_LUT_INPUT = "hdrLutInput";

    //1D LUT文件导入
    public static final String COMMON_ONE_LUT_INPUT = "oneLutInput";

    //3D LUT文件导入
    public static final String COMMON_HDR_LUT_INPUT_LOADING = "lutInputLoading";

    //AI开机广告是否在播放
    public static final String COMMON_IS_OPEN_AD_PLAYING = "common_is_open_ad_playing";

    //调节屏幕曲度及角度
    public static final String ADJUST_SCREEN_CURVATURE_ANGLE = "adjustScreenCurvatureAngle";

    //获取云台摄像头角度限位值
    public static final String GET_PTZ_CAMERA_LIMIT = "getPtzCameraLimit";

    //自动垂直梯形校正
    public static final String SET_PROJECTION_CORRECTION = "setProjectionCorrection";
    //投影缩放
    public static final String SET_PROJECTION_SCALE = "setProjectionScale";
    //顶点位移(变形位移)
    public static final String SET_PROJECTION_SLIDE_LINE = "setProjectionSlideLine";

    //待机后定时开机参数
    public static final String SKY_CFG_SET_TIMER_WAKEUP_PARAM = "SKY_CFG_SET_TIMER_WAKEUP_PARAM";

    //获取当前声音输出设备
    public static final String GET_SOUND_OUTPUT_DEVICE = "GET_SOUND_OUTPUT_DEVICE";

    //设置左右窗口的声音输出设备
    public static final String SET_SOUND_OUTPUT_DEVICE = "SET_SOUND_OUTPUT_DEVICE";

    //获取窗口声音输出设备的音量
    public static final String GET_WINDOW_VOLUME = "GET_WINDOW_VOLUME";

    //获取左窗口声音输出设备的音量权重
    public static final String GET_LEFT_WINDOW_VOLUME_WEIGHT = "GET_LEFT_WINDOW_VOLUME_WEIGHT";

    //设置左窗口声音输出设备的音量权重
    public static final String SET_LEFT_WINDOW_VOLUME_WEIGHT = "SET_LEFT_WINDOW_VOLUME_WEIGHT";

    //设置左右窗口声音输出设备的音量
    public static final String SET_WINDOW_VOLUME = "SET_WINDOW_VOLUME";

    //当前是否为新机器
    public static final String IS_NEW_MACHINE = "IS_NEW_MACHINE";

    //重新激活的原因
    public static final String RE_ACTIVE_REASON = "RE_ACTIVE_REASON";

    // Camera upgrade
    public static final String CAMERA_UPGRADE = "CAMERA_UPGRADE";

    // Camera Version
    public static final String CAMERA_VERSION = "CAMERA_VERSION";

    //Mini Led灯状态设置和获取
    public static final String MINI_LED_SWITCH_STATUS = "SET_MINI_LED_SWITCH_STATUS";

    //MiniLed亮度设置和获取
    public static final String MINI_LED_BRIGHTNESS = "MINI_LED_BRIGHTNESS";

    //获取MiniLed灯连接状态
    public static final String GET_LED_CONNECTED_STATUS = "GET_LED_CONNECTED_STATUS";

    //设置MiniLed灯连接状态改变回调监听接口
    public static final String SET_ITC_LED_CONNECTED_STATUS_LISTENER = "SET_ITC_LED_CONNECTED_STATUS_LISTENER";

    //显示一键直达引导页
    public static final String SHOW_RH_GUIDE_PAGE = "SHOW_RH_GUIDE_PAGE";

    //系统数据采集上报
    public static final String SYSTEM_DATER_COLLECT = "SYSTEM_DATER_COLLECT";

    // Game speed array
    public static final String  GAME_SPEED_WHITE_ARRAY = "GAME_SPEED_WHITE_ARRAY";

    // 获取蓝牙灯带的设备信息
    public static final String GET_TAPE_LIGHT_DEVICE_INFO ="GET_TAPE_LIGHT_DEVICE_INFO";

    /**
     * 获取蓝牙灯带的连接状态
     */
    public static final String SKY_CFG_TV_TAPE_LIGHT_CONNECTED_STATUS ="SKY_CFG_TV_TAPE_LIGHT_CONNECT_STATUS";

    /**
     * 设置蓝牙灯带通知升级接口
     */
    public static final String SKY_CFG_TV_TAPE_LIGHT_REQUEST_UPGRADE ="SKY_CFG_TV_TAPE_LIGHT_REQUEST_UPGRADE";

    /**
     * 获取蓝牙灯带亮灯方向
     */
    public static final String SKY_CFG_TV_TAPE_GET_LIGHT_DIRECTION = "SKY_CFG_TV_TAPE_GET_LIGHT_DIRECTION";

    /**
     * 设置蓝牙灯带亮灯方向
     */
    public static final String SKY_CFG_TV_TAPE_SET_LIGHT_DIRECTION = "SKY_CFG_TV_TAPE_SET_LIGHT_DIRECTION";

    /**
     * 蓝牙灯带是否为低配版本
     */
    public static final String SKY_CFG_TV_TAPE_LIGHT_STRIP_IS_LOW_PROFILE="SKY_CFG_TV_TAPE_LIGHT_STRIP_IS_LOW_PROFILE";

    /**
     * 是否支持OPS
     */
    public static final String COMMON_TV_SUPPORT_OPS ="COMMON_TV_SUPPORT_OPS";

    /**
     * 获取网络时间
     */
    public static final String COMMON_WEB_TIME = "COMMON_WEB_TIME";

    /**
     * 设置平台模式启动项式及模式主页（包名类名参数）
     * 设置后，下次开机启动要进入这个模式主页，并且按主页键也启动这个。
     */
    public static final String PLATFORM_MODE_BOOT_SET = "PLATFORM_MODE_BOOT_SET";

    /**
     * 读取平台模式启动项式及模式主页（包名类名参数）
     */
    public static final String PLATFORM_MODE_BOOT_GET = "PLATFORM_MODE_BOOT_GET";

    /**
     * 清除平台开机启动模式及模式主页（包名类名参数）
     */
    public static final String PLATFORM_MODE_BOOT_CLEAR = "PLATFORM_MODE_BOOT_CLEAR";


    /**
     * 设置rtc的开机时间
     */
    public static final String SET_RTC_STARTUP_TIME = "SET_RTC_STARTUP_TIME";

    // setOledRepair
    public static final String SET_OLED_REPAIR = "setOledRepair";

    // 是否支持 Feature
    public static final String IS_FEATURE_SUPPORT = "isFeatureSupport";


    /**
     * 获取升级前的酷开系统版本
     */
    public static final String SKY_LAST_SYSTEM_VERSION ="SKY_LAST_SYSTEM_VERSION";

    /**
     * 获取升级前的编译版本
     */
    public static final String SKY_LAST_BUILD_VERSION ="SKY_LAST_BUILD_VERSION";

    /**
     * 设置待机原因
     */
    public static final String COMMON_STANDBY_REASON = "standbyReason";

    /**
     * 获取解码器是否可用
     */
    public static final String COMMON_IS_DECODER_AVAILABLE = "COMMON_IS_DECODER_AVAILABLE";

    /**
     * 获取EMMC 寿命
     */
    public static final String GET_EMMC_LIFE = "GET_EMMC_LIFE";

    /**
     * 是否支持更换牌照商
     */
    public static final String IS_SUPPORT_CHANGE_LICENSOR ="IS_SUPPORT_CHANGE_LICENSOR";

    /**
     * 虚拟机型
     */
    public static final String VIRTUAL_SKY_TYPE = "VIRTUAL_SKY_TYPE";

    /**
     * DEV MAC
     */
     public static final String DEV_MAC = "DEV_MAC";

    /**
     * 系统埋点采集
     */
    public static final String  QUERY_SKY_USAGE_STATUS = "QUERY_SKY_USAGE_STATUS";

    /**
     * 系统启动时间
     */
    public static final String SYSTEM_BOOT_DURATION = "SYSTEM_BOOT_DURATION";

    //是否支持HDR
    public static final String COMMON_IS_SUPPORT_HDR = "COMMON_IS_SUPPORT_HDR";
}
