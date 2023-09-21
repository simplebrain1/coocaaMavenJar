package com.skyworth.framework.event;

import android.content.Context;
import android.content.Intent;

/**
 * 广播键值定义
 *
 * @since 1
 */
public class BroadcastKey {
    /**
     * 待机 {@value}
     */
    public static final int KEYCODE_POWER = 0x1A;
    /**
     * 频道+ {@value}
     */
    public static final int KEYCODE_CHANNEL_UP = 0xA6;
    /**
     * 频道- {@value}
     */
    public static final int KEYCODE_CHANNEL_DOWN = 0xA7;
    /**
     * 音量+ {@value}
     */
    public static final int KEYCODE_VOLUME_ADD = 0x18;
    /**
     * 音量- {@value}
     */
    public static final int KEYCODE_VOLUME_REDUCE = 0x19;
    /**
     * 静音 {@value}
     */
    public static final int KEYCODE_MUTE = 0xA4;
    /**
     * 信号源 {@value}
     */
    public static final int KEYCODE_SIGNAL = 0xB2;
    /**
     * 语音 {@value}
     */
    public static final int KEYCODE_VOICE = 0x2E8;
    /**
     * 长按确定 {@value}
     */
    public static final int KEYCODE_LONG_ENTER = 0x2FB;
    /**
     * 菜单 {@value}
     */
    public static final int KEYCODE_MENU = 0x52;
    /**
     * 长按菜单 {@value}
     */
    public static final int KEYCODE_LONG_MENU = 0x53;
    /**
     * 酷开系统 {@value}
     */
    public static final int KEYCODE_TIANCI = 0x3;
    /**
     * 主页 {@value}
     */
    public static final int KEYCODE_HOME = 0x3;
    /**
     * 长按主页键{@value}
     */
    public static final int KEYCODE_LONG_HOME = 0x2f9;
    /**
     * 长按返回 {@value}
     */
    public static final int KEYCODE_LONG_BACK = 0x2fa;
    /**
     * 首页 {@value}
     */
    public static final int KEYCODE_HOME_PAGE = 0x2E9;
    /**
     * 云分享 {@value}
     */
    public static final int KEYCODE_SHARE = 0x2E9;

    /**
     * 红\电视广播 {@value}
     */
    public static final int KEYCODE_RED_KEY_TV_BROADCASTING = 0xB7;
    /**
     * 绿\声道 {@value}
     */
    public static final int KEYCODE_GREEN_KEY_SOUND_CHANNEL = 0xB8;
    /**
     * 黄\信息 {@value}
     */
    public static final int KEYCODE_YELLOW_KEY_MESSAGEE = 0xB9;
    /**
     * 蓝\点播 {@value}
     */
    public static final int KEYCODE_BLUE_KEY_VOD = 0xBA;
    /**
     * 图像模式 {@value}
     */
    public static final int KEYCODE_IMAGE_MODE = 0x2EB;
    /**
     * 声音模式 {@value}
     */
    public static final int KEYCODE_SOUND_MODE = 0x2EC;
    /**
     * 显示模式 {@value}
     */
    public static final int KEYCODE_DISPLAY_MODE = 0x2ED;
    /**
     * 数位键 {@value}
     */
    public static final int KEYCODE_INPUT_NUMBER = 0x4E;
    /**
     * 屏显 {@value}
     */
    public static final int KEYCODE_SCREEN_DISPLAY = 0xA5;

    /**
     * 靠近键控板感应 {@value}
     */
    public static final int KEYCODE_SENSE_ALL = 0x319;
    /**
     * 靠近键控板菜单键感应 {@value}
     */
    public static final int KEYCODE_SENSE_MENU = 0x31A;
    /**
     * 靠近键控板确定键感应 {@value}
     */
    public static final int KEYCODE_SENSE_CONFIRM = 0x31B;
    /**
     * 靠近键控板返回键感应 {@value}
     */
    public static final int KEYCODE_SENSE_BACK = 0x31C;
    /**
     * 靠近键控板音量加感应 {@value}
     */
    public static final int KEYCODE_SENSE_VOLUME_ADD = 0x31D;
    /**
     * 靠近键控板音量减感应 {@value}
     */
    public static final int KEYCODE_SENSE_VOLUME_REDUCE = 0x31E;
    /**
     * 靠近键控板频道加感应 {@value}
     */
    public static final int KEYCODE_SENSE_CHANNEL_ADD = 0x31F;
    /**
     * 靠近键控板频道减感应 {@value}
     */
    public static final int KEYCODE_SENSE_CHANNEL_REDUCE = 0x320;
    /**
     * 离开键控板感应 {@value}
     */
    public static final int KEYCODE_SENSE_LEAVE = 0x321;

    /**
     * 童锁 {@value}
     */
    public static final int KEYCODE_CHILD_LOCK = 0x322;

    /**
     * 工厂调试 {@value}
     */
    public static final int KEYCODE_FACTORY_FACTORY_MODE = 0x2BC;
    /**
     * 调测恢复 {@value}
     */
    public static final int KEYCODE_FACTORY_RESET = 0x2BD;
    /**
     * 退出工厂模式 {@value}
     */
    public static final int KEYCODE_FACTORY_OUTSET = 0x2C0;
    /**
     * 总线键 {@value}
     */
    public static final int KEYCODE_FACTORY_BUS_OFF = 0x2C1;
    /**
     * 老化模式 {@value}
     */
    public static final int KEYCODE_FACTORY_AGING_MODE = 0x2C2;
    /**
     * 电子码 {@value}
     */
    public static final int KEYCODE_FACTORY_BARCODE = 0x2D6;
    /**
     * 通道加 {@value}
     */
    public static final int KEYCODE_FACTORY_SOURCE_ADD = 0x2BE;
    /**
     * 通道减 {@value}
     */
    public static final int KEYCODE_FACTORY_SOURCE_REDUCE = 0x2BF;
    /**
     * Uplayer {@value}
     */
    public static final int KEYCODE_FACTORY_UPLAYER = 0x2D0;
    /**
     * 网络调测 {@value}
     */
    public static final int KEYCODE_FACTORY_NET_TEST = 0x2D1;
    /**
     * 屏变调测 {@value}
     */
    public static final int KEYCODE_FACTORY_DREAM_PANEL = 0x2D2;
    /**
     * AV1 {@value}
     */
    public static final int KEYCODE_FACTORY_SOURCE_AV1 = 0x2C4;
    /**
     * AV2 {@value}
     */
    public static final int KEYCODE_FACTORY_SOURCE_AV2 = 0x2C6;
    /**
     * AV3 {@value}
     */
    public static final int KEYCODE_FACTORY_SOURCE_AV3 = 0x2C7;
    /**
     * DVBC {@value}
     */
    public static final int KEYCODE_FACTORY_SOURCE_DVBC = 0x2C8;
    /**
     * 分量1 {@value}
     */
    public static final int KEYCODE_FACTORY_SOURCE_YUV = 0x2C9;
    /**
     * 分量2 {@value}
     */
    public static final int KEYCODE_FACTORY_SOURCE_YUV2 = 0x2CA;
    /**
     * VGA {@value}
     */
    public static final int KEYCODE_FACTORY_SOURCE_VGA = 0x2CB;
    /**
     * HDMI {@value}
     */
    public static final int KEYCODE_FACTORY_SOURCE_HDMI1 = 0x2CC;
    /**
     * HDMI2 {@value}
     */
    public static final int KEYCODE_FACTORY_SOURCE_HDMI2 = 0x2CD;
    /**
     * HDMI3 {@value}
     */
    public static final int KEYCODE_FACTORY_SOURCE_HDMI3 = 0x2CE;
    /**
     * ATV {@value}
     */
    public static final int KEYCODE_FACTORY_SOURCE_ATV = 0x2CF;

    /**
     * 向上搜台 {@value}
     */
    public static final int KEYCODE_FACTORY_SEARCH_UP = 0x2D7;
    /**
     * 向下搜台 {@value}
     */
    public static final int KEYCODE_FACTORY_SEARCH_DOWN = 0x2D8;
    /**
     * 一键启动蓝牙 {@value}
     */
    public static final int KEYCODE_FACTORY_BLUETOOTH = 0x30D;
    /**
     * 抓取LOG信息 {@value}
     */
    public static final int KEYCODE_FACTORY_LOGCAT = 0x2D9;
    /**
     * HDMI3 {@value}
     */
    public static final int KEYCODE_FACTORY_SOURCE_DTMB = 0x2DA;
    /**
     * 普通遥控器长按确定 {@value}
     */
    public static final int KEYCODE_COMMON_LONG_ENTER = 0x2E8;
    /**
     * 松开语音按键 {@value}
     */
    public static final int KEYCODE_VOICE_CANCEL = 0x2F6;
    /**
     * 我的足迹 {@value}
     */
    public static final int KEYCODE_MY_HISTORY = 0x302;

    /**
     * 本地媒体 {@value}
     */
    public static final int KEYCODE_LOCAL_MEDIA = 0x2FF;
    /**
     * 广告时间 {@value}
     */
    public static final int KEYCODE_AD_TIME = 0x2F4;
    /**
     * 设置 {@value}
     */
    public static final int KEYCODE_SETTING = 0x301;
    /**
     * 搜索 {@value}
     */
    public static final int KEYCODE_SEARCH = 0x300;
    /**
     * 耳机插入 {@value}
     */
    public static final int KEYCODE_HEADPHONE_INSERT = 0x89;
    /**
     * 耳机拔出 {@value}
     */
    public static final int KEYCODE_HEADPHONE_PULLOUT = 0x8A;

    /**
     * soundBar {@value}
     */
    public static final int KEYCODE_SOUNDBAR_MODE = 0x303;
    /**
     * tvMode {@value}
     */
    public static final int KEYCODE_TV_MODE = 0x304;
    /**
     * 对码 {@value}
     */
    public static final int KEYCODE_CHECK_CODE = 0x8D;
    /**
     * 低电量提示 {@value}
     */
    public static final int KEYCODE_LOW_POWER = 0x8C;
    /**
     * 机顶盒按键 {@value}
     */
    public static final int KEYCODE_STB = 0x30C;
    /**
     * COOCAATV {@value}
     */
    public static final int KEYCODE_COOCAA_TV = 0x2F8;
    /**
     * 短码飞梭顺时针 {@value}
     */
    public static final int KEYCODE_SHORT_SHUTTLE_RIGHT = 765;
    /**
     * 短码飞梭逆时针 {@value}
     */
    public static final int KEYCODE_SHORT_SHUTTLE_LEFT = 764;
    /**
     * 导视 {@value}
     */
    public static final int KEYCODE_DTV_GUIDE_PREVIOUS = 229;  // 早期机器用的
    public static final int KEYCODE_DTV_GUIDE = 746;

    /**
     * 交替 {@value}
     */
    public static final int KEYCODE_ALTERNATE_PREVIOUS = 226; // 早期机器用的
    public static final int KEYCODE_ALTERNATE = 743;

    /**
     * 重低音开关 {@value}
     */
    public static final int KEYCODE_BASS_SWITCH = 0x310;

    public static final int KEYCODE_DEL = 0x43;
    public static final int KEYCODE_F1 = 131;
    public static final int KEYCODE_F2 = 132;
    public static final int KEYCODE_F3 = 133;
    public static final int KEYCODE_F4 = 134;
    public static final int KEYCODE_F5 = 135;
    public static final int KEYCODE_F6 = 136;
    public static final int KEYCODE_F7 = 137;
    public static final int KEYCODE_F8 = 138;
    public static final int KEYCODE_F9 = 139;
    public static final int KEYCODE_F10 = 140;
    public static final int KEYCODE_F11 = 141;
    public static final int KEYCODE_F12 = 142;
    public static final int KEYCODE_PAGE_UP = 92;
    public static final int KEYCODE_PAGE_DOWN = 93;
    public static final int KEYCODE_META_LEFT = 117;
    public static final int KEYCODE_META_RIGHT = 118;

    /**
     * soundbar蓝牙音量开关按钮 {@value}
     */
    public static final int KEYCODE_SOUNDBAR_SWITCH = 788;

    /**
     * 音轨按键 {@value}
     */
    public static final int KEYCODE_AUDIO_SETTING = 803;

    /**
     * 字幕按键 {@value}
     */
    public static final int KEYCODE_SUBTITLE_SETTING = 804;

    /**
     * 关屏按键，假待机 {@value}
     */
    public static final int KEYCODE_POWER_LONG = 759;
    /**
     * 老人待机键 {@value}
     */
    public static final int KEYCODE_OLDER_POWER = 807;
    /**
     * 儿童待机键 {@value}
     */
    public static final int KEYCODE_CHILD_POWER = 808;
    /**
     * "酷"按键 {@value}
     */
    public static final int KEYCODE_COOL = 813;
    /**
     * 儿童酷键 {@value}
     */
    public static final int KEYCODE_CHILD_COOL = 806;
    /**
     * 老人酷键 {@value}
     */
    public static final int KEYCODE_OLDER_COOL = 805;
    /**
     * 老人首页 {@value}
     */
    public static final int KEYCODE_OLDER_HOME = 809;
    /**
     * 儿童首页 {@value}
     */
    public static final int KEYCODE_CHILD_HOME = 810;
    /**
     * 老人遥控器信号源 {@value}
     */
    public static final int KEYCODE_OLD_SOURCE = 811;

    /**
     * 菜单长按 {@value}
     */
    public static final int KEYCODE_MENU_LONG = 814;

    /**
     * 找回遥控器FindMe按键 {@value}
     */
    public static final int KEYCODE_FIND_ME = 818;
    /**
     * 长按找回遥控器FindMe按键 {@value}
     */
    public static final int KEYCODE_FIND_ME_LONG = 819;
    /**
     * FindMe开 {@value}
     */
    public static final int KEYCODE_FIND_ME_ON = 821;
    /**
     * FindMe关 {@value}
     */
    public static final int KEYCODE_FIND_ME_OFF = 822;

    /**
     * 长按Soundbar按键 {@value}
     */
    public static final int KEYCODE_SOUNDBAR_MODE_LONG = 820;
    /**
     * 遥控器电量低，20% {@value}
     */
    public static final int KEYCODE_RC_LOW_POWER = 817;
    /**
     * 遥控器电量低，5% {@value}
     */
    public static final int KEYCODE_RC_LOW_POWER_BELOW_FIVE = 922;
    /**
     * 蓝牙遥控器已配对未连接 {@value}
     */
    public static final int KEYCODE_RC_PAIRED_NOCONNECT = 858;
    /**
     * 新一键配对 {@value}
     */
    public static final int KEYCODE_BT_REQUEST_PAIRING = 853;
    /**
     * 新一键配对底层取消配对键值(HID) {@value}
     */
    public static final int KEYCODE_BT_CANCEL_PAIRING = 925;


    /**
     * 卖场遥控器-本机信息 {@value}
     */
    public static final int KEYCODE_SYSTEM_INFO = 825;
    /**
     * 卖场遥控器-网络浏览/网络设置 {@value}
     */
    public static final int KEYCODE_NETWORK = 826;
    /**
     * 卖场遥控器-我的应用 {@value}
     */
    public static final int KEYCODE_MYAPP = 827;
    /**
     * 卖场遥控器-恢复出厂设置 {@value}
     */
    public static final int KEYCODE_RECOVERY = 828;
    /**
     * 卖场遥控器-系统码修改 {@value}
     */
    public static final int KEYCODE_CUSTOMER_KEY_CHANGE = 829;

    /**
     * 播放关机广告再待机 {@value}
     */
    public static final int KEYCODE_POWER_AD = 848;

    /**
     * 一键切换至DTV通道快捷键 {@value}
     */
    public static final int KEYCODE_DTV_SOURCE_SWITCH = 849;
    /**
     * 恢复TV通道预置频道列表 {@value}
     */
    public static final int KEYCODE_FACTORY_GUIDE_TV = 906;


    /**
     * 弹出数字输入菜单 {@value}
     */
    public static final int KEYCODE_0_9 = 910;

    /**
     * 手柄start按键 {@value}
     */
    public static final int KEYCODE_BUTTON_START = 108;

    /**
     * 一键启动之红键 {@value}
     */
    public static final int KEYCODE_RED_ONEKEY_ACTION = 850;
    /**
     * 一键启动之红键长按 {@value}
     */
    public static final int KEYCODE_RED_ONEKEY_ACTION_LONG = 861;
    /**
     * 一键启动之绿键 {@value}
     */
    public static final int KEYCODE_GREEN_ONEKEY_ACTION = 851;
    /**
     * 一键启动之蓝键 {@value}
     */
    public static final int KEYCODE_BLUE_ONEKEY_ACTION = 852;

    /**
     * Q7D快捷键 {@value}
     */
    public static final int KEYCODE_SHORTCUT_KEY_ACTION = 986;

    /**
     * Q7D快捷键长按{@value}
     */
    public static final int KEYCODE_SHORTCUT_KEY_ACTION_LONG = 987;

    /**
     * Q7D酷开模式切换键{@value}
     */
    public static final int KEYCODE_PATTERN_ACTION = 985;

    /**
     * dongle主页键
     */
    public static final int KEYCODE_DONGLE_HOME = 962;

    /**
     * 酷健康快捷键 {@value}
     */
    public static final int KEYCODE_COOL_HEALTH = 990;

    /**
     * 熊猫视频1通道键 {@value}
     */
    public static final int KEYCODE_PANDA_FACTORY_AV1 = 2001;
    /**
     * 熊猫视频2通道键 {@value}
     */
    public static final int KEYCODE_PANDA_FACTORY_AV2 = 2002;
    /**
     * 熊猫视频3通道键 {@value}
     */
    public static final int KEYCODE_PANDA_FACTORY_AV3 = 2003;
    /**
     * 熊猫分量1通道键 {@value}
     */
    public static final int KEYCODE_PANDA_FACTORY_YUV1 = 2004;
    /**
     * 熊猫分量2通道键 {@value}
     */
    public static final int KEYCODE_PANDA_FACTORY_YUV2 = 2005;
    /**
     * 熊猫VGA通道键 {@value}
     */
    public static final int KEYCODE_PANDA_FACTORY_VGA = 2006;
    /**
     * 熊猫HDMI1通道键 {@value}
     */
    public static final int KEYCODE_PANDA_FACTORY_HDMI1 = 2007;
    /**
     * 熊猫HDMI2通道键 {@value}
     */
    public static final int KEYCODE_PANDA_FACTORY_HDMI2 = 2008;
    /**
     * 熊猫HDMI3通道键 {@value}
     */
    public static final int KEYCODE_PANDA_FACTORY_HDMI3 = 2009;
    /**
     * 熊猫ATV通道键 {@value}
     */
    public static final int KEYCODE_PANDA_FACTORY_ATV = 2010;
    /**
     * 熊猫地面数字通道键 {@value}
     */
    public static final int KEYCODE_PANDA_FACTORY_DTMB = 2012;
    /**
     * 熊猫右侧EPG按键键 {@value}
     */
    public static final int KEYCODE_PANDA_TV_EPG = 278;


    /**
     * 支持快速搜台 {@value}
     */
    public static final int KEYCODE_CHANNEL_SEARCH = 787;
    /**
     * TV 按键，从其他应用一键切换到记忆的TV通道 {@value}
     */
    public static final int KEYCODE_TV = 865;
    /**
     * 频道列表 {@value}
     */
    public static final int KEYCODE_CHANNEL_LIST = 866;
    /**
     * 飞利浦酒店定制机帮助键值 {@value}
     */
    public static final int KEYCODE_PHILIPS_HOTEL_HELP = 867;

    /**
     * 一键启动设置WIFI界面按键 {@value}
     */
    public static final int KEYCODE_FACTORY_WLAN = 298;
    /**
     * 一键退出开机引导按键 {@value}
     */
    public static final int KEYCODE_SKY_TV_FACTORY_EXIT_GUIDE = 302;

    /**
     * 单项键键控板短按 {@value}
     */
    public static final int KEYCODE_SINGLE_KEYPAD_SHORT = 878;
    /**
     * 单项键键控板长按 {@value}
     */
    public static final int KEYCODE_SINGLE_KEYPAD_LONG = 879;
    /**
     * 五项键键控板长按 {@value}
     */
    public static final int KEYCODE_FIVE_KEYPAD_CENTER_LONG = 880;
    /**
     * 蓝牙遥控器耳机插入 静音 {@value}
     */
    public static final int KEYCODE_BT_REMOTE_EARPHONE_IN = 859;
    /**
     * 蓝牙遥控器耳机插入 解除静音 {@value}
     */
    public static final int KEYCODE_BT_REMOTE_EARPHONE_OUT = 860;
    /**
     * 蓝牙遥控器插入耳机 音量太大保护 {@value}
     */
    public static final int KEYCODE_HEARING_CONSERVATION = 881;
    /**
     * 音源键或者Soundbar上的音源键 {@value}
     */
    public static final int KEYCODE_SOUNDBAR_AUDIO_INPUT = 884;
    /**
     * 蓝牙Dongle 连接成功 {@value}
     * 注意：蓝牙Dongle跟本机的蓝牙模块没有联系
     */
    public static final int KEYCODE_BTDONGLE_CONNECTED = 891;
    /**
     * 蓝牙Dongle 断开连接 {@value}
     * 注意：蓝牙Dongle跟本机的蓝牙模块没有联系
     */
    public static final int KEYCODE_BTDONGLE_DISCONNECTED = 892;
    /**
     * 退出 {@value}
     */
    public static final int KEYCODE_EXIT = 839;
    /**
     * 快捷通道按键HDMI1 {@value}
     */
    public static final int KEYCODE_HDMI1 = 935;
    /**
     * 快捷通道按键HDMI2 {@value}
     */
    public static final int KEYCODE_HDMI2 = 936;
    /**
     * 快捷通道按键HDMI3 {@value}
     */
    public static final int KEYCODE_HDMI3 = 937;
    /**
     * 快捷通道按键日本地上波 {@value}
     */
    public static final int KEYCODE_ISDBT = 938;
    /**
     * 快捷通道按键日本BS {@value}
     */
    public static final int KEYCODE_BS = 939;
    /**
     * 快捷通道按键日本CS {@value}
     */
    public static final int KEYCODE_CS = 940;
    /**
     * 快捷通道按键日本BS4K {@value}
     */
    public static final int KEYCODE_BS4K = 941;
    /**
     * 快捷通道按键日本CS4K {@value}
     */
    public static final int KEYCODE_CS4K = 942;
    /**
     * 睡眠 {@value}
     */
    public static final int KEYCODE_SLEEP = 934;
    /**
     * 冠捷需求 AOC 电视 ,广播 {@value}
     */
    public static final int KEYCODE_AOC_ATV_DTV = 894;
    /**
     * 冠捷需求 AOC回看，普通按键 {@value}
     */
    public static final int KEYCODE_TV_REVIEW = 895;

    /**
     * MEte机  HIFI键
     */
    public static final int KEYCODE_SOUNDBAR_HIFI = 885;
    /**
     * MEte机，BT键
     */
    public static final int KEYCODE_BT = 886;
    /**
     * MEte机， Bass上键
     */
    public static final int KEYCODE_HIFI_BASS_UP = 887;
    /**
     * MEte机，bass下键
     */
    public static final int KEYCODE_HIFI_BASS_DOWN = 888;
    /**
     * MEte机，treble 上键
     */
    public static final int KEYCODE_HIFI_TREBLE_UP = 889;
    /**
     * MEte机，treble 下键
     */
    public static final int KEYCODE_HIFI_TREBLE_DOWN = 890;

    /**
     *  启动白板
     */
    public static final int KEYCODE_START_WHITE_BOARD = 964;

    /**
     * 巴金麦克风菜单长按
     */
    public static final int KEYCODE_BAJIN_MENU_LONG_PRESSED = 969;

    /**
     * 显示器机型背部的自定义按键
     */
    public static final int KEYCODE_DISPLAYER_DEFINE_BACK_KEY = 970;

    /**
     * 显示器第一次进入云游戏，u盘，投屏发送的按键
     */
    public static final int KEYCODE_DISPLAYER_SHOW_PAD_KEY = 972;

    /**
     * 显示器系统切换按键
     */
    public static final int KEYCODE_SYSTEM_SWITCH = 973;

    /**
     * 专业显示器智控器:按下
     */
    public static final int KEYCODE_MAXSCEND_BTN_PRESS = 974;
    /**
     * 专业显示器智控器:右旋
     */
    public static final int KEYCODE_MAXSCEND_BTN_ROTATE_CLOCKWISE = 975;
    /**
     * 专业显示器智控器:左旋
     */
    public static final int KEYCODE_MAXSCEND_BTN_ROTATE_ANTICLOCKWISE = 976;
    /**
     * 专业显示器智控器:按下右旋
     */
    public static final int KEYCODE_MAXSCEND_BTN_PRESS_ROTATE_CLOCKWISE = 977;
    /**
     * 专业显示器智控器:按下左旋
     */
    public static final int KEYCODE_MAXSCEND_BTN_PRESS_ROTATE_ANTICLOCKWISE = 978;
    /**
     * 专业显示器智控器:双击
     */
    public static final int KEYCODE_MAXSCEND_BTN_DOUBLE_PRESS = 979;
    /**
     * 专业显示器智控器:长按
     */
    public static final int KEYCODE_MAXSCEND_BTN_LONG_PRESS = 980; /**
     * 发送广播按键
     *
     * @param context 上下文
     * @param keycode 广播键值
     * @since 1
     */
    public static void sendHotKey(Context context, final int keycode) {
        Intent intent = new Intent(BroadcastAction.BROADCAST_SEND_HOTKEYS);
        intent.putExtra("specialKey", keycode);
        context.sendBroadcast(intent);
    }
}
