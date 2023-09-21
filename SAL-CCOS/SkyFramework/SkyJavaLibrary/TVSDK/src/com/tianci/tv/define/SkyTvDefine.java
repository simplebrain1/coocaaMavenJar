package com.tianci.tv.define;


public class SkyTvDefine
{
    public static final int SET_WINDOW_SIZE_SUCCESS = 0x0000;
    public static final int SET_WINDOW_SIZE_EXCEPTION = 0xFFFF;
    // Switch Channel Status
    public static final int SWITCH_CHANNEL_SUCCESS = 0x0000;
    public static final int SWITCH_CHANNEL_PARAM_ERROR = 0x0001;
    public static final int SWITCH_CHANNEL_NO_CHANNEL = 0x0002;
    public static final int SWITCH_CHANNEL_NO_SCAN = 0x0003;
    public static final int SWITCH_CHANNEL_BUSY = 0x0004;
    public static final int SWITCH_CHANNEL_EXCEPTION = 0xFFFF;

    // Signal State
    public static final int STATE_SIGNALED = 0;
    public static final int STATE_NOSIGNAL = 1;
    public static final int STATE_UNSUPPORT = 2;
    public static final int STATE_UNSTABLE = 3;

    // Standby Reason
    public static final int STANDBY_REASON_SCREEN_SAVER = 0x01;

    // Hold Source Flag
    // 最高8为给TVSDK本身使用，低24位用于往中间件等底层传递
    public static final int HOLD_SOURCE_FLAG_TVSDK_MASK = 0x00FFFFFF;
    public static final int HOLD_SOURCE_FLAG_DONT_SAVE = 0x01000000;

    // sendMessage Defines
    // what
    public static final int MESSAGE_WHAT_PVR_STATUS = 0x01;
    public static final int MESSAGE_WHAT_PVR_INFO = 0x02;
    public static final int MESSAGE_WHAT_EXTERNAL_SOURCE = 0x03;
    // PVR arg1
    public static final int MESSAGE_PVR_ARG_START = 0x01;
    public static final int MESSAGE_PVR_ARG_END = 0x02;

    //=====================================================================================================//
    public enum COLORSYSTEM
    {
        PAL, NTSC, PAL_BGHI, NTSC_M, SECAM, NTSC_44, PAL_M, PAL_N, PAL_60, NOTSTANDARD, AUTO,
    }

    public enum SOUNDSYSTEM
    {
        I, DK, BG, M, L, AUTO,
    }

    public enum ATVAUDIONICAM
    {
        ATV_AUDIOMODE_NICAM_MONO,
        ATV_AUDIOMODE_NICAM_STEREO,
        ATV_AUDIOMODE_NICAM_DUAL_A,
        ATV_AUDIOMODE_NICAM_DUAL_B,
        ATV_AUDIOMODE_NICAM_DUAL_AB,
    }
    
    public enum HDMIPCMODE
    {
        HDMI_PCMODE_AUTO,
        HDMI_PCMODE_PC,
        HDMI_PCMODE_VIDEO
    }

    /**
     * <p>
     * Description:
     * </p>
     * <p>
     * 通道的信号状态
     * </p>
     * 
     * @ClassName SOURCE_SIGNAL_STATE
     * @author xiangge
     * @date 2013-11-30
     * @version V*.*.*
     */
    public enum SOURCE_SIGNAL_STATE
    {
        /**
         * Description:正常播放。不出无信号屏保，信号源菜单高亮。 弹出已经连接菜单。
         */
        PLAY,

        /**
         * Description:无信号，没有正常播放 。出屏保图片，显示  "无信号"文字。信号源菜单灰。不弹出已经连接菜单。
         */
        NOSIGNAL,

        /**
                  * Description:iplive特有的，没有有效的epg节目单
                  */
        NOEPG,

        // new Enum must be added to tail,in order to remain compliant with the sdk released to
        // third
        // party apk

        /**
         * Description:网络已经连接(如电视节目下)，不出无信号屏保。信号源菜单亮。不弹出已经连接菜单。
         */
        NET,
        /**
         * Description:没有网络(如电视节目下)，出屏保图片，文字由通道决定。信号源菜单灰。不弹出已经连接菜单
         */
        NONET,

        /**
         * Description:当前节目无法播放（如电视界面下的收费节目），出屏保图片，文字由通道决定。信号源菜单高亮。不弹出已经连接菜单
         */
        CURRENTCHANNEL_NO_PLAY,
        
//        /**
//         * Description:信号不稳定
//         */
//        UNSTABLE,
//        /**
//         * Description:信号格式不支持      
//        */
//        UNSUPPORT,
    }

    /**
     * 通过什么方式切导致TV内部switchsource的
     * @author wlang
     * @date 2015-01-23
     */
    public enum SwitchSourceType
    {
        BY_COMMON, // 未知的方式
        BY_KEY_SOURCE, // 信源点击
        BY_KEY_BACK, // 返回键退出应用
        BY_STANDBY, // 待机起来
    }
    
    
    /**
     * @ClassName AUDIOSTREAM_TYPE
     * @Description 音频格式
     * @author shen
     * @date 2015-11-23
     * @version TODO (write something)
     * enum order can not be modified,because must in compliance with  
     */
    public enum AUDIOSTREAM_TYPE
    {
        AV_AUD_STREAM_TYPE_UNKOWN,
        AV_AUD_STREAM_TYPE_MPEG1,
        AV_AUD_STREAM_TYPE_MPEG,
        AV_AUD_STREAM_TYPE_MP3,
        AV_AUD_STREAM_TYPE_AAC,
        AV_AUD_STREAM_TYPE_AAC_PLUS, 
        AV_AUD_STREAM_TYPE_AC3, 
        AV_AUD_STREAM_TYPE_AC3_PLUS,
        AV_AUD_STREAM_TYPE_DTS, 
        AV_AUD_STREAM_TYPE_LPCM_BLURAY, 
        AV_AUD_STREAM_TYPE_PCM,
        AV_AUD_STREAM_TYPE_WMA_STD,
        AV_AUD_STREAM_TYPE_WMA_PRO,
        AV_AUD_STREAM_TYPE_DRA,
        AV_AUD_STREAM_TYPE_COOK,
        AV_AUD_STREAM_TYPE_AVS, 
        AV_AUD_STREAM_TYPE_WAVE,
        AV_AUD_STREAM_TYPE_INVALID, 
        AV_AUD_STREAM_TYPE_DRA10, 
        AV_AUD_STREAM_TYPE_DRA11, 
        AV_AUD_STREAM_TYPE_DRA20,
        AV_AUD_STREAM_TYPE_DRA21,
        AV_AUD_STREAM_TYPE_DRA30,
        AV_AUD_STREAM_TYPE_DRA31,
        AV_AUD_STREAM_TYPE_DRA40,
        AV_AUD_STREAM_TYPE_DRA41, 
        AV_AUD_STREAM_TYPE_DRA50, 
        AV_AUD_STREAM_TYPE_DRA51,
        AV_AUD_STREAM_TYPE_AC4,
        AV_AUD_STREAM_TYPE_TRUEHD,
        AV_AUD_STREAM_TYPE_NOTSUPPORTED,
    	AV_AUD_STREAM_TYPE_DTS_EXPRESS,
        AV_AUD_STREAM_TYPE_DTS_HD_MASTER_AUDIO,
        AV_AUD_STREAM_TYPE_DTS_DIGITAL_SURROUND
    }
    
    public static final String SKY_SHARE_DATA_KEY_CALOCATION = "env_location";
    public static final String SKYLAUNCHER_STARTTYPE = "startType";
    public static final String SKYLAUNCHER_STARTTYPE_ONBOOT = "onBoot";
    public static final String SKYLAUNCHER_STARTTYPE_OTHER= "other";
    public static final String SKYLAUNCHER_STARTTYPE_ONSTR = "onStr";

    // Display Mode
    public static final int DISPLAY_MODE_16_9 = 0x00;
    public static final int DISPLAY_MODE_4_3 = 0x01;
    public static final int DISPLAY_MODE_AUTO = 0x02;
    public static final int DISPLAY_MODE_MOVIE = 0x03;
    public static final int DISPLAY_MODE_CAPTION = 0x04;
    public static final int DISPLAY_MODE_PANORAMA = 0x05;
    public static final int DISPLAY_MODE_PERSON = 0x06;
    public static final int DISPLAY_MODE_21_9 = 0x07;
    public static final int DISPLAY_MODE_FULL = 0x08;
    public static final int DISPLAY_MODE_INVALID = 0xFF;

    public static final int EDID_1P4_2K = 0x1402;
    public static final int EDID_1P4_4K = 0x1404;
    public static final int EDID_2P0_4K = 0x2004;
    public static final int EDID_2P1_4K = 0x2104;
    public static final int EDID_2P1_8K = 0x2108;

    public enum SOURCE_RENAME{
        SKY_CFG_TV_SOURCE_RENAME_1,
        SKY_CFG_TV_SOURCE_RENAME_2,
        SKY_CFG_TV_SOURCE_RENAME_3,
        SKY_CFG_TV_SOURCE_RENAME_4,
        SKY_CFG_TV_SOURCE_RENAME_5,
        SKY_CFG_TV_SOURCE_RENAME_6,
        SKY_CFG_TV_SOURCE_RENAME_7,
        SKY_CFG_TV_SOURCE_RENAME_8,
        SKY_CFG_TV_SOURCE_RENAME_9,
    }


    public static final String SUPPORT_VRR = "setting/support_vrr";

    public static final String ENABLE_VRR = "setting/enable_vrr";
    public static final String ENABLE_XDR = "setting/enable_xdr";
    public static final String ENABLE_EDID = "setting/enable_edid";
    public static final String ENABLE_DISPLAYMODE = "setting/enable_displaymode";
    public static final String ENABLE_VIDEO_SIGNAL = "setting/enable_video_signal";
    public static final String ENABLE_SOUND_CHANNEL = "setting/enable_sound_channel";
    public static final String ENABLE_SOUND_TRACK = "setting/enable_sound_track";
    public static final String ENABLE_SUBTITLE_MODE = "setting/enable_subtitle_mode";
    public static final String ENABLE_COLOUR_SYSTEM = "setting/enable_colour_system";
    public static final String ENABLE_SEARCH_CHANNEL = "setting/enable_search_channel";
    public static final String ENABLE_CHANNEL_DETAIL = "setting/enable_channel_detail";
    public static final String ENABLE_TV_MODE = "setting/enable_tv_mode";
    public static final String ENABLE_RESET_GUIDE = "setting/enable_reset_guide";
    public static final String ENABLE_LOGO_SWITCH = "setting/enable_logo_switch";
    public static final String ENABLE_GAME_BAR = "setting/enable_game_bar";

    public static final String GET_VRR = "setting/get_vrr";
    public static final String GET_XDR = "setting/get_xdr";
    public static final String GET_DISPLAYMODE = "setting/get_displaymode";
    public static final String GET_EDIE = "setting/get_edie";
    public static final String GET_VIDEO_SIGNAL = "setting/get_video_signal";
    public static final String GET_SOUND_CHANNEL = "setting/get_sound_channel";
    public static final String GET_SOUND_TRACK = "setting/get_sound_track";
    public static final String GET_SUBTITLE_MODE = "setting/get_subtitle_mode";
    public static final String GET_COLOUR_SYSTEM = "setting/get_colour_system";
    public static final String GET_LOGO_SWITCH = "setting/get_logo_switch";

    public static final String GET_DISPLAYMODE_LIST = "setting/get_displaymode_list";
    public static final String GET_EDIE_LIST = "setting/get_edie_list";
    public static final String GET_VIDEO_SIGNAL_LIST = "setting/get_video_signal_list";
    public static final String GET_SOUND_CHANNEL_LIST = "setting/get_sound_channel_list";
    public static final String GET_SOUND_TRACK_LIST = "setting/get_sound_track_list";
    public static final String GET_SUBTITLE_MODE_LIST = "setting/get_subtitle_mode_list";
    public static final String GET_COLOUR_SYSTEM_LIST = "setting/get_colour_system_list";

    public static final String SET_VRR = "setting/set_vrr";
    public static final String SET_XDR = "setting/set_xdr";
    public static final String SET_DISPLAYMODE = "setting/set_displaymode";
    public static final String SET_EDIE = "setting/set_edie";
    public static final String SET_VIDEO_SIGNAL = "setting/set_video_signal";
    public static final String SET_SOUND_CHANNEL = "setting/set_sound_channel";
    public static final String SET_SOUND_TRACK = "setting/set_sound_track";
    public static final String SET_SUBTITLE_MODE = "setting/set_subtitle_mode";
    public static final String SET_COLOUR_SYSTEM = "setting/set_colour_system";
    public static final String SET_LOGO_SWITCH = "setting/set_logo_switch";

    public static final String CHECK_SET_EDID = "setting/check_before_set_edie";
}
