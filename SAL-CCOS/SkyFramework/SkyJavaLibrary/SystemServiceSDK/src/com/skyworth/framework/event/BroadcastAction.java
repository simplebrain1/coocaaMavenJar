package com.skyworth.framework.event;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;

/**
 * 广播名称定义
 *
 * @since 1
 */
public class BroadcastAction {
    /**
     * 待确认
     */
    public static final String BROADCAST_SYSTEM_ENV_CHANGED = "com.skyworth.broadcast.system.env";
    /**
     * 待确认
     */
    public static final String BROADCAST_DIRECTIVE = "com.skyworth.directive";
    /**
     * 退出播放器
     */
    public static final String BROADCAST_MEDIAPLAYER_EXIT = "com.skyworth.mediaplayer.exit";
    /**
     * 每曲开始
     */
    public static final String BROADCAST_MEDIAPLAYER_BEGIN = "com.skyworth.mediaplayer.begin";
    /**
     * 外部调播放器
     */
    public static final String BROADCAST_MEDIAPLAYER_OUTEROPEN = "com.skyworth.mediaplayer.outeropen";
    /**
     * 信号源
     */
    public static final String BROADCAST_HOTKEYS_SOURCESWITCH = "com.skyworth.hotkeys.sourceswitch";
    /**
     * 静音
     */
    public static final String BROADCAST_HOTKEYS_MUTE = "com.skyworth.hotkeys.mute";
    /**
     * 音量调节
     */
    public static final String BROADCAST_HOTKEYS_VOLUMEADJUST = "com.skyworth.hotkeys.volumeadjust";
    /**
     * 键控板
     */
    public static final String BROADCAST_HOTKEYS_SIDEKEYS = "com.skyworth.hotkeys.sidekeys";
    /**
     * 显示模式
     */
    public static final String BROADCAST_HOTKEYS_DISPLAYMODE = "com.skyworth.hotkeys.displaymode";
    /**
     * 图像模式
     */
    public static final String BROADCAST_HOTKEYS_PICTUREMODE = "com.skyworth.hotkeys.picturemode";
    /**
     * 声音模式
     */
    public static final String BROADCAST_HOTKEYS_SOUNDMODE = "com.skyworth.hotkeys.soundmode";
    /**
     * 3D模式
     */
    public static final String BROADCAST_HOTKEYS_3DMODE = "com.skyworth.hotkeys.3dmode";
    /**
     * 待确认
     */
    public static final String BROADCAST_LANG_SETTING_CHANGED = "com.skyworth.broadcast.lang.setting";
    /**
     * 菜单主题设置
     */
    public static final String BROADCAST_MENU_THEME_CHANGED = "com.skyworth.themechanged";

    /**
     * 设置睡眠时间
     */
    public static final String BROADCAST_SLEEP_TIME_SETTING = "com.skyworth.broadcast.sleeptime.setting";
    /**
     * 状态栏
     */
    public static final String BROADCAST_SYSTEM_STATUS_ENV_CHANGED = "com.skyworth.broadcast.status.env";
    /**
     * 键值广播
     */
    public static final String BROADCAST_SEND_HOTKEYS = "com.android.sky.SendHotKey";
    /**
     * 语音键广播
     */
    public static final String BROADCAST_VOICE_KEYS = "com.skyworth.voice.key";
    /**
     * 语音View发广播到SFloatUIService
     */
    public static final String BROADCAST_VOICE_VIEW = "com.skyworth.voice.view";
    /**
     * 接收手机端语音广播
     */
    public static final String BROADCAST_VOICE_FROM_PHONE = "com.skyworth.controlservice.voicetext";
    /**
     * 接收手机端语音广播
     */
    public static final String BROADCAST_VOICE_SYNTHESIS = "com.skyworth.voice.synthesis";
    /**
     * 关闭语音
     */
    public static final String BROADCAST_VOICE_CLOSE = "com.skyworth.voice.close";
    /**
     * 打开天气广播
     */
    public static final String BROADCAST_WEATHER_VIEW = "com.skyworth.weather.view";
    /**
     * 退出工厂模式
     */
    public static final String BROADCAST_FAC_EXIT = "com.skyworth.broadcast.factory.eixt";
    /**
     * 出厂键要求显示OutsetOK
     */
    public static final String BROADCAST_FAC_OUTSETOK = "com.skyworth.broadcast.factory.outsetok";
    /**
     * 老化键要求显示“M”
     */
    public static final String BROADCAST_FAC_MMODE = "com.skyworth.broadcast.factory.mmode";
    /**
     * 收到待机消息
     */
    public static final String BROADCAST_FAC_RECEIVE_STANDBY = "tv.intent.action.standby";
    /**
     * 打开股票页面
     */
    public static final String BROADCAST_STOCK_VIEW = "com.skyworth.stock.view";
    /**
     * 调用全局的Toast
     */
    public static final String BROADCAST_COMMENT_VIEW = "com.skyworth.broadcast.toast";
    /**
     * 讯飞输入焦点状态
     */
    public static final String BROADCAST_DMT_ITV_VOICEINPUT = "com.skyworth.dmt.itv.voiceplatform.voicedetect.intputstatus";
    /**
     * 启动酷开系统输入法
     */
    public static final String BROADCAST_SKY_INPUTS_START = "com.skyworth.sky.inputstart";
    /**
     * 关闭酷开系统输入法
     */
    public static final String BROADCAST_SKY_INPUTS_STOP = "com.skyworth.sky.inputstop";
    /**
     * 第三方应用调logActivity
     */
    public static final String BROADCAST_LOGACTIVITY = "com.skyworth.broadcast.logactivity";
    /**
     * standardservices不处理广播按键的广播
     */
    public static final String BROADCAST_HOTKEYS_DISABLE = "com.skyworth.broadcast.hotkeys.disable";
    /**
     * 打开百科页面
     */
    public static final String BROADCAST_ENCYCLOPEDIAS_VIEW = "com.skyworth.encyclopedias.view";
    /**
     * 暂停atv的音频
     */
    public static final String BROADCAST_PAUSE_AUDIO_PLAY = "com.skyworth.broadcast.resource.audioPlay.pause";
    /**
     * atv音频暂停中
     */
    public static final String BROADCAST_PLAYER_RESOURCE_AUDIO_PAUSING = "com.skyworth.broadcast.resource.audioPlay.pause.doing";
    /**
     * atv音频已暂停
     */
    public static final String BROADCAST_PLAYER_RESOURCE_AUDIO_PAUSED = "com.skyworth.broadcast.resource.audioPlay.pause.done";
    /**
     * 恢复原来的音频
     */
    public static final String BROADCAST_RESUME_AUDIO_PLAY = "com.skyworth.broadcast.resource.audioPlay.resume";
    /**
     * atv音频恢复中
     */
    public static final String BROADCAST_PLAYER_RESOURCE_AUDIO_RESUMING = "com.skyworth.broadcast.resource.audioPlay.resume.doing";
    /**
     * atv音频已恢复
     */
    public static final String BROADCAST_PLAYER_RESOURCE_AUDIO_RESUMED = "com.skyworth.broadcast.resource.audioPlay.resume.done";
    /**
     * 通知MStar主页是否挖一角TV
     */
    public static final String BROADCAST_DIG_ON_HOME = "com.skyworth.mstar.home.window";
    /**
     * 第三方应用设置环境变量
     */
    public static final String BROADCAST_SYSTEM_SET_ENV = "com.skyworth.broadcast.set.env";
    /**
     * 第三方调分享
     */
    public static final String BROADCAST_SNS_SHARE = "com.skyworth.broadcast.sns.share";
    /**
     * 信号源切换
     */
    public static final String BROADCAST_SOURCE_SELECT = "com.skyworth.broadcast.source.select";
    /**
     * StandardService重启
     */
    public static final String BROADCAST_STANDARDSERVICES_RESTART = "com.skyworth.broadcast.standardservices.restart";
    /**
     * 显示实时评论的UI
     */
    public static final String BROADCAST_SHOWRECOMMENT = "com.skyworth.controlservice.showrecomment";
    /**
     * 显示实时评论的UI
     */
    public static final String BROADCAST_GETCLOUDSYCDATA = "com.skyworth.homepage.showrecomment";
    /**
     * 七键控板按键广播
     */
    public static final String BROADCAST_SEND_KEYPAD = "com.android.sky.ShowKeypadUI";
    /**
     * 五键控板按键广播
     */
    public static final String BROADCAST_SEND_VIRTUAL_KEYPAD = "com.android.sky.ShowVirtualKeypadUI";
    /**
     * BROADCAST_CUSTOMER_TOKEN_CREATED 金山快盘token产生 2013/6/4 guiqingwen add
     */
    public static final String BROADCAST_CUSTOMER_TOKEN_CREATED = "com.skyworth.customer_token.created";
    /**
     * BROADCAST_CUSTOMER_TOKEN_EXPIRED 金山快盘token过期 2013/6/4 guiqingwen add
     */
    public static final String BROADCAST_CUSTOMER_TOKEN_EXPIRED = "com.skyworth.customer_token.expired";
    /**
     * BROADCAST_CUSTOMER_TOKEN_GET 金山快盘get token 2013/6/13 guiqingwen add
     */
    public static final String BROADCAST_CUSTOMER_TOKEN_GET = "com.skyworth.customer_token.get";
    /**
     * BROADCAST_REFRESH_JINSAN_DATA 通知UI刷新金山数据
     */
    public static final String BROADCAST_REFRESH_JINSAN_DATA = "com.skyworth.broadcast.refresh.jinsan";

    /**
     * 网络类型接口切换之后发出的广播名称
     */
    public static final String BROADCAST_NETWORK_INTERFACE_CHANGE = "com.skyworth.broadcast.network.interfaceChange";
    /**
     * 有线网络配置之后发出的广播名称
     */
    public static final String BROADCAST_NETWORK_ETHERNET_CONFIGURATION = "com.skyworth.broadcast.network.ethernetConfiguration";
    /**
     * WIFI扫描AP之后发出的广播名称
     */
    public static final String BROADCAST_NETWORK_WIFI_SCAN = "com.skyworth.broadcast.network.wifiScan";
    /**
     * 无线网络配置之后发出的广播名称
     */
    public static final String BROADCAST_NETWORK_WIFI_CONFIGURATION = "com.skyworth.broadcast.network.wifiConfiguration";
    /**
     * WIFI进行WPS配置之后发出的广播名称
     */
    public static final String BROADCAST_NETWORK_WIFI_WPS_AUTO_CONNECT = "com.skyworth.broadcast.network.wifiWpsConfiguration";

    /**
     * 有线网络：网线拔出
     */
    public static final String BROADCAST_NETWORK_ETHERNET_DEVICE_REMOVE = "com.skyworth.broadcast.network.ethernetDeviceRemove";
    /**
     * 有线网络：网线插上
     */
    public static final String BROADCAST_NETWORK_ETHERNET_DEVICE_ADD = "com.skyworth.broadcast.network.ethernetDeviceAdd";
    /**
     * 有线网络：网络断开
     */
    public static final String BROADCAST_NETWORK_ETHERNET_NETWORK_DISCONNECT = "com.skyworth.broadcast.network.ethernetNetworkDisconnect";
    /**
     * 有线网络： 网络连通
     */
    public static final String BROADCAST_NETWORK_ETHERNET_NETWORK_CONNECT = "com.skyworth.broadcast.network.ethernetNetworkConnect";

    public static final String BROADCAST_CHANGE_USER = "change_user";

    /**
     * 首页添加图标广播
     */
    public static final String BROADCAST_HOME_REMOVE_APP_SHORTCUT = "com.skyworth.broadcast.home.removeShortcut";
    /**
     * 停止文件扫描广播
     */
    public static final String BROADCAST_PAUSE_FILESCAN = "com.skyworth.broadcast.media_pause_filescan";
    /**
     * 重启文件扫描广播
     */
    public static final String BROADCAST_RESUME_FILESCAN = "com.skyworth.broadcast.media_resume_filescan";
    /**
     * 语音输入文本
     */
    public static final String BROADCAST_UPDATE_TEXT = "com.skyworth.controlservice.updatetext";
    /**
     * 纹理加载结束广播
     */
    public static final String BROADCAST_UI_TEXTURESLOADED = "com.skyworth.broadcast.ui.texturesloaded";
    /**
     * HOME主页启动完毕的广播
     */
    public static final String BROADCAST_HOME_STARTED = "com.skyworth.broadcast.homestarted";

    /**
     * 播放资源状态请求
     */
    public static final String BROADCAST_PLAYER_RESOURCE_REQUEST_STATE = "com.skyworth.broadcast.resource.player.requestState";
    /**
     * 播放资源释放请求
     */
    public static final String BROADCAST_PLAYER_RESOURCE_REQUEST_RELEASE = "com.skyworth.broadcast.resource.player.requestRelease";
    /**
     * 资源释放回复
     */
    public static final String BROADCAST_PLAYER_RESOURCE_ACK_RELEASING = "com.skyworth.broadcast.resource.player.ackReleasing";
    /**
     * 播放资源已释放
     */
    public static final String BROADCAST_PLAYER_RESOURCE_FREE = "com.skyworth.broadcast.resource.player.free";
    /**
     * 播放资源使用中
     */
    public static final String BROADCAST_PLAYER_RESOURCE_INUSE = "com.skyworth.broadcast.resource.player.inuse";

    /**
     * 恢复音频播放
     */
    public static final String BROADCAST_AUDIO_RESOURCE_RESUME_INFO = "com.skyworth.broadcast.resource.audio.resumeInfo";
    /**
     * 暂停原来的音频播放
     */
    public static final String BROADCAST_AUDIO_RESOURCE_REQUEST_RELEASE = "com.skyworth.broadcast.resource.audioPlay.resume";

    public static final String SKY_MOVIE_CHANGED_NOTIFY = "com.skyworth.play.moivechanged";

    /**
     * 输入法程序发过来的广播，参数为："FLAG_KEYBOARD_MODE"，true或者false
     */
    public static final String BROADCAST_START_INPUT_METHOD = "com.skyworthnj.startinputmethod";

    /**
     * 电视助手
     */
    public static final String BROADCAST_TV_ASSISTANT = "com.skyworth.tv.assistant";

    /**
     * 电视助手webView
     */
    public static final String BROADCAST_TV_ASSISTANT_WEBVIEW = "com.skyworth.tv.assistant.web";

    /**
     * DTV-退出单独听
     */
    public static final String BROADCAST_EXIT_AUDIO_ALONE = "com.skyworth.exit.audioalone";
    /**
     * DTV-搜台完成
     */
    public static final String BROADCAST_DTV_CHANNEL_SEARCHED = "com.skyworth.dtv.channelsearched";
    /**
     * DTV-换台发的广播
     */
    public static final String BROADCAST_DTV_CHANGE_CHANNEL = "com.skyworth.dtv.changechannel";
    /**
     * DTV-退出dtv广播
     */
    public static final String BROADCAST_DTV_EXIT = "com.skyworth.broadcast.dtv.release.ok";
    /**
     * UICommonService连接
     */
    public static final String SKY_UICOMMONSERCIE_CONNECT = "com.skyworth.broadcast.uicommonservice.connect";
    /**
     * UIContentViewService连接
     */
    public static final String SKY_UICONTENTVIEWSERCIE_CONNECT = "com.skyworth.broadcast.uicontentview.connect";

    /**
     * UITvViewService连接
     */
    public static final String SKY_UITVVIEWSERVICE_CONNECT = "com.skyworth.broadcast.uitvviewservice.connect";
    /**
     * UIOtherViewService连接
     */
    public static final String SKY_UIOTHERVIEWSERCIE_CONNECT = "com.skyworth.broadcast.uiotherviewservice.connect";


    public static final String BROADCAST_FACTORY_SDCARD_INITOK = "com.skyworth.sdcard.initok";
    public static final String BROADCAST_FACTORY_SDCARD_INITNG = "com.skyworth.sdcard.initng";
    public static final String BROADCAST_CLEAR_FOOTPRINT = "com.skyworth.clear.footprint";
    /**
     * 主页状态
     */
    public static final String BROADCAST_HOME_STATUS = "com.android.sky.homestatus";

    public static final String BROADCAST_ACTION_RESUME = "android.intent.action.RESUME";
    public static final String BROADCAST_ACTION_SUSPEND = "android.intent.action.SUSPEND";
    public static final String BROADCAST_ACTION_SET_3D_MODE = "skyworth.acton.set.3d.mode";
    public static final String BROADCAST_ACTION_A2DP_CONNECTION_STATE_CHANGED = "android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED";

    /**
     * 设置本地屏保
     */
    public static final String BROADCAST_ACTION_SET_LOCAL_SCREEN_SAVER = "skyworth.action.set.location.screensaver";
    /**
     * 清空当前显示的float ui
     */
    public static final String BROADCAST_CLEAR_FLOAT_UI = "com.skyworth.broadcast.clear.float.ui";
    /**
     * 蓝牙遥控器按键，检查是否需要配对
     */
    public static final String BROADCAST_CHECK_SKYRC_BLUETOOTH_PAIR = "com.skyworth.skyrc.bluetooth";
    /**
     * 新一键配对底层广播
     */
    public static final String BROADCAST_CHECK_SKYRC_BLUETOOTH_PAIR_PRO = "com.skyworth.skyrc.bluetooth.pro";
    /**
     * 新一键配对底层取消配对广播(HID广播)
     */
    public static final String BROADCAST_CHECK_SKYRC_BLUETOOTH_UNPAIR_PRO = "com.skyworth.skyrc.bluetooth.unpaire";
    /**
     * LGD再次确认， 待机后OLED数据恢复时间。接收这样一个广播，接到广播后show一个UI
     */
    public static final String BROADCAST_SHOW_FOUR_HOURS_UI = "com.skyworth.broadcast.show.fourhours.ui";
    /**
     * 快速待机，STR待机——待机下去
     */
    public static final String BROADCAST_QUICK_STANDBY_SUSPEND = "com.skyworth.broadcast.standby.quick.suspend";

    /**
     * 快速待机，STR待机——待机起来
     */
    public static final String BROADCAST_QUICK_STANDBY_RESUME = "com.skyworth.broadcast.standby.quick.resume";

    /**
     * 恢复出厂设置开始广播
     */
    public static final String BROADCAST_RECOVERY_START = "com.skyworth.broadcast.recovery.start";

    /**
     * 恢复出厂设置结束广播
     */
    public static final String BROADCAST_RECOVERY_FINISH = "com.skyworth.broadcast.recovery.finish";

    /**
     * 工厂单键/工厂菜单恢复出厂完结束广播
     */
    public static final String BROADCAST_FACTORY_RECOVERY_FINISH = "com.skyworth.broadcast.factory.recovery.finish";

    /**
     * 首页切换广播
     */
    public static final String BROADCAST_HOMEPAGE_SWITCH = "com.skyworth.broadcast.homepage.switch";

    /**
     * 关屏待机-关屏下去
     */
    public static final String BROADCAST_SCREEN_SOUND_OFF = "com.skyworth.broadcast.screensound.close";

    /**
     * 关屏待机-开启屏幕
     */
    public static final String BROADCAST_SCREEN_SOUND_ON = "com.skyworth.broadcast.screensound.open";

    /**
     * 屏幕打开关闭-打开
     */
    public static final String BROADCAST_SCREEN_ON = "com.skyworth.broadcast.screen.open";

    /**
     * 屏幕打开关闭-关闭
     */
    public static final String BROADCAST_SCREEN_OFF = "com.skyworth.broadcast.screen.close";

    /**
     * 声音打开关闭-打开
     */
    public static final String BROADCAST_SOUND_ON = "com.skyworth.broadcast.sound.open";

    /**
     * 声音打开关闭- 关闭
     */
    public static final String BROADCAST_SOUND_OFF = "com.skyworth.broadcast.sound.close";

    /**
     * 模式切换广播，eg：切换到游戏大厅等
     */
    public static final String BROADCAST_SWITCH_MODE = "com.skyworth.broadcast.switchmode";

    /**
     * 退出关屏待机
     */
    public static final String BROADCAST_QUIT_WAKE_LOCK = "com.skyworth.broadcast.wakelock.quit";

    /**
     * 特殊设置项的值发生变化广播
     */
    public static final String BROADCAST_NOTIFY_SETTING_DATA_CHANGED = "com.skyworth.broadcast.notify.setting_data_changed";

    /**
     * SystemSerivce准备好响应hotkey广播
     */
    public static final String BROADCAST_PREPARE_ACCEPT_HOTKEY = "com.skyworth.broadcast.prepare_accept_hotkey";
    /**
     * 屏保启动时发出的广播
     */
    public static final String BROADCAST_START_SCREENSAVER = "com.tianci.ad.start_screensaver";
    /**
     * 屏保退出时的广播
     */
    public static final String BROADCAST_EXIT_SCREENSAVER = "com.skyworth.screensaverexit";
    /**
     * 智能摄像头 调节时的广播
     */
    public static final String BROADCAST_SMART_CAMERA_CONTROL = "com.coocaa.smart_camera_control";
    /**
     *
     */
    public static final String BROADCAST_12_HOUR_CLOCK = "com.skyworth.12_hour_clock";

    public static void registerBroadcast(Context context, String broadCast, BroadcastReceiver receiver) {
        IntentFilter filter = new IntentFilter(broadCast);
        context.registerReceiver(receiver, filter);
    }
}
