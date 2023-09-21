package com.tianci.system.define;


public class SkyConfigDefs {

    // 图像设置部分接口名称定义
    // 亮度
    public static final String SKY_CFG_TV_BRIGHTNESS = "SKY_CFG_TV_BRIGHTNESS";
    // 对比度
    public static final String SKY_CFG_TV_CONTRAST = "SKY_CFG_TV_CONTRAST";
    // 彩色
    public static final String SKY_CFG_TV_COLOR = "SKY_CFG_TV_COLOR";
    // 色调
    public static final String SKY_CFG_TV_HUE = "SKY_CFG_TV_HUE";
    // 清晰度
    public static final String SKY_CFG_TV_SHARPNESS = "SKY_CFG_TV_SHARPNESS";
    // 色温
    public static final String SKY_CFG_TV_COLOR_TEMPERATURE = "SKY_CFG_TV_COLOR_TEMPERATURE";
    /**
     * 接口描述：进入本地屏保时{@link SkyConfigDefs#SKY_CFG_TV_ACTIVE_LOCAL_SCREENSAVER}的色温接口
     * 取值范围：{@link TCWallpaperEnumColorTemperature}
     */
    public static final String SKY_CFG_TV_COLOR_TEMPERATURE_LSS = "SKY_CFG_TV_COLOR_TEMPERATURE_LSS";
    /**
     * 应用层通知中间件是否激活本地屏保
     */
    public static final String SKY_CFG_TV_ACTIVE_LOCAL_SCREENSAVER = "SKY_CFG_TV_ACTIVE_LOCAL_SCREENSAVER";

    /**
     * 壁纸模式
     */
    public static final String SKY_CFG_TV_WALLPAPER_MODE = "SKY_CFG_TV_WALLPAPER_MODE";

    /**
     * 壁纸出现后自动休眠（待机）时间
     */
    public static final String SKY_CFG_TV_WALLPAPER_MODE_AUTO_SLEEP_TIME = "SKY_CFG_TV_WALLPAPER_MODE_AUTO_SLEEP_TIME";

    /**
     * 仅“u盘中屏保设置菜单呼出后的当前图片”和本地屏保，壁纸应用
     * 壁纸模式色温
     */
    public enum TCWallpaperEnumColorTemperature
    {
        E_TV_COLOR_TEMPERATURE_LSS0, // 5000K
        E_TV_COLOR_TEMPERATURE_LSS1, // 5300K
        E_TV_COLOR_TEMPERATURE_LSS2, // 5600K
        E_TV_COLOR_TEMPERATURE_LSS3, // 5900K
        E_TV_COLOR_TEMPERATURE_LSS4, // 6200K
        E_TV_COLOR_TEMPERATURE_LSS5, // 6500K
        E_TV_COLOR_TEMPERATURE_LSS6, // 6800K
        E_TV_COLOR_TEMPERATURE_LSS7, // 7100K
        E_TV_COLOR_TEMPERATURE_LSS8, // 7400K
        E_TV_COLOR_TEMPERATURE_LSS9,  // 7700K
        E_TV_COLOR_TEMPERATURE_LSS10  // 8000K
    }
    // 降噪
    public static final String SKY_CFG_TV_DNR = "SKY_CFG_TV_DNR";
    // MPEG_NR
    @Deprecated
    public static final String SKY_CFG_TV_MPEG_NR = "SKY_CFG_TV_MPEG_NR";//add by gengkaiyang 2014-05-19
    // Cinema Mode
    @Deprecated
    public static final String SKY_CFG_TV_CINEMA_MODE = "SKY_CFG_TV_CINEMA_MODE";//add by gengkaiyang 2014-05-19
    // 显示模式
    public static final String SKY_CFG_TV_DISPLAY_MODE = "SKY_CFG_TV_DISPLAY_MODE";
    // 预设值显示模式
    @Deprecated
    public static final String SKY_CFG_TV_PREPMPLAYER_DISPLAY_MODE = "SKY_CFG_TV_PREPMPLAYER_DISPLAY_MODE";
    // 4K显示模式
    @Deprecated
    public static final String SKY_CFG_TV_DISPLAY_MODE_4K = "SKY_CFG_TV_DISPLAY_MODE_4K";
    // 图象模式
    public static final String SKY_CFG_TV_PICTURE_MODE = "SKY_CFG_TV_PICTURE_MODE";
    // 图象模式(设置外应用使用)
    public static final String SKY_CFG_TV_PICTURE_MODE_OTHER = "SKY_CFG_TV_PICTURE_MODE_OTHER";
    // AV通道的彩色制式
    @Deprecated
    public static final String SKY_CFG_TV_AV_COLOR_SYSTEM = "SKY_CFG_TV_AV_COLOR_SYSTEM";

    // 声音设置部分接口名称定义
    // 音量调节
    public static final String SKY_CFG_TV_VOLUME = "SKY_CFG_TV_VOLUME";
    // 静音功能控制
    public static final String SKY_CFG_TV_AUDIO_MUTE = "SKY_CFG_TV_AUDIO_MUTE";

    //
    public static final String SKY_CFG_TV_ARC_CUR_VOLUME = "SKY_CFG_TV_ARC_CUR_VOLUME";

    //低音
    public static final String SKY_CFG_TV_BASS = "SKY_CFG_TV_BASS";

    //高音
    public static final String SKY_CFG_TV_TREBLE = "SKY_CFG_TV_TREBLE";


    //低音
    public static final String SKY_CFG_TV_MZ_BASS = "SKY_CFG_TV_MZ_BASS";

    //高音
    public static final String SKY_CFG_TV_MZ_TREBLE = "SKY_CFG_TV_MZ_TREBLE";


    // 平衡
    @Deprecated
    public static final String SKY_CFG_TV_BALANCE = "SKY_CFG_TV_BALANCE";
    // 环绕声
    public static final String SKY_CFG_TV_SURROUND = "SKY_CFG_TV_SURROUND";
    // 语音清晰度
    @Deprecated
    public static final String SKY_CFG_TV_SOUND_CLARITY = "SKY_CFG_TV_SOUND_CLARITY";
    // 低音提升
    @Deprecated
    public static final String SKY_CFG_TV_TRUBASS = "SKY_CFG_TV_TRUBASS";
    // 重低音开关
    @Deprecated
    public static final String SKY_CFG_TV_SUBWOOFER = "SKY_CFG_TV_SUBWOOFER";
    // 重低音音量
    public static final String SKY_CFG_TV_SUBWOOFER_VOLUME = "SKY_CFG_TV_SUBWOOFER_VOLUME";
    // 自动音量
    @Deprecated
    public static final String SKY_CFG_TV_AVL = "SKY_CFG_TV_AVL";

    public static final String SKY_CFG_TV_EQUALIZER = "SKY_CFG_TV_EQUALIZER";
    // 均衡器100Hz频率点调整
    public static final String SKY_CFG_TV_EQUALIZER_100HZ = "SKY_CFG_TV_EQUALIZER_100HZ";
    // 均衡器500Hz频率点调整
    public static final String SKY_CFG_TV_EQUALIZER_500HZ = "SKY_CFG_TV_EQUALIZER_500HZ";
    @Deprecated
    public static final String SKY_CFG_TV_EQUALIZER_1KHZ = "SKY_CFG_TV_EQUALIZER_1KHZ";
    @Deprecated
    public static final String SKY_CFG_TV_EQUALIZER_3KHZ = "SKY_CFG_TV_EQUALIZER_3KHZ";
    // 均衡器1K5Hz频率点调整
    public static final String SKY_CFG_TV_EQUALIZER_1K5HZ = "SKY_CFG_TV_EQUALIZER_1K5HZ";
    // 均衡器5KHz频率点调整
    public static final String SKY_CFG_TV_EQUALIZER_5KHZ = "SKY_CFG_TV_EQUALIZER_5KHZ";
    // 均衡器10KHz频率点调整
    public static final String SKY_CFG_TV_EQUALIZER_10KHZ = "SKY_CFG_TV_EQUALIZER_10KHZ";
    // 声音模式
    public static final String SKY_CFG_TV_SOUND_MODE = "SKY_CFG_TV_SOUND_MODE";
    // 声音模式(设置外其他应用使用)
    public static final String SKY_CFG_TV_SOUND_MODE_OTHER = "SKY_CFG_TV_SOUND_MODE_OTHER";
    // 开关机音乐
    @Deprecated
    public static final String SKY_CFG_TV_POWER_ONOFF_MUSIC = "SKY_CFG_TV_POWER_ONOFF_MUSIC";
    // 开关机音乐音量
    @Deprecated
    public static final String SKY_CFG_TV_POWER_ONOFF_MUSIC_VOLUME = "SKY_CFG_TV_POWER_ONOFF_MUSIC_VOLUME";
    // 按键音音量
    @Deprecated
    public static final String SKY_CFG_TV_KEY_VOLUME = "SKY_CFG_TV_KEY_VOLUME";
    // 壁挂音效
    @Deprecated
    public static final String SKY_CFG_TV_WALL_SOUND_EFFECTS = "SKY_CFG_TV_WALL_SOUND_EFFECTS";
    // OUTPUT_PCMTYPE
    public static final String SKY_CFG_TV_SOUND_OUTPUT_PCMTYPE = "SKY_CFG_TV_SOUND_OUTPUT_PCMTYPE";
    // SPDIF
    public static final String SKY_CFG_TV_SOUND_SPDIF = "SKY_CFG_TV_SOUND_SPDIF";

    // 声音增益
    public static final String SKY_CFG_TV_EXTRA_SOUND_GAIN = "SKY_CFG_TV_EXTRA_SOUND_GAIN";

    // 音画同步
    public static final String SKY_CFG_TV_SOUND_VIDEO_SYNC = "SKY_CFG_TV_SOUND_VIDEO_SYNC";

    /**
     * 获取是否支持用户UI调节外接音箱音画同步
     */
    public static final String SKY_GFC_TV_IS_SUPPORT_SOUND_VIDEO_SYNC = "SKY_GFC_TV_IS_SUPPORT_SOUND_VIDEO_SYNC";

    /**
     * 获取是否支持UI调节蓝牙设备音画同步
     *
     */
    public static final String SKY_CFG_IS_SUPPORT_BTAV_SYNC = "SKY_CFG_IS_SUPPORT_BTAV_SYNC";

    // 高级设置菜单部分
    // 语言
    @Deprecated
    public static final String SKY_CFG_TV_LANGUAGE = "SKY_CFG_TV_LANGUAGE";
    // 睡眠时间
    public static final String SKY_CFG_TV_SLEEP_TIMER = "SKY_CFG_TV_SLEEP_TIMER";
    // 童锁
    public static final String SKY_CFG_TV_CHILD_LOCK = "SKY_CFG_TV_CHILD_LOCK";
    // 屏变
    public static final String SKY_CFG_TV_DREAM_PANEL = "SKY_CFG_TV_DREAM_PANEL";
    // 屏变演示模式
    @Deprecated
    public static final String SKY_CFG_TV_DREAM_PANEL_DEMO_FLAG = "SKY_CFG_TV_DREAM_PANEL_DEMO_FLAG";
    // 手动背光调整
    public static final String SKY_CFG_TV_MANUAL_BACKLIGHT_ADJUST = "SKY_CFG_TV_MANUAL_BACKLIGHT_ADJUST";
    // MEMC模式
    public static final String SKY_CFG_TV_MEMC_MODE = "SKY_CFG_TV_MEMC_MODE";
    // MEMC 120Hz演示模式
    @Deprecated
    public static final String SKY_CFG_TV_MEMC_120HZ_DEMO_MODE = "SKY_CFG_TV_MEMC_120HZ_DEMO_MODE";
    // 屏稳开关
    @Deprecated
    public static final String SKY_CFG_TV_MEMC_SWITCH = "SKY_CFG_TV_MEMC_SWITCH";
    // 矩阵背光
    public static final String SKY_CFG_TV_LOCAL_DIMMING_MODE = "SKY_CFG_TV_LOCAL_DIMMING_MODE";
    // 单独听
    public static final String SKY_CFG_TV_SOUND_SEPERATE = "SKY_CFG_TV_SOUND_SEPERATE";
    // 恢复出厂设置
    public static final String SKY_CFG_TV_RECOVERY = "SKY_CFG_TV_RECOVERY";
    // 电视名称
    public static final String SKY_CFG_TV_TVNAME = "SKY_CFG_TV_TVNAME";
    // 重启电视
    public static final String SKY_CFG_TV_REBOOT_SYSTEM = "SKY_CFG_TV_REBOOT_SYSTEM";
    // 获取电视唯一识别码
    public static final String SKY_CFG_TV_UNIQUE_IDENTIFICATION_CODE = "SKY_CFG_TV_UNIQUE_IDENTIFICATION_CODE";

    // 磁盘信息获取
    public static final String SKY_CFG_DISKINFO_SETTING = "SKY_CFG_DISKINFO_SETTING";

    // 磁盘信息获取非sdcard
    public static final String SKY_CFG_DISKINFO_SETTING_EXCEPT_INTERNAL_SDCARD = "SKY_CFG_DISKINFO_SETTING_EXCEPT_INTERNAL_SDCARD";

    // 3D设置菜单部分
    public static final String SKY_CFG_TV_THREED_SETTING = "SKY_CFG_TV_THREED_SETTING";
    // 3D模式
    public static final String SKY_CFG_TV_3D_MODE = "SKY_CFG_TV_3D_MODE";
    // 3D景深调节
    public static final String SKY_CFG_TV_3D_DEPTH = "SKY_CFG_TV_3D_DEPTH";
    // 3D左右交换
    public static final String SKY_CFG_TV_3D_LR_SWAP = "SKY_CFG_TV_3D_LR_SWAP";
    // 3D转2D
    public static final String SKY_CFG_TV_3D_TO_2D = "SKY_CFG_TV_3D_TO_2D";
    // 3D效果
    public static final String SKY_CFG_TV_3D_EFFECT = "SKY_CFG_TV_3D_EFFECT";

    // 是否3D屏
    public static final String SKY_CFG_TV_3D_PANEL = "SKY_CFG_TV_3D_PANEL";

    // 本机信息
    @Deprecated
    public static final String SKY_CFG_INFORMATION_GET_SYSTEMINFO = "SKY_CFG_INFORMATION_GET_SYSTEMINFO";

    // 是否支持均衡器功能
    @Deprecated
    public static final String SKY_CFG_GET_SUPPORT_EQUALIZER = "SKY_CFG_GET_SUPPORT_EQUALIZER";
    // 语音阅读
    @Deprecated
    public static final String SKY_CFG_TV_VOICE_READER = "SKY_CFG_TV_VOICE_READER";
    // 记录历史
    public static final String SKY_CFG_TV_USER_HISTORY = "SKY_CFG_TV_USER_HISTORY";
    // 屏变开关
    @Deprecated
    public static final String SKY_CFG_TV_DREAM_PANEL_SWITCH = "SKY_CFG_TV_DREAM_PANEL_SWITCH";

    // 丽声 Live Sound
    @Deprecated
    public static final String SKY_CFG_TV_SOUND_LIVESOUND = "SKY_CFG_TV_SOUND_LIVESOUND";

    // 自定义屏保
    @Deprecated
    public static final String SKY_CFG_TV_SET_SCREENSAVER = "SKY_CFG_TV_SET_SCREENSAVER";
    //城市设置
    public static final String SKY_CFG_TV_SET_LOCATION = "SKY_CFG_TV_SET_LOCATION";

    // 4K新增部分
    //4KUI开关 Q91-4K/2K UI切换
    public static final String SKY_CFG_TV_4KUI = "SKY_CFG_TV_4KUI";
    // 4K图像引擎
    public static final String SKY_CFG_TV_4K_IMAGE_ENHANCEMENT_PROCESSING_ENGINE = "SKY_CFG_TV_4K_IMAGE_ENHANCEMENT_PROCESSING_ENGINE";
    // 全色域演示
    public static final String SKY_CFG_TV_INFORMATION_GET_PANEL_COLOR_DEPTH_DEMO = "SKY_CFG_TV_INFORMATION_GET_PANEL_COLOR_DEPTH_DEMO";
    // 4K全景动态补偿
    public static final String SKY_CFG_TV_4K_IMAGE_ENHANCEMENT_PROCESSING_COMPENSATE = "SKY_CFG_TV_4K_IMAGE_ENHANCEMENT_PROCESSING_COMPENSATE";
    // 4K全景动态补偿演示
    public static final String SKY_CFG_TV_4K_IMAGE_ENHANCEMENT_PROCESSING_DEMO_MODE = "SKY_CFG_TV_4K_IMAGE_ENHANCEMENT_PROCESSING_DEMO_MODE";
    //无线音频开关
    @Deprecated
    public static final String SKY_CFG_CHANGE_WIRELESS_AUDIO_ONOFF = "SKY_CFG_CHANGE_WIRELESS_AUDIO_ONOFF";
    // 当前播放是否为4K片源
    @Deprecated
    public static final String SKY_CFG_TV_4K_SWITCH_4K_SOURCE = "SKY_CFG_TV_4K_SWITCH_4K_SOURCE";

    @Deprecated
    public static final String SKY_CFG_TV_FACTORY_SETUP_PANEL_SWING = "SKY_CFG_TV_FACTORY_SETUP_PANEL_SWING";
    @Deprecated
    public static final String SKY_CFG_TV_FACTORY_SETUP_WOLE_STATUS = "SKY_CFG_TV_FACTORY_SETUP_WOLE_STATUS";
    @Deprecated
    public static final String SKY_CFG_TV_SOURCE_STORAGE = "SKY_CFG_TV_SOURCE_STORAGE";

    // 待机 关机
    public static final String SKY_CFG_TV_POWER_OFF = "SKY_CFG_TV_POWER_OFF";

    //低音还原
    @Deprecated
    public static final String SKY_CFG_TV_BASS_REDUCTION = "SKY_CFG_TV_BASS_REDUCTION";

    //声道切换
    @Deprecated
    public static final String SKY_CFG_TV_TRACK_SWITCH = "SKY_CFG_TV_TRACK_SWITCH";

    //当彩色制式为auto时，利用此项获取真实的彩色制式
    @Deprecated
    public static final String SKY_CFG_TV_REAL_COLOR_SYSTEM = "SKY_CFG_TV_REAL_COLOR_SYSTEM";

    //重启前释放TV资源
    @Deprecated
    public static final String SKY_CFG_TV_NEED_RELEASE_BEFORE_REBOOT = "SKY_CFG_TV_NEED_RELEASE_BEFORE_REBOOT";

    //爱奇艺视频播放
    @Deprecated
    public static final String SKY_CFG_TV_IQIYI_PLAYER = "SKY_CFG_TV_IQIYI_PLAYER";

    // 游戏模式
    @Deprecated
    public static final String SKY_CFG_TV_SWITCH_BY_PASS = "SKY_CFG_TV_SWITCH_BY_PASS";

    //添加获取真实的3D模式
    @Deprecated
    public static final String SKY_CFG_TV_REAL_3D_MODE = "SKY_CFG_TV_REAL_3D_MODE";
    //选择适当的microphone device
    @Deprecated
    public static final String SKY_CFG_TV_MICROPHONE_DEVICE = "SKY_CFG_TV_MICROPHONE_DEVICE";
    //是否是4k2k
    @Deprecated
    public static final String SKY_CFG_TV_IS_4K2K_MODE = "SKY_CFG_TV_IS_4K2K_MODE";
    //当按键板UI的focus不在确认上时，收到确认键就掉用plugin的方法的清除底层的计
    @Deprecated
    public static final String SKY_CFG_TV_CLEAR_PAD_REBOOT_CNT = "SKY_CFG_TV_CLEAR_PAD_REBOOT_CNT";

    //外部存储设备的一个开关项
    @Deprecated
    public static final String SKY_CFG_TV_EXTERNAL_STORAGE_IDENTIFICATION = "SKY_CFG_TV_EXTERNAL_STORAGE_IDENTIFICATION";

    // 快速待机模式
    public static final String SKY_CFG_TV_STANDBY_MODE_QUICK = "SKY_CFG_TV_STANDBY_MODE_QUICK";

    // 截屏 (TC4.2新增)
    public static final String SKY_CFG_TV_SCREENSHOT = "SKY_CFG_TV_SCREENSHOT";

    // 截屏功能是否只截取video层
    public static final String SKY_CFG_TV_SCREENSHOT_IS_ONLY_HAVE_VIDEO_LAYER = "SKY_CFG_TV_SCREENSHOT_IS_ONLY_HAVE_VIDEO_LAYER";

    // 主页开关
    @Deprecated
    public static final String SKY_CFG_TV_HOMEPAGE_SWITCH = "SKY_CFG_TV_HOMEPAGE_SWITCH";

    // 呼吸灯开关
    public static final String SKY_CFG_TV_BREATH_LIGHT_SWITCH = "SKY_CFG_TV_BREATH_LIGHT_SWITCH";

    //触摸开关
    public static final String SKY_CFG_TV_TOUCH_LOGO_SWITCH = "SKY_CFG_TV_TOUCH_LOGO_SWITCH";

    //MENU以及各个通道菜单的路径
    public static final String SKY_CFG_TV_MENU_FILE_PATH = "SKY_CFG_TV_MENU_FILE_PATH";

    //设置背光和声音是否关闭
    public static final String SKY_CFG_TV_SET_SCREEN_SOUND = "SKY_CFG_TV_SET_SCREEN_SOUND";

    //Soundbar模式定义
    public static final String SKY_CFG_TV_SOUNDBAR_MODE = "SKY_CFG_TV_SOUNDBAR_MODE";

    //首页切换开关
    public static final String SKY_CFG_TV_EDID = "SKY_CFG_TV_EDID";

    public static final String SKY_CFG_TV_POWER_ON_MODE = "SKY_CFG_TV_POWER_ON_MODE";

    // 高动态范围图象演示
    public static final String SKY_CFG_TV_HDR_DEMO = "SKY_CFG_TV_HDR_DEMO";

    // 护眼模式
    public static final String SKY_CFG_TV_EYE_PROTECTED = "SKY_CFG_TV_EYE_PROTECTED";

    // 4K MEMC
    public static final String SKY_CFG_TV_4K_MEMC = "SKY_CFG_TV_4K_MEMC";

    // 4K LocalDimming
    public static final String SKY_CFG_TV_4K_LOCAL_DIMMING = "SKY_CFG_TV_4K_LOCAL_DIMMING";

    // 4K HDR
    public static final String SKY_CFG_TV_4K_HDR = "SKY_CFG_TV_4K_HDR";

    // 关屏待机默认开关
    public static final String SKY_CFG_TV_CLOSE_SCREEN_SOUND_SWITCH = "SKY_CFG_TV_CLOSE_SCREEN_SOUND_SWITCH";

    //开机视频声音开关
    public static final String SKY_CFG_TV_BOOT_VIDEO_SOUND_SWITCH = "SKY_CFG_TV_BOOT_VIDEO_SOUND_SWITCH";

    //体育模式开关
    public static final String SKY_CFG_TV_SPORT_MODE_SWITCH = "SKY_CFG_TV_SPORT_MODE_SWITCH";

    // 是否支持关屏待机
    public static final String SKY_CFG_TV_IS_SUPPORT_CLOSE_SCREEN_SOUND = "SKY_CFG_TV_IS_SUPPORT_CLOSE_SCREEN_SOUND";

    // 是否支持STR待机
    public static final String SKY_CFG_TV_IS_SUPPORT_STR_STANDBY = "SKY_CFG_TV_IS_SUPPORT_STR_STANDBY";

    // STR待机开关
    public static final String SKY_CFG_TV_STR_STATE = "SKY_CFG_TV_STR_STATE";

    //音乐灯、喇叭灯
    public static final String SKY_CFG_TV_MUSIC_LIGHT = "SKY_CFG_TV_MUSIC_LIGHT";

    //重显率，盒子产品需要
    public static final String SKY_CFG_TV_DISPLAY_OVER_SCAN = "SKY_CFG_TV_DISPLAY_OVER_SCAN";

    //HDMI输出格式，盒子产品需要
    public static final String SKY_CFG_TV_HDMI_OUTPUT = "SKY_CFG_TV_HDMI_OUTPUT";

    //声音输出，区分内置音响与外接音响
    public static final String SKY_CFG_TV_AUDIO_OUTPUT = "SKY_CFG_TV_AUDIO_OUTPUT";

    //屏幕尺寸
    public static final String SKY_CFG_TV_PANEL_SIZE = "SKY_CFG_TV_PANEL_SIZE";

    // 是否支持截屏
    public static final String SKY_CFG_TV_IS_SUPPORT_SCREENSHOT = "SKY_CFG_TV_IS_SUPPORT_SCREENSHOT";

    public static final String SKY_CFG_TV_BUILD_SERVER = "SKY_CFG_TV_BUILD_SERVER"; //编译服务器

    public static final String SKY_CFG_TV_OLED_SCREEN_FIX_OFFRS_TIME = "SKY_CFG_TV_OLED_SCREEN_FIX_OFFRS_TIME";// OLED未做 OFFRS 屏幕修复累计时间

    public static final String SKY_CFG_TV_OLED_SCREEN_FIX_JB_TIME = "SKY_CFG_TV_OLED_SCREEN_FIX_JB_TIME";//OLED未做JB 屏幕修复累计时间

    public static final String SKY_CFG_TV_OLED_JB_FIX_USER_CHOICE = "SKY_CFG_TV_OLED_JB_FIX_USER_CHOICE";//OLED JB 屏幕修复用户选择

    public static final String SKY_CFG_TV_OLED_PIXEL_REFRESH_SWITCH = "SKY_CFG_TV_OLED_PIXEL_REFRESH_SWITCH";// OLED PIXEL REFRESH SWITCH

    public static final String SKY_CFG_TV_OLED_FIX_OFFERS_TIME_NEEDED = "SKY_CFG_TV_OLED_FIX_OFFERS_TIME_NEEDED";// Offers 修复耗时

    public static final String SKY_CFG_TV_OLED_FIX_JB_TIME_NEEDED = "SKY_CFG_TV_OLED_FIX_JB_TIME_NEEDED";// JB 修复耗时

    public static final String SKY_CFG_TV_ATMOS_SOUND_EFFECTS = "SKY_CFG_TV_ATMOS_SOUND_EFFECTS"; //ATMOS音效
    public static final String SKY_CFG_TV_ATMOS_SCENE_SELECT = "SKY_CFG_TV_ATMOS_SCENE_SELECT"; //ATMOS场景选择
    public static final String SKY_CFG_TV_ATMOS_MIDDLE_VOLUME = "SKY_CFG_TV_ATMOS_MIDDLE_VOLUME"; //ATMOS中置音量微调
    public static final String SKY_CFG_TV_ATMOS_SUBWOOFER_VOLUME = "SKY_CFG_TV_ATMOS_SUBWOOFER_VOLUME"; //ATMOS重低音微调
    //    public static final String SKY_CFG_TV_ATMOS_SURROUND_VOLUME_ADJUST = "SKY_CFG_TV_ATMOS_SURROUND_VOLUME_ADJUST"; //ATMOS环绕音量微调
    public static final String SKY_CFG_TV_ATMOS_PANORAMIC_VOLUME_ADJUST = "SKY_CFG_TV_ATMOS_PANORAMIC_VOLUME_ADJUST"; //ATMOS全景音量微调
    public static final String SKY_CFG_TV_ATMOS_DRC = "SKY_CFG_TV_ATMOS_DRC"; //ATMOS动态范围控制
    public static final String SKY_CFG_TV_ATMOS_MAIN_SOUNDTRACK_VOLUME = "SKY_CFG_TV_ATMOS_MAIN_SOUNDTRACK_VOLUME"; //ATMOS主声道音量
    public static final String SKY_CFG_TV_ATMOS_SURROUND_LEFT_VOLUME_ADJUST = "SKY_CFG_TV_ATMOS_SURROUND_LEFT_VOLUME_ADJUST"; //ATMOS左环绕声微调
    public static final String SKY_CFG_TV_ATMOS_SURROUND_RIGHT_VOLUME_ADJUST = "SKY_CFG_TV_ATMOS_SURROUND_RIGHT_VOLUME_ADJUST"; //ATMOS右环绕声微调


    public static final String SKY_CFG_TV_VIDEO_STREAM_TYPE = "SKY_CFG_TV_VIDEO_STREAM_TYPE";

    /**
     * 电影模式，海外系统有此功能
     */
    public static final String SKY_CFG_TV_MOVIE_MODE = "SKY_CFG_TV_MOVIE_MODE";

    /**
     * 自动低延迟模式
     */
    public static final String SKY_CFG_TV_ALLM_MODE = "SKY_CFG_TV_ALLM_MODE";

    /**
     * is support ALLM
     */
    public static final String SKY_CFG_IS_SUPPORT_ALLM_MODE = "SKY_CFG_IS_SUPPORT_ALLM_MODE";

    /**
     * 120Hz超高刷新引擎
     */
    public static final String SKY_CFG_TV_HSR120HZ = "SKY_CFG_TV_HSR120HZ";

    /**
     * 是否支持SKY_CFG_TV_HSR120HZ
     */
    public static final String SKY_CFG_IS_SUPPORT_HSR120HZ = "SKY_CFG_IS_SUPPORT_HSR120HZ";

    /**
     * is support XDR
     */
    public static final String SKY_CFG_IS_SUPPORT_XDR_MODE = "SKY_CFG_IS_SUPPORT_XDR_MODE";

    /**
     * XDR Mode
     */
    public static final String SKY_CFG_TV_XDR_MODE = "SKY_CFG_TV_XDR_MODE";

    /**
     * XDR Mode flag open ,not call middleware pq.
     */
    public static final String SKY_CFG_TV_RESET_XDR_FLAG = "SKY_CFG_TV_RESET_XDR_FLAG";


    /**
     * 外销产品的图像降噪，海外系统有此功能
     */
    public static final String SKY_CFG_TV_MPEG_DNR = "SKY_CFG_TV_MPEG_DNR";

    /**
     * 动态对比度，海外系统有此功能
     */
    public static final String SKY_CFG_TV_DYNAMIC_CONTRAST = "SKY_CFG_TV_DYNAMIC_CONTRAST";

    /**
     * 声音平衡，海外系统有此功能
     */
    public static final String SKY_CFG_TV_SOUND_BALANCE = "SKY_CFG_TV_SOUND_BALANCE";

    /**
     * 智能音量调节，海外系统有此功能
     */
    public static final String SKY_CFG_TV_AI_VOLUME_ADJUST = "SKY_CFG_TV_AI_VOLUME_ADJUST";

    /**
     * 使用情境，海外系统有此功能
     */
    public static final String SKY_CFG_TV_USE_ENVIRONMENT = "SKY_CFG_TV_USE_ENVIRONMENT";

    /**
     * StickerDemo，海外系统有此功能
     */
    public static final String SKY_CFG_TV_STICKER_DEMO = "SKY_CFG_TV_STICKER_DEMO";

    /**
     * 模拟音频输出，海外系统有此功能
     */
    public static final String SKY_CFG_TV_ANOLOG_AUDIO_OUTPUT = "SKY_CFG_TV_ANOLOG_AUDIO_OUTPUT";

    /**
     * 数字音频输出，海外系统有此功能
     */
    public static final String SKY_CFG_TV_DIGITAL_AUDIO_OUTPUT = "SKY_CFG_TV_DIGITAL_AUDIO_OUTPUT";

    /**
     * 夏令时开关，海外系统有此功能
     */
    public static final String SKY_CFG_TV_DAYLIGHT_SAVING_TIME = "SKY_CFG_TV_DAYLIGHT_SAVING_TIME";

    /**
     * 客户ID，海外系统有此功能
     */
    public static final String SKY_CFG_TV_CUSTOM_ID = "SKY_CFG_TV_CUSTOM_ID";
    /**
     * 分辨率，盒子产品有此功能
     */
    public static final String SKY_CFG_TV_RESOLUTION = "SKY_CFG_TV_RESOLUTION";

    // 截屏_带UI (TC6.0新增)
    public static final String SKY_CFG_TV_SCREENSHOT_WITH_UI = "SKY_CFG_TV_SCREENSHOT_WITH_UI";

    public enum SKY_CFG_TV_COLOR_TEMPERATURE_ENUM_TYPE {
        SKY_CFG_TV_COLOR_TEMPERATURE_WARM,
        SKY_CFG_TV_COLOR_TEMPERATURE_STANDARD,
        SKY_CFG_TV_COLOR_TEMPERATURE_COOL,
        SKY_CFG_TV_COLOR_TEMPERATURE_USER,
        SKY_CFG_TV_COLOR_TEMPERATURE_INVALID,
    }

    public enum SKY_CFG_TV_DNR_ENUM_TYPE {
        SKY_CFG_TV_DNR_OFF,
        SKY_CFG_TV_DNR_LOW,
        SKY_CFG_TV_DNR_MID,
        SKY_CFG_TV_DNR_HIGH,
        SKY_CFG_TV_DNR_AUTO,
        SKY_CFG_TV_DNR_INVALID,
    }

    /**
     * 需要和TCPlatformDefs.java下的定义对应，与下边的 stream type相关联
     * SKY_CFG_TV_VIDEO_STREAM_TYPE_ENUM_TYPE
     */
    public enum SKY_CFG_TV_PICTURE_MODE_ENUM_TYPE {
        SKY_CFG_TV_PICTURE_MODE_STANDARD, // 标准
        SKY_CFG_TV_PICTURE_MODE_VIVID, // 亮丽、鲜艳
        SKY_CFG_TV_PICTURE_MODE_GENTLE, // 柔和
        SKY_CFG_TV_PICTURE_MODE_USER, // 自设
        SKY_CFG_TV_PICTURE_MODE_4K_CINEMA, // 4K影院
        SKY_CFG_TV_PICTURE_MODE_NATURAL, // 自然
        SKY_CFG_TV_PICTURE_MODE_GAME, //游戏模式，也就是HDMI通道下走RGB信号时采用的“点对点”模式
        SKY_CFG_TV_PICTURE_MODE_DOLBY_VISION_MOVIE_DARK, //DolbyVision Movie dark
        SKY_CFG_TV_PICTURE_MODE_DOLBY_VISION_MOVIE_BRIGHT, //DolbyVision Movie bright
        SKY_CFG_TV_PICTURE_MODE_PROFESSIONAL, //专业模式
        SKY_CFG_TV_PICTURE_MODE_SPORT, //运动，
        SKY_CFG_TV_PICTURE_MODE_MOVIE, //电影
        SKY_CFG_TV_PICTURE_MODE_DYNAMIC, //动态模式，海外产品有此模式
        SKY_CFG_TV_PICTURE_MODE_PHOTO, //照片
        SKY_CFG_TV_PICTURE_MODE_ECO, //节能
        SKY_CFG_TV_PICTURE_MODE_CARE,//长辈
        SKY_CFG_TV_PICTURE_MODE_AI,//AI
        SKY_CFG_TV_PICTURE_MODE_CAMERA,//摄像头
        SKY_CFG_TV_PICTURE_MODE_AUTO,//自动
        // used in displayer ，suitable for game
        SKY_CFG_TV_PICTURE_MODE_GAME_GENERAL,  //  游戏通用模式
        SKY_CFG_TV_PICTURE_MODE_GAME_RTS_RPG,  // RTS/RPG 模式
        SKY_CFG_TV_PICTURE_MODE_GAME_MOBA,   // MOBA 模式
        SKY_CFG_TV_PICTURE_MODE_GAME_FPS,   // FPS 模式
        SKY_CFG_TV_PICTURE_MODE_GAME_CINEMA,  // 影院模式
        SKY_CFG_TV_PICTURE_MODE_GAME_READ,  // 阅读模式
        SKY_CFG_TV_PICTURE_MODE_DOLBY_VISION_MOVIE_IQ,
        SKY_CFG_TV_PICTURE_MODE_DOLBY_VISION_GAME, //杜比视界游戏
        SKY_CFG_TV_PICTURE_MODE_CUVA_HDR,    // CUVA_HDR
        SKY_CFG_TV_PICTURE_MODE_LOW_BLUE_LIGHT, //显示器:低蓝光
        SKY_CFG_TV_PICTURE_MODE_DARKROOM, //显示器:暗房
        SKY_CFG_TV_PICTURE_MODE_DICOM, //显示器:DICOM
        SKY_CFG_TV_PICTURE_MODE_NOTEBOOK, //显示器:Notebook
        SKY_CFG_TV_PICTURE_MODE_FRAME_ENJOY, // 帧享
        SKY_CFG_TV_PICTURE_MODE_PC, // 办公
        SKY_CFG_TV_PICTURE_MODE_CUSTOM, // 音画定制，图像定制，私人模式

        SKY_CFG_TV_PICTURE_MODE_DOLBY_VISION_MOVIE_STANDARD, //DolbyVision Movie standard
        SKY_CFG_TV_PICTURE_MODE_DOLBY_VISION_MOVIE_VIVID, // DolbyVision Movie vivid
    }

    /**
     * @内容场景
     */
    public enum SKY_CFG_TV_CONTENT_SCENE_ENUM_TYPE {
        SKY_CFG_TV_PICTURE_SCENE_MOVIE, //电影
        SKY_CFG_TV_PICTURE_SCENE_NEWS, // 新闻
        SKY_CFG_TV_PICTURE_SCENE_SHOW, // 综艺
        SKY_CFG_TV_PICTURE_SCENE_SPORTS, //体育
        SKY_CFG_TV_PICTURE_SCENE_GAME,//游戏
        SKY_CFG_TV_PICTURE_SCENE_GAME_APP,//游戏app
        SKY_CFG_TV_PICTURE_SCENE_MUSIC,//音乐
        SKY_CFG_TV_PICTURE_SCENE_MUSIC_APP,//音乐app
        SKY_CFG_TV_PICTURE_SCENE_OTHER,//其他
    }

    public enum SKY_CFG_TV_DISPLAY_MODE_ENUM_TYPE {
        SKY_CFG_TV_DISPLAY_MODE_16_9,
        SKY_CFG_TV_DISPLAY_MODE_4_3,
        SKY_CFG_TV_DISPLAY_MODE_AUTO,
        SKY_CFG_TV_DISPLAY_MODE_MOVIE,
        SKY_CFG_TV_DISPLAY_MODE_CAPTION,
        SKY_CFG_TV_DISPLAY_MODE_PANORAMA,
        SKY_CFG_TV_DISPLAY_MODE_PERSON,
        SKY_CFG_TV_DISPLAY_MODE_21_9,
        SKY_CFG_TV_DISPLAY_MODE_INVALID,
    }

    public enum SKY_CFG_TV_DISPLAY_MODE_4K_ENUM_TYPE {
        SKY_CFG_TV_PICTURE_4K_DISPLAY,
        SKY_CFG_TV_PICTURE_HIGH_DISPLAY,
        SKY_CFG_TV_DISPLAY_MODE_4K_INVALID,
    }

    @Deprecated
    // 声音部分枚举参数表
    public enum SkyConfigSoundEffectModeType {
        SKY_CFG_TV_SOUND_EFFECT_MODE_OFF,
        SKY_CFG_TV_SOUND_EFFECT_MODE_EFFECT_1,
        SKY_CFG_TV_SOUND_EFFECT_MODE_EFFECT_2,
        SKY_CFG_TV_SOUND_EFFECT_MODE_EFFECT_3,
        SKY_CFG_TV_SOUND_EFFECT_MODE_INVALID,
    }

    public enum SKY_CFG_TV_ON_OFF_MODE_ENUM_TYPE {
        // off mode
        SKY_CFG_TV_OFF_MODE,
        // on mode
        SKY_CFG_TV_ON_MODE,
        // invalid mode 开关项 invalid 用于计数
        SKY_CFG_TV_ON_OFF_INVALID,
    }

    public enum SKY_CFG_TV_SOUND_MODE_ENUM_TYPE {
        SKY_CFG_TV_SOUND_MODE_STANDARD,
        SKY_CFG_TV_SOUND_MODE_MUSIC,
        SKY_CFG_TV_SOUND_MODE_NEWS,
        SKY_CFG_TV_SOUND_MODE_MOVIE,
        SKY_CFG_TV_SOUND_MODE_3D_MOVIE_EFFECT,
        SKY_CFG_TV_SOUND_MODE_USER,
        SKY_CFG_TV_SOUND_MODE_SPORT,
        SKY_CFG_TV_SOUND_MODE_GAME,
        SKY_CFG_TV_SOUND_MODE_CARE,//关爱
        SKY_CFG_TV_SOUND_MODE_AI,//AI模式
        SKY_CFG_TV_SOUND_MODE_AUTO,//Auto模式
        SKY_CFG_TV_SOUND_MODE_CUSTOM,//音画定制，私人模式
    }

    public enum SKY_CFG_TV_SOUND_SPDIF_ENUM_TYPE {
        SKY_CFG_TV_SOUND_SPDIF_SOURCE,
        SKY_CFG_TV_SOUND_SPDIF_PCM,
        SKY_CFG_TV_SOUND_SPDIF_PASSTHROUGH,
    }

    public enum SKY_CFG_TV_SOUND_OUTPUT_PCMTYPE_ENUM_TYPE {
        SKY_CFG_TV_SOUND_OUTPUT_PCM2_0,
        SKY_CFG_TV_SOUND_OUTPUT_PCM5_1,
    }

    public enum SKY_CFG_TV_DREAM_PANEL_ENUM_TYPE {
        SKY_CFG_TV_DREAM_PANEL_OFF,
        SKY_CFG_TV_DREAM_PANEL_LIGHT_SENSOR,
        SKY_CFG_TV_DREAM_PANEL_IMAGE_DETECT,
        SKY_CFG_TV_DREAM_PANEL_MULTI_DETECT,
        // 另有机制支持演示，打开 --lw
        SKY_CFG_TV_DREAM_PANEL_DEMO,
        SKY_CFG_TV_DREAM_PANEL_INVALID,
    }

    public enum SKY_CFG_TV_MEMC_MODE_ENUM_TYPE {
        SKY_CFG_TV_MEMC_MODE_OFF,
        SKY_CFG_TV_MEMC_MODE_LOW,
        SKY_CFG_TV_MEMC_MODE_MID,
        SKY_CFG_TV_MEMC_MODE_HIGH,
        SKY_CFG_TV_MEMC_MODE_INAVLID,
    }

    @Deprecated
    public enum SKY_CFG_TV_MEMC_120HZ_DEMO_MODE_ENUM_TYPE {
        SKY_CFG_TV_MEMC_120HZ_DEMO_OFF,
        SKY_CFG_TV_MEMC_120HZ_DEMO_LEFT_RIGHT,
        SKY_CFG_TV_MEMC_120HZ_DEMO_UP_DOWN,
        SKY_CFG_TV_MEMC_120HZ_DEMO_FULL_SCREEN,
        SKY_CFG_TV_MEMC_120HZ_DEMO_INVALID,
    }

    public enum SKY_CFG_TV_LOCAL_DIMMING_MODE_ENUM_TYPE {
        SKY_CFG_TV_LOCAL_DIMMING_OFF_MODE,
        SKY_CFG_TV_LOCAL_DIMMING_ON_MODE,
        SKY_CFG_TV_LOCAL_DIMMING_LOW_MODE,
        SKY_CFG_TV_LOCAL_DIMMING_MID_MODE,
        SKY_CFG_TV_LOCAL_DIMMING_HIGH_MODE,
        SKY_CFG_TV_LOCAL_DIMMING_DEMO_MODE,
        SKY_CFG_TV_LOCAL_DIMMING_INVALID_MODE,
    }

    public enum SKY_CFG_TV_LOCAL_DIMMING_MODE_NO_BACKLIGHT_ENUM_TYPE {
        SKY_CFG_TV_LOCAL_DIMMING_OFF_MODE,
        SKY_CFG_TV_LOCAL_DIMMING_ON_MODE,
        SKY_CFG_TV_LOCAL_DIMMING_LOW_MODE,
        SKY_CFG_TV_LOCAL_DIMMING_MID_MODE,
        SKY_CFG_TV_LOCAL_DIMMING_HIGH_MODE,
        SKY_CFG_TV_LOCAL_DIMMING_DEMO_MODE,
        SKY_CFG_TV_LOCAL_DIMMING_INVALID_MODE,
    }

    public enum SKY_CFG_TV_3D_MODE_ENUM_TYPE {
        SKY_CFG_TV_3D_MODE_OFF,
        SKY_CFG_TV_3D_MODE_AUTO,
        //        SKY_CFG_TV_3D_MODE_2D_TO_3D,
        SKY_CFG_TV_3D_MODE_SIDE_BY_SIDE,
        SKY_CFG_TV_3D_MODE_TOP_AND_BOTTOM,
        SKY_CFG_TV_3D_MODE_FRAME_PACKING,//add by gengkaiyang
        SKY_CFG_TV_3D_MODE_INVALID,
    }

    @Deprecated
    public enum SKY_CFG_TV_3D_EFFECT_ENUM_TYPE {
        SKY_CFG_TV_3D_EFFECT_LOW,
        SKY_CFG_TV_3D_EFFECT_MID,
        SKY_CFG_TV_3D_EFFECT_HIGH,
        SKY_CFG_TV_3D_EFFECT_INVALID,
    }

    public enum SKY_CFG_TV_3D_TO_2D_ENUM_TYPE {
        SKY_CFG_TV_3D_TO_2D_OFF,
        SKY_CFG_TV_3D_TO_2D_LEFT,
        SKY_CFG_TV_3D_TO_2D_RIGHT,
        SKY_CFG_TV_3D_TO_2D_INVALID,
    }

    public enum SKY_CFG_TV_4K_IMAGE_ENHANCEMENT_PROCESSING_ENGINE_ENUM_TYPE {
        SKY_CFG_TV_4K_IMAGE_ENHANCEMENT_PROCESSING_ENGINE_OPEN,
        SKY_CFG_TV_4K_IMAGE_ENHANCEMENT_PROCESSING_ENGINE_DEMO,
        SKY_CFG_TV_4K_IMAGE_ENHANCEMENT_PROCESSING_ENGINE_CLOSE,
        SKY_CFG_TV_4K_IMAGE_ENHANCEMENT_PROCESSING_ENGINE_INVALID,
    }

    public enum SKY_CFG_TV_4KUI_ENUM_TYPE {
        SKY_CFG_TV_4KUI_CLOSE,//高清
        SKY_CFG_TV_4KUI_OPEN//超清
    }

    public enum SKY_CFG_TV_4K_IMAGE_ENHANCEMENT_PROCESSING_COMPENSATE_ENUM_TYPE {
        SKY_CFG_TV_4K_IMAGE_ENHANCEMENT_PROCESSING_COMPENSATE_WEAK,
        SKY_CFG_TV_4K_IMAGE_ENHANCEMENT_PROCESSING_COMPENSATE_MIDDLE,
        SKY_CFG_TV_4K_IMAGE_ENHANCEMENT_PROCESSING_COMPENSATE_STRONG,
        SKY_CFG_TV_4K_IMAGE_ENHANCEMENT_PROCESSING_COMPENSATE_CLOSE,
        SKY_CFG_TV_4K_IMAGE_ENHANCEMENT_PROCESSING_COMPENSATE_AUTO,
        SKY_CFG_TV_4K_IMAGE_ENHANCEMENT_PROCESSING_COMPENSATE_INVALID,
    }

    public enum SKY_CFG_TV_4K_IMAGE_ENHANCEMENT_PROCESSING_DEMO_MODE_ENUM_TYPE {
        SKY_CFG_TV_4K_IMAGE_ENHANCEMENT_PROCESSING_DEMO_MODE_RIGHT,
        SKY_CFG_TV_4K_IMAGE_ENHANCEMENT_PROCESSING_DEMO_MODE_FULLSCREEN,
        SKY_CFG_TV_4K_IMAGE_ENHANCEMENT_PROCESSING_DEMO_MODE_CLOSE,
        SKY_CFG_TV_4K_IMAGE_ENHANCEMENT_PROCESSING_DEMO_MODE_OPEN,
        SKY_CFG_TV_4K_IMAGE_ENHANCEMENT_PROCESSING_DEMO_MODE_INVALID,
    }

    public enum SKY_CFG_TV_INFORMATION_GET_PANEL_COLOR_DEPTH_DEMO_ENUM_TYPE {
        SKY_CFG_TV_INFORMATION_GET_PANEL_COLOR_DEPTH_OPEN,
        SKY_CFG_TV_INFORMATION_GET_PANEL_COLOR_DEPTH_CLOSE,
        SKY_CFG_TV_INFORMATION_GET_PANEL_COLOR_DEPTH_DEMO_DEMO,
        SKY_CFG_TV_INFORMATION_GET_PANEL_COLOR_DEPTH_DEMO_INVALID,
    }

    //真实的3D的枚举
    public enum SKY_CFG_TV_REAL_3D_MODE_ENUM_TYPE {
        SKY_CFG_TV_REAL_3D_MODE_2D,
        SKY_CFG_TV_REAL_3D_MODE_3D,
    }

    @Deprecated
    public enum SKY_CONFIG_SET {
        SKY_CFG_TV_SOUND_SETTING,
        SKY_CFG_TV_PICTURE_SETTING,
        SKY_CFG_TV_ADVANCED_SETTING,
        SKY_CFG_TV_SYSTEM_SETTING,
        SKY_CFG_TV_THREED_SETTING,
    }

    @Deprecated
    /**
     * @Description globle definitions
     */
    public static final String SKY_CFG_STATISTIC_ENABLED = "enableStatisic";

    //断电开机启动方式
    public enum SKY_CFG_TV_POWER_ON_MODE_ENUM_TYPE {
        SKY_CFG_TV_POWER_ON_MODE_STANDBY, // 待机
        SKY_CFG_TV_POWER_ON_MODE_BOOT, //开机
        SKY_CFG_TV_POWER_ON_MODE_REMEMBER //记忆
    }

    /**
     * @Description 开机唤醒方式
     */
    public enum SKY_CFG_TV_POWER_ON_BOOT_MODE_ENUM_TYPE {
        SKY_CFG_TV_POWER_ON_BOOT_MODE_IR,    //红外
        SKY_CFG_TV_POWER_ON_BOOT_MODE_BT,    //蓝牙
        SKY_CFG_TV_POWER_ON_BOOT_MODE_KEYPAD //键控板
    }

    /**
     * @Description 上一次关机方式
     */
    public enum SKY_CFG_TV_LAST_POWER_OFF_MODE_ENUM_TYPE {
        SKY_CFG_TV_LAST_POWER_OFF_MODE_NORMAL,       //正常
        SKY_CFG_TV_LAST_POWER_OFF_MODE_AC,           //交流
        SKY_CFG_TV_LAST_POWER_OFF_MODE_REBOOT,       //reboot
        SKY_CFG_TV_LAST_POWER_OFF_MODE_WATCHDOG     //看门狗
    }

    @Deprecated
    public static final String SKY_CFG_TV_COMMON_DEMO = "SKY_CFG_TV_COMMON_DEMO";

    /**
     * 图像恢复默认（出厂）
     */
    public static final String SKY_CFG_TV_RESET_PICTURE_SETS = "SKY_CFG_TV_RESET_PICTURE_SETS";

    /**
     * 声音恢复默认（出厂）
     */
    public static final String SKY_CFG_TV_RESET_SOUND_SETS = "SKY_CFG_TV_RESET_SOUND_SETS";

    /**
     * 工厂单键
     */
    public static final String SKY_CFG_TV_FACTORY_SINGLE_KEY = "SKY_CFG_TV_FACTORY_SINGLE_KEY";

    /**
     * 电视LED灯控制
     */
    public static final String SKY_CFG_TV_LED_CONTROL = "SKY_CFG_TV_LED_CONTROL";

    /**
     * 获取系统所属(仅针对海外版本使用, 国内版本无需实现)
     */
    public static final String SKY_CFG_TV_SYSTEM_OWNER = "SKY_CFG_TV_SYSTEM_OWNER";

    public enum SKY_CFG_TV_LED_CONTROL_ENUM_TYPE {
        SKY_CFG_TV_LED_CONTROL_CLOSE,
        SKY_CFG_TV_LED_CONTROL_RED,
        SKY_CFG_TV_LED_CONTROL_BLUE,
        SKY_CFG_TV_LED_CONTROL_PURPLE,
        SKY_CFG_TV_LED_CONTROL_FLASH,
        SKY_CFG_TV_LED_CONTROL_OPEN,
        SKY_CFG_TV_LED_CONTROL_HIGH_LIGHT
    }

    /**
     * 护眼模式的用户行为
     * 联合以下两个功能的功能：
     * 立即启动{@link SKY_CFG_TV_NIGHT_GUARD_EYE_SWITCH}
     * 定时开关{@link SKY_CFG_TV_NIGHT_GUARD_EYE_TIME_SWITCH}
     */
    public enum SKY_CFG_TV_HEALTH_EYE_MODE_ACTION_ENUM {
        /**
         * 关闭
         */
        HEALTH_EYE_ACTION_OFF,
        /**
         * 全天开启
         */
        HEALTH_EYE_ACTION_DAY,
        /**
         * 定时
         */
        HEALTH_EYE_ACTION_TIME,
    }

    /**
     * 护眼模式的技术实现方案
     */
    public enum SKY_CFG_TV_HEALTH_EYE_MODE_ENUM {
        /**
         * 不支持护目模式
         */
        NOT_SUPPORT,
        /**
         * 仅在视频（video层）开启
         */
        VIDEO,
        /**
         * 全局（osd层）护眼
         */
        OSD,
        /**
         * 同时支持VIDEO与OSD
         */
        VIDEO_OSD
    }

    /**
     * @高动态范围图象演示
     */
    public enum SKY_CFG_TV_HDR_DEMO_ENUM_TYPE {
        E_TV_HDR_DEMO_OPEN,
        E_TV_HDR_DEMO_CLOSE,
    }

    public enum SKY_SYSTEM_ENV_START_SCREEN_SAVER_MODE_ENUM_TYPE {
        ONE_MIN(SysConst.ONE_MIN),
        TWO_MIN(SysConst.TWO_MIN),
        FIVE_MIN(SysConst.FIVE_MIN),
        TEN_MIN(SysConst.TEN_MIN),
        THIRTY_MIN(SysConst.THIRTY_MIN),
        NONE(SysConst.NONE);
        public int type;

        SKY_SYSTEM_ENV_START_SCREEN_SAVER_MODE_ENUM_TYPE(int type) {
            this.type = type;
        }

        public static SKY_SYSTEM_ENV_START_SCREEN_SAVER_MODE_ENUM_TYPE typeOf(int type) {
            switch (type) {
                case 0:
                    return ONE_MIN;
                case 1:
                    return TWO_MIN;
                case 2:
                    return FIVE_MIN;
                case 3:
                    return TEN_MIN;
                case 4:
                    return THIRTY_MIN;
                case 5:
                    return NONE;
            }
            return null;
        }
    }

    public enum SKY_CFG_TV_MUSIC_LIGHT_ENUM_TYPE {
        SKY_CFG_TV_MUSIC_LIGHT_HIGH,
        SKY_CFG_TV_MUSIC_LIGHT_MID,
        SKY_CFG_TV_MUSIC_LIGHT_LOW,
        SKY_CFG_TV_MUSIC_LIGHT_OFF
    }

    public enum SKY_CFG_TV_HDMI_OUTPUT_ENUM_TYPE {
        SKY_CFG_TV_HDMI_OUTPUT_AUTO,
        SKY_CFG_TV_HDMI_OUTPUT_RAW
    }

    public enum SKY_CFG_TV_AUDIO_OUTPUT_ENUM_TYPE {
        SKY_CFG_TV_AUDIO_OUTPUT_EXTERNAL,
        SKY_CFG_TV_AUDIO_OUTPUT_INTERNAL,
        SKY_CFG_TV_AUDIO_OUTPUT_MIX,
        SKY_CFG_TV_AUDIO_OUTPUT_BLUETOOTH
    }

    public enum SKY_CFG_TV_ATMOS_SCENE_SELECT_ENUM_TYPE {
        SKY_CFG_TV_ATMOS_SCENE_SELECT_STANDARD,
        SKY_CFG_TV_ATMOS_SCENE_SELECT_LIVING_ROOM,
        SKY_CFG_TV_ATMOS_SCENE_SELECT_BEDROOM,
        SKY_CFG_TV_ATMOS_SCENE_SELECT_OTHER
    }

    public enum SKY_CFG_TV_ATMOS_DRC_ENUM_TYPE {
        SKY_CFG_TV_ATMOS_DRC_ON,
        SKY_CFG_TV_ATMOS_DRC_OFF,
        SKY_CFG_TV_ATMOS_DRC_AUTO
    }

    public enum SKY_CFG_TV_VIDEO_STREAM_TYPE_ENUM_TYPE {
        SKY_CFG_TV_VIDEO_STREAM_TYPE_UNKNOWN,
        SKY_CFG_TV_VIDEO_STREAM_TYPE_SDR,
        SKY_CFG_TV_VIDEO_STREAM_TYPE_HDR10,
        SKY_CFG_TV_VIDEO_STREAM_TYPE_DOLBY_HDR,
        SKY_CFG_TV_VIDEO_STREAM_TYPE_HLG_HDR,
        SKY_CFG_TV_VIDEO_STREAM_TYPE_CUVA_HDR
    }

    public enum SKY_CFG_TV_USE_ENVIRONMENT_ENUM_TYPE {
        SKY_CFG_TV_USE_ENVIRONMENT_HOME,
        SKY_CFG_TV_USE_ENVIRONMENT_STORE
    }

    public enum SKY_CFG_TV_ANOLOG_AUDIO_OUTPUT_ENUM_TYPE {
        SKY_CFG_TV_ANOLOG_AUDIO_OUTPUT_LINEOUT,
        SKY_CFG_TV_ANOLOG_AUDIO_OUTPUT_HEADPHONE
    }

    public enum SKY_CFG_TV_DIGITAL_AUDIO_OUTPUT_ENUM_TYPE {
        SKY_CFG_TV_DIGITAL_AUDIO_OUTPUT_AUTO,
        SKY_CFG_TV_DIGITAL_AUDIO_OUTPUT_PCM
    }

    public enum SKY_CFG_TV_RESOLUTION_ENUM_TYPE {
        SKY_CFG_TV_RESOLUTION_AUTO,
        SKY_CFG_TV_RESOLUTION_PAL,
        SKY_CFG_TV_RESOLUTION_PALN,
        SKY_CFG_TV_RESOLUTION_PALM,
        SKY_CFG_TV_RESOLUTION_480P_60HZ,
        SKY_CFG_TV_RESOLUTION_576P_50HZ,
        SKY_CFG_TV_RESOLUTION_720p_50Hz,
        SKY_CFG_TV_RESOLUTION_720p_60Hz,
        SKY_CFG_TV_RESOLUTION_1080i_50Hz,
        SKY_CFG_TV_RESOLUTION_1080i_60Hz,
        SKY_CFG_TV_RESOLUTION_1080p_50Hz,
        SKY_CFG_TV_RESOLUTION_1080p_60Hz,
        SKY_CFG_TV_RESOLUTION_1080p_24Hz,
        SKY_CFG_TV_RESOLUTION_1080p_30Hz,
        SKY_CFG_TV_RESOLUTION_3840p_24Hz,
        SKY_CFG_TV_RESOLUTION_3840p_25Hz,
        SKY_CFG_TV_RESOLUTION_3840p_30Hz,
        SKY_CFG_TV_RESOLUTION_3840p_50Hz,
        SKY_CFG_TV_RESOLUTION_3840p_60Hz
    }

    /**
     * 获取emmc cid,防止串货
     */
    public static final String SKY_CFG_EMMC_CID = "SKY_CFG_EMMC_CID";

    /**
     * 获取屏幕参数，用于本级信息web版获取屏参
     */
    public static final String SKY_CFG_PANEL_CHARACTERISTIC = "SKY_CFG_PANEL_CHARACTERISTIC";
    /**
     * 从plugin获取电视机信息是否完整，此标志是用于防窜货。主板维修后置为false。
     * 不要拿来用作其他用途。
     */
    public static final String SKY_CFG_TV_IS_REPAIRED_BOARD = "SKY_CFG_TV_IS_REPAIRED_BOARD";
    /**
     * @Fields sdr转hdr
     */
    public static final String SKY_CFG_TV_SDR_TO_HDR_SWITCH = "SKY_CFG_TV_SDR_TO_HDR_SWITCH";
    /**
     * @Fields 区域对比提升 开关
     */
    public static final String SKY_CFG_TV_LOCAL_CONTRAST_SWITCH = "SKY_CFG_TV_LOCAL_CONTRAST_SWITCH";
    /**
     * @Fields 区域对比提升 关弱中强
     */
    public static final String SKY_CFG_TV_LOCAL_CONTRAST_ENUM = "SKY_CFG_TV_LOCAL_CONTRAST_ENUM";
    /**
     * @Fields 声音输出为外接音响时是否支持静音操作
     */
    public static final String SKY_CFG_TV_IS_SUPPORT_EXTERNAL_AUDIO_OUTPUT_MUTE = "SKY_CFG_TV_IS_SUPPORT_EXTERNAL_AUDIO_OUTPUT_MUTE";
    /**
     * @Fields arc连通状态
     */
    public static final String SKY_CFG_TV_ARC_CONNECT_STATE = "SKY_CFG_TV_ARC_CONNECT_STATE";
    /**
     * @Fields arc音量的状态：用boolean表示，音量加，音量减
     */
    public static final String SKY_CFG_TV_ARC_VOLUME_STATE = "SKY_CFG_TV_ARC_VOLUME_STATE";
    /**
     * @Fields 指示灯勿扰开关【true==待机后5秒熄灭，false==常亮】
     */
    public static final String SKY_CFG_TV_INDICATOR_LIGHT_AUTO_OFF_SWITCH = "SKY_CFG_TV_INDICATOR_LIGHT_AUTO_OFF_SWITCH";
    /**
     * @Fields Q7新增版的护眼模式
     */
    public static final String SKY_CFG_TV_NEW_EYE_PROTECT_SWITCH = "SKY_CFG_TV_NEW_EYE_PROTECT_SWITCH";
    /**
     * @Fields Q7新增的纯色技术
     */
    public static final String SKY_CFG_TV_SOLID_COLOR_TECHNOLOGY_SWITCH = "SKY_CFG_TV_SOLID_COLOR_TECHNOLOGY_SWITCH";

    /**
     * @Fields spdif为解码输出pcm的时候是否可以调节耳机接口【true=可以调节，false=不可以调节】
     */
    public static final String SKY_CFG_TV_SPDIF_PCM_ADJUST_LINEOUT = "SKY_CFG_TV_SPDIF_PCM_ADJUST_LINEOUT";
    /**
     * @Fields spdif为解码输出pcm的时候调节耳机接口音量【用boolean表示，true=音量加，false=音量减】
     */
    public static final String SKY_CFG_TV_PCM_LINEOUT_VOLUME_STATE = "SKY_CFG_TV_PCM_LINEOUT_VOLUME_STATE";
    /**
     * @Description tv安装模式
     */
    public static final String SKY_CFG_TV_INSTALL_MODE = "SKY_CFG_TV_INSTALL_MODE";

    /**
     * @Description tv安装模式, 分壁挂和桌面模式
     */
    public enum SKY_CFG_TV_INSTALL_MODE_ENUM_TYPE {
        SKY_CFG_TV_INSTALL_MODE_DESKTOP,
        SKY_CFG_TV_INSTALL_MODE_WALL
    }


    /**
     * @Description 智能夜灯开关
     */
    public static final String SKY_CFG_SMART_NIGHT_LIGHT_SWITCH = "SKY_CFG_SMART_NIGHT_LIGHT_SWITCH";

    /**
     * 是否支持智能夜灯开关
     */
    public static final String SKY_GFG_TV_IS_SUPPORT_SMART_NIGHT_LIGHT_SWITCH = "SKY_GFG_TV_IS_SUPPORT_SMART_NIGHT_LIGHT_SWITCH";

    /**
     * @Description 指引灯，开关类型，开启10分钟后自动关闭
     */
    public static final String SKY_CFG_TV_INTERFACE_LIGHT = "SKY_CFG_TV_INTERFACE_LIGHT";
    /**
     * @Description 语音智控模块待机充电，开关类型，下面备注：充电开启后，确保主机箱电源连接正常
     */
    public static final String SKY_CFG_TV_VOICE_MODULE_SC = "SKY_CFG_TV_VOICE_MODULE_SC";
    /**
     * @Description 语音智控模块解除配对
     */
    public static final String SKY_CFG_TV_VOICE_MODULE_UNPAIR = "SKY_CFG_TV_VOICE_MODULE_UNPAIR";
    /**
     * @Description 飞利浦机器 HDMI CEC
     */
    public static final String SKY_CFG_TV_HDMI_CEC = "SKY_CFG_TV_HDMI_CEC";
    /**
     * @Description 飞利浦机器CEC设备同步关机 CEC Device Synchronous Standby
     */
    public static final String SKY_CFG_TV_CEC_DEV_SYNC_STANDBY = "SKY_CFG_TV_CEC_DEV_SYNC_STANDBY";
    /**
     * @Description 飞利浦机器CEC电视同步开机  TV Synchronous Power On
     */
    public static final String SKY_CFG_TV_CEC_TV_SYNC_POWERON = "SKY_CFG_TV_CEC_TV_SYNC_POWERON";
    /**
     * @Description 飞利浦机器HDMI声音回传(ARC)
     */
    public static final String SKY_CFG_TV_HDMI_ARC = "SKY_CFG_TV_HDMI_ARC";

    /**
     * @Description 码流仪项目盒子需要，4k/2k输出
     */
    public static final String SKY_CFG_TV_OUTPUT_4K2K = "SKY_CFG_TV_OUTPUT_4K2K";

    public enum SkyEnumOutput4k2k {
        SKY_CFG_TV_OUTPUT_4K2K_4K,
        SKY_CFG_TV_OUTPUT_4K2K_2K
    }

    /**
     * @Description 盒子需要，缩放移动功能
     */
    public static final String SKY_CFG_TV_MOVE_ZOOM = "SKY_CFG_TV_MOVE_ZOOM";

    /*==================这CC没有意义，只是区别之前的版本====================*/
    /**
     * @Fields 当前通道信号/视频片源类型
     */
    public static final String SKY_CFG_CC_STREAM_TYPE = "SKY_CFG_CC_STREAM_TYPE";
    /**
     * @Fields 新逻辑下的图像模式
     */
    public static final String SKY_CFG_CC_PICTURE_MODE = "SKY_CFG_CC_PICTURE_MODE";
    /**
     * @Fields 动态对比度
     */
    public static final String SKY_CFG_CC_DYNAMIC_CONTRAST = "SKY_CFG_CC_DYNAMIC_CONTRAST";
    /**
     * @Fields 黑色增强
     */
    public static final String SKY_CFG_CC_BLACK_STRETCH = "SKY_CFG_CC_BLACK_STRETCH";

    /**
     * @Fields 暗场增强
     */
    public static final String SKY_CFG_CC_DARK_FIELD_STRETCH = "SKY_CFG_CC_DARK_FIELD_STRETCH";

    /**
     * @Fields 红色增益
     */
    public static final String SKY_CFG_CC_GAIN_RED = "SKY_CFG_CC_GAIN_RED";
    /**
     * @Fields 绿色增益
     */
    public static final String SKY_CFG_CC_GAIN_GREEN = "SKY_CFG_CC_GAIN_GREEN";
    /**
     * @Fields 蓝色增益
     */
    public static final String SKY_CFG_CC_GAIN_BLUE = "SKY_CFG_CC_GAIN_BLUE";
    /**
     * @Fields 蓝电平延伸
     */
    public static final String SKY_CFG_CC_BLUE_STRETCH = "SKY_CFG_CC_BLUE_STRETCH";
    /**
     * @Fields 肤色亮度
     */
    public static final String SKY_CFG_CC_FLESH_BRIGHT = "SKY_CFG_CC_FLESH_BRIGHT";
    /**
     * @Fields 肤色色调
     */
    public static final String SKY_CFG_CC_FLESH_HUE = "SKY_CFG_CC_FLESH_HUE";
    /**
     * @Fields 肤色饱和度
     */
    public static final String SKY_CFG_CC_FLESH_SATUATION = "SKY_CFG_CC_FLESH_SATUATION";
    /**
     * @Fields 色域范围
     */
    public static final String SKY_CFG_CC_COLOR_GAMUT = "SKY_CFG_CC_COLOR_GAMUT";
    /**
     * @Fields 超解像
     */
    public static final String SKY_CFG_CC_SUPER_RESOLUTION = "SKY_CFG_CC_SUPER_RESOLUTION";
    /**
     * @Fields 平滑处理
     */
    public static final String SKY_CFG_CC_DE_CONTOUR = "SKY_CFG_CC_DE_CONTOUR";
    /**
     * @Fields 块噪声消除
     */
    public static final String SKY_CFG_CC_DE_BLOCK = "SKY_CFG_CC_DE_BLOCK";
    /**
     * @Fields 蚊状噪声消除
     */
    public static final String SKY_CFG_CC_DE_MOSQUITO = "SKY_CFG_CC_DE_MOSQUITO";
    /**
     * @Fields 动态图像校正
     */
    public static final String SKY_CFG_CC_MEMC = "SKY_CFG_CC_MEMC";
    /**
     * @Fields 动态清晰校正
     */
    public static final String SKY_CFG_CC_DE_BLUR = "SKY_CFG_CC_DE_BLUR";
    /**
     * @Fields 动态平滑校正
     */
    public static final String SKY_CFG_CC_DE_JUDDER = "SKY_CFG_CC_DE_JUDDER";
    /**
     * @Description 声音模块 开关类型 智能音量控制开关
     */
    public static final String SKY_CFG_TV_INTEL_CONTROL_VOLUME = "SKY_CFG_TV_INTEL_CONTROL_VOLUME";
    /**
     * @Description 声音模块 开关类型 对白增强控制开关
     */
    public static final String SKY_CFG_TV_DIALOGUE_ENHANCED = "SKY_CFG_TV_DIALOGUE_ENHANCED";
    /**
     * @Description 声音模块 均衡器
     */
    public static final String SKY_CFG_TV_NEW_EQUALIZER = "SKY_CFG_TV_NEW_EQUALIZER";
    /**
     * @Description 开机启动项
     */
    public static final String SKY_CFG_TV_BOOT_OPTIONS = "SKY_CFG_TV_BOOT_OPTIONS";
    /*==================只是区别之前的版本,以下是用到的枚举类型定义,start====================*/

    /**
     * @Description 动态对比度
     */
    public enum SkyEnumDynamicContrast {
        SKY_CFG_TV_DYNAMIC_CONTRAST_OFF,
        SKY_CFG_TV_DYNAMIC_CONTRAST_OPEN,
        SKY_CFG_TV_DYNAMIC_CONTRAST_LOW,
        SKY_CFG_TV_DYNAMIC_CONTRAST_MID,
        SKY_CFG_TV_DYNAMIC_CONTRAST_HIGH,
        SKY_CFG_TV_DYNAMIC_CONTRAST_SUPERHIGH
    }

    /**
     * @Description 副屏升降开关
     */
    public enum SkyEnumSubScreenLift {
        SKY_CFG_TV_SUB_SCREEN_LIFT_OFF,
        SKY_CFG_TV_SUB_SCREEN_LIFT_AUTO
    }

    /**
     * @Description 黑色增强Black Stretch
     */
    public enum SkyEnumBlackStretch {
        SKY_CFG_TV_BLACK_STRETCH_OFF,
        SKY_CFG_TV_BLACK_STRETCH_OPEN,
        SKY_CFG_TV_BLACK_STRETCH_LOW,
        SKY_CFG_TV_BLACK_STRETCH_MID,
        SKY_CFG_TV_BLACK_STRETCH_HIGH
    }

    /**
     * @Description 暗场增强 Dark Field Stretch
     */
    public enum SkyEnumDarkFieldStretch {
        SKY_CFG_TV_DARK_FIELD_STRETCH_OFF,
        SKY_CFG_TV_DARK_FIELD_STRETCH_LOW,
        SKY_CFG_TV_DARK_FIELD_STRETCH_MID,
        SKY_CFG_TV_DARK_FIELD_STRETCH_HIGH
    }

    /**
     * @Description “AI超级分辨率”新需求，“关,开”，改为“关,弱,中,强”
     */
    public enum EnumAISRLevel {
        OFF,
        LOW,
        MID,
        HIGH
    }

    /**
     * @Description 红绿蓝增益
     */
    public enum SkyEnumRGBGain {
        SKY_CFG_TV_GAIN_R,
        SKY_CFG_TV_GAIN_G,
        SKY_CFG_TV_GAIN_B
    }

    /**
     * @Description 肤色亮度Brightness、色调Hue、饱和度Satuation，范围0~32
     */
    public enum SkyEnumFleshBHS {
        SKY_CFG_TV_FLESH_BRIGHT,
        SKY_CFG_TV_FLESH_HUE,
        SKY_CFG_TV_FLESH_SATUATION
    }

    /**
     * @Description 超解像Super Resolution
     */
    public enum SkyEnumSuperResolution {
        SKY_CFG_TV_SUPER_RESOLUTION_OFF,
        SKY_CFG_TV_SUPER_RESOLUTION_OPEN,
        SKY_CFG_TV_SUPER_RESOLUTION_LOW,
        SKY_CFG_TV_SUPER_RESOLUTION_MID,
        SKY_CFG_TV_SUPER_RESOLUTION_HIGH,
        SKY_CFG_TV_SUPER_RESOLUTION_SUPERHIGH
    }

    /**
     * @Description 块噪声消除De-Block
     */
    public enum SkyEnumDeBlock {
        SKY_CFG_TV_DE_BLOCK_OFF,
        SKY_CFG_TV_DE_BLOCK_LOW,
        SKY_CFG_TV_DE_BLOCK_MID,
        SKY_CFG_TV_DE_BLOCK_HIGH,
        SKY_CFG_TV_DE_BLOCK_OPEN
    }

    /**
     * @Description 蚊状噪声消除De-Mosquito
     */
    public enum SkyEnumDeMosquito {
        SKY_CFG_TV_DE_MOSQUITO_OFF,
        SKY_CFG_TV_DE_MOSQUITO_LOW,
        SKY_CFG_TV_DE_MOSQUITO_MID,
        SKY_CFG_TV_DE_MOSQUITO_HIGH
    }

    /**
     * @Description 动态图像校正MEMC 关/弱/中/强
     */
    public enum SkyEnumMEMC {
        SKY_CFG_TV_MEMC_OFF,
        SKY_CFG_TV_MEMC_OPEN,
        SKY_CFG_TV_MEMC_LOW,
        SKY_CFG_TV_MEMC_MID,
        SKY_CFG_TV_MEMC_HIGH
    }

    /**
     * @Description 动态清晰校正DeBlur、动态平滑校正DeJudder
     */
    public enum SkyEnumDeBJ {
        SKY_CFG_TV_DE_BLUR,
        SKY_CFG_TV_DE_JUDDER
    }
    /*==================只是区别之前的版本,以下是用到的枚举类型定义,end====================*/


    /**
     * @Description 开机启动项，分电视和主页两种
     */
    public enum SKY_CFG_TV_BOOT_OPTIONS_TYPE {
        SKY_CFG_TV_BOOT_START_HOME_PAGE,
        SKY_CFG_TV_BOOT_START_TV
    }

    /**
     * @Description 新增均衡器低音
     */
    public static final String SKY_CFG_TV_NEW_EQUALIZER_BASS = "SKY_CFG_TV_NEW_EQUALIZER_BASS";
    /**
     * @Description 新增均衡器中低音
     */
    public static final String SKY_CFG_TV_NEW_EQUALIZER_MIDBASS = "SKY_CFG_TV_NEW_EQUALIZER_MIDBASS";
    /**
     * @Description 新增均衡器中音
     */
    public static final String SKY_CFG_TV_NEW_EQUALIZER_ALTO = "SKY_CFG_TV_NEW_EQUALIZER_ALTO";
    /**
     * @Description 新增均衡器中高音
     */
    public static final String SKY_CFG_TV_NEW_EQUALIZER_ALT = "SKY_CFG_TV_NEW_EQUALIZER_ALT";
    /**
     * @Description 新增均衡器高音
     */
    public static final String SKY_CFG_TV_NEW_EQUALIZER_TREBLE = "SKY_CFG_TV_NEW_EQUALIZER_TREBLE";

    /**
     * @Description 精密平滑处理
     */
    public enum SkyEnumDeContour {
        SKY_CFG_TV_DE_CONTOUR_OFF,
        SKY_CFG_TV_DE_CONTOUR_OPEN,
        SKY_CFG_TV_DE_CONTOUR_LOW,
        SKY_CFG_TV_DE_CONTOUR_MID,
        SKY_CFG_TV_DE_CONTOUR_HIGH,
        SKY_CFG_ENUM_AUTO
    }

    /**
     * @Description 码流仪项目盒子需要，颜色空间输出
     */
    public static final String SKY_CFG_TV_COLOR_SPACE = "SKY_CFG_TV_COLOR_SPACE";

    public enum SkyEnumColorSpace {
        SKY_CFG_TV_COLOR_SPACE_RGB,
        SKY_CFG_TV_COLOR_SPACE_YUV420,
        SKY_CFG_TV_COLOR_SPACE_YUV420_10BIT,
        SKY_CFG_TV_COLOR_SPACE_YUV422,
        SKY_CFG_TV_COLOR_SPACE_YUV444
    }

    /**
     * 飞利浦项目：获取系统记录的BackLightTime
     */
    public static final String SKY_CFG_RECORD_BACK_LIGHT_TIME = "SKY_CFG_RECORD_BACK_LIGHT_TIME";
    /**
     * 飞利浦项目：获取系统记录的TotalTime
     */
    public static final String SKY_CFG_RECORD_TOTAL_TIME = "SKY_CFG_RECORD_TOTAL_TIME";
    /**
     * 飞利浦项目：获取系统记录的The first connect internet time
     */
    public static final String SKY_CFG_RECORD_FCI_TIME = "SKY_CFG_RECORD_FCI_TIME";
    /**
     * 峰值亮度扩展
     */
    public static final String SKY_CFG_BACKLIGHT_BOOST = "SKY_CFG_BACKLIGHT_BOOST";

    /**
     * 峰值亮度扩展:枚举类型
     */
    public enum SkyEnumBacklightBoost {
        SKY_CFG_ENUM_OFF,
        SKY_CFG_ENUM_LOW,
        SKY_CFG_ENUM_MID,
        SKY_CFG_ENUM_HIGH
    }

    /**
     * 动态范围扩展
     */
    public static final String SKY_CFG_SPECULAR_BOOST = "SKY_CFG_SPECULAR_BOOST";

    /**
     * 动态范围扩展:枚举类型
     */
    public enum SkyEnumSpecularBoost {
        SKY_CFG_ENUM_OFF,
        SKY_CFG_ENUM_OPEN,
        SKY_CFG_ENUM_LOW,
        SKY_CFG_ENUM_MID,
        SKY_CFG_ENUM_HIGH,
        SKY_CFG_ENUM_AUTO
    }

    /**
     * 肤色偏好：进度类型
     */
    public static final String SKY_CFG_FLESH_TONE = "SKY_CFG_FLESH_TONE";
    /**
     * AI抗锯齿：开关类型
     */
    public static final String SKY_CFG_AI_DEJAGGY = "SKY_CFG_AI_DEJAGGY";
    /**
     * AI关屏待机开关
     */
    public static final String SKY_CFG_TV_AI_CLOSE_SCREEN_SWITCH = "SKY_CFG_TV_AI_CLOSE_SCREEN_SWITCH";
    /**
     * 设置屏幕是否关闭（只关闭屏幕不关闭声音）
     */
    public static final String SKY_CFG_TV_SET_SCREEN_SWITCH = "SKY_CFG_TV_SET_SCREEN_SWITCH";
    /**
     * 设置OLED屏保屏幕亮度为50
     */
    public static final String SKY_CFG_TV_SET_SCREEN_BRIGHTNESS = "SKY_CFG_TV_SET_SCREEN_BRIGHTNESS";
    /**
     * 设置触控开关
     */
    public static final String SKY_CFG_TV_SET_TOUCH_CONTROL_SWITCH = "SKY_CFG_TV_SET_TOUCH_CONTROL_SWITCH";
    /**
     * 商用设置最大音量
     */
    public static final String SKY_CFG_TV_MAXIMUN_VOLUME = "SKY_CFG_TV_MAXIMUN_VOLUME";
    /**
     * 商用设置开机音量
     */
    public static final String SKY_CFG_TV_BOOT_VOLUME = "SKY_CFG_TV_BOOT_VOLUME";
    /**
     * 商用设置PBS菜单开关
     */
    public static final String SKY_CFG_TV_PBS_MENU_SWITCH = "SKY_CFG_TV_PBS_MENU_SWITCH";
    /**
     * 商用设置Upan安装apk开关
     */
    public static final String SKY_CFG_TV_USB_INSTALL_APK_SWITCH = "SKY_CFG_TV_USB_INSTALL_APK_SWITCH";
    /**
     * 商用设置开机图片路径
     */
    public static final String SKY_CFG_TV_BOOT_PICTURE_PATH = "SKY_CFG_TV_BOOT_PICTURE_PATH";
    /**
     * DTS-DRC, dts认证用，开关类型
     */
    public static final String SKY_CFG_TV_DTSDRC_SWITCH = "SKY_CFG_TV_DTSDRC_SWITCH";
    /**
     * 语音控制logo状态
     */
    public static final String SKY_GFG_TV_VOICE_CONTRAL_LOGO = "SKY_GFG_TV_VOICE_CONTROL_LOGO";
    /**
     * 声音延时功能
     */
    public static final String SKY_GFG_TV_VOICE_DELAY = "SKY_GFG_TV_VOICE_DELAY";
    /**
     * 开机唤醒方式
     */
    public static final String SKY_GFG_TV_POWER_ON_BOOT_MODE = "SKY_GFG_TV_POWER_ON_BOOT_MODE";
    /**
     * 上次关机方式
     */
    public static final String SKY_GFG_TV_LAST_POWER_OFF_MODE = "SKY_GFG_TV_LAST_POWER_OFF_MODE";
    /**
     * 是否支持ai待机
     */
    public static final String SKY_GFG_TV_IS_SUPPORT_AI_STANDBY = "SKY_GFG_TV_IS_SUPPORT_AI_STANDBY";

    /**
     * 麦克风设备插拔开关
     */
    public static final String SKY_CFG_MICROPHONE_SWITCH = "SKY_CFG_MICROPHONE_SWITCH";

    /**
     * 是否支持MEMC
     */
    public static final String SKY_GFG_TV_IS_SUPPORT_MEMC = "SKY_GFG_TV_IS_SUPPORT_MEMC";

    /**
     * 是否支持 动态范围扩展
     */
    public static final String SKY_GFG_TV_IS_SUPPORT_SPECULAR_BOOST = "SKY_GFG_TV_IS_SUPPORT_SPECULAR_BOOST";
    /**
     * 是否支持localdimm
     */
    public static final String SKY_GFG_TV_IS_SUPPORT_LOCAL_DIMM = "SKY_GFG_TV_IS_SUPPORT_LOCAL_DIMM";
    /**
     * 是否支持重低音
     */
    public static final String SKY_GFG_TV_IS_SUPPORT_SUBWOOFER = "SKY_GFG_TV_IS_SUPPORT_SUBWOOFER";

    /**
     * 是否支持重低音增强
     */
    public static final String SKY_GFG_TV_IS_SUPPORT_SUBWOOFER_ENHANCE = "SKY_GFG_TV_IS_SUPPORT_SUBWOOFER_ENHANCE";

    /**
     * 判断是否支持网络EOC
     */
    public static final String SKY_GFG_TV_IS_SUPPORT_ROUTER = "SKY_GFG_TV_IS_SUPPORT_ROUTER";
    /**
     * 判断是否支持网络CM
     */
    public static final String SKY_GFG_TV_IS_SUPPORT_CM = "SKY_GFG_TV_IS_SUPPORT_CM";
    /**
     * 判断是否支持wifi
     */
    public static final String SKY_GFG_TV_IS_SUPPORT_WIFI = "SKY_GFG_TV_IS_SUPPORT_WIFI";
    /**
     * 激光设置 亮度模式
     */
    public static final String SKY_GFC_TV_BRIGHT_MODE = "SKY_GFC_TV_BRIGHT_MODE";
    /**
     * 激光设置 放置模式
     */
    public static final String SKY_GFC_TV_PONPUT_MODE = "SKY_GFC_TV_PONPUT_MODE";
    /**
     * 激光设置 人眼保护模式
     */
    public static final String SKY_GFC_TV_EYESHIELD_MODE = "SKY_GFC_TV_EYESHIELD_MODE";

    /**
     * @亮度模式
     */
    public enum SkyEnumBrightMode {
        SKY_CFG_TV_BRIGHT_MODE_STANDARD, // 标准
        SKY_CFG_TV_BRIGHT_MODE_VIVID, // 明亮
        SKY_CFG_TV_BRIGHT_MODE_ECO, // 节能
    }

    /**
     * @放置模式
     */
    public enum SkyEnumPonputMode {
        SKY_CFG_PONPUT_MODE_DESKTOP_CAST, // 桌面正投
        SKY_CFG_PONPUT_MODE_DESKTOP_DISPLAY, // 桌面背投
        SKY_CFG_PONPUT_MODE_HANGING_CAST, // 挂顶正投
        SKY_CFG_PONPUT_MODE_HANGING_DISPLAY,// 挂顶背投
    }

    /**
     * @清晰度调整
     */
    public static final String SKY_GFC_TV_DEFINITION_ADJUSTMENT = "SKY_GFC_TV_DEFINITION_ADJUSTMENT";

    /**
     * @梯形校正
     */
    public static final String SKY_CFG_TV_KEYSTONE_CORRECTION = "SKY_CFG_TV_KEYSTONE_CORRECTION";

    /**
     * @梯形校正
     */
    public enum SkyEnumKeyStoneCorre {
        SKY_CFG_ADJUST_TOP_LEFT, // 左顶点
        SKY_CFG_ADJUST_TOP_CENTER, // 中心上点
        SKY_CFG_ADJUST_TOP_RIGHT, // 上右
        SKY_CFG_ADJUST_RIGHT_CENTER, // 右边中心点
        SKY_CFG_ADJUST_RIGHT_BOTTOM, // 右边底点
        SKY_CFG_ADJUST_BOTTOM_CENTER, // 底边中心点
        SKY_CFG_ADJUST_LEFT_BOTTOM, // 左边底点
        SKY_CFG_ADJUST_LEFT_CENTER,// 左边中心点
    }

    /**
     * 区域对比提示:枚举类型
     */
    public enum SkyEnumLocalContrast {
        SKY_CFG_ENUM_OFF,
        SKY_CFG_ENUM_LOW,
        SKY_CFG_ENUM_MID,
        SKY_CFG_ENUM_HIGH,
        SKY_CFG_ENUM_SUPERHIGH,
    }

    /**
     * @Fields 高级色彩 红色亮度
     */
    public static final String SKY_CFG_CC_NEW_BRIGHT = "SKY_CFG_CC_NEW_BRIGHT";
    /**
     * @Fields 高级色彩 红色色调
     */
    public static final String SKY_CFG_CC_NEW_HUE = "SKY_CFG_CC_NEW_HUE";
    /**
     * @Fields 高级色彩 红色饱和度
     */
    public static final String SKY_CFG_CC_NEW_SATUATION = "SKY_CFG_CC_NEW_SATUATION";

    /**
     * 色彩高级:枚举类型
     */
    public enum SkyEnumColorAdvanced {
        SKY_CFG_ENUM_RED,
        SKY_CFG_ENUM_GREEN,
        SKY_CFG_ENUM_YELLOW,
        SKY_CFG_ENUM_CYAN,
        SKY_CFG_ENUM_BLUE,
        SKY_CFG_ENUM_PURPLE,
        SKY_CFG_ENUM_SKIN,
    }

    /**
     * @Fields 高级色彩 重置
     */
    public static final String SKY_CFG_CC_COLOR_ADVANCED_RESET = "SKY_CFG_CC_COLOR_ADVANCED_RESET";


    /*==================新增定义超级音响====================*/
    /**
     * 是否插入超级音响
     */
    public static final String SKY_CFG_TV_SUPER_SOUND_MOUNTED_STATE = "SKY_CFG_TV_SUPER_SOUND_MOUNTED_STATE";

    /**
     * 超级音响(声音设置中的超级音响)
     */
    public static final String SKY_CFG_TV_SOUND_SETTING_SUPER_SOUND = "SKY_CFG_TV_SOUND_SETTING_SUPER_SOUND";
    /**
     * 超级音响声音模式
     */
    public static final String SKY_CFG_TV_SUPER_SOUND_MODE = "SKY_CFG_TV_SUPER_SOUND_MODE";

    /**
     * 超级音响声音模式枚举
     */
    public enum SKY_CFG_TV_SUPER_SOUND_MODE_ENUM_TYPE {
        SKY_CFG_TV_SOUND_MODE_STANDARD,
        SKY_CFG_TV_SOUND_MODE_MUSIC,
        SKY_CFG_TV_SOUND_MODE_MOVIE,
        SKY_CFG_TV_SOUND_MODE_GAME,
        SKY_CFG_TV_SOUND_MODE_USER
//        SKY_CFG_TV_SOUND_MODE_KARAOKE
    }

    /**
     * 超级音响：开关类型
     */
    public static final String SKY_CFG_TV_SUPER_SOUND = "SKY_CFG_TV_SUPER_SOUND";
    /**
     * 扬声器声道：枚举类型
     */
    public static final String SKY_CFG_TV_SPEAKER_CHANNEL = "SKY_CFG_TV_SPEAKER_CHANNEL";

    /**
     * 扬声器声道SKY_CFG_TV_SPEAKER_CHANNEL
     * 依次为：
     * 2.0，5.1,5.1.2,7.1.4
     */
    public enum SKY_CFG_TV_SPEAKER_CHANNEL_ENUM_TYPE {
        SKY_CFG_TV_SPEAKER_CHANNEL_2_0,
        //        SKY_CFG_TV_SPEAKER_CHANNEL_5_1,
//        SKY_CFG_TV_SPEAKER_CHANNEL_5_1_2,
        SKY_CFG_TV_SPEAKER_CHANNEL_7_1_4
    }

    /**
     * 主声道音量：进度条类型
     */
    public static final String SKY_CFG_TV_MAIN_CHANNEL_VOLUME = "SKY_CFG_TV_MAIN_CHANNEL_VOLUME";
    /**
     * 中置音量：进度条类型
     */
    public static final String SKY_CFG_TV_CENTER_CHANNEL_VOLUME = "SKY_CFG_TV_CENTER_CHANNEL_VOLUME";
    /**
     * 环绕声音量：进度条类型
     */
    public static final String SKY_CFG_TV_SURROUND_VOLUME = "SKY_CFG_TV_SURROUND_VOLUME";
    /**
     * 天空音量：进度条类型
     */
    public static final String SKY_CFG_TV_SKY_VOLUME = "SKY_CFG_TV_SKY_VOLUME";
    /**
     * 超低音音量：进度条类型
     */
    public static final String SKY_CFG_TV_INFRASOUND_VOLUME = "SKY_CFG_TV_INFRASOUND_VOLUME";

    /**
     * 声场扩展：开关类型
     */
    public static final String SKY_CFG_TV_SOUND_FIELD_EXTENDSION = "SKY_CFG_TV_SOUND_FIELD_EXTENDSION";
    /**
     * 音色调节：下一级
     */
    public static final String SKY_CFG_TV_TONE_ADJUST = "SKY_CFG_TV_TONE_ADJUST";

    /**
     * @Description 音色30
     */
    public static final String SKY_CFG_TV_TONE_ADJUST_30 = "SKY_CFG_TV_TONE_ADJUST_30";
    /**
     * @Description 音色53
     */
    public static final String SKY_CFG_TV_TONE_ADJUST_53 = "SKY_CFG_TV_TONE_ADJUST_53";
    /**
     * @Description 音色95
     */
    public static final String SKY_CFG_TV_TONE_ADJUST_95 = "SKY_CFG_TV_TONE_ADJUST_95";
    /**
     * @Description 音色170
     */
    public static final String SKY_CFG_TV_TONE_ADJUST_170 = "SKY_CFG_TV_TONE_ADJUST_170";
    /**
     * @Description 音色300
     */
    public static final String SKY_CFG_TV_TONE_ADJUST_300 = "SKY_CFG_TV_TONE_ADJUST_300";
    /**
     * @Description 音色550
     */
    public static final String SKY_CFG_TV_TONE_ADJUST_550 = "SKY_CFG_TV_TONE_ADJUST_550";

    /**
     * @Description 音色1K
     */
    public static final String SKY_CFG_TV_TONE_ADJUST_1K = "SKY_CFG_TV_TONE_ADJUST_1K";
    /**
     * @Description 音色2k
     */
    public static final String SKY_CFG_TV_TONE_ADJUST_2K = "SKY_CFG_TV_TONE_ADJUST_2K";
    /**
     * @Description 音色4k
     */
    public static final String SKY_CFG_TV_TONE_ADJUST_4K = "SKY_CFG_TV_TONE_ADJUST_4K";
    /**
     * @Description 音色8k
     */
    public static final String SKY_CFG_TV_TONE_ADJUST_8K = "SKY_CFG_TV_TONE_ADJUST_8K";
    /**
     * @Description 音色16k
     */
    public static final String SKY_CFG_TV_TONE_ADJUST_16K = "SKY_CFG_TV_TONE_ADJUST_16K";
    /**
     * 两声道信号：枚举类型
     */
    public static final String SKY_CFG_TV_DUAL_TRACK = "SKY_CFG_TV_DUAL_TRACK";

    /**
     * 两声道信号枚举
     * 依次为：
     * （两声道、多声道、UPMIX、NEO_X，这里已经进行了修改，如下：）
     * 关闭，UPMIX，Neural:x
     */
    public enum SKY_CFG_TV_DUAL_TRACK_ENUM_TYPE {
        SKY_CFG_TV_DUAL_TRACK_OFF,
        //        SKY_CFG_TV_DUAL_TRACK_MULTI,
        SKY_CFG_TV_DUAL_TRACK_UPMIX,
        SKY_CFG_TV_DUAL_TRACK_NETURAL_X
    }

    /**
     * 开机音频输出：开关类型
     */
    public static final String SKY_CFG_TV_BOOT_AUDIO_OUTPUT = "SKY_CFG_TV_BOOT_AUDIO_OUTPUT";
    /**
     * DRC开关：开关类型
     */
    public static final String SKY_CFG_TV_SOUND_DRC = "SKY_CFG_TV_SOUND_DRC";


    public enum SKY_CFG_TV_SUPER_SOUND_DRC_ENUM_TYPE {
        SKY_CFG_TV_SUPER_SOUND_DRC_OFF,
        SKY_CFG_TV_SUPER_SOUND_DRC_ON,
        SKY_CFG_TV_SUPER_SOUND_DRC_AUTO
    }

    /**
     * 激光电视项目 激发最大亮度 开关
     */
    public static final String SKY_GFC_TV_MOTIVATE_MAXBRIGHT = "SKY_GFC_TV_MOTIVATE_MAXBRIGHT";
    /**
     * 激光电视项目 光源亮度
     */
    public static final String SKY_GFC_TV_SOURCE_BRIGHTNESS = "SKY_GFC_TV_SOURCE_BRIGHTNESS";


    /**
     * 判断是否支持STLoader升级
     */
    public static final String SKY_GFG_TV_IS_SUPPORT_STLOADER = "SKY_GFG_TV_IS_SUPPORT_STLOADER";

    /**
     * 获取STLoader升级的相关信息，包括版本信息等
     */
    public static final String SKY_GFG_TV_GET_STLOADER_VERSION_INFO = "SKY_GFG_TV_GET_STLOADER_VERSION_INFO";
    /**
     * 开始STLoader升级的下载
     */
    public static final String SKY_GFG_TV_START_STLOADER_DOWNLOAD = "SKY_GFG_TV_START_STLOADER_DOWNLOAD";

    /**
     * 设置STLoader升级相关的Listener
     */
    public static final String SKY_GFG_TV_SET_STLOADER_LISTENER = "SKY_GFG_TV_SET_STLOADER_LISTENER";
    /**
     * CEC开关
     */
    public static final String SKY_GFG_TV_CEC_MODE_SWITCH = "SKY_GFG_TV_CEC_MODE_SWITCH";
    /**
     * AI开机是否是CEC状态
     */
    public static final String SKY_GFG_TV_IS_SUPPORT_CEC_MODE = "SKY_GFG_TV_IS_SUPPORT_CEC_MODE";
    /**
     * 本地设置语言
     */
    public static final String SKY_GFG_TV_LOCAL_SET_LANGUAGE = "SKY_GFG_TV_LOCAL_SET_LANGUAGE";
    /**
     * 5.8G无线重低音音箱音量调节
     */
    public static final String SKY_CFG_TV_WIRELESS_BASS_VOLUME = "SKY_CFG_TV_WIRELESS_BASS_VOLUME";
    /**
     * 8G无线环绕音音箱音量调节
     */
    public static final String SKY_CFG_TV_WIRELESS_AROUND_VOLUME = "SKY_CFG_TV_WIRELESS_AROUND_VOLUME";

    /**
     * 获取5.8G无线重低音音箱连接状态
     */
    public static final String SKY_CFG_TV_GET_WIRELESS_BASS_CONNECT_STATE = "SKY_CFG_TV_GET_WIRELESS_BASS_CONNECT_STATE";
    /**
     * 执行连接5.8G无线重低音音箱操作
     */
    public static final String SKY_CFG_TV_GET_WIRELESS_BASS_CONNECT = "SKY_CFG_TV_GET_WIRELESS_BASS_CONNECT";
    /**
     * 获取有线和无线能否同时连接
     */
    public static final String SKY_GFG_TV_IS_ETH_WIFI_COMPATIBLE = "SKY_GFG_TV_IS_ETH_WIFI_COMPATIBLE";
    /**
     * 打开HDMI设备时让电视自动开机，开关状态
     */
    public static final String SKY_GFG_TV_AUTO_POWER_ON_HDMI = "SKY_GFG_TV_AUTO_POWER_ON_HDMI";
    /**
     * 关闭HDMI设备时让电视自动关机，开关状态
     */
    public static final String SKY_GFG_TV_AUTO_POWER_OFF_HDMI = "SKY_GFG_TV_AUTO_POWER_OFF_HDMI";
    /**
     * 关闭电视时让HDMI设备让自动关机，开关状态
     */
    public static final String SKY_GFG_TV_HDMI_AUTO_POWER_OFF = "SKY_GFG_TV_HDMI_AUTO_POWER_OFF";
    /**
     * CEC多功能遥控器
     */
    public static final String SKY_GFG_TV_MULTI_FUN_REMOTE_CONTROL = "SKY_GFG_TV_MULTI_FUN_REMOTE_CONTROL";
    /**
     * CEC一触即播(插入信号自动跳转)
     */
    public static final String SKY_GFG_TV_AUTO_START_SOURCE = "SKY_GFG_TV_AUTO_START_SOURCE";

    /**
     * HDMI设备开关机电视自动开关机(合并SKY_GFG_TV_AUTO_POWER_ON_HDMI和SKY_GFG_TV_AUTO_POWER_OFF_HDMI)
     */
    public static final String SKY_GFG_TV_AUTO_POWER_ON_OFF = "SKY_GFG_TV_AUTO_POWER_ON_OFF";

    /**
     * ARC 开关（腾龙项目） 设置开关状态
     */
    public static final String SKY_GFG_TV_ARC = "SKY_GFG_TV_ARC";
    /**
     * MCU及DDP升级接口
     */
    public static final String SKY_GFG_TV_MCU_DDP_UPGRADE = "SKY_GFG_TV_MCU_DDP_UPGRADE";
    /**
     * 红外学习 设置code
     */
    public static final String SKY_GFG_TV_INFRARED_LEARNING = "SKY_GFG_TV_INFRARED_LEARNING";
    /**
     * 红外学习 设置回调结果
     */
    public static final String SKY_GFC_TV_INFRARED_LEARNING_LISTENER = "SKY_GFC_TV_INFRARED_LEARNING_LISTENER";
    /**
     * 音源模式
     */
    public static final String SKY_GFC_TV_SOUND_SOURCE_MODE = "SKY_GFC_TV_SOUND_SOURCE_MODE";

    /**
     * 音源模式枚举
     */
    public enum SKY_CFG_TV_SOUND_SOURCE_ENUM_TYPE {
        SKY_CFG_TV_ENUM_SOUND_BAR,//soundbar
        SKY_CFG_TV_ENUM_BLUETOOTH,//蓝牙
        SKY_CFG_TV_ENUM_EARPHONE,//耳机
        SKY_CFG_TV_ENUM_OPTICAL_FIBER,//光纤
        SKY_CFG_TV_ENUM_COAXIAL,//同轴
    }

    /**
     * 七天自动进行AI待机重启
     */
    public static final String SKY_GFC_TV_AI_REBOOT_MODE = "SKY_GFC_TV_AI_REBOOT_MODE";
    /**
     * 判断底层是否支持七天自动进行AI待机重启
     */
    public static final String SKY_GFC_TV_SUPPORT_AI_REBOOT = "SKY_GFC_TV_SUPPORT_AI_REBOOT";
    /**
     * 新版声音输出
     */
    public static final String SKY_GFC_TV_NEW_AUDIO_OUTPUT = "SKY_GFC_TV_NEW_AUDIO_OUTPUT";
    /**
     * 判断是否支持新版声音输出
     */
    public static final String SKY_GFC_TV_IS_SUPPORT_NEW_AUDIO_OUTPUT = "SKY_GFC_TV_IS_SUPPORT_NEW_AUDIO_OUTPUT";

    /**
     * 新版声音输出 枚举项
     */
    public enum SKY_CFG_TV_NEW_AUDIO_OUTPUT_ENUM {
        /**
         * 本机
         */
        SKY_CFG_TV_NEW_AUDIO_OUTPUT_ENUM_INTERNAL,
        /**
         * HDMI ARC
         */
        SKY_CFG_TV_NEW_AUDIO_OUTPUT_ENUM_HDMI_ARC,
        /**
         * SPDIF
         */
        SKY_CFG_TV_NEW_AUDIO_OUTPUT_ENUM_SPDIF,
        /**
         * 蓝牙，蓝牙耳机
         */
        SKY_CFG_TV_NEW_AUDIO_OUTPUT_ENUM_BLUETOOTH,
        /**
         * USB音频
         */
        SKY_CFG_TV_NEW_AUDIO_OUTPUT_ENUM_USB
    }

    /**
     * 系统开机固定音量
     */
    public static final String SKY_GFC_TV_HOTEL_BOOT_VOLUME = "SKY_GFC_TV_HOTEL_BOOT_VOLUME";
    /**
     * 设置定时开机接口
     */
    public static final String SKY_GFC_TV_HOTEL_AUTO_POWER_ON = "SKY_GFC_TV_HOTEL_AUTO_POWER_ON";
    /**
     * 设置系统时间
     */
    public static final String SKY_GFC_TV_HOTEL_SYSTEM_TIME = "SKY_GFC_TV_HOTEL_SYSTEM_TIME";
    /**
     * 设置加电启动
     */
    public static final String SKY_GFC_TV_HOTEL_ADD_ELECTRIC_POWER = "SKY_GFC_TV_HOTEL_ADD_ELECTRIC_POWER";

    public enum SKY_CFG_TV_ENUM_ADD_ELECTRIC_POWER {
        /**
         * 加电开机
         */
        SKY_TV_ENUM_ADD_ELECTRIC_POWER_ON,
        /**
         * 加电关机
         */
        SKY_TV_ENUM_ADD_ELECTRIC_POWER_OFF,
        /**
         * 加电记忆
         */
        SKY_TV_ENUM_ADD_ELECTRIC_REMEMBER
    }

    /**
     * 判断电视是否支持RTC电路
     */
    public static final String SKY_GFC_TV_IS_SUPPORT_RTC_CIRCUIT = "SKY_GFC_TV_IS_SUPPORT_RTC_CIRCUIT";
    /**
     * 酒店机音量设置开关
     */
    public static final String SKY_GFC_TV_HOTEL_VOLUME_SETTING_SWITCH = "SKY_GFC_TV_HOTEL_VOLUME_SETTING_SWITCH";
    /**
     * 定时开机功能开关
     */
    public static final String SKY_GFC_TV_HOTEL_AUTO_START_UP_SWITCH = "SKY_GFC_TV_HOTEL_AUTO_START_UP_SWITCH";
    /**
     * 酒店机功能开关
     */
    public static final String SKY_GFC_TV_HOTEL_FUN_SWITCH = "SKY_GFC_TV_HOTEL_FUN_SWITCH";

    /**
     * 副屏升降功能开关
     */
    public static final String SKY_CFG_TV_SUB_SCREEN_LIFT_SWITCH = "SKY_CFG_TV_SUB_SCREEN_LIFT_SWITCH";
    /**
     * AI模式下的图像模式
     */
    public static final String SKY_CFG_TV_AI_PICTURE_MODE = "SKY_CFG_TV_AI_PICTURE_MODE";
    /**
     * 屏幕全透明开关控制
     */
    public static final String SKY_CFG_TV_PANEL_TRANSPARENT_CTRL = "SKY_CFG_TV_PANEL_TRANSPARENT_CTRL";
    /**
     * 屏幕半透明开关控制
     */
    public static final String SKY_CFG_TV_PANEL_TRANSLUCENT_CTRL = "SKY_CFG_TV_PANEL_TRANSLUCENT_CTRL";


    public enum SKY_CFG_TV_AI_PICTURE_MODE_ENUM_TYPE {
        SKY_CFG_TV_AI_PICTURE_MODE_STANDARD, // 标准
        SKY_CFG_TV_AI_PICTURE_MODE_VIVID, // 亮丽、鲜艳
        SKY_CFG_TV_AI_PICTURE_MODE_GENTLE,
        SKY_CFG_TV_AI_PICTURE_MODE_USER,
        SKY_CFG_TV_AI_PICTURE_MODE_4K_CINEMA,
        SKY_CFG_TV_AI_PICTURE_MODE_NATURAL,
        SKY_CFG_TV_AI_PICTURE_MODE_GAME, //游戏
        SKY_CFG_TV_AI_PICTURE_MODE_DOLBY_VISION_MOVIE_DARK,
        SKY_CFG_TV_AI_PICTURE_MODE_DOLBY_VISION_MOVIE_BRIGHT,
        SKY_CFG_TV_AI_PICTURE_MODE_PROFESSIONAL,
        SKY_CFG_TV_AI_PICTURE_MODE_SPORT, //体育
        SKY_CFG_TV_AI_PICTURE_MODE_MOVIE, //影院
        SKY_CFG_TV_AI_PICTURE_MODE_DYNAMIC,
        SKY_CFG_TV_AI_PICTURE_MODE_PHOTO,
        SKY_CFG_TV_AI_PICTURE_MODE_ECO, //节能
        SKY_CFG_TV_AI_PICTURE_MODE_CARE,
        SKY_CFG_TV_AI_PICTURE_MODE_AI,//AI 默认
    }

    /**
     * AI模式下的声音模式
     */
    public static final String SKY_CFG_TV_AI_SOUND_MODE = "SKY_CFG_TV_AI_SOUND_MODE";

    public enum SKY_CFG_TV_AI_SOUND_MODE_ENUM_TYPE {
        SKY_CFG_TV_AI_SOUND_MODE_STANDARD,// 标准
        SKY_CFG_TV_AI_SOUND_MODE_MUSIC,//音乐
        SKY_CFG_TV_AI_SOUND_MODE_NEWS,//新闻
        SKY_CFG_TV_AI_SOUND_MODE_MOVIE,//影院
        SKY_CFG_TV_AI_SOUND_MODE_3D_MOVIE_EFFECT,
        SKY_CFG_TV_AI_SOUND_MODE_USER,
        SKY_CFG_TV_AI_SOUND_MODE_SPORT,//体育
        SKY_CFG_TV_AI_SOUND_MODE_GAME,
        SKY_CFG_TV_AI_SOUND_MODE_CARE,
        SKY_CFG_TV_AI_SOUND_MODE_AI,//AI 默认
        SKY_CFG_TV_AI_SOUND_MODE_AUTO//自动
    }

    /**
     * 艺术家定时关机
     */
    public static final String SKY_CFG_TV_ARTIST_MODE_SLEEP_TIMER = "SKY_CFG_TV_ARTIST_MODE_SLEEP_TIMER";

    public enum SKY_CFG_TV_ARTIST_MODE_SLEEP_TIMER_ENUM {
        SKY_SYSTEM_ENV_ARTIST_MODE_SLEEP_TIMER_0, //未设定
        SKY_SYSTEM_ENV_ARTIST_MODE_SLEEP_TIMER_30,//30分钟
        SKY_SYSTEM_ENV_ARTIST_MODE_SLEEP_TIMER_60,//1个小时
        SKY_SYSTEM_ENV_ARTIST_MODE_SLEEP_TIMER_240,//4个小时
        SKY_SYSTEM_ENV_ARTIST_MODE_SLEEP_TIMER_720 //12小时
    }

    /**
     * 壁纸模式下自动关机时间
     */
    public enum SKY_CFG_TV_WALLPAPER_MODE_AUTO_SLEEP_TIME_ENUM {
        SKY_CFG_TV_WALLPAPER_MODE_AUTO_SLEEP_TIME_0_min, //未设定
        SKY_CFG_TV_WALLPAPER_MODE_AUTO_SLEEP_TIME_10_min,//10分钟
        SKY_CFG_TV_WALLPAPER_MODE_AUTO_SLEEP_TIME_30_min,//30分钟
        SKY_CFG_TV_WALLPAPER_MODE_AUTO_SLEEP_TIME_60_min,//60分钟
        SKY_CFG_TV_WALLPAPER_MODE_AUTO_SLEEP_TIME_120_min,//120分钟
        SKY_CFG_TV_WALLPAPER_MODE_AUTO_SLEEP_TIME_180_min,//180分钟
    }

    /**
     * 一键直达 切换重低音接口
     */
    public static final String SKY_CFG_TV_USB_SUB_WOOFER_MUTE_SWITCH = "SKY_CFG_TV_USB_SUB_WOOFER_MUTE_SWITCH";

    /**
     * 切换 SPDIF 重低音接口
     */
    public static final String SKY_CFG_TV_SPDIF_SUB_WOOFER_MUTE_SWITCH = "SKY_CFG_TV_SPDIF_SUB_WOOFER_MUTE_SWITCH";

    /**
     * 重低音增强
     */
    public static final String SKY_CFG_TV_WIRELESS_BASS_ENHANCE = "SKY_CFG_TV_WIRELESS_BASS_ENHANCE";

    /**
     * AIPQ 智能化调节 提示开关
     */
    public static final String SKY_CFG_TV_AIPQ_TIPS_SWITCH = "SKY_CFG_TV_AIPQ_TIPS_SWITCH";
    /**
     * AIPQ 智能化调节 底层是否支持
     */
    public static final String SKY_CFG_TV_IS_SUPPORT_AIPQ = "SKY_CFG_TV_IS_SUPPORT_AIPQ";
    /**
     * AIPQ 传递场景识别结果
     */
    public static final String SKY_CFG_TV_SET_AI_RECOGNIZING_SCENE = "SKY_CFG_TV_SET_AI_RECOGNIZING_SCENE";
    /**
     * AIPQ 传递场景识别结果以及置信度
     */
    public static final String SKY_CFG_TV_SET_AI_SCENE_CONFIDENCE = "SKY_CFG_TV_SET_AI_SCENE_CONFIDENCE";

    /**
     * 声音耳机 外放同时发声的 开关
     */
    public static final String SKY_CFG_TV_EARPHONE_EXTERNAL_SWITCH = "SKY_CFG_TV_EARPHONE_EXTERNAL_SWITCH";
    /**
     * 摄像头升降状态 true：升，false ：降
     */
    public static final String SKY_CFG_TV_CAMERA_LIFTING_STATE = "SKY_CFG_TV_CAMERA_LIFTING_STATE";

    /**
     * 是否支持2852公板遥控协议模式
     */
    public static final String SKY_CFG_TV_IS_SUPPORT_REMOTE_PROTOCOL = "SKY_CFG_TV_IS_SUPPORT_REMOTE_PROTOCOL";
    /**
     * 2852公板遥控协议模式：NEC协议，TOSHIBA协议
     */
    public static final String SKY_CFG_TV_SET_REMOTE_PROTOCOL_MODE = "SKY_CFG_TV_SET_REMOTE_PROTOCOL_MODE";

    public enum SKY_CFG_TV_REMOTE_PROTOCOL_ENUM {
        SKY_CFG_TV_REMOTE_PROTOCOL_NEC,
        SKY_CFG_TV_REMOTE_PROTOCOL_TOSHIBA,
    }

    /**
     * 场景识别 提示语枚举
     */

    public enum SKY_CFG_TV_AIPQ_MODE_ENUM_TYPE {
        SKY_CFG_TV_AIPQ_MODE_MOVIE,//影院
        SKY_CFG_TV_AIPQ_MODE_NEWS,//新闻
        SKY_CFG_TV_AIPQ_MODE_SPORT,//体育
        SKY_CFG_TV_AIPQ_MODE_GAME,//游戏
        SKY_CFG_TV_AIPQ_MODE_MUSIC,//音乐
        SKY_CFG_TV_AIPQ_MODE_ECO,//节能
        SKY_CFG_TV_AIPQ_MODE_CARTOON,//动画片
        SKY_CFG_TV_AIPQ_MODE_VARIETY,//综艺
        SKY_CFG_TV_AIPQ_MODE_CARE,//关爱
        SKY_CFG_TV_AIPQ_MODE_STANDARD,//标准
        SKY_CFG_TV_AIPQ_MODE_VIVID,//鲜艳
    }

    /**
     * 判断是否是移动机器
     */
    public static final String SKY_CFG_TV_IS_CMCC_MACHINE = "SKY_CFG_TV_IS_CMCC_MACHINE";
    /**
     * 获取移动机器IMEI号
     */
    public static final String SKY_CFG_TV_GET_CMCC_IMEI_CODE = "SKY_CFG_TV_GET_CMCC_IMEI_CODE";
    /**
     * 获取移动机器32位SN号（SN加mac）
     */
    public static final String SKY_CFG_TV_GET_CMCC_SERIAL_NUMBER = "SKY_CFG_TV_GET_CMCC_SERIAL_NUMBER";

    /**
     * 主屏AOD
     */
    public static final String SKY_CFG_TV_SET_AOD_SCREEN_MODE = "SKY_CFG_TV_SET_AOD_SCREEN_MODE";

    /**
     * 副屏AOD
     */
    public static final String SKY_CFG_TV_SET_AOD_SCREEN_ASSISTANT = "SKY_CFG_TV_SET_AOD_SCREEN_ASSISTANT";

    /**
     * aod待机模式
     */
    public enum SKY_CFG_TV_AOD_STANDBY_MODE_ENUM {
        SKY_CFG_TV_AOD_WAKE_STANDBY_MODE(SysConst.SKY_CFG_TV_AOD_WAKE_STANDBY_MODE), // 唤醒显示
        SKY_CFG_TV_AOD_AI_STANDBY_MODE(SysConst.SKY_CFG_TV_AOD_AI_STANDBY_MODE),// 关闭
        SKY_CFG_TV_AOD_WAKE_ALWAY_MODE_ASSISTANT(SysConst.SKY_CFG_TV_AOD_WAKE_ALWAY_MODE_ASSISTANT),// 始终显示
        SKY_CFG_TV_AOD_WAKE_STANDBY_MODE_ASSISTANT(SysConst.SKY_CFG_TV_AOD_WAKE_STANDBY_MODE_ASSISTANT),// 唤醒显示
        SKY_CFG_TV_AOD_WAKE_SYSTEM_MODE_ASSISTANT(SysConst.SKY_CFG_TV_AOD_WAKE_SYSTEM_MODE_ASSISTANT);// 跟随系统
        public int type = 0;

        SKY_CFG_TV_AOD_STANDBY_MODE_ENUM(int type) {
            this.type = type;
        }

        public static SKY_CFG_TV_AOD_STANDBY_MODE_ENUM typeOf(int type) {
            switch (type) {
                case 0:
                    return SKY_CFG_TV_AOD_WAKE_STANDBY_MODE;
                case 1:
                    return SKY_CFG_TV_AOD_AI_STANDBY_MODE;
                case 2:
                    return SKY_CFG_TV_AOD_WAKE_ALWAY_MODE_ASSISTANT;
                case 3:
                    return SKY_CFG_TV_AOD_WAKE_STANDBY_MODE_ASSISTANT;
                case 4:
                    return SKY_CFG_TV_AOD_WAKE_SYSTEM_MODE_ASSISTANT;
            }
            return null;
        }
    }

    /**
     * 屏幕打开关闭
     */
    public static final String SKY_CFG_TV_PANEL_ON_OFF = "SKY_CFG_TV_PANEL_ON_OFF";

    /**
     * 设置第一张开机图片
     * jpg 文件
     */
    public static final String SKY_GFC_TV_HOTEL_FIRST_BOOT_LOGO = "SKY_GFC_TV_HOTEL_FIRST_BOOT_LOGO";

    /**
     * AI画质增强
     */
    public static final String SKY_GFC_TV_AI_IMAGE_ENHANCER = "SKY_GFC_TV_AI_IMAGE_ENHANCER";

    /**
     * @Fields 白平衡红色增益
     */
    public static final String SKY_CFG_CC_WHITE_BALANCE_GAIN_RED = "SKY_CFG_CC_WHITE_BALANCE_GAIN_RED";
    /**
     * @Fields 白平衡绿色增益
     */
    public static final String SKY_CFG_CC_WHITE_BALANCE_GAIN_GREEN = "SKY_CFG_CC_WHITE_BALANCE_GAIN_GREEN";
    /**
     * @Fields 白平衡蓝色增益
     */
    public static final String SKY_CFG_CC_WHITE_BALANCE_GAIN_BLUE = "SKY_CFG_CC_WHITE_BALANCE_GAIN_BLUE";
    /**
     * @Fields 白平衡 20点
     */
    public static final String SKY_CFG_CC_WHITE_BALANCE_CORRECTION = "SKY_CFG_CC_WHITE_BALANCE_CORRECTION";
    /**
     * @Fields 白平衡校正复位
     */
    public static final String SKY_CFG_CC_WHITE_BALANCE_CORRECTION_RESET = "SKY_CFG_CC_WHITE_BALANCE_CORRECTION_RESET";

    /**
     * 环境光开关
     */
    public static final String SKY_GFC_TV_ENVIRONMENT_LIGHT = "SKY_GFC_TV_ENVIRONMENT_LIGHT";

    /**
     * 智能色温调节
     */
    public static final String SKY_CFG_TV_ENVIRONMENT_TEMPERATURE = "SKY_CFG_TV_ENVIRONMENT_TEMPERATURE";

    /**
     * 悬浮球
     */
    public static final String SKY_CFG_TV_FLOATING_BALL = "SKY_CFG_TV_FLOATING_BALL";

    // AB自动更新系统开关
    public static final String SKY_CFG_TV_AB_UPGRADE_AUTO_STATE = "SKY_CFG_TV_AB_UPGRADE_AUTO_STATE";

    public enum SKY_CFG_TV_AB_UPGRADE_AUTO_ENUM_TYPE {
        SKY_CFG_TV_AB_UPGRADE_AUTO_OPEN,//开启 default
        SKY_CFG_TV_AB_UPGRADE_AUTO_CLOSE//关闭

    }

    /**
     * 屏幕显示方向
     */
    public static final String SKY_CFG_TV_DISPLAY_MODE_ROTATION = "SKY_CFG_TV_DISPLAY_MODE_ROTATION";

    public enum SKY_CFG_TV_DISPLAY_MODE_ENUM {
        SKY_CFG_TV_DISPLAY_MODE_ROTATION_LEVEL(SysConst.SKY_CFG_TV_DISPLAY_MODE_ROTATION_LEVEL),//横屏
        SKY_CFG_TV_DISPLAY_MODE_ROTATION_CLOCKWISE(SysConst.SKY_CFG_TV_DISPLAY_MODE_ROTATION_CLOCKWISE),//顺时针90度
        SKY_CFG_TV_DISPLAY_MODE_ROTATION_ANTI_CLOCKWISE(SysConst.SKY_CFG_TV_DISPLAY_MODE_ROTATION_ANTI_CLOCKWISE);//逆时针90度
        public int type;

        SKY_CFG_TV_DISPLAY_MODE_ENUM(int type) {
            this.type = type;
        }

        public static SKY_CFG_TV_DISPLAY_MODE_ENUM typeOf(int type) {
            switch (type) {
                case 0:
                    return SKY_CFG_TV_DISPLAY_MODE_ROTATION_LEVEL;
                case 1:
                    return SKY_CFG_TV_DISPLAY_MODE_ROTATION_CLOCKWISE;
                case 2:
                    return SKY_CFG_TV_DISPLAY_MODE_ROTATION_ANTI_CLOCKWISE;
            }
            return null;
        }
    }

    /**
     * 屏幕方向自动旋转
     */
    public static final String SKY_CFG_TV_DISPLAY_AUTO_ROTATE = "SKY_CFG_TV_DISPLAY_AUTO_ROTATE";

    /**
     * 动态清晰度补偿
     */
    public static final String SKY_CFG_TV_CMO_STATE = "SKY_CFG_TV_CMO_STATE";

    /**
     * HIFI 音质开关
     */
    public static final String SKY_CFG_TV_HIFI_TONE_QUALITY = "SKY_CFG_TV_HIFI_TONE_QUALITY";

    /**
     * HIFI音箱连接状态
     */
    public static final String SKY_CFG_TV_SOUND_BOX_STATUS = "SKY_CFG_TV_SOUND_BOX_STATUS";

    /**
     * oled屏体修复状态
     */
    public static final String SKY_CFG_TV_OLED_FIX_STATE = "SKY_CFG_TV_OLED_FIX_STATE";

    /**
     * 获取健康护眼模式技术方案
     *
     * @return {@link SKY_CFG_TV_HEALTH_EYE_MODE_ENUM}
     */
    public static final String SKY_CFG_TV_HEALTH_EYE_MODE = "SKY_CFG_TV_HEALTH_EYE_MODE";

    /**
     * 健康护眼的前端用户行为
     * 2022/5/6新需求
     *
     * @return {@link SKY_CFG_TV_HEALTH_EYE_MODE_ACTION_ENUM}
     */
    public static final String SKY_CFG_TV_HEALTH_EYE_MODE_ACTION = "SKY_CFG_TV_HEALTH_EYE_MODE_ACTION";

    /**
     * 休息提醒
     */
    public static final String SKY_CFG_TV_REST_REMINDER = "SKY_CFG_TV_REST_REMINDER";

    /**
     * 护眼开关
     */
    public static final String SKY_CFG_TV_NIGHT_GUARD_EYE_SETTING = "SKY_CFG_TV_NIGHT_GUARD_EYE_SETTING";

    /**
     * 护眼立即开启
     */
    public static final String SKY_CFG_TV_NIGHT_GUARD_EYE_SWITCH = "SKY_CFG_TV_NIGHT_GUARD_EYE_SWITCH";

    /**
     * 护眼开启时间
     */
    public static final String SKY_CFG_TV_NIGHT_GUARD_EYE_TIME = "SKY_CFG_TV_NIGHT_GUARD_EYE_TIME";

    /**
     * 护眼定时开关
     */
    public static final String SKY_CFG_TV_NIGHT_GUARD_EYE_TIME_SWITCH = "SKY_CFG_TV_NIGHT_GUARD_EYE_TIME_SWITCH";

    /**
     * HDR-3D LUT文件
     */
    public static final String SKY_CFG_TV_HDR_LUT_FILE = "SKY_CFG_TV_HDR_LUT_FILE";

    /**
     * SDR-3D LUT文件
     */
    public static final String SKY_CFG_TV_SDR_LUT_FILE = "SKY_CFG_TV_SDR_LUT_FILE";

    /**
     * 1D LUT文件
     */
    public static final String SKY_CFG_TV_ONE_LUT_FILE = "SKY_CFG_TV_ONE_LUT_FILE";

    /**
     * 画面缩放
     */
    public static final String SKY_CFG_TV_PICTURE_ZOOM = "SKY_CFG_TV_PICTURE_ZOOM";


    public enum SKY_CFG_TV_GAMMA_MODE_ENUM_TYPE {
        SKY_CFG_TV_GAMMA_MODE_POINT_8,   // 1.8
        SKY_CFG_TV_GAMMA_MODE_POINT_0,   // 2.0
        SKY_CFG_TV_GAMMA_MODE_POINT_2,   // 2.2
        SKY_CFG_TV_GAMMA_MODE_POINT_4,   // 2.4
        SKY_CFG_TV_GAMMA_MODE_POINT_6,   // 2.6
        SKY_CFG_TV_GAMMA_MODE_BYPASS     //bypass选项
    }

    //Gamma Mode
    public static final String SKY_CFG_TV_GAMMA_MODE = "SKY_CFG_TV_GAMMA_MODE";


    public enum SKY_CFG_TV_COLOR_TEMPERATURE_D_ENUM_TYPE {
        SKY_CFG_TV_COLOR_TEMPERATURE_D55,
        SKY_CFG_TV_COLOR_TEMPERATURE_D61,
        SKY_CFG_TV_COLOR_TEMPERATURE_D65,
        SKY_CFG_TV_COLOR_TEMPERATURE_D93,
        SKY_CFG_TV_COLOR_TEMPERATURE_USER,
    }

    // ColorTemperature , such as D55 ,D61,D65,D93
    public static final String SKY_CFG_TV_COLOR_TEMPERATURE_D = "SKY_CFG_TV_COLOR_TEMPERATURE_D";

    /**
     * @Fields Red Gain Monitor
     */
    public static final String SKY_CFG_CC_GAIN_RED_MONITOR = "SKY_CFG_CC_GAIN_RED_MONITOR";
    /**
     * @Fields Green Gain Monitor
     */
    public static final String SKY_CFG_CC_GAIN_GREEN_MONITOR = "SKY_CFG_CC_GAIN_GREEN_MONITOR";
    /**
     * @Fields Blue Gain Monitor
     */
    public static final String SKY_CFG_CC_GAIN_BLUE_MONITOR = "SKY_CFG_CC_GAIN_BLUE_MONITOR";

    /**
     * @Fields Red Bias Monitor
     */
    public static final String SKY_CFG_CC_BIAS_RED_MONITOR = "SKY_CFG_CC_BIAS_RED_MONITOR";

    /**
     * @Fields Green Bias Monitor
     */
    public static final String SKY_CFG_CC_BIAS_GREEN_MONITOR = "SKY_CFG_CC_BIAS_GREEN_MONITOR";
    /**
     * @Fields Blue Bias Monitor
     */
    public static final String SKY_CFG_CC_BIAS_BLUE_MONITOR = "SKY_CFG_CC_BIAS_BLUE_MONITOR";


    /**
     * Music light Effect Switch
     */
    public static final String SKY_CFG_MUSIC_LIGHT_EFFECT_SWITCH = "SKY_CFG_MUSIC_LIGHT_EFFECT_SWITCH";

    /**
     * Spill light Effect Switch
     */
    public static final String SKY_CFG_SPILL_LIGHT_EFFECT_SWITCH = "SKY_CFG_SPILL_LIGHT_EFFECT_SWITCH";

    /**
     * 兼容两种设置模式
     * 模式1：Switch(开,关),
     * 模式2：2021.11 新需求Enum(关，弱，中，强)
     */
    public static final String SKY_CFG_CC_AISR = "SKY_CFG_CC_AISR";

    /**
     * power off light effect
     */
    public static final String SKY_CFG_POWER_OFF_LIGHT_EFFECT_SWITCH = "SKY_CFG_POWER_OFF_LIGHT_EFFECT_SWITCH";

    /**
     * is support light effect
     */
    public static final String SKY_CFG_IS_SUPPORT_LIGHT_EFFECT_SWITCH = "SKY_CFG_POWER_OFF_LIGHT_EFFECT_SWITCH";

    /**
     * 投影亮度模式
     */
    public static final String SKY_CFG_TV_PROJECTOR_BRIGHTNESS_MODE = "SKY_CFG_TV_PROJECTOR_BRIGHTNESS_MODE";

    /**
     * 投影亮度
     */
    public static final String SKY_CFG_TV_PROJECTOR_BRIGHTNESS = "SKY_CFG_TV_PROJECTOR_BRIGHTNESS";

    /**
     * 内容场景，用户选择自动切换音画场景
     */
    public static final String SKY_CFG_TV_CONTENT_SCENE_OTHER = "SKY_CFG_TV_CONTENT_SCENE_OTHER";

    /**
     * 氛围灯亮度
     */
    public static final String SKY_GFG_TV_AMBIENT_LIGHT_BRIGHTNESS = "SKY_GFG_TV_AMBIENT_LIGHT_BRIGHTNESS";
    /**
     * 氛围灯模式
     */
    public static final String SKY_GFG_TV_AMBIENT_LIGHT_MODE = "SKY_GFG_TV_AMBIENT_LIGHT_MODE";
    /**
     * 氛围的颜色
     */
    public static final String SKY_GFG_TV_AMBIENT_LIGHT_COLOR = "SKY_GFG_TV_AMBIENT_LIGHT_COLOR";

    /**
     * 视觉辅助模式
     */
    public static final String SKY_CFG_TV_VISION_ASSIST_MODE = "SKY_CFG_TV_VISION_ASSIST_MODE";

    /**
     * 视觉辅助程度
     */
    public static final String SKY_CFG_TV_VISION_ASSIST_HELP_LEVEL = "SKY_CFG_TV_VISION_ASSIST_HELP_LEVEL";

    /**
     * 光感强度
     */
    public static final String SKY_CFG_TV_LIGHT_INTENSITY = "SKY_CFG_TV_LIGHT_INTENSITY";


    /**
     * 专家色彩空间
     */
    public enum SKY_CFG_TV_EXPERT_COLOR_SPACE_ENUM_TYPE {
        SKY_CFG_TV_EXPERT_COLOR_SPACE_ENUM_AUTO,
        SKY_CFG_TV_EXPERT_COLOR_SPACE_ENUM_BT_709,
        SKY_CFG_TV_EXPERT_COLOR_SPACE_ENUM_BT_2020,
        SKY_CFG_TV_EXPERT_COLOR_SPACE_ENUM_DCI_P3,
        SKY_CFG_TV_EXPERT_COLOR_SPACE_ENUM_ADOBE_RGB,
        SKY_CFG_TV_EXPERT_COLOR_SPACE_ENUM_BYPASS
    }

    /**
     * 专家色彩空间
     */
    public static final String SKY_CFG_TV_EXPERT_COLOR_SPACE = "SKY_CFG_TV_EXPERT_COLOR_SPACE";


    /**
     * 灯带安装方式 枚举
     */
    public enum TapeLightMountType {
        E_TAPE_LIGHT_MOUNT_TYPE_IN_LINE,//一字形
        E_TAPE_LIGHT_MOUNT_TYPE_INVERTED_U,//倒U形
    }

    /**
     * 设置灯带灯珠数量时位置信息枚举
     */
    public enum TapeLightLedNumPosType {
        E_TAPE_LIGHT_LED_NUM_POS_TYPE_IN_LINE,//一字形灯珠数量
        E_TAPE_LIGHT_LED_NUM_POS_TYPE_INVERTED_U_LEFT,//倒U形左边灯珠数量
        E_TAPE_LIGHT_LED_NUM_POS_TYPE_INVERTED_U_TOP,//倒U形顶边灯珠数量
        E_TAPE_LIGHT_LED_NUM_POS_TYPE_INVERTED_U_RIGHT,//倒U形右边灯珠数量
    }

    /**
     * 灯带模式
     */
    public enum TapeLightMode {
        E_TAPE_LIGHT_MODE_AI,//AI模式
        E_TAPE_LIGHT_MODE_MUSIC,//音乐模式
        E_TAPE_LIGHT_MODE_IMAGE,//光随影变模式
        E_TAPE_LIGHT_MODE_ATMOSPHERE,//氛围模式
        E_TAPE_LIGHT_MODE_CLOSE,//关闭
    }

    /**
     * 灯带音乐灯效模式
     */
    public enum TapeLightMusicEffectMode {
        E_TAPE_LIGHT_MUSIC_EFFECT_MODE_FOLLOW_THE_RHYTHM,//跟随律动
        E_TAPE_LIGHT_MUSIC_EFFECT_MODE_FULL_BRIGHT_RHYTHM,//全亮律动
    }

    /**
     * 灯带氛围灯效颜色
     */
    public enum TapeLightAtmosphereModeColor {
        E_TAPE_LIGHT_ATMOSPHERE_MODE_WARM_WHITE,//单色暖白
        E_TAPE_LIGHT_ATMOSPHERE_MODE_COLD_WHITE,//单色冷白
        E_TAPE_LIGHT_ATMOSPHERE_MODE_GREEN,//单色绿
        E_TAPE_LIGHT_ATMOSPHERE_MODE_RED,//单色红
        E_TAPE_LIGHT_ATMOSPHERE_MODE_BLUE,//单色蓝
        E_TAPE_LIGHT_ATMOSPHERE_MODE_PURPLE,//单色紫
        E_TAPE_LIGHT_ATMOSPHERE_MODE_FADE_COLOR,//渐变色
        E_TAPE_LIGHT_ATMOSPHERE_MODE_ILLUSION_COLOR_1,//幻彩1
        E_TAPE_LIGHT_ATMOSPHERE_MODE_ILLUSION_COLOR_2,//幻彩2
        E_TAPE_LIGHT_ATMOSPHERE_MODE_ILLUSION_COLOR_3,//幻彩3
    }

    /**
     * 灯带颜色变化速度
     */
    public enum TapeLightColorChangeSpeed {
        E_TAPE_LIGHT_COLOR_CHANGE_SPEED_LOW,//低
        E_TAPE_LIGHT_COLOR_CHANGE_SPEED_MID,//中
        E_TAPE_LIGHT_COLOR_CHANGE_SPEED_HIGH,//高
    }

    /**
     * 灯带状态变化类型
     */
    public enum TapeLightStateChangeType {
        E_TAPE_LIGHT_STATE_CHANGE_TYPE_MODE,//灯带“模式”变化
        E_TAPE_LIGHT_STATE_CHANGE_TYPE_LED_BRIGHT,//灯带“亮度”变化
        E_TAPE_LIGHT_STATE_CHANGE_TYPE_COLOR_CHANGE_SPEED,//灯带“颜色变化速度”变化
        E_TAPE_LIGHT_STATE_CHANGE_TYPE_ATMOSPHERE_MODE_COLOR,//灯带“氛围灯效颜色”变化
        E_TAPE_LIGHT_STATE_CHANGE_TYPE_UART_ERROR,//灯带“串口通讯异常”，此项不需要判断参数
        E_TAPE_LIGHT_STATE_CHANGE_TYPE_MUSIC_EFFECT_MODE,//灯带“音乐律动方式”变化
        E_TAPE_LIGHT_STATE_CHANGE_TYPE_RESET,//灯带面板按键复位
    }

    /**
     * 灯带设备连接状态
     */
    public enum ConnectStatus {
        DISCONNECTED,//未连接
        CONNECTING,//正在连接
        CONNECTED,//已连接
        CONNECTION_FAILED;//连接失败（例如：超时）
    }


    /**
     * 灯带工作状态枚举
     */
    public enum TapeLightWorkState {
        E_TAPE_LIGHT_WORK_STATE_NORMAL,         //灯带处于正常工作状态
        E_TAPE_LIGHT_WORK_STATE_ADJUST_LED_NUM, //灯带处于灯珠数量调节状态
    }

    /**
     * 灯带工作状态
     */
    public static final String SKY_CFG_TV_TAPE_LIGHT_WORK_STATE = "SKY_CFG_TV_TAPE_LIGHT_WORK_STATE";


    /**
     * 灯带安装方式
     */
    public static final String SKY_CFG_TV_TAPE_LIGHT_MOUNT_TYPE = "SKY_CFG_TV_TAPE_LIGHT_MOUNT_TYPE";

    /**
     * 一字形灯珠数量
     */
    public static final String SKY_CFG_TV_TAPE_LIGHT_LED_NUM_POS_TYPE_IN_LINE = "SKY_CFG_TV_TAPE_LIGHT_LED_NUM_POS_TYPE_IN_LINE";

    /**
     * 倒U形左边灯珠数量
     */
    public static final String SKY_CFG_TV_TAPE_LIGHT_LED_NUM_POS_TYPE_INVERTED_U_LEFT = "SKY_CFG_TV_TAPE_LIGHT_LED_NUM_POS_TYPE_INVERTED_U_LEFT";

    /**
     * 倒U形顶边灯珠数量
     */
    public static final String SKY_CFG_TV_TAPE_LIGHT_LED_NUM_POS_TYPE_INVERTED_U_TOP = "SKY_CFG_TV_TAPE_LIGHT_LED_NUM_POS_TYPE_INVERTED_U_TOP";

    /**
     * 倒U形右边灯珠数量
     */
    public static final String SKY_CFG_TV_TAPE_LIGHT_LED_NUM_POS_TYPE_INVERTED_U_RIGHT = "SKY_CFG_TV_TAPE_LIGHT_LED_NUM_POS_TYPE_INVERTED_U_RIGHT";


    /**
     * 灯带亮度
     */
    public static final String SKY_CFG_TV_TAPE_LIGHT_BRIGHT = "SKY_CFG_TV_TAPE_LIGHT_BRIGHT";


    /**
     * 灯带模式
     */
    public static final String SKY_CFG_TV_TAPE_LIGHT_MODE = "SKY_CFG_TV_TAPE_LIGHT_MODE";


    /**
     * 灯带音乐灯效模式
     */
    public static final String SKY_CFG_TV_TAPE_LIGHT_MUSIC_EFFECT_MODE = "SKY_CFG_TV_TAPE_LIGHT_MUSIC_EFFECT_MODE";


    /**
     * 灯带氛围灯效颜色
     */
    public static final String SKY_CFG_TV_TAPE_LIGHT_ATMOSPHERE_MODE_COLOR = "SKY_CFG_TV_TAPE_LIGHT_ATMOSPHERE_MODE_COLOR";

    /**
     * 灯带颜色变化速度
     */
    public static final String SKY_CFG_TV_TAPE_LIGHT_COLOR_CHANGE_SPEED = "SKY_CFG_TV_TAPE_LIGHT_COLOR_CHANGE_SPEED";


    /**
     * 灯带颜色变化速度
     */
    public static final String SKY_CFG_TV_TAPE_LIGHT_COLOR_CONNECT_STATE = "SKY_CFG_TV_TAPE_LIGHT_COLOR_CONNECT_STATE";

    /**
     * 设置蓝牙灯带连接时候配对/回连状态
     */
    public static final String SKY_CFG_TV_TAPE_LIGHT_CONNECT_STATE = "SKY_CFG_TV_TAPE_LIGHT_CONNECT_STATE";

    /**
     * 设置蓝牙灯带断开时候配对/回连状态
     */
    public static final String SKY_CFG_TV_TAPE_LIGHT_DISCONNECT_STATE = "SKY_CFG_TV_TAPE_LIGHT_DISCONNECT_STATE";


    /**
     * 获取 本机是否支持“蓝牙灯带”功能
     */
    public static final String SKY_CFG_TV_TAPE_LIGHT_IS_SUPPORT = "SKY_CFG_TV_TAPE_LIGHT_IS_SUPPORT";

    /**
     * 设置蓝牙灯带状态变化监听
     */
    public static final String SKY_CFG_TV_TAPE_LIGHT_STATE_CHANGE_LISTENER = "SKY_CFG_TV_TAPE_LIGHT_STATE_CHANGE_LISTENER";

    /**
     * 获取蓝牙灯带的设备信息
     */
    public static final String SKY_CFG_TV_TAPE_LIGHT_DEVICE_INFO = "SKY_CFG_TV_TAPE_LIGHT_DEVICE_INFO";

    /**
     * 获取蓝牙灯带的软件版本
     */
    public static final String SKY_CFG_TV_TAPE_LIGHT_SOFTWARE_VERSION = "SKY_CFG_TV_TAPE_LIGHT_SOFTWARE_VERSION";

    /**
     * 设置蓝牙灯带的升级包路径
     */
    public static final String SKY_CFG_TV_TAPE_LIGHT_UPDATE_PKG_PATH = "SKY_CFG_TV_TAPE_LIGHT_UPDATE_PKG_PATH";

    /**
     * 设置蓝牙灯带的升级状态回调
     */
    public static final String SKY_CFG_TV_TAPE_LIGHT_UPGRADE_STATUS_CHANGE = "SKY_CFG_TV_TAPE_LIGHT_UPGRADE_STATUS_CHANGE";

    /**
     * 蓝牙灯带亮灯方向
     */
    public static final String SKY_CFG_TV_TAPE_LIGHT_DIRECTION = "SKY_CFG_TV_TAPE_LIGHT_DIRECTION";

    /**
     * 蓝牙灯带是否为低配版本
     */
    public static final String SKY_CFG_TV_TAPE_LIGHT_STRIP_IS_LOW_PROFILE = "SKY_CFG_TV_TAPE_LIGHT_STRIP_IS_LOW_PROFILE";

    /**
     * 听觉辅助-AI字幕
     */
    public static final String SKY_CFG_TV_AUDITORY_ASSIST_AI_SUBTITLES_SWITCH = "SKY_CFG_TV_AUDITORY_ASSIST_AI_SUBTITLES_SWITCH";
    /**
     * 省流模式
     */
    public static final String SKY_CFG_TV_AUDITORY_ASSIST_STREAM_SAVING_MODE = "SKY_CFG_TV_AUDITORY_ASSIST_STREAM_SAVING_MODE";
    /**
     * 听觉辅助-AI字幕大小
     */
    public static final String SKY_CFG_TV_AUDITORY_ASSIST_AI_SUBTITLES_SIZE = "SKY_CFG_TV_AUDITORY_ASSIST_AI_SUBTITLES_SIZE";
    /**
     * 听觉辅助-AI字幕位置
     */
    public static final String SKY_CFG_TV_AUDITORY_ASSIST_AI_SUBTITLES_LOCATION = "SKY_CFG_TV_AUDITORY_ASSIST_AI_SUBTITLES_LOCATION";

    /**
     * 判断是否支持环境光
     */
    public static final String SKY_GFC_TV_SUPPORT_ENVIRONMENT_LIGHT = "SKY_GFC_TV_SUPPORT_ENVIRONMENT_LIGHT";

    /**
     * 通知 Demo Mode
     */
    public static final String SKY_CFG_DEMO_MODE_STATE_NOTIFY = "SKY_CFG_DEMO_MODE_STATE_NOTIFY";


    //   旧的对话增强，会被废弃
    /**
     * 对话增加模式
     */
    public static final String SKY_CFG_DIALOG_ENHANCE = "SKY_CFG_DIALOG_ENHANCE";

    /**
     * 是否支持对话增加模式
     */
    public static final String SKY_CFG_DIALOG_ENHANCE_SUPPORT = "SKY_CFG_DIALOG_ENHANCE_SUPPORT";

    /**
     * 对话增加模式类型
     */
    public enum TapeDialogEnhanceType {
        E_TAPE_DIALOG_ENHANCE_MODE_OFF,//关
        E_TAPE_DIALOG_ENHANCE_MODE_WEAK,//弱
        E_TAPE_DIALOG_ENHANCE_MODE_MIDDLE,//中
        E_TAPE_DIALOG_ENHANCE_MODE_STRONG,//强
    }


    //  新加的对话增强，上面旧的会被废弃

    /**
     * 增强类型
     */
    public enum BoostType {
        E_TYPE_BOOST_OFF,//关
        E_TYPE_BOOST_WEAK,//弱
        E_TYPE_BOOST_MIDDLE,//中
        E_TYPE_BOOST_STRONG,//强
    }


    /**
     * 对话增强模式
     */
    public static final String SKY_CFG_DIALOGUE_BOOST = "SKY_CFG_DIALOGUE_BOOST";

    /**
     * 是否支持对话增加模式
     */
    public static final String SKY_CFG_DIALOGUE_BOOST_SUPPORT = "SKY_CFG_DIALOGUE_BOOST_SUPPORT";


    /**
     * Surround Sound Boost
     */
    public static final String SKY_CFG_SURROUND_SOUND_BOOST = "SKY_CFG_SURROUND_SOUND_BOOST";

    /**
     * Surround Sound Boost Support
     */
    public static final String SKY_CFG_SURROUND_SOUND_BOOST_SUPPORT = "SKY_CFG_SURROUND_SOUND_BOOST_SUPPORT";


    /**
     * Bass boost
     */
    public static final String SKY_CFG_BASS_BOOST = "SKY_CFG_BASS_BOOST";

    /**
     * Bass boost support
     */
    public static final String SKY_CFG_BASS_BOOST_SUPPORT = "SKY_CFG_BASS_BOOST_SUPPORT";


    /**
     * Treble boost
     */
    public static final String SKY_CFG_TREBLE_BOOST = "SKY_CFG_TREBLE_BOOST";

    /**
     * Treble boost support
     */
    public static final String SKY_CFG_TREBLE_BOOST_SUPPORT = "SKY_CFG_TREBLE_BOOST_SUPPORT";


    /**
     * Gamma
     */
    public static final String SKY_CFG_CC_GAMMA = "SKY_CFG_CC_GAMMA";

    /**
     * Gamma类型
     */
    public enum SKY_CFG_CC_GAMMA_ENUM_TYPE {
        SKY_CFG_CC_GAMMA_NORMAL,//正常
        SKY_CFG_CC_GAMMA_BIAS,//偏亮
        SKY_CFG_CC_GAMMA_DARK,//偏暗
    }

    /**
     * HDR模式
     */
    public static final String SKY_CFG_TV_HDR_MODE = "SKY_CFG_TV_HDR_MODE";


    /**
     * HDR模式类型
     */
    public enum SKY_CFG_TV_HDR_MODE_ENUM_TYPE {
        SKY_CFG_TV_HDR_MODE_AUTO,//自动
        SKY_CFG_TV_HDR_MODE_HDR10,//HDR10
        SKY_CFG_TV_HDR_MODE_HLG,//HLG
        SKY_CFG_TV_HDR_MODE_CLOSE,//关
    }


    /**
     * HDR色调映射
     */
    public static final String SKY_CFG_TV_HDR_TONE_MAP = "SKY_CFG_TV_HDR_TONE_MAP";

    /**
     * HDR色调映射类型
     */
    public enum SKY_CFG_TV_HDR_TONE_MAP_ENUM_TYPE {
        SKY_CFG_TV_HDR_TONE_MAP_PRIOR_BRIGHTNESS, //优先亮度
        SKY_CFG_TV_TONE_MAP_PRIOR_DETAILS, //优先细节
        SKY_CFG_TV_HDR_TONE_MAP_BALANCED, //平衡
        SKY_CFG_TV_HDR_TONE_MAP_OFF, //关
    }

    /**
     * HDMI视频信号范围
     */
    public static final String SKY_CFG_TV_HDMI_VIDEO_SIGNAL_RANGE = "SKY_CFG_TV_HDMI_VIDEO_SIGNAL_RANGE";


    /**
     * HDMI视频信号范围类型
     */
    public enum SKY_CFG_TV_HDMI_VIDEO_SIGNAL_RANGE_TYPE {
        SKY_CFG_TV_HDMI_VIDEO_SIGNAL_RANGE_MODE_AUTO,//自动
        SKY_CFG_TV_HDMI_VIDEO_SIGNAL_RANGE_MODE_ALL,//全部
        SKY_CFG_TV_HDMI_VIDEO_SIGNAL_RANGE_MODE_LIMIT,//限制
    }

    /**
     * 是否支持eARC
     */
    public static final String SKY_CFG_TV_SUPPORT_HDMI_eARC = "SKY_CFG_TV_SUPPORT_HDMI_eARC";

    /**
     * HDMI-eARC模式
     */
    public static final String SKY_CFG_TV_HDMI_eARC = "SKY_CFG_TV_HDMI_eARC";

    /**
     * 获取是否支持关怀模式
     */
    public static final String SKY_CFG_TV_SUPPORT_CARE_MODE = "SKY_CFG_TV_SUPPORT_CARE_MODE";

    //  平台模式
    public static final String NormalMode = "NormalMode";   // 标准模式
    public static final String ChildMode = "ChildMode";     // 儿童模式
    public static final String ElderMode = "ElderMode";     // 老人模式
    public static final String OfficeMode = "OfficeMode";   // 办公模式


    /**
     * 支持HSR的情况
     */
    public static final String SKY_GFG_IS_SUPPORT_HSR = "SKY_GFG_IS_SUPPORT_HSR";

    /**
     * 240Hz高刷模式
     */
    public static final String SKY_CFG_TV_240HZ_MODE = "SKY_CFG_TV_240HZ_MODE";

    /**
     * 288Hz高刷模式
     */
    public static final String SKY_CFG_TV_288HZ_MODE = "SKY_CFG_TV_288HZ_MODE";

    /**
     * 是否支持日志控制
     */
    public static final String SKY_CFG_IS_SUPPORT_LOG_CONTROL = "SKY_CFG_IS_SUPPORT_LOG_CONTROL";
    /**
     * 控制日志打印
     * {@link SKY_CFG_LOG_CONTROL_MODE_ENUM}
     */
    public static final String SKY_CFG_LOG_CONTROL_MODE = "SKY_CFG_LOG_CONTROL_MODE";

    /**
     * 控制日志打印控制类型
     */
    public enum SKY_CFG_LOG_CONTROL_MODE_ENUM {
        OFF,//关闭调试模式
        DEBUG,//开启调试模式
        LOG//抓取日志
    }


    // D80 Displayer start
    /**
     * Over Driver 屏幕响应速度
     */
    public static final String SKY_CFG_TV_OVER_DRIVER = "SKY_CFG_TV_OVER_DRIVER";

    /**
     * D80显示器控制模式
     * {@link SKY_CFG_D80_DISPLAYER_CONTROL_MODE_TYPE}
     */
    public static final String SKY_CFG_D80_DISPLAYER_CONTROL_MODE = "SKY_CFG_D80_DISPLAYER_CONTROL_MODE";

    /**
     * D80显示器控制模式类型
     */
    public enum SKY_CFG_D80_DISPLAYER_CONTROL_MODE_TYPE {
        HAND_LAMP,//挂灯控制模式
        OSD,//OSD控制模式
    }

    /**
     * 是否支持 KVM
     */
    public static final String SKY_CFG_KVM_SUPPORT = "SKY_CFG_KVM_SUPPORT";

    /**
     * KVM Mode
     */
    public static final String SKY_CFG_KVM_MODE = "SKY_CFG_KVM_MODE";

    /**
     * KVM 类型
     */
    public enum KVM_MODE {
        KVM_MODE_DISPLAYER, //关
        KVM_MODE_TYPE_C, // TYPE C
        KVM_MODE_USB_B, // USB B
    }

    /**
     * 是否支持 TYPE C 关机充电
     */
    public static final String SKY_CFG_TYPE_C_STANDBY_SUPPORT = "SKY_CFG_TYPE_C_STANDBY_SUPPORT";

    /**
     * TYPE C 关机充电开关
     */
    public static final String SKY_CFG_TYPE_C_STANDBY_MODE = "SKY_CFG_TYPE_C_STANDBY_MODE";

    /**
     * 是否支持 USB B 待机充电
     */
    public static final String SKY_CFG_USB_B_STANDBY_SUPPORT = "SKY_CFG_USB_B_STANDBY_SUPPORT";

    /**
     * USB B 待机充电开关
     */
    public static final String SKY_CFG_USB_B_STANDBY_MODE = "SKY_CFG_USB_B_STANDBY_MODE";


    /**
     * 显示器待机指示灯开关
     */
    public static final String SKY_CFG_STANDBY_LIGHT_SWITCH = "SKY_CFG_STANDBY_LIGHT_SWITCH";

    // D80 Displayer end


    /**
     * 其他应用向System Services发送数据
     */
    public static final String SKY_CFG_SEND_DATA_TO_SYSTEM_SERVICE = "SKY_CFG_SEND_DATA_TO_SYSTEM_SERVICE";

    /**
     * 音画定制  声音定制  设置默认声音模式，中间件使用该接口区别于正常设置声音模式
     */
    public static final String SKY_CFG_TV_SOUND_CUSTOM_DEFAULT = "SKY_CFG_TV_SOUND_CUSTOM_DEFAULT";

    /**
     * 音画定制  声音定制 保存声音定制
     */
    public static final String SKY_CFG_TV_SOUND_CUSTOM_CREATE = "SKY_CFG_TV_SOUND_CUSTOM_CREATE";

    /**
     * 音画定制  声音定制  重置AmpEq参数
     */
    public static final String SKY_CFG_TV_SOUND_CUSTOM_RESET_EQ = "SKY_CFG_TV_SOUND_CUSTOM_RESET_EQ";

    /**
     * 音画定制  声音定制  恢复AmpEq参数（异常情况使用）
     */
    public static final String SKY_CFG_TV_SOUND_CUSTOM_RESTORE_EQ = "SKY_CFG_TV_SOUND_CUSTOM_RESTORE_EQ";

    /**
     * 音画定制  声音定制 置AmpEq参数
     */
    public static final String SKY_CFG_TV_SOUND_CUSTOM_SET_EQ = "SKY_CFG_TV_SOUND_CUSTOM_SET_EQ";

    /**
     * 音画定制 图像定制  设置默认标准图像模式
     */
    public static final String SKY_CFG_TV_PICTURE_MODE_CUSTOM_DEFAULT = "SKY_CFG_TV_PICTURE_MODE_CUSTOM_DEFAULT";

    /**
     * 音画定制 图像定制  生成私人定制模式
     */
    public static final String SKY_CFG_TV_PICTURE_MODE_CUSTOM_CREATE = "SKY_CFG_TV_PICTURE_MODE_CUSTOM_CREATE";

    /**
     * 遥控器关机
     */
    public static final String SKY_CFG_TV_SYSTEM_REMOTE_CONTROL_POWEROFF_TYPE = "SKY_CFG_TV_SYSTEM_REMOTE_CONTROL_POWEROFF_TYPE";

    //user 场景定义
    public enum SCENE_BY_USER_TYPE {
        SCENE_BY_USER_STANDARD_MODE,
        SCENE_BY_USER_ELDER_MODE,
        SCENE_BY_USER_CHILD_MODE
    }

    public enum REMOTE_CONTROL_POWER_OFF_TYPE{
        SKY_CFG_TV_SYSTEM_POWEROFF_MENU,  //关机选择
        SKY_CFG_TV_SYSTEM_IMMEDIATE_SCREENOFF, // 直接息屏
        SKY_CFG_TV_SYSTEM_IMMEDIATE_SHUTDOWN, // 直接关机(包括真待机 和STR待机)
    }
}
